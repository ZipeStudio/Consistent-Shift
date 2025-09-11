package me.zipestudio.consistentshift.mixin;

import me.zipestudio.consistentshift.client.ConsistentShiftClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin {

    @Shadow
    @Final
    public DefaultedList<Slot> slots;

    @Shadow
    @Final
    public int syncId;

    @Inject(method = "internalOnSlotClick", at = @At("HEAD"), cancellable = true)
    private void onCustomQuickMove(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {

        if (actionType != SlotActionType.QUICK_MOVE || slotIndex < 0) return;

        ScreenHandler handler = (ScreenHandler) (Object) this;
        Slot clickedSlot = this.slots.get(slotIndex);

        if (!clickedSlot.hasStack() || clickedSlot.inventory == player.getInventory()) return;

        ItemStack cursorStack = handler.getCursorStack();
        ItemStack clickedStack = clickedSlot.getStack();

        if (actionType == SlotActionType.PICKUP_ALL) return;
        if (!cursorStack.isEmpty() && ItemStack.areItemsEqual(cursorStack, clickedStack)) return;

        ClientPlayerInteractionManager im = MinecraftClient.getInstance().interactionManager;
        if (im == null) return;

        ItemStack originalCursor = cursorStack.copy();
        boolean cursorInitiallyEmpty = originalCursor.isEmpty();
        int hotbarStart = this.slots.size() - 9;

        List<Integer> targets = ConsistentShiftClient.findInsertSlots(handler, clickedStack, hotbarStart, this.slots.size(), false);
        targets.addAll(ConsistentShiftClient.findInsertSlots(handler, clickedStack, clickedSlot.inventory.size(), hotbarStart, false));
        if (targets.isEmpty()) return;

        Integer tempIndex = null;

        if (!cursorInitiallyEmpty) {
            for (int i = clickedSlot.inventory.size(); i < this.slots.size(); i++) {
                if (i == slotIndex || targets.contains(i)) continue;
                Slot s = this.slots.get(i);
                if (s.getStack().isEmpty() && s.canInsert(originalCursor)) {
                    tempIndex = i;
                    break;
                }
            }
            if (tempIndex != null) {
                targets.remove(tempIndex);
                im.clickSlot(this.syncId, tempIndex, 0, SlotActionType.PICKUP, player);
            }
        }

        im.clickSlot(this.syncId, slotIndex, 0, SlotActionType.PICKUP, player);

        for (int targetIndex : targets) {
            if (handler.getCursorStack().isEmpty()) break;
            im.clickSlot(this.syncId, targetIndex, 0, SlotActionType.PICKUP, player);
        }

        if (!handler.getCursorStack().isEmpty()) {
            im.clickSlot(this.syncId, slotIndex, 0, SlotActionType.PICKUP, player);
        }

        if (tempIndex != null) {
            im.clickSlot(this.syncId, tempIndex, 0, SlotActionType.PICKUP, player);
        }

        ci.cancel();
    }
}
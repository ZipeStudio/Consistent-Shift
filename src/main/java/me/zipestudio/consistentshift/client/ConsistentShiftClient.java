package me.zipestudio.consistentshift.client;

import me.zipestudio.consistentshift.ConsistentShift;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ClientModInitializer;

import java.util.ArrayList;
import java.util.List;

public class ConsistentShiftClient implements ClientModInitializer {

	public static Logger LOGGER = LoggerFactory.getLogger(ConsistentShift.MOD_NAME + "/Client");

	@Override
	public void onInitializeClient() {
		LOGGER.info("{} Client Initialized", ConsistentShift.MOD_NAME);
	}

	public static List<Integer> findInsertSlots(ScreenHandler handler, ItemStack stack, int startIndex, int endIndex, boolean fromLast) {
		List<Integer> result = new ArrayList<>();
		if (stack.isEmpty()) return result;

		int step = fromLast ? -1 : 1;
		int i = fromLast ? endIndex - 1 : startIndex;

		while (fromLast ? i >= startIndex : i < endIndex) {
			Slot slot = handler.getSlot(i);
			ItemStack existing = slot.getStack();

			if (!existing.isEmpty() && ItemStack.areItemsEqual(stack, existing)) {
				int maxCount = Math.min(slot.getMaxItemCount(stack), stack.getMaxCount());
				if (existing.getCount() < maxCount && slot.canInsert(stack)) {
					result.add(i);
				}
			}
			i += step;
		}

		i = fromLast ? endIndex - 1 : startIndex;
		while (fromLast ? i >= startIndex : i < endIndex) {
			Slot slot = handler.getSlot(i);
			if (slot.getStack().isEmpty() && slot.canInsert(stack)) {
				result.add(i);
			}
			i += step;
		}

		return result;
	}

}
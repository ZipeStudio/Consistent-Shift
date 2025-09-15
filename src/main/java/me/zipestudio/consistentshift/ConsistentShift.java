package me.zipestudio.consistentshift;

import net.minecraft.text.*;
import net.minecraft.util.Identifier;
import org.slf4j.*;

import net.fabricmc.api.ModInitializer;

public class ConsistentShift implements ModInitializer {

	public static final String MOD_NAME = /*$ mod_name*/ "Consistent Shift";
	public static final String MOD_ID = /*$ mod_id*/ "consistent-shift";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("{} Initialized", MOD_NAME);
	}
}
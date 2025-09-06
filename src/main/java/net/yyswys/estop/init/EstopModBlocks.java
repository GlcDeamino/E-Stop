/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.yyswys.estop.init;

import net.yyswys.estop.block.EStopReversedBlock;
import net.yyswys.estop.block.EStopBlock;
import net.yyswys.estop.EstopMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.Block;

public class EstopModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(EstopMod.MODID);
	public static final DeferredBlock<Block> E_STOP = REGISTRY.register("e_stop", EStopBlock::new);
	public static final DeferredBlock<Block> E_STOP_REVERSED = REGISTRY.register("e_stop_reversed", EStopReversedBlock::new);
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
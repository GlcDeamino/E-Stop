/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.yyswys.estop.init;

import net.yyswys.estop.EstopMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

public class EstopModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(EstopMod.MODID);
	public static final DeferredItem<Item> E_STOP = block(EstopModBlocks.E_STOP);
	public static final DeferredItem<Item> E_STOP_REVERSED = block(EstopModBlocks.E_STOP_REVERSED);

	// Start of user code block custom items
	// End of user code block custom items
	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
}
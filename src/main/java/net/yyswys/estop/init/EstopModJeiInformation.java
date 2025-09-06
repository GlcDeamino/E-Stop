package net.yyswys.estop.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.List;

@JeiPlugin
public class EstopModJeiInformation implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return ResourceLocation.parse("estop:information");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addIngredientInfo(List.of(new ItemStack(EstopModBlocks.E_STOP.get()), new ItemStack(EstopModBlocks.E_STOP_REVERSED.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.estop.e_stop_inf"));
	}
}
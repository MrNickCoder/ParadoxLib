package com.ncoder.paradoxlib.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public abstract class ParadoxGeneratorBlock extends ParadoxTickingBlock implements EnergyNetProvider {

    protected int energyPerTick = -1;
    protected int energyCapacity = -1;

    public ParadoxGeneratorBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void tick(Block b, BlockMenu menu) {

    }

    @Nonnull
    @Override
    public final EnergyNetComponentType getEnergyComponentType() { return EnergyNetComponentType.GENERATOR; }

}

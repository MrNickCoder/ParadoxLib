package com.ncoder.paradoxlib.blocks;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;

import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;

import lombok.Setter;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@Setter
@ParametersAreNonnullByDefault
public abstract class ParadoxGeneratorBlock extends ParadoxTickingBlock implements EnergyNetProvider {

    protected int energyCapacity = -1;

    public ParadoxGeneratorBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        this(itemGroup, item, recipeType, recipe, null);
    }

    public ParadoxGeneratorBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    @Override
    public int getGeneratedOutput(Location l, Config config) {
        BlockMenu menu = BlockStorage.getInventory(l);

        int gen = onGenerate(l.getWorld(), l.getBlock(), menu);

        if (menu.hasViewer() && getStatusSlot() != -1) {
            if (gen == 0) {
                menu.replaceExistingItem(getStatusSlot(), NOT_GENERATING);
            } else {
                menu.replaceExistingItem(getStatusSlot(), GENERATING);
            }
        }

        return gen;
    }

    public abstract int onGenerate(World world, Block block, BlockMenu menu);

    protected abstract int getStatusSlot();

    @Nonnull
    @Override
    public final EnergyNetComponentType getEnergyComponentType() { return EnergyNetComponentType.GENERATOR; }

    @Override
    public int getCapacity() { return energyCapacity; }

    @Override
    public final void register(@Nonnull SlimefunAddon addon) {
        if (energyCapacity == -1) throw new IllegalStateException("You must call .energyCapacity() before registering!");
        super.register(addon);
    }

}

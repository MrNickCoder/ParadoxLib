package com.ncoder.paradoxlib.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

import java.util.Objects;

public abstract class ParadoxGeneratorBlock extends ParadoxInventoryBlock implements EnergyNetProvider {

    public ParadoxGeneratorBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public int getGeneratedOutput(Location l, Config config) {
        BlockMenu menu = BlockStorage.getInventory(l);

        int gen = generate(l.getWorld(), l.getBlock(), menu);

        if (menu.hasViewer()) {
            if (gen == 0) {
                menu.replaceExistingItem(getStatusSlot(), NOT_GENERATING);
            } else {
                menu.replaceExistingItem(getStatusSlot(), GENERATING);
            }
        }

        return gen;
    }

    public abstract int generate(World world, Block block, BlockMenu menu);

    protected abstract int getStatusSlot();

    @Nonnull
    @Override
    public final EnergyNetComponentType getEnergyComponentType() { return EnergyNetComponentType.GENERATOR; }

}

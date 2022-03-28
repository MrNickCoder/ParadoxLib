package com.ncoder.paradoxlib.blocks;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public abstract class ParadoxGeneratorBlock extends ParadoxTickingBlock implements EnergyNetProvider {

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

}

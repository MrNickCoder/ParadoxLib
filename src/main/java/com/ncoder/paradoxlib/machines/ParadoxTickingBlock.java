package com.ncoder.paradoxlib.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class ParadoxTickingBlock extends ParadoxInventoryBlock {

    public ParadoxTickingBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(
                new BlockTicker() {
                    @Override
                    public boolean isSynchronized() {
                        return synchronous();
                    }

                    @Override
                    public void tick(Block b, SlimefunItem item, Config data) {
                        BlockMenu menu = BlockStorage.getInventory(b);
                        if (menu != null) {
                            ParadoxTickingBlock.this.tick(b, menu);
                        }
                    }
                }
        );
    }

    protected abstract void tick(Block b, BlockMenu menu);

    protected boolean synchronous() { return false; }
}

package com.ncoder.paradoxlib.blocks;

import com.ncoder.paradoxlib.common.StackUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemStackSnapshot;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;

import lombok.Getter;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class ParadoxCraftingBlockRecipe {

    private final ItemStack[] recipe;
    final ItemStack output;
    final SlimefunItem item;

    ParadoxCraftingBlockRecipe(ItemStack output, ItemStack[] recipe) {
        this.output = output;
        this.recipe = ItemStackSnapshot.wrapArray(recipe);
        this.item = SlimefunItem.getByItem(output);
    }

    boolean check(ItemStackSnapshot[] input) {
        for (int i = 0; i < recipe.length; i++) {
            boolean similar = StackUtils.isSimilar(input[i], recipe[i]);
            if (!similar || (recipe[i] != null && recipe[i].getAmount() > input[i].getAmount())) {
                return false;
            }
        }
        return true;
    }

    boolean check(Player p) {
        return item == null || item.canUse(p, true);
    }

    void consume(BlockMenu menu, int[] slots) {
        ItemStack[] input = new ItemStack[slots.length];
        for (int i = 0; i < slots.length; i++) {
            input[i] = menu.getItemInSlot(slots[i]);
        }
        consume(input);
    }

    void consume(ItemStack[] input) {
        for (int i = 0; i < recipe.length; i++) {
            if (recipe[i] != null) {
                ItemUtils.consumeItem(input[i], recipe[i].getAmount(), true);
            }
        }
    }

}

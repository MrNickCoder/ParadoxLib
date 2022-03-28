package com.ncoder.paradoxlib.blocks;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemStackSnapshot;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class ParadoxCraftingBlock extends ParadoxInventoryBlock {

    private final List<ParadoxCraftingBlockRecipe> recipes = new ArrayList<>();

    public ParadoxCraftingBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        this(itemGroup, item, recipeType, recipe, null);
    }

    public ParadoxCraftingBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    protected void onCraft(Block b, BlockMenu menu, Player p) {
        int[] slots = getRecipeSlots();
        ItemStack[] input = new ItemStack[slots.length];
        for (int i = 0; i < slots.length; i++) {
            input[i] = menu.getItemInSlot(slots[i]);
        }

        ParadoxCraftingBlockRecipe recipe = getOutput(input);

        if (recipe != null) {
            if (recipe.check(p)) {
                if (menu.fits(recipe.output, getOutputSlots())) {
                    ItemStack output = recipe.output.clone();
                    onSuccessfulCraft(menu, output);
                    menu.pushItem(output, getOutputSlots());
                    recipe.consume(input);
                    p.sendMessage(ChatColor.GREEN + "Successfully Crafted: " + ItemUtils.getItemName(output));
                } else {
                    p.sendMessage(ChatColor.GOLD + "Not Enough Room!");
                }
            }
        } else {
            p.sendMessage(ChatColor.RED + "Invalid Recipe!");
        }
    }

    protected void onSuccessfulCraft(BlockMenu menu, ItemStack output) {  }

    @Override
    protected void onNewInstance(BlockMenu menu, Block b) {
        menu.addMenuClickHandler(getCraftSlot(), (p, slot, item, action) -> {
            onCraft(b, menu, p);
            return false;
        });
    }

    @Nonnull
    public final ParadoxCraftingBlock addRecipe(ItemStack output, ItemStack[] input) {
        if (input.length == 0) throw new IllegalArgumentException("Cannot add recipe with no input!");

        ParadoxCraftingBlockRecipe recipe = new ParadoxCraftingBlockRecipe(output, input);
        recipes.add(recipe);
        return this;
    }

    @Nonnull
    public final ParadoxCraftingBlock addRecipe(ParadoxRecipeType recipeType) {
        recipeType.sendRecipesTo((in, out) -> addRecipe(out, in));
        return this;
    }

    @Nullable
    public final ParadoxCraftingBlockRecipe getOutput(ItemStack[] input) {
        ItemStackSnapshot[] snapshots = ItemStackSnapshot.wrapArray(input);
        for (ParadoxCraftingBlockRecipe recipe : recipes) {
            if (recipe.check(snapshots)) return recipe;
        }
        return null;
    }

    protected abstract int[] getRecipeSlots();

    protected abstract int getCraftSlot();

}

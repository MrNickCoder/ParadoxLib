package com.ncoder.paradoxlib.blocks;

import com.ncoder.paradoxlib.common.Scheduler;
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

    protected Result onCheck(BlockMenu menu, Player p) { return onCheck(menu, p, null); }

    protected Result onCheck(BlockMenu menu, Player p, @Nullable ParadoxCraftingBlockRecipe output) {
        ParadoxCraftingBlockRecipe recipe = output != null ? output : getOutput(menu, getRecipeSlots());

        if (recipe != null) {
            if (recipe.check(p)) {
                if (menu.fits(recipe.output, getOutputSlots())) return Result.AVAILABLE;
                return Result.NO_ROOM;
            }
        }
        return Result.INVALID;
    }

    protected ItemStack onCraft(Block b, BlockMenu menu, Player p) {
        ParadoxCraftingBlockRecipe recipe = getOutput(menu, getRecipeSlots());

        ItemStack output = recipe.output.clone();
        onSuccessfulCraft(menu, output);
        menu.pushItem(output, getOutputSlots());
        recipe.consume(menu, getRecipeSlots());
        return output;
    }

    protected void onSuccessfulCraft(BlockMenu menu, ItemStack output) {  }

    protected void onUpdate(Block b, BlockMenu menu, Result result) {  }

    @Override
    protected void onNewInstance(BlockMenu menu, Block b) {
        for (int slots : getRecipeSlots()) {
            menu.addMenuClickHandler(slots, (p, slot, item, action) -> {
                Scheduler.run(() -> {
                    onUpdate(b, menu, onCheck(menu, p));
                });
                return true;
            });
        }

        menu.addMenuClickHandler(getCraftSlot(), (p, slot, item, action) -> {
            Result result = onCheck(menu, p);
            if (result == Result.AVAILABLE) {
                ItemStack output = onCraft(b, menu, p);
                p.sendMessage(ChatColor.GREEN + "Successfully Crafted: " + ItemUtils.getItemName(output));
            } else if (result == Result.NO_ROOM) {
                p.sendMessage(ChatColor.GOLD + "Not Enough Room!");
            } else if (result == Result.INVALID) {
                p.sendMessage(ChatColor.RED + "Invalid Recipe!");
            }
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
    public final ParadoxCraftingBlockRecipe getOutput(BlockMenu menu, int[] slots) {
        ItemStack[] input = new ItemStack[slots.length];
        for (int i = 0; i < slots.length; i++) {
            input[i] = menu.getItemInSlot(slots[i]);
        }
        return getOutput(input);
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

    public enum Result {
        AVAILABLE, NO_ROOM, INVALID
    }

}

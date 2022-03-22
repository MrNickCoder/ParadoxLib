package com.ncoder.paradoxlib.machines;

import com.ncoder.paradoxlib.core.ParadoxAddon;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public final class ParadoxRecipeType extends RecipeType {

    @Getter
    private final Map<ItemStack[], ItemStack> recipes = new LinkedHashMap<>();
    private final List<BiConsumer<ItemStack[], ItemStack>> callbacks = new ArrayList<>();

    public ParadoxRecipeType(String key, ItemStack item) {
        super(ParadoxAddon.createKey(key), item);
    }

    @Override
    public void register(ItemStack[] recipe, ItemStack result) {
        callbacks.forEach(c -> c.accept(recipe, result));
        recipes.put(recipe, result);
    }

    public void RecipesTo(BiConsumer<ItemStack[], ItemStack> callback) {
        recipes.forEach(callback);
        callbacks.add(callback);
    }

}

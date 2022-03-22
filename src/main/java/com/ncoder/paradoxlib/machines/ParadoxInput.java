package com.ncoder.paradoxlib.machines;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

final class ParadoxInput {

    final List<ItemStack> items = new ArrayList<>();
    int amount;

    ParadoxInput(ItemStack item) {
        add(item);
    }

    ParadoxInput add(ItemStack item) {
        items.add(item);
        amount += item.getAmount();
        return this;
    }

}

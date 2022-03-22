package com.ncoder.paradoxlib.enums;

import org.bukkit.inventory.ItemStack;

public enum ToolType {
    PICKAXE, AXE, SHOVEL, HOE;

    public static ToolType matchType(final ItemStack itemStack) {
        if (itemStack.getType().name().endsWith("_PICKAXE")) return PICKAXE;
        if (itemStack.getType().name().endsWith("_AXE")) return AXE;
        if (itemStack.getType().name().endsWith("_SHOVEL")) return SHOVEL;
        if (itemStack.getType().name().endsWith("_HOE")) return HOE;
        return null;
    }
}

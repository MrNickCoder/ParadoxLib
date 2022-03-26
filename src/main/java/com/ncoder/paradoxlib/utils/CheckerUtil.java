package com.ncoder.paradoxlib.utils;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class CheckerUtil {

    public static boolean isAirOrNull(ItemStack itemStack) { return itemStack == null || itemStack.getType().equals(Material.AIR); }

    public static boolean isCreative(Player player) { return player.getGameMode() == GameMode.CREATIVE; }
    public static boolean isSurvival(Player player) { return player.getGameMode() == GameMode.SURVIVAL; }
    public static boolean isAdventure(Player player) { return player.getGameMode() == GameMode.ADVENTURE; }
    public static boolean isSpectator(Player player) { return player.getGameMode() == GameMode.SPECTATOR; }

    public static boolean isGear(ItemStack itemStack) { return isGear(itemStack.getType()); }
    public static boolean isGear(Material material) {
        if (isArmor(material) || isWeapon(material) || isTool(material)) return true;
        return false;
    }

    public static boolean isArmor(ItemStack itemStack) { return isArmor(itemStack.getType()); }
    public static boolean isArmor(Material material) { return material.toString().endsWith("_HELMET") || material.toString().endsWith("_SKULL") || material.toString().endsWith("_HEAD") || material.toString().equals("ELYTRA") || material.toString().endsWith("_CHESTPLATE") || material.toString().endsWith("_LEGGINGS") || material.toString().endsWith("_BOOTS"); }

    public static boolean isWeapon(ItemStack itemStack) { return isWeapon(itemStack.getType()); }
    public static boolean isWeapon(Material material) { return material.toString().endsWith("_SWORD") || material.toString().endsWith("_AXE") || material.toString().endsWith("BOW"); }

    public static boolean isTool(ItemStack itemStack) { return isTool(itemStack.getType()); }
    public static boolean isTool(Material material) { return material.toString().endsWith("_AXE") || material.toString().endsWith("_PICKAXE") || material.toString().endsWith("_SHOVEL") || material.toString().endsWith("_HOE") || material.toString().equals("FISHING_ROD"); }

}

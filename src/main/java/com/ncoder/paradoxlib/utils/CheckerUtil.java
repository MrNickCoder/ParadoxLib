package com.ncoder.paradoxlib.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class CheckerUtil {

    /**
     * Server Version
     */
    public static boolean isVersion18() { return Bukkit.getVersion().contains("1.18"); }
    public static boolean isVersion17() { return Bukkit.getVersion().contains("1.17"); }
    public static boolean isVersion16() { return Bukkit.getVersion().contains("1.16"); }
    public static boolean isVersion15() { return Bukkit.getVersion().contains("1.15"); }
    public static boolean isVersion14() { return Bukkit.getVersion().contains("1.14"); }
    public static boolean isVersion13() { return Bukkit.getVersion().contains("1.13"); }

    /**
     * Items
     */
    public static boolean isAirOrNull(ItemStack itemStack) { return itemStack == null || itemStack.getType().equals(Material.AIR); }

    public static boolean isGear(ItemStack itemStack) { return isGear(itemStack.getType()); }
    public static boolean isGear(Material material) {
        return isArmor(material) || isWeapon(material) || isTool(material);
    }

    public static boolean isArmor(ItemStack itemStack) { return isArmor(itemStack.getType()); }
    public static boolean isArmor(Material material) { return material.name().endsWith("_HELMET") || material.name().endsWith("_SKULL") || material.name().endsWith("_HEAD") || material.name().equals("ELYTRA") || material.name().endsWith("_CHESTPLATE") || material.name().endsWith("_LEGGINGS") || material.name().endsWith("_BOOTS"); }

    public static boolean isWeapon(ItemStack itemStack) { return isWeapon(itemStack.getType()); }
    public static boolean isWeapon(Material material) { return material.name().endsWith("_SWORD") || material.name().endsWith("_AXE") || material.name().endsWith("BOW"); }

    public static boolean isTool(ItemStack itemStack) { return isTool(itemStack.getType()); }
    public static boolean isTool(Material material) { return material.name().endsWith("_AXE") || material.name().endsWith("_PICKAXE") || material.name().endsWith("_SHOVEL") || material.name().endsWith("_HOE") || material.name().equals("FISHING_ROD"); }

    public static boolean isWood(ItemStack itemStack) { return isWood(itemStack.getType()); }
    public static boolean isWood(Block block) { return isWood(block.getType()); }
    public static boolean isWood(Material material) { return material.name().endsWith("_WOOD") || material.name().endsWith("_LOG") || material.name().endsWith("_HYPHAE") || material.name().endsWith("_STEM"); }

    /**
     * Player
     */
    public static boolean isCreative(Player player) { return player.getGameMode() == GameMode.CREATIVE; }
    public static boolean isSurvival(Player player) { return player.getGameMode() == GameMode.SURVIVAL; }
    public static boolean isAdventure(Player player) { return player.getGameMode() == GameMode.ADVENTURE; }
    public static boolean isSpectator(Player player) { return player.getGameMode() == GameMode.SPECTATOR; }

    /**
     * Blocks
     */
    public static boolean isOre(ItemStack itemStack) { return isOre(itemStack.getType()); }
    public static boolean isOre(Block block) { return isOre(block.getType()); }
    public static boolean isOre(Material material) { return material.name().endsWith("_ORE") || material.name().equals("ANCIENT_DEBRIS"); }

    public static boolean isPlant(ItemStack itemStack) { return isPlant(itemStack); }
    public static boolean isPlant(Block block) { return isPlant(block.getType()); }
    public static boolean isPlant(Material material) {
        return material.name().equals("GRASS") || material.name().equals("TALL_GRASS") || material.name().equals("FERN") || material.name().equals("LARGE_FERN") || material.name().equals("SEAGRASS") || material.name().equals("TALL_SEAGRASS") ||
                material.name().equals("CARROTS") || material.name().equals("POTATOES") || material.name().equals("WHEAT") || material.name().equals("BEETROOTS") ||
                material.name().equals("DEAD_BUSH") || material.name().equals("SWEET_BERRY_BUSH") ||
                material.name().equals("PUMPKIN") || material.name().equals("MELON") ||
                material.name().equals("BAMBOO") || material.name().equals("SUGAR_CANE") ||
                material.name().equals("CACTUS") ||
                material.name().equals("COCOA") ||
                material.name().equals("DANDELION") || material.name().equals("POPPY") || material.name().equals("BLUE_ORCHID") || material.name().equals("ALLIUM") ||
                material.name().equals("AZURE_BLUET") || material.name().equals("OXEYE_DAISY") || material.name().equals("CORNFLOWER") || material.name().equals("LILY_OF_THE_VALLEY") ||
                material.name().equals("WITHER_ROSE") || material.name().equals("SUNFLOWER") || material.name().equals("LILAC") || material.name().equals("ROSE_BUSH") ||
                material.name().equals("PEONY") || (!material.name().startsWith("POTTED_") && material.name().endsWith("_TULIP"));

    }

}

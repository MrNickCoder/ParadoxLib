package com.ncoder.paradoxlib.core.listeners;

import com.ncoder.paradoxlib.core.ParadoxAPIs;
import com.ncoder.paradoxlib.events.PlayerBreakOreEvent;
import com.ncoder.paradoxlib.events.PlayerBreakWoodEvent;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

import net.coreprotect.CoreProtectAPI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(final BlockBreakEvent e) {
        if (e.getPlayer() == null) return;
        if (BlockStorage.check(e.getBlock()) != null) return;

        if (validateOre(e.getBlock().getType())) {
            if (ParadoxAPIs.getCoreProtectAPI() != null) {
                CoreProtectAPI api = ParadoxAPIs.getCoreProtectAPI();
                if (api.blockLookup(e.getBlock(), Integer.MAX_VALUE) == null) {
                    PlayerBreakOreEvent playerBreakOreEvent = new PlayerBreakOreEvent(e.getPlayer(), e.getPlayer().getItemInUse(), e.getBlock(), e.getExpToDrop(), false);
                    Bukkit.getServer().getPluginManager().callEvent(playerBreakOreEvent);
                    if (playerBreakOreEvent.isCancelled()) e.setCancelled(true);
                    e.setExpToDrop(playerBreakOreEvent.getExpToDrop());
                } else {
                    PlayerBreakOreEvent playerBreakOreEvent = new PlayerBreakOreEvent(e.getPlayer(), e.getPlayer().getItemInUse(), e.getBlock(), e.getExpToDrop(), false);
                    Bukkit.getServer().getPluginManager().callEvent(playerBreakOreEvent);
                    if (playerBreakOreEvent.isCancelled()) e.setCancelled(true);
                    e.setExpToDrop(playerBreakOreEvent.getExpToDrop());
                }
            } else {
                PlayerBreakOreEvent playerBreakOreEvent = new PlayerBreakOreEvent(e.getPlayer(), e.getPlayer().getItemInUse(), e.getBlock(), e.getExpToDrop(), true);
                Bukkit.getServer().getPluginManager().callEvent(playerBreakOreEvent);
                if (playerBreakOreEvent.isCancelled()) e.setCancelled(true);
                e.setExpToDrop(playerBreakOreEvent.getExpToDrop());
            }
        }
        if (validateWood(e.getBlock().getType())) {
            if (ParadoxAPIs.getCoreProtectAPI() != null) {
                CoreProtectAPI api = ParadoxAPIs.getCoreProtectAPI();
                if (api.blockLookup(e.getBlock(), Integer.MAX_VALUE) == null) {
                    PlayerBreakWoodEvent playerBreakWoodEvent = new PlayerBreakWoodEvent(e.getPlayer(), e.getPlayer().getItemInUse(), e.getBlock(), e.getExpToDrop(), true);
                    Bukkit.getServer().getPluginManager().callEvent(playerBreakWoodEvent);
                    if (playerBreakWoodEvent.isCancelled()) e.setCancelled(true);
                    e.setExpToDrop(playerBreakWoodEvent.getExpToDrop());
                } else {
                    PlayerBreakWoodEvent playerBreakWoodEvent = new PlayerBreakWoodEvent(e.getPlayer(), e.getPlayer().getItemInUse(), e.getBlock(), e.getExpToDrop(), false);
                    Bukkit.getServer().getPluginManager().callEvent(playerBreakWoodEvent);
                    if (playerBreakWoodEvent.isCancelled()) e.setCancelled(true);
                    e.setExpToDrop(playerBreakWoodEvent.getExpToDrop());
                }
            } else {
                PlayerBreakWoodEvent playerBreakWoodEvent = new PlayerBreakWoodEvent(e.getPlayer(), e.getPlayer().getItemInUse(), e.getBlock(), e.getExpToDrop(), true);
                Bukkit.getServer().getPluginManager().callEvent(playerBreakWoodEvent);
                if (playerBreakWoodEvent.isCancelled()) e.setCancelled(true);
                e.setExpToDrop(playerBreakWoodEvent.getExpToDrop());
            }
        }
    }

    public boolean validateOre(final Material material) {
        String name = material.name();
        if (name.endsWith("_ORE")) return true;
        if (name.equals("ANCIENT_DEBRIS")) return true;
        return false;
    }

    public boolean validateWood(final Material material) {
        String name = material.name();
        if (name.endsWith("_LOG")) return true;
        if (name.endsWith("_WOOD")) return true;
        if (name.endsWith("_STEM")) return true;
        if (name.endsWith("_HYPHAE")) return true;
        return false;
    }

    public boolean validatePlant(final Material material) {
        String name = material.name();
        if (name.equals("GRASS") || name.equals("TALL_GRASS") || name.equals("FERN") || name.equals("LARGE_FERN") || name.equals("SEAGRASS") || name.equals("TALL_SEAGRASS")) return true;
        if (name.equals("CARROTS") || name.equals("POTATOES") || name.equals("WHEAT") || name.equals("BEETROOTS")) return true;
        if (name.equals("DEAD_BUSH") || name.equals("SWEET_BERRY_BUSH")) return true;
        if (name.equals("PUMPKIN") || name.equals("MELON")) return true;
        if (name.equals("BAMBOO") || name.equals("SUGAR_CANE")) return true;
        if (name.equals("CACTUS")) return true;
        if (name.equals("COCOA")) return true;
        if (name.equals("DANDELION") || name.equals("POPPY") || name.equals("BLUE_ORCHID") || name.equals("ALLIUM") ||
                name.equals("AZURE_BLUET") || name.equals("OXEYE_DAISY") || name.equals("CORNFLOWER") || name.equals("LILY_OF_THE_VALLEY") ||
                name.equals("WITHER_ROSE") || name.equals("SUNFLOWER") || name.equals("LILAC") || name.equals("ROSE_BUSH") ||
                name.equals("PEONY") || (!name.startsWith("POTTED_") && name.endsWith("_TULIP"))) return true;
        return false;
    }

}

package com.ncoder.paradoxlib.core.listeners;

import com.ncoder.paradoxlib.events.PlayerBreakOreEvent;
import com.ncoder.paradoxlib.events.PlayerBreakWoodEvent;
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
        if (validateOre(e.getBlock().getType())) {
            PlayerBreakOreEvent playerBreakOreEvent = new PlayerBreakOreEvent(e.getPlayer(), e.getPlayer().getItemInUse(), e.getBlock(), e.getExpToDrop());
            Bukkit.getServer().getPluginManager().callEvent(playerBreakOreEvent);
            if (playerBreakOreEvent.isCancelled()) e.setCancelled(true);
            e.setExpToDrop(playerBreakOreEvent.getExpToDrop());
        }
        if (validateWood(e.getBlock().getType())) {
            PlayerBreakWoodEvent playerBreakWoodEvent = new PlayerBreakWoodEvent(e.getPlayer(), e.getPlayer().getItemInUse(), e.getBlock(), e.getExpToDrop());
            Bukkit.getServer().getPluginManager().callEvent(playerBreakWoodEvent);
            if (playerBreakWoodEvent.isCancelled()) e.setCancelled(true);
            e.setExpToDrop(playerBreakWoodEvent.getExpToDrop());
        }
    }

    public boolean validateOre(final Material material) {
        if (material.name().endsWith("_ORE") || material.name().equals("ANCIENT_DEBRIS")) return true;
        return false;
    }

    public boolean validateWood(final Material material) {
        if (material.name().endsWith("_LOG") || material.name().endsWith("_WOOD") || material.name().endsWith("_STEM") || material.name().endsWith("_YPHAE")) return true;
        return false;
    }

}

package com.ncoder.paradoxlib.core.listeners;

import com.ncoder.paradoxlib.core.ParadoxAPIs;
import com.ncoder.paradoxlib.events.PlayerBreakOreEvent;
import com.ncoder.paradoxlib.events.PlayerBreakWoodEvent;
import com.ncoder.paradoxlib.utils.CheckerUtil;
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

        if (CheckerUtil.isOre(e.getBlock())) {
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

        if (CheckerUtil.isWood(e.getBlock())) {
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

        if (CheckerUtil.isPlant(e.getBlock())) {

        }
    }

}

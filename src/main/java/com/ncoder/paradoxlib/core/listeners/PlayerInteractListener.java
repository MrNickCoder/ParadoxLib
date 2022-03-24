package com.ncoder.paradoxlib.core.listeners;

import com.ncoder.paradoxlib.core.ParadoxAPIs;
import com.ncoder.paradoxlib.enums.ToolType;
import com.ncoder.paradoxlib.enums.WoodType;
import com.ncoder.paradoxlib.events.PlayerStrippedLogEvent;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

import net.coreprotect.CoreProtectAPI;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteract(final PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (ToolType.matchType(e.getItem()) != ToolType.AXE) return;
        if (WoodType.matchType(e.getClickedBlock().getType()) == null) return;
        if (BlockStorage.check(e.getClickedBlock()) != null) return;

        if (ParadoxAPIs.getCoreProtectAPI() != null) {
            CoreProtectAPI api = ParadoxAPIs.getCoreProtectAPI();
            if (api.blockLookup(e.getClickedBlock(), Integer.MAX_VALUE) == null) {
                PlayerStrippedLogEvent playerStrippedLogEvent = new PlayerStrippedLogEvent(e.getPlayer(), e.getClickedBlock(), WoodType.matchType(e.getClickedBlock().getType()), true);
                Bukkit.getServer().getPluginManager().callEvent(playerStrippedLogEvent);
                if (playerStrippedLogEvent.isCancelled()) e.setCancelled(true);
            } else {
                PlayerStrippedLogEvent playerStrippedLogEvent = new PlayerStrippedLogEvent(e.getPlayer(), e.getClickedBlock(), WoodType.matchType(e.getClickedBlock().getType()), false);
                Bukkit.getServer().getPluginManager().callEvent(playerStrippedLogEvent);
                if (playerStrippedLogEvent.isCancelled()) e.setCancelled(true);
            }
        } else {
            PlayerStrippedLogEvent playerStrippedLogEvent = new PlayerStrippedLogEvent(e.getPlayer(), e.getClickedBlock(), WoodType.matchType(e.getClickedBlock().getType()), true);
            Bukkit.getServer().getPluginManager().callEvent(playerStrippedLogEvent);
            if (playerStrippedLogEvent.isCancelled()) e.setCancelled(true);
        }
    }

}

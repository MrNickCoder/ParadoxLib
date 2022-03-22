package com.ncoder.paradoxlib.core.listeners;

import com.ncoder.paradoxlib.enums.ToolType;
import com.ncoder.paradoxlib.enums.WoodType;
import com.ncoder.paradoxlib.events.PlayerStrippedLogEvent;
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
        PlayerStrippedLogEvent playerStrippedLogEvent = new PlayerStrippedLogEvent(e.getPlayer(), e.getClickedBlock(), WoodType.matchType(e.getClickedBlock().getType()));
        Bukkit.getServer().getPluginManager().callEvent(playerStrippedLogEvent);
        if (playerStrippedLogEvent.isCancelled()) e.setCancelled(true);
    }

}

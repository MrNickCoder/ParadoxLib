package com.ncoder.paradoxlib.core.listeners;

import com.ncoder.paradoxlib.events.PlayerFishItemEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class PlayerFishListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerFish(final PlayerFishEvent e) {
        if (e.isCancelled()) return;
        if (!(e.getCaught() instanceof Item)) return;
        Item item = (Item) e.getCaught();
        PlayerFishItemEvent playerFishItemEvent = new PlayerFishItemEvent(e.getPlayer(), item.getItemStack());
        Bukkit.getServer().getPluginManager().callEvent(playerFishItemEvent);
        if (playerFishItemEvent.isCancelled()) e.setCancelled(true);
        item.setItemStack(playerFishItemEvent.getItem());
    }

}

package com.ncoder.paradoxlib.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public final class PlayerFishItemEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;

    private ItemStack item;

    public PlayerFishItemEvent(final Player player, final ItemStack item) {
        super(player);
        this.item = item;
    }

    public static HandlerList getHandlerList() { return handlers; }

    @Override
    public final HandlerList getHandlers() { return handlers; }

    public final void setCancelled(final boolean cancel) { this.cancel = cancel; }

    public final boolean isCancelled() { return cancel; }

    public final void setItem(final ItemStack item) { this.item = item; }

    public final ItemStack getItem() { return item; }
}

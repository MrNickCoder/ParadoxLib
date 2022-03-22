package com.ncoder.paradoxlib.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerBreakWoodEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;

    private final Block block;
    private int expToDrop;

    public PlayerBreakWoodEvent(final Player player, final Block block, final int expToDrop) {
        super(player);
        this.block = block;
        this.expToDrop = expToDrop;
    }

    public static HandlerList getHandlerList() { return handlers; }

    @Override
    public final HandlerList getHandlers() { return handlers; }

    public final void setCancelled(final boolean cancel) { this.cancel = cancel; }

    public final boolean isCancelled() { return cancel; }

    public final void setExpToDrop(final int expToDrop) { this.expToDrop = expToDrop; }

    public final int getExpToDrop() { return expToDrop; }

}

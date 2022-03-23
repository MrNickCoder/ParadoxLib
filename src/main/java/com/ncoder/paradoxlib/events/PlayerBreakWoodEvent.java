package com.ncoder.paradoxlib.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerBreakWoodEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;

    private final ItemStack tool;
    private final Block block;
    private int expToDrop;
    private final boolean natural;

    public PlayerBreakWoodEvent(final Player player, final ItemStack tool, final Block block, final int expToDrop, final boolean natural) {
        super(player);
        this.tool = tool;
        this.block = block;
        this.expToDrop = expToDrop;
        this.natural = natural;
    }

    public static HandlerList getHandlerList() { return handlers; }

    @Override
    public final HandlerList getHandlers() { return handlers; }

    public final void setCancelled(final boolean cancel) { this.cancel = cancel; }

    public final boolean isCancelled() { return cancel; }

    public final ItemStack getTool() { return tool; }

    public final Block getBlock() { return block; }

    public final void setExpToDrop(final int expToDrop) { this.expToDrop = expToDrop; }

    public final int getExpToDrop() { return expToDrop; }

    public boolean isNatural() { return natural; }

}

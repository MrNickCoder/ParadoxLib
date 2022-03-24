package com.ncoder.paradoxlib.events;

import com.ncoder.paradoxlib.enums.WoodType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import java.util.Arrays;

public class PlayerStrippedLogEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;

    private final Block block;
    private final WoodType type;
    private final boolean natural;

    public PlayerStrippedLogEvent(final Player player, final Block block, final WoodType type, final boolean natural) {
        super(player);
        this.block = block;
        this.type = type;
        this.natural = natural;
    }

    public static HandlerList getHandlerList() { return handlers; }

    @Override
    public final HandlerList getHandlers() { return handlers; }

    public final void setCancelled(final boolean cancel) { this.cancel = cancel; }

    public final boolean isCancelled() { return cancel; }

    public final Block getBlock() { return block; }

    public final WoodType getType() { return type; }

    public boolean isNatural() { return natural; }

}

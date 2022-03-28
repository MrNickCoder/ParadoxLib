package com.ncoder.paradoxlib.blocks;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ParadoxBlockPreset extends BlockMenuPreset {

    private final ParadoxInventoryBlock paradoxInventoryBlock;

    ParadoxBlockPreset(ParadoxInventoryBlock paradoxInventoryBlock) {
        super(paradoxInventoryBlock.getId(), paradoxInventoryBlock.getItemName());
        this.paradoxInventoryBlock = paradoxInventoryBlock;
        paradoxInventoryBlock.onSetup(this);
    }

    @Override
    public void newInstance(BlockMenu menu, Block b) {
        paradoxInventoryBlock.onNewInstance(menu, b);
    }

    @Override
    public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) { return paradoxInventoryBlock.getTransportSlots(menu, flow, item); }

    @Override
    public void init() { paradoxInventoryBlock.onInit(); }

    @Override
    public boolean canOpen(Block b, Player p) { return Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.INTERACT_BLOCK) && paradoxInventoryBlock.canUse(p, false); }

    @Override
    public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) { return new int[0]; }

}

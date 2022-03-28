package com.ncoder.paradoxlib.blocks;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import lombok.Setter;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@Setter
@ParametersAreNonnullByDefault
public abstract class ParadoxMachineBlock extends ParadoxTickingBlock implements EnergyNetComponent {

    protected int energyPerTick = -1;
    protected int energyCapacity = -1;

    public ParadoxMachineBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        this(itemGroup, item, recipeType, recipe, null);
    }

    public ParadoxMachineBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    @Override
    protected void onTick(Block b, BlockMenu menu) {
        if (getCharge(menu.getLocation()) < energyPerTick) {
            if (menu.hasViewer() && getStatusSlot() != -1) {
                menu.replaceExistingItem(getStatusSlot(), NO_ENERGY_ITEM);
            }
        } else if (onProcess(b, menu)) {
            removeCharge(menu.getLocation(), energyPerTick);
        }
    }

    protected abstract boolean onProcess(Block b, BlockMenu menu);

    protected abstract int getStatusSlot();

    @Override
    public final int getCapacity() { return energyCapacity; }

    @Nonnull
    @Override
    public final EnergyNetComponentType getEnergyComponentType() { return EnergyNetComponentType.CONSUMER; }

    @Override
    public final void register(@Nonnull SlimefunAddon addon) {
        if (energyPerTick == -1) throw new IllegalStateException("You must call .energyPerTick() before registering!");
        if (energyCapacity == -1) energyCapacity = energyPerTick * 2;
        super.register(addon);
    }

}

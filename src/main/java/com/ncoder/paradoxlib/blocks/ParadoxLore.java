package com.ncoder.paradoxlib.blocks;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import java.text.DecimalFormat;

@UtilityClass
public final class ParadoxLore {

    private static final DecimalFormat FORMAT = new DecimalFormat("###,###,###,###,###,###.#");
    private static final double TPS = 20D / Slimefun.getTickerTask().getTickRate();

    private static final String ENERGY_PREFIX = "&8\u21E8 &e\u26A1 &7";
    private static final String SPEED_PREFIX = "&8\u21E8 &b\u26A1 &7";
    private static final String TIME_PREFIX = "&8\u21E8 &e\u23F3 &7";
    private static final String CAPACITY_PREFIX = "&8\u21E8 &e\uD83D\uDD0B &7";

    @Nonnull
    public static String energyPerTick(int energy) { return ENERGY_PREFIX + format(energy) + " J/t"; }

    @Nonnull
    public static String energyPerSecond(int energy) { return ENERGY_PREFIX + formatEnergy(energy) + " J/s"; }

    @Nonnull
    public static String energyBuffer(int energy) { return ENERGY_PREFIX + format(energy) + " J Buffer"; }

    @Nonnull
    public static String energy(int energy) { return ENERGY_PREFIX + format(energy) + " J "; }

    @Nonnull
    public static String speed(int speed) { return SPEED_PREFIX + speed + 'x'; }

    @Nonnull
    public static String capacity(int capacity) { return CAPACITY_PREFIX + format(capacity) + " J "; }

    @Nonnull
    public static String formatEnergy(int energy) { return FORMAT.format(energy * TPS); }

    @Nonnull
    public static String format(double number) { return FORMAT.format(number); }

}

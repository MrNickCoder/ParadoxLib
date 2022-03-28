package com.ncoder.paradoxlib.blocks;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import java.text.DecimalFormat;

@UtilityClass
public final class ParadoxLore {

    private static final DecimalFormat FORMAT = new DecimalFormat("###,###,###,###,###,###.#");
    private static final double TPS = 20D / Slimefun.getTickerTask().getTickRate();
    private static final String PREFIX = "&8\u21E8 &e\u26A1 &7";

    @Nonnull
    public static String energyPerSecond(int energy) { return PREFIX + formatEnergy(energy) + " J/s"; }

    @Nonnull
    public static String energyBuffer(int energy) { return PREFIX + format(energy) + " J Buffer"; }

    @Nonnull
    public static String energy(int energy) { return PREFIX + format(energy) + " J "; }

    @Nonnull
    public static String speed(int speed) { return PREFIX + "Speed: &b" + speed + 'x'; }

    @Nonnull
    public static String formatEnergy(int energy) { return FORMAT.format(energy * TPS); }

    @Nonnull
    public static String format(double number) { return FORMAT.format(number); }

}

package com.ncoder.paradoxlib.common;

import com.ncoder.paradoxlib.core.ParadoxAddon;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

import javax.annotation.ParametersAreNonnullByDefault;

@UtilityClass
@ParametersAreNonnullByDefault
public final class Scheduler {

    public static void run(Runnable runnable) {
        Bukkit.getScheduler().runTask(ParadoxAddon.instance(), runnable);
    }

    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(ParadoxAddon.instance(), runnable);
    }

    public static void run(int delayTicks, Runnable runnable) {
        Bukkit.getScheduler().runTaskLater(ParadoxAddon.instance(), runnable, delayTicks);
    }

    public static void runAsync(int delayTicks, Runnable runnable) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(ParadoxAddon.instance(), runnable, delayTicks);
    }

    public static void repeat(int intervalTicks, Runnable runnable) {
        repeat(intervalTicks, 1, runnable);
    }

    public static void repeatAsync(int intervalTicks, Runnable runnable) {
        repeatAsync(intervalTicks, 1, runnable);
    }

    public static void repeat(int intervalTicks, int delayTicks, Runnable runnable) {
        Bukkit.getScheduler().runTaskTimer(ParadoxAddon.instance(), runnable, delayTicks, Math.max(1, intervalTicks));
    }

    public static void repeatAsync(int intervalTicks, int delayTicks, Runnable runnable) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(ParadoxAddon.instance(), runnable, delayTicks, Math.max(1, intervalTicks));
    }

}
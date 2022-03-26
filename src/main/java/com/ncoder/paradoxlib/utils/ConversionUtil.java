package com.ncoder.paradoxlib.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import javax.annotation.Nonnull;

public class ConversionUtil {

    public static class LOCATION {

        public static String toString(@Nonnull Location location) {
            return location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getYaw() + ";" + location.getPitch();
        }

        public static Location toLocation(@Nonnull String string) {
            String[] split = string.split(";");
            return new Location(Bukkit.getServer().getWorld(split[0]), Double.valueOf(split[1]), Double.valueOf(split[2]), Double.valueOf(split[3]), Float.valueOf(split[4]), Float.valueOf(split[5]));
        }

    }

}

package com.ncoder.paradoxlib.utils;

import com.ncoder.paradoxlib.core.ParadoxAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class MessageUtil {

    private static CommandSender console = Bukkit.getConsoleSender();

    /**
     * In-Game Personal Message
     */
    public static void sendMessage(Player player, String message) { sendMessage(ParadoxAddon.instance().getName(), player, message); }
    public static void sendMessage(String title, Player player, String message) { player.sendMessage("§7[" + title + "§7] §f" + ChatColors.color(message)); }

    /**
     * In-Game Server Message
     */

    /**
     * Console Message
     */

    public static void log(int message) { log(String.valueOf(message)); }
    public static void log(double message) { log(String.valueOf(message)); }
    public static void log(float message) { log(String.valueOf(message)); }
    public static void log(long message) { log(String.valueOf(message)); }
    public static void log(String message) { console.sendMessage(message); }


    public static void info(int message) { info(String.valueOf(message)); }
    public static void info(double message) { info(String.valueOf(message)); }
    public static void info(float message) { info(String.valueOf(message)); }
    public static void info(long message) { info(String.valueOf(message)); }
    public static void info(String message) {
        ParadoxAddon.instance().getLogger().info(message);
    }

    public static void debug(int message) { debug(String.valueOf(message)); }
    public static void debug(double message) { debug(String.valueOf(message)); }
    public static void debug(float message) { debug(String.valueOf(message)); }
    public static void debug(long message) { debug(String.valueOf(message)); }
    public static void debug(String message) { ParadoxAddon.instance().getLogger().log(Level.OFF, "[" + ParadoxAddon.instance().getName() + "-DEBUG]: " + message); }

}

package com.ncoder.paradoxlib.commands;

import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
final class HelpCommand extends SubCommand {

    private final ParentCommand command;

    HelpCommand(ParentCommand command) {
        super("help", "Displays this");
        this.command = command;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("");
        sender.sendMessage(ChatColors.color("&7----------&b /" + command.fullName() + " Help &7----------"));
        sender.sendMessage("");
        for (SubCommand sub : command.available(sender)) {
            sender.sendMessage("/" + sub.fullName() + ChatColor.YELLOW + " - " + sub.description());
        }
        sender.sendMessage("");
    }

    @Override
    public void complete(CommandSender sender, String[] args, List<String> tabs) {

    }
}

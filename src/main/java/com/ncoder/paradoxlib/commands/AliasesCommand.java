package com.ncoder.paradoxlib.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
final class AliasesCommand extends SubCommand {

    private final String message;

    AliasesCommand(PluginCommand command) {
        super("aliases", "lists the available aliases for this command");
        message = "Aliases for /" + command.getName() + ": " + command.getAliases();
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        sender.sendMessage(message);
    }

    @Override
    protected void complete(CommandSender sender, String[] args, List<String> completions) {

    }
}

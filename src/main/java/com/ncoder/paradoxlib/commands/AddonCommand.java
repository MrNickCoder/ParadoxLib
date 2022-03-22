package com.ncoder.paradoxlib.commands;

import com.ncoder.paradoxlib.common.Events;
import com.ncoder.paradoxlib.core.ParadoxAddon;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

@ParametersAreNonnullByDefault
public final class AddonCommand extends ParentCommand implements TabExecutor, Listener {

    private final String help;
    private final String slashHelp;

    public AddonCommand(String command) {
        this(Objects.requireNonNull(ParadoxAddon.instance().getCommand(command),
                "No such command '" + command + "'! Add it it to your plugin.yml!"));
    }

    public AddonCommand(PluginCommand command) {
        super(command.getName(), command.getDescription());

        command.setExecutor(this);
        command.setTabCompleter(this);

        Events.registerListener(this);

        help = "help " + command.getName();
        slashHelp = "/" + help;

        addSub(new InfoCommand(ParadoxAddon.instance()));
        addSub(new AliasesCommand(command));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onServerCommand(ServerCommandEvent e) {
        if (e.getCommand().toLowerCase(Locale.ROOT).startsWith(help)) {
            e.setCommand(name());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().toLowerCase(Locale.ROOT).startsWith(slashHelp)) {
            e.setMessage("/" + name());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        execute(sender, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> strings = new ArrayList<>();
        complete(sender, args, strings);
        List<String> returnList = new ArrayList<>();
        String arg = args[args.length - 1].toLowerCase(Locale.ROOT);
        for (String item : strings) {
            if (item.toLowerCase(Locale.ROOT).contains(arg)) {
                returnList.add(item);
                if (returnList.size() >= 64) {
                    break;
                }
            }
            else if (item.equalsIgnoreCase(arg)) {
                return Collections.emptyList();
            }
        }
        return returnList;
    }

    @Override
    String fullName() {
        return name();
    }

}

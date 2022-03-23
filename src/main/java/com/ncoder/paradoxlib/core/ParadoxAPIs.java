package com.ncoder.paradoxlib.core;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyAPI;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;

import net.luckperms.api.LuckPerms;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

public final class ParadoxAPIs {

    public static CoreProtectAPI getCoreProtectAPI() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("CoreProtect");
        if (plugin == null || !(plugin instanceof CoreProtect)) return null;

        RegisteredServiceProvider<CoreProtect> provider = Bukkit.getServicesManager().getRegistration(CoreProtect.class);
        if (provider == null) return null;

        CoreProtectAPI api = provider.getProvider().getAPI();
        if (api.isEnabled() == false) return null;
        if (api.APIVersion() < 7) return null;

        return api;
    }

    public static TownyAPI getTownyAPI() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("Towny");
        if (plugin == null || !(plugin instanceof Towny)) return null;

        TownyAPI api = TownyAPI.getInstance();
        if (((Towny) plugin).isEnabled() == false) return null;

        return api;
    }

    public static LuckPerms getLuckPerms() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("LuckPerms");
        if (plugin == null || !(plugin instanceof LuckPerms)) return null;

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider == null) return null;

        return provider.getProvider();
    }

}

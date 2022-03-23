package com.ncoder.paradoxlib.core;

import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;

import org.bukkit.plugin.Plugin;

public final class ParadoxAPIs {

    public static CoreProtectAPI getCoreProtectAPI() {
        Plugin plugin = ParadoxAddon.instance().getServer().getPluginManager().getPlugin("CoreProtect");

        if (plugin == null || !(plugin instanceof CoreProtect)) return null;

        CoreProtectAPI api = ((CoreProtect) plugin).getAPI();
        if (api.isEnabled() == false) return null;
        if (api.APIVersion() < 7) return null;

        return api;
    }

}

package org.awaken.pickupmobs;

import org.awaken.pickupmobs.handlers.MobsHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Pickupmobs extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        new MobsHandler(this);
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Shutting down");
    }
}

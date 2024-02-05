package org.slavawins.uikit;

import org.bukkit.plugin.java.JavaPlugin;
import org.slavawins.reassets.integration.ResourceExtractor;
import org.slavawins.uikit.listeners.StartListener;

import java.io.File;

public final class Uikit extends JavaPlugin {
    private static JavaPlugin instanse;

    public static JavaPlugin getInstanse() {
        return instanse;
    }

    public static File myDataFolder;
    @Override
    public void onEnable() {
        myDataFolder = getDataFolder();

        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new StartListener(), this);

        ResourceExtractor.extract(this,"reassets");

        instanse = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}

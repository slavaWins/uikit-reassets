package org.slavawins.uikit;

import org.bukkit.plugin.java.JavaPlugin;
import org.slavawins.reassets.integration.ResourceExtractor;
import org.slavawins.textrender.service.generator.contract.TextFileContract;
import org.slavawins.textrender.service.generator.integration.TRRegister;
import org.slavawins.uikit.listeners.DupeListener;
import org.slavawins.uikit.listeners.ReassetsUiComandListener;
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

        instanse = this;

        myDataFolder = getDataFolder();

        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new StartListener(), this);
        getServer().getPluginManager().registerEvents(new DupeListener(), this);

        ResourceExtractor.extract(this,"reassets");

        //TRRegister.Add("test", new TextFileContract("Сперва обязательно нужно выполнить или провалить квест «Недоступный тайник». Чтобы начать квест, нужно заказать у Шустрого оружие!", 0, 2));

        ReassetsUiComandListener comandListener = new ReassetsUiComandListener("reassetsui");
        getCommand("reassetsui").setExecutor(comandListener);
        getCommand("reassetsui").setTabCompleter(comandListener);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}

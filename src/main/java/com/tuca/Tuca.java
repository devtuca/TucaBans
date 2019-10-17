package com.tuca;

import org.bukkit.plugin.java.JavaPlugin;

public final class Tuca extends JavaPlugin {

    private static Tuca instance;
    @Override
    public void onEnable() {
       instance = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Tuca getInstance() {
        return instance;
    }

}

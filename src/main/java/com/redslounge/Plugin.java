package com.redslounge;

import co.aikar.commands.BukkitCommandManager;
import com.redslounge.commands.AllChatCommand;
import com.redslounge.events.BlockCommands;
import com.redslounge.events.ChatEvents;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Plugin extends JavaPlugin
{
    private BukkitCommandManager commandManager;
    private FileConfiguration config = getConfig();
    private Settings settings;

    @Override
    public void onEnable()
    {
        commandManager = new BukkitCommandManager(this);
        settings = new Settings();

        setupCommands();
        setupEvents();
        loadConfig();
        saveConfig();

        settings.setAllChatTag(config.getString("AllChatTag"));
        settings.setBannedCommands(config.getStringList("spectatorBannedCommands"));
        try
        {
            settings.setSpecTeam(this.getServer().getScoreboardManager().getMainScoreboard().getTeam(config.getString("spectatorTeam")));
        }
        catch(NullPointerException e)
        {
            this.getLogger().severe("Failed to load spectator team from config! Plugin/loadConfig/loadSettings\n" + e.toString());
        }
    }

    private void setupCommands()
    {
        commandManager.registerCommand(new AllChatCommand(this));
    }

    private void setupEvents()
    {
        getServer().getPluginManager().registerEvents(new ChatEvents(this), this);
        getServer().getPluginManager().registerEvents(new BlockCommands(this), this);
    }

    private void loadConfig()
    {
        config.addDefault("AllChatTag", "&8[&4All&6Chat&8] ");
        config.addDefault("spectatorTeam", "*YOUR TEAM HERE*");
        config.addDefault("spectatorBannedCommands", new ArrayList<String>());
        config.options().copyDefaults(true);
    }

    public Settings getSettings()
    {
        return settings;
    }
}

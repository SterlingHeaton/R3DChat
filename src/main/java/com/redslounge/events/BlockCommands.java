package com.redslounge.events;

import com.redslounge.Plugin;
import com.redslounge.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scoreboard.Team;

public class BlockCommands implements Listener
{
    private Plugin plugin;

    public BlockCommands(Plugin plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event)
    {
        Player player = event.getPlayer();
        Team team = player.getScoreboard().getEntryTeam(player.getName());

        if(!team.getName().equalsIgnoreCase(plugin.getSettings().getSpecTeam().getName()))
        {
            return;
        }

        for(String command : plugin.getSettings().getBannedCommands())
        {
            if(event.getMessage().contains(command))
            {
                player.sendMessage(Utils.color("&cThis command has been disabled because you are a spectator!"));
                event.setCancelled(true);
                return;
            }
        }
    }
}

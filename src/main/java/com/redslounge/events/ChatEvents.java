package com.redslounge.events;

import com.redslounge.Plugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class ChatEvents implements Listener
{
    private Plugin plugin;

    public ChatEvents(Plugin plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        Team playerTeam = player.getScoreboard().getEntryTeam(player.getName());

        if(playerTeam == null)
        {
            return;
        }

        event.getRecipients().clear();
        event.getRecipients().addAll(getPlayersOnTeam(playerTeam));
    }

    private Set<Player> getPlayersOnTeam(Team team)
    {
        Set<Player> players = new HashSet<>();
        Set<String> teamPlayers = team.getEntries();
        Set<Player> onlinePlayers = new HashSet<Player>(plugin.getServer().getOnlinePlayers());

        for(Player player : onlinePlayers)
        {
            if(teamPlayers.contains(player.getName()))
            {
                players.add(player);
            }
        }
        return players;
    }
}

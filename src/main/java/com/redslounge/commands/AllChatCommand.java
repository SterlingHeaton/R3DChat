package com.redslounge.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.redslounge.Plugin;
import com.redslounge.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandAlias("all")
public class AllChatCommand extends BaseCommand
{
    private Plugin plugin;

    public AllChatCommand(Plugin plugin)
    {
        this.plugin = plugin;
    }

    @Default
    private void onPlayerChat(Player player, String[] args)
    {
        String name = player.getDisplayName();
        ChatColor playerColor = Utils.getTeamColor(player);
        String playerMessage = Utils.buildMessage(args, 0);

        plugin.getServer().broadcastMessage(Utils.color(plugin.getSettings().getAllChatTag() + playerColor + name + "&7: &f" + playerMessage));
    }
}

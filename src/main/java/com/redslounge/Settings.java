package com.redslounge;

import org.bukkit.scoreboard.Team;

import java.util.List;

public class Settings
{
    private String allChatTag;
    private Team specTeam;
    private List<String> bannedCommands;

    public String getAllChatTag()
    {
        return allChatTag;
    }

    public void setAllChatTag(String allChatTag)
    {
        this.allChatTag = allChatTag;
    }

    public Team getSpecTeam()
    {
        return specTeam;
    }

    public void setSpecTeam(Team specTeam)
    {
        this.specTeam = specTeam;
    }

    public List<String> getBannedCommands()
    {
        return bannedCommands;
    }

    public void setBannedCommands(List<String> bannedCommands)
    {
        this.bannedCommands = bannedCommands;
    }
}

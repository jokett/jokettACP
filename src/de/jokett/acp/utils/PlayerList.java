package de.jokett.acp.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerList {

    public static String getPlayerList() {
        String response = "";
        for(Player all : Bukkit.getOnlinePlayers()) {
            response = response +
            "<tr><td><img src=\"https://minotar.net/avatar/%player-name%/32\"></td><td><p>%player-name% - %player-uuid% <input type=\"button\" class=\"small\" value=\"Kick\" onclick=\"sendAction('kick %player-name% Kein Grund angegeben!')\"> <input type=\"button\" class=\"small\" value=\"Perma-Ban\" onclick=\"sendAction('ban %player_name%')\"></p></td></tr>"
            .replace("%player-name%", all.getName())
            .replace("%player-uuid%", all.getUniqueId().toString());
        }
        if(response.trim().equalsIgnoreCase("")) {
            response = "<p id=\"noon\">Es sind keine Spieler online! :(</p><br />";
        }
        return response;
    }
}

package de.jokett.acp;

import de.jokett.acp.utils.LoginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.jokett.acp.utils.WebFileManager;
import de.jokett.acp.utils.WebServerManager;

import org.bukkit.ChatColor;

public class Main extends JavaPlugin {

    private static Main instance;
    private static String prefix = "§8[§eACP§8] §7";


    private static WebServerManager webServerManager;
    private static LoginManager loginManager;

    @Override
    public void onEnable() {
        instance = this;
        webServerManager = new WebServerManager(80);
        webServerManager.start();
        loginManager = new LoginManager();
        getLogger().info(ChatColor.GREEN + "ACP von jokett/Dienstfahrt aktiviert!");


        loginManager.addUser("dienstfahrt", "kiddy");

        new WebFileManager();
    }
    @Override
    public void onDisable() {
        webServerManager.stop();
    }
    public static Main getInstance() {
        return instance;
    }
    public static String getPrefix() {
        return prefix;
    }
    public static WebServerManager getWebServerManager() {
        return webServerManager;
    }

    public static LoginManager getLoginManager() {
        return loginManager;
    }
}
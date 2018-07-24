package de.jokett.acp.utils;

import com.sun.net.httpserver.BasicAuthenticator;
import de.jokett.acp.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoginManager {

    BasicAuthenticator basicAuthenticator;

    File f = new File(Main.getInstance().getDataFolder() + "//acp_admins.yml");
    YamlConfiguration cfg;

    public LoginManager() {
        cfg = YamlConfiguration.loadConfiguration(f);
        cfg.set("acp_admins", new ArrayList<String>());
        saveConfig();

        basicAuthenticator = new BasicAuthenticator("/") {
            @Override
            public boolean checkCredentials(String username, String password) {
                return userExists(username, password);
            }
        };
    }

    public void addUser(String username, String password) {
        List<String> users = cfg.getStringList("acp_admins");
        if(!userExists(username)) {
            users.add(username + ":" + password);
            cfg.set("acp_admins", users);
            saveConfig();
        }
    }

    public boolean userExists(String username, String password) {
        List<String> users = cfg.getStringList("acp_admins");
        for(String str : users) {
            if(str.split(":") [0].equals(username) && str.split(";") [1].equals(password)) {
                return true;
            }
        }
        return false;
    }


    public boolean userExists(String username) {
        List<String> users = cfg.getStringList("users");
        for(String str : users) {
            if(str.split(":") [0].equals(username)) {
                return true;
            }
        }
        return false;
    }


    public void saveConfig () {
        try {

            cfg.save(f);

        } catch (Exception e) {

        }
    }

    public BasicAuthenticator getBasicAuthenticator() {
        return basicAuthenticator;
    }
}

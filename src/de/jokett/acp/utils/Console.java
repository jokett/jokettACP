package de.jokett.acp.utils;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.jokett.acp.Main;
import org.bukkit.Bukkit;

import java.io.IOException;

public class Console implements HttpHandler {

    public static String getLog() {
        return FileUtils.getLogContents();
    }

    public static void sendCommand(String command) {
        Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }
        });

    }

    @Override
    public void handle(HttpExchange httpExchange) {
        if(httpExchange.getRequestURI().getPath().toString().equalsIgnoreCase("/console_send")) {
            sendCommand(httpExchange.getRequestURI().getQuery().toString().replace("%20", " "));
        }
    }
}

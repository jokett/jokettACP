package de.jokett.acp.utils;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;



public class WebServerManager {

    private HttpServer server;

    public WebServerManager(int port) {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop(0);
    }

    public HttpServer getServer(){
        return server;
    }
}
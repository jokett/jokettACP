package de.jokett.acp.utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class PluginDownloader implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = FileUtils.getFileContents("hidden/plugins.html");
        if(httpExchange.getRequestURI().getQuery() != null) {
            String query = httpExchange.getRequestURI().getQuery();
            String url = query.split("&param=") [0];
            String filename = query.split("&param=") [1];

            if(downloadFile(url, filename)) {
                response = response.replace("%download_state%", "Der Download wurde erfolgreich abgeschlossen!");
            } else {
                response = response.replace("%download_state%", "Es gab einen Fehler beim Download. Sorry :/ ");
            }
        } else {
            response = response.replace("%download_state%", "");
        }


        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    public static boolean downloadFile(String url, String filename) {
        try{
            ReadableByteChannel byteChannel = Channels.newChannel(new URL(url).openStream());
            FileChannel fileChannel = new FileOutputStream("plugins/" + filename + ".jar").getChannel();
            fileChannel.transferFrom(byteChannel, 0, Long.MAX_VALUE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

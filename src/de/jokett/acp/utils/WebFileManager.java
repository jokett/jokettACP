package de.jokett.acp.utils;



        import com.sun.net.httpserver.HttpExchange;
        import com.sun.net.httpserver.HttpHandler;
        import de.jokett.acp.Main;

        import java.io.File;
        import java.io.IOException;
        import java.io.OutputStream;




public class WebFileManager implements HttpHandler {

    public WebFileManager() {
        load();
    }

    public void load() {
        File dir = new File(Main.getInstance().getDataFolder() + "//webpages//");
        if(dir.getParentFile().exists()) {
            dir.getParentFile().mkdirs();
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File[] files = dir.listFiles();
        if(files != null) {
            for(int i = 0; i< files.length; i++) {
                if(!files[i].isDirectory()) {
                    Main.getWebServerManager().getServer().createContext("/" + files[i].getName(), this)
                            .setAuthenticator(Main.getLoginManager().getBasicAuthenticator());
                }
            }
        }
        Main.getWebServerManager().getServer().createContext("/plugins", new PluginDownloader())
                .setAuthenticator(Main.getLoginManager().getBasicAuthenticator());
        Main.getWebServerManager().getServer().createContext("/console_send", new Console())
                .setAuthenticator(Main.getLoginManager().getBasicAuthenticator());


        Main.getWebServerManager().getServer().createContext("/", this)
                .setAuthenticator(Main.getLoginManager().getBasicAuthenticator());

    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = FileUtils.getFileContents(httpExchange.getRequestURI().getPath());

        response = response
                .replace("%console-view%", Console.getLog())
                .replace("%player-list%", PlayerList.getPlayerList())
                .replace("%account-name%", httpExchange.getPrincipal().getUsername());

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

}

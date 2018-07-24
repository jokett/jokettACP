package de.jokett.acp.utils;

import java.io.BufferedReader;
import java.io.FileReader;

import de.jokett.acp.Main;

public class FileUtils {

    public static String getFileContents(String filename) {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(Main.getInstance().getDataFolder() + "//webpages/" + filename));
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();

        } catch (Exception e) {
            return "<body style=\"background:#008e8c;\"><h1 style=\"font-family: \"Fjalla One\",Arial;font-color:red\">Fehler.</h1></body>";

        }

    }

    public static String getLogContents() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("logs//latest.log"));
            String end = "";
            String line = bufferedReader.readLine();
            while(line != null) {
                end = line + "<br />" + end;
                line = bufferedReader.readLine();
            }
            return end;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "<h1>Error!</h1>";
    }

}
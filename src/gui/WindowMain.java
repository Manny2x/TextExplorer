package gui;

import java.awt.*;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WindowMain {
    public static Window window = new Window();
    public static File errorLogs =
            new File(File.separator + "TextExplorer" +
                    File.separator
            + "textfiles" + File.separator + "ERRORLOGS.txt");
    static{
        if(Window.file.exists() && !errorLogs.exists()){
            try (FileWriter fileWriter =
                    new FileWriter(errorLogs)){
                assert errorLogs.createNewFile();
                fileWriter.write("ERROR LOGS CREATED AT>> " + DateTimeFormatter
                        .ofPattern("dd/MM/yyyy HH:mm:ss")
                        .format(LocalDateTime.now()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("TextExplorer v0.2.5" +
                "\nNOTES:\n. Smaller file size" + "\n" +
                ". Bug fixes" + "\n" +
                "\t Directory creation on Linux, Ubuntu and other less popular OS's fixed \n");


        window.setSize(new Dimension(500, 550));
        window.setTitle("TextExplorer v0.2.5");
        window.setVisible(true);
    }
}

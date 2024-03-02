package filecomputations;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GUIFiler {
    File file;

    public GUIFiler(File file){
        this.file = file;
    }

    public void listText(List list){
        try { 
            System.out.println("Checking>> Directory");
            File[] files = this.file.listFiles();
            ArrayList<File> textFiles =
                    new ArrayList<>();

            assert files != null;
            for (File file : files) {
                if (String.valueOf(file.toPath()).contains(".txt")) {
                    System.out.println("Found>> " + file.toPath());
                    textFiles.add(file);
                }
            }

            for (File textFile : textFiles) {
                String substring = String.valueOf(textFile);
                list.add(substring);
            }
        } catch (NullPointerException exc){
            System.out.println("Directory>> Unavailable>> Creating dirs");
            boolean success = file.mkdirs();
            File errorLogs = new File(File.separator + "TextExplorer" +
                    File.separator + "textfiles" + File.separator + "ERRORLOGS.txt");
            try {
                Files.createFile(errorLogs.toPath());
                Files.writeString(
                        errorLogs.toPath(),
                        "ERROR LOGS CREATED AT>> " + DateTimeFormatter
                        .ofPattern("dd/MM/yyyy HH:mm:ss")
                        .format(LocalDateTime.now()), StandardOpenOption.WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(success)
                listText(list);
        }
    }
}

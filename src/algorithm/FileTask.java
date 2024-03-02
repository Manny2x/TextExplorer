package algorithm;

import algorithm.searchers.StringSearcher;
import filecomputations.FileInformation;
import gui.Window;
import gui.WindowMain;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileTask {

    final public int errorCode = -1;
    public boolean checkError = false;

    public List<String> list = fileToString();
    public static Searcher searcher;
    List<FileInformation> informationList =
            new ArrayList<>(3);



    Runnable task1 = () -> {
        String string = list.get(0);

        informationList.add(searcher.search(
                searcher.strip(
                        string)));
    };
    Runnable task2 = () -> {
        String string = list.get(1);

        informationList.add(
                searcher.search(
                searcher.strip(string)));
    };
    Runnable task3 = () -> {
        String string = list.get(2);

        informationList.add(searcher.search(
                searcher.strip(
                        string)));
    };
    Runnable task4 = () -> {
        File file = new File(Window.fileList.getSelectedItem());

        StringBuilder str1 = new StringBuilder();

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            BufferedInputStream bufferedInputStream =
                    new BufferedInputStream(fileInputStream);
            int c;
            while((c = bufferedInputStream.read()) != -1)
                str1.append((char) c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try(FileWriter writer = new FileWriter(WindowMain.errorLogs)) {
                writer.write("\n ERROR AT: " + DateTimeFormatter
                        .ofPattern("dd/MM/yyyy HH:mm:ss"));
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));

                writer.write("ERROR MESSAGE" + "\n" +
                        sw.toString());
                checkError = true;
            } catch (IOException e1){
                System.out.println("ERROR IN WRITING ERROR MESSAGE");
            }
            System.out.println("File does not exist");
        } catch (IOException e) {
            e.printStackTrace();
            try(FileWriter writer = new FileWriter(WindowMain.errorLogs)) {
                writer.write("\n ERROR AT: " + DateTimeFormatter
                        .ofPattern("dd/MM/yyyy HH:mm:ss"));
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));

                writer.write("ERROR MESSAGE" + "\n" +
                        sw.toString());
                checkError = true;
            } catch (IOException e1){
                System.out.println("ERROR IN WRITING ERROR MESSAGE");
            }
            System.out.println("Error in IO");
        }
        informationList.add(searcher.search(
                searcher.strip(
                        String.valueOf(str1)
                )
        ));
    };


    public FileInformation computeTask(){


        list = fileToString();
        informationList.clear();
        System.out.println("Information List>> Cleared");
        if(searcher instanceof StringSearcher){
            Thread tTask4 = new Thread(task4);
            tTask4.start();
            try {
                tTask4.join();
            } catch (InterruptedException e){
                e.printStackTrace();
                try(FileWriter writer = new FileWriter(WindowMain.errorLogs)) {
                    writer.write("\n ERROR AT: " + DateTimeFormatter
                            .ofPattern("dd/MM/yyyy HH:mm:ss"));
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));

                    writer.write("ERROR MESSAGE" + "\n" +
                            sw.toString());
                    return new FileInformation(-1);
                } catch (IOException e1){
                    System.out.println("ERROR IN WRITING ERROR MESSAGE");
                }
            }

            return informationList.get(0);
        }
        else {
            Thread tTask1 = new Thread(task1);
            Thread tTask2 = new Thread(task2);
            Thread tTask3 = new Thread(task3);
            tTask1.start();
            tTask2.start();
            tTask3.start();
            System.out.println("Threads>> Ran");

            try {
                tTask1.join();
                tTask2.join();
                tTask3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                try(FileWriter writer = new FileWriter(WindowMain.errorLogs)) {
                    writer.write("\n ERROR AT: " + DateTimeFormatter
                            .ofPattern("dd/MM/yyyy HH:mm:ss"));
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));

                    writer.write("ERROR MESSAGE" + "\n" +
                            sw.toString());
                    return new FileInformation(-1);
                } catch (IOException e1){
                    System.out.println("ERROR IN WRITING ERROR MESSAGE");
                }
            }

            return FileInformation.addAll(informationList);
        }

    }




    public static List<String> fileToString(){
        File file = new File(Window.fileList.getSelectedItem());
        List<String> list = new ArrayList<>(3);

        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        StringBuilder str3 = new StringBuilder();

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            BufferedInputStream bufferedInputStream =
                    new BufferedInputStream(fileInputStream);
            int c;
            long fileLength = Files.size(
                    Paths.get(file.getAbsolutePath()
                    ));

            for (int i = 0; (c = bufferedInputStream.read())
                    != -1 || i > fileLength; i++) {
                if(i % 100 == 0)
                    Runtime.getRuntime().gc();
                if (i < fileLength / 3) {
                    str1.append((char) c);
                }
                else if (i >= fileLength / 3 &&
                        i <= (fileLength / 3) * 2)
                    str2.append((char) c);
                else if (i > (fileLength / 3) * 2)
                    str3.append((char) c);

            }
            System.out.println("Stream>> Split>> 3");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try(FileWriter writer = new FileWriter(WindowMain.errorLogs)) {
                writer.write("\n ERROR AT: " + DateTimeFormatter
                        .ofPattern("dd/MM/yyyy HH:mm:ss"));
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));

                writer.write("ERROR MESSAGE" + "\n" +
                        sw.toString());
                System.out.println("File>> Not Exist");
            } catch (IOException e1){
                System.out.println("ERROR IN WRITING ERROR MESSAGE");
            }
            str1.append(" ");
            str2.append(" ");
            str3.append(" ");
        } catch (IOException e) {
            e.printStackTrace();
            try(FileWriter writer = new FileWriter(WindowMain.errorLogs)) {
                writer.write("\n ERROR AT: " + DateTimeFormatter
                        .ofPattern("dd/MM/yyyy HH:mm:ss"));
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));

                writer.write("ERROR MESSAGE" + "\n" +
                        sw.toString());
            } catch(IOException e1){
                System.out.println("ERROR IN WRITING ERROR MESSAGE");
            }
            System.out.println("IO>> error");
            str1.append(" ");
            str2.append(" ");
            str1.append(" ");
        }

        list.add(String.valueOf(str1));
        list.add(String.valueOf(str2));
        list.add(String.valueOf(str3));

        return list;
    }
}

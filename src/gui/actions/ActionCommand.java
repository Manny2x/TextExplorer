package gui.actions;

import algorithm.searchers.*;
import algorithm.FileTask;

import filecomputations.FileInformation;
import filecomputations.GUIFiler;

import gui.Window;
import gui.WindowMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;

public enum ActionCommand implements ActionListener {
    VOWEL(){
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Vowel");

            Window.rType = "Vowel";
            Window.status = "(Selected Vowel) Waiting...";
            WindowMain.window.repaint();
            WindowMain.window.curSearcher = new VowelSearcher();
        }
    },
    NUMBER(){
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Number");

            Window.rType = "Number";
            Window.status = "(Selected Number) Waiting...";
            WindowMain.window.repaint();
            WindowMain.window.curSearcher = new NumberSearcher();
        }
    },
    SYMBOL(){
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Symbol");


            Window.rType = "Symbol";
            Window.status = "(Selected Symbol) Waiting...";
            WindowMain.window.repaint();
            WindowMain.window.curSearcher = new SymbolSearcher();
        }
    },
    PUNCTUATION(){
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Punctuation");

            Window.rType = "Punctuation";
            Window.status = "(Selected Punctuation) Waiting...";
            WindowMain.window.repaint();
            WindowMain.window.curSearcher = new PunctuationSearcher();
        }
    },
    WHITESPACE(){
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pressed>> Whitespace");

            Window.rType = "Whitespace";
            Window.status = "(Selected Whitespace) Waiting...";
            WindowMain.window.repaint();
            WindowMain.window.curSearcher = new WhitespaceSearcher();
        }
    },
    READ(){
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pressed>> Read");
            Window.textArea.setText("Status: Reading..." + "\n" +
                    "Reading: " + Window.fileList.getSelectedItem());

            FileTask.searcher = WindowMain.window.curSearcher;
            long readTime = System.currentTimeMillis();
            try {
                FileTask fileTask =
                        new FileTask();
                FileInformation fileInformation =
                        fileTask.computeTask();

                if(fileTask.checkError)
                    Window.readInfo[0] =
                            fileTask.errorCode;
                else
                    Window.readInfo[0] = String.valueOf(
                            fileInformation.getStringLength()
                    );

                Window.readInfo[1] = System.currentTimeMillis() - readTime;
                if(Window.readInfo[0].equals(-1)) {
                    Window.textArea.setText("Error in reading file\n " +
                            "See Error Logs: " + "\n" +
                            "Read time(ms): " + Window.readInfo[1]);
                    return;
                }

                WindowMain.window.repaint();
                Window.status = "Read...";
                WindowMain.window.repaint();
            } catch(OutOfMemoryError error){
                Window.status = "Out of memory";
                System.out.println("Memory>> Out of memory");

                Window.readInfo[0] = "Out of memory";
                Window.readInfo[1] = System.currentTimeMillis() - readTime;

                WindowMain.window.repaint();
            }
        }
    },
    CHOOSE_STRING(){
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Selected>> " + Window.chooseString.getText());
            Window.rType = Window.chooseString.getText();
            Window.status = "(Selected String Choose) Waiting...";

            WindowMain.window.curSearcher = new StringSearcher();

            WindowMain.window.repaint();
        }
    },
    REFRESH(){
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pressed>> Refresh");

            Window.fileList.removeAll();
            new GUIFiler(Window.file).listText(Window.fileList);

            Window.chooseString.setText("Search string.... ");
            Window.status = "Waiting...";

            Window.readInfo[0] = " "; Window.readInfo[1] = 0;
            Window.rType = " ";
            Window.readType.select(0);

            WindowMain.window.repaint();
        }
    },
    CREATE_FILE(){
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("Running>> Notepad");

            new Thread(() ->{
                try {
                    ProcessBuilder processBuilder =
                            new ProcessBuilder("notepad.exe");
                    processBuilder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    try(FileWriter writer = new FileWriter(WindowMain.errorLogs)) {
                        writer.write("\n ERROR AT: " + DateTimeFormatter
                                .ofPattern("dd/MM/yyyy HH:mm:ss"));
                        StringWriter sw = new StringWriter();
                        e.printStackTrace(new PrintWriter(sw));

                        writer.write("ERROR MESSAGE" + "\n" +
                                sw.toString());
                    } catch (IOException e1){
                        System.out.println("ERROR IN WRITING ERROR MESSAGE");
                    }
                }
            }).start();
        }
    };

    public static ActionCommand enumString(String string){
        ArrayList<ActionCommand> actionCommands =
                new ArrayList<>(Arrays.asList(
                        VOWEL,
                        WHITESPACE,
                        NUMBER,
                        PUNCTUATION,
                        SYMBOL
                ));

        for (ActionCommand actionCommand : actionCommands) {
            if (String.valueOf(actionCommand).equals(string)) {
                return actionCommand;
            }
        }
        return null;
    }
}

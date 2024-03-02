package gui;

import algorithm.Searcher;
import filecomputations.GUIFiler;
import gui.actions.ActionCommand;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import static java.util.Arrays.asList;

public class Window extends Frame{
    Button vowel,
            whitespace,
            symbol,
            number,
            punctuation,
            read,
            refresh,
            createFile;

    Dimension dimension =
            new Dimension(500, 500);

    public Searcher curSearcher;
    String[] names =
            {"Vowel", "Whitespace",
                    "Symbol", "Number",
                    "Punctuation"};
    public static Path path =
            Path.of(File.separator + "TextExplorer"
                    + File.separator + "textfiles");
    public static Choice readType;
    public static java.awt.List fileList;
    public static TextArea textArea;
    public static TextField chooseString;
    public static File file = new File(String.valueOf(path));
    Window(){
        WindowCreator windowCreator =
                new WindowCreator(dimension);
        GUIFiler guiFiler =
                new GUIFiler(file);

        setLayout(null);

        List<Button> list =  windowCreator.assignButtons(names, asList(vowel,
                whitespace,
                symbol,
                number,
                punctuation));
        readType = new Choice();
        readType.add("All");
        readType.add("Half");
        readType.add("Quarter");
        readType.add("Tenth");
        readType.addItemListener((itemListener) -> repaint());

        fileList = new java.awt.List();
        guiFiler.listText(fileList);
        fileList.addItemListener((itemListener) -> repaint());

        read = new Button("Read");
        read.addActionListener(ActionCommand.READ);

        textArea = new TextArea();

        chooseString = new TextField(20);
        chooseString.setText("Search string.... ");
        chooseString.addActionListener(ActionCommand.CHOOSE_STRING);

        refresh = new Button("Refresh");
        refresh.addActionListener(ActionCommand.REFRESH);

        createFile = new Button("Create File");
        createFile.addActionListener(ActionCommand.CREATE_FILE);

        HashMap<Button, ActionCommand> buttonActions =
                new HashMap<>();

        for (int i = 0; i < list.size(); i++)
            buttonActions.put(list.get(i),
                    ActionCommand.enumString(names[i].toUpperCase()));
        windowCreator.addActions(buttonActions);


        fileList.setSize(150, 120);
        windowCreator.putComponents(Position.NORTH,
                Collections.singletonList(fileList));
        add(fileList);


        windowCreator.setButtonSizes(list);
        windowCreator.putButtons(Position.SOUTHEAST, list);
        windowCreator.addButtons(this,
                list);

        windowCreator.putComponents(Position.NORTHEAST,
                Collections.singletonList(readType));
        add(readType);

        read.setSize(75, 30);
        windowCreator.putButtons(Position.WEST,
                Collections.singletonList(read));
        add(read);

        textArea.setSize(240, 120);
        windowCreator.putComponents(Position.SOUTHWEST,
                Collections.singletonList(textArea));
        add(textArea);

        createFile.setSize(75, 30);

        chooseString.setSize(100, 30);
        windowCreator.putComponents(Position.EAST,
                Arrays.asList(createFile, new Button(),
                        chooseString));
        add(chooseString);

        refresh.setSize(75, 30);
        windowCreator.putButtons(Position.NORTHWEST,
                Collections.singletonList(refresh));
        add(refresh);

        add(createFile);



        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Window>> Closed");
                System.exit(0);
            }

            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("Window>> Opened");
            }
        });
    }
    public static String rType = " ";
    public static Object[] readInfo = {" ", 0};
    public static String status = "Waiting...";
    @Override
    public void paint(Graphics g) {
        textArea.setText("The type that will be read: " + rType + "\n" +
                "The file that will be read: " + fileList.getSelectedItem() + "\n" +
                "The amount that will be read: " + readType.getSelectedItem() + "\n\n" +
                "Status: " + status + "\n" +
                "Occurrences: " + readInfo[0] + "\n" +
                "Read time(ms): " + readInfo[1]);
    }
}

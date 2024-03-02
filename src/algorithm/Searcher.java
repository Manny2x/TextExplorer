package algorithm;

import filecomputations.FileInformation;
import gui.Window;

import java.nio.file.Path;

public interface Searcher {
    /**
     * METHOD SEARCH USED TO SEARCH FOR THE SPECIFIED ITEM.
     * SEARCHES A FILE THAT HAS ALREADY BEEN SPLIT
     */
    FileInformation search(String string);

    /**
     * METHOD STRIPS THE FILE OF THINGS THAT ARE NOT NEEDED
     * EXAMPLE: VOWEL WOULD STRIP NUMBERS AND SYMBOLS
     * PUNCTUATION WOULD STRIP ALL NUMBERS AND LETTERS
     */
    String strip(String string);

    /**
     * THE SPECIFIED FILE TO SEARCH
     */
    Path path =
        Path.of(Window.fileList.getSelectedItem());
}

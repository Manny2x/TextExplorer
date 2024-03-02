package algorithm.searchers;

import algorithm.Searcher;
import filecomputations.FileInformation;
import filecomputations.FractionAmount;
import gui.Window;

import java.nio.charset.StandardCharsets;

public class WhitespaceSearcher implements Searcher {
    @Override
    public FileInformation search(String string) {
        FileInformation fileInformation;

        StringBuilder strLength =
                new StringBuilder();

        // Searching
        for (int i = 0; i < string.length() ; i++){
            char character =
                    string.charAt(i);

            Runtime.getRuntime().gc();
            if(Character.isWhitespace(character)) {
                strLength.append(character);
            }
        }

        fileInformation = new FileInformation(strLength.length() / FractionAmount.enumString(
                Window.readType.getSelectedItem()
        ).getFraction());
        return fileInformation;
    }


    @Override
    public String strip(String string) {
        return string;
    }
}

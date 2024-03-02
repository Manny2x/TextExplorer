package algorithm.searchers;

import algorithm.Searcher;
import filecomputations.FileInformation;
import filecomputations.FractionAmount;
import gui.Window;

public class VowelSearcher implements Searcher {
    String vowels = "aeiouAEIOU";

    @Override
    public FileInformation search(String string) {
        FileInformation fileInformation;

        StringBuilder strLength =
                new StringBuilder();

        // Searching
        for (int i = 0; i < string.length(); i++){
            char character =
                    string.charAt(i);

            if(i % 100 == 0)
                Runtime.getRuntime().gc();
            if(vowels.contains(String.valueOf(character))){
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
        StringBuilder fileStr =
                new StringBuilder(string);

        for (int i = 0; i < fileStr.length(); i++){
            char character =
                    fileStr.charAt(i);
            if(i % 100 == 0)
                Runtime.getRuntime().gc();
            if (!vowels.contains(String.valueOf(character))){
                fileStr.deleteCharAt(i);
            }
        }

        return String.valueOf(fileStr);
    }
}

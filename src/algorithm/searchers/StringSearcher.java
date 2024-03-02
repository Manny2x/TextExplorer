package algorithm.searchers;

import algorithm.Searcher;
import filecomputations.FileInformation;
import filecomputations.FractionAmount;
import gui.Window;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSearcher implements Searcher {
    String search = Window.rType;

    /**
     * @param string String that will be searched
     * @return Returns the information found in the file
     */
    @Override
    public FileInformation search(String string){
        FileInformation fileInformation;


        int occurrences = 0;

        Pattern pattern = Pattern.compile(search);
        Matcher matcher = pattern.matcher(string.toLowerCase());


        while(matcher.find()){
            System.out.println("Found");
            occurrences++;
            if(occurrences % 20 == 0)
                Runtime.getRuntime().gc();
        }
        fileInformation = new FileInformation(occurrences / FractionAmount.enumString(
                Window.readType.getSelectedItem()
        ).getFraction());
        return fileInformation;
    }

    /**
     * @param string String that will be stripped
     * @return Same string, since there is nothing to strip
     */
    @Override
    public String strip(String string) {
        return string;
    }
}

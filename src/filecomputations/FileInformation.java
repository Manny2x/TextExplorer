package filecomputations;

import java.util.List;

public class FileInformation {
    private int stringLength;
    public FileInformation(int stringLength){
        this.stringLength = stringLength;
    }

    public static FileInformation addAll(List<FileInformation> list){
        int stringLength = 0;

        for (FileInformation fileInformation : list) {
            stringLength += fileInformation.stringLength;
        }

        return new FileInformation(
                stringLength);
    }

    public int getStringLength() {
        return stringLength;
    }

}

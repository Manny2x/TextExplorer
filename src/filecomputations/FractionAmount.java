package filecomputations;

import java.util.ArrayList;
import java.util.Arrays;

public enum FractionAmount {
    ALL(1),
    HALF(2),
    QUARTER(4),
    TENTH(10);

    private int fraction;
    FractionAmount(int fraction){
        this.fraction = fraction;
    }

    public static FractionAmount enumString(String str){
        ArrayList<FractionAmount> fractionAmounts =
                new ArrayList<>(Arrays.asList(ALL,
                        HALF,
                        QUARTER,
                        TENTH));

        for (FractionAmount fractionAmount : fractionAmounts) {
            if (str.toUpperCase().equals(
                    String.valueOf(fractionAmount))) {
                return fractionAmount;
            }
        }

        return ALL;
    }

    public int getFraction() {
        return fraction;
    }
}

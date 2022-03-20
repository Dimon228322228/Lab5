package Content.Product;

import java.io.Serializable;

@SuppressWarnings("unused")
public enum UnitOfMeasure  implements Serializable {
    KILOGRAMS("kilograms"),
    CENTIMETERS("centimeters"),
    PCS("amount"),
    MILLILITERS("milliliters"),
    GRAMS("grams");

    private final String title;

    UnitOfMeasure(String title){
        this.title = title;
    }

    public static String getTitleinString(){
        return System.lineSeparator() + KILOGRAMS.title + " " +
                GRAMS.title + " " +
                CENTIMETERS.title + " " +
                PCS.title + " " +
                MILLILITERS.title + System.lineSeparator();
    }

    public static String getTitleinColumn(){
        return System.lineSeparator() + KILOGRAMS.title + System.lineSeparator() +
                                        GRAMS.title + System.lineSeparator() +
                                        CENTIMETERS.title + System.lineSeparator() +
                                        PCS.title + System.lineSeparator() +
                                        MILLILITERS.title + System.lineSeparator();
    }

    public String getTitle(){
        return title;
    }

    public static UnitOfMeasure fromString(String text) {
        for (UnitOfMeasure b : UnitOfMeasure.values()) {
            if (b.title.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

}

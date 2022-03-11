package Content.Product;

@SuppressWarnings("unused")
public enum UnitOfMeasure {
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
        return  KILOGRAMS.title + " " +
                GRAMS.title + " " +
                CENTIMETERS.title + " " +
                PCS.title + " " +
                MILLILITERS.title;
    }

    public static String getTitleinColumn(){
        return  KILOGRAMS.title + System.lineSeparator() +
                GRAMS.title + System.lineSeparator() +
                CENTIMETERS.title + System.lineSeparator() +
                PCS.title + System.lineSeparator() +
                MILLILITERS.title;
    }

    public String getTitle(){
        return title;
    }

}

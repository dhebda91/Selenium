package page.objects;

public enum Categories {

    ELEMENTS("Elements"),
    FORMS("Forms"),
    ALERTS("Alerts"),
    WIDGETS("Widgets"),
    INTERACTIONS("Interactions"),
    BOOKS("Books");

    public final String category;

    Categories(String category) {
        this.category = category;
    }

    public String getName(){
        return category;
    }
}


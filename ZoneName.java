package game;

public enum ZoneName {
    BIB("Bibliotheque"),
    BDE("Bureau des etudiants"),
    QA("Quartier administratif"),
    HI("Halles industrielles"),
    HS("Halle sportive");
    private String name;
    private ZoneName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

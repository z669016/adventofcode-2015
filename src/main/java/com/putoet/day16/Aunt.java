package com.putoet.day16;

public class Aunt {
    private final String name;
    private final int number;

    private int children;
    private int cats;
    private int samoyeds;
    private int pomeranians;
    private int akitas;
    private int vizslas;
    private int goldfish;
    private int trees;
    private int cars;
    private int perfumes;

    private boolean childrenKnown;
    private boolean catsKnown;
    private boolean samoyedsKnown;
    private boolean pomeraniansKnown;
    private boolean akitasKnown;
    private boolean vizslasKnown;
    private boolean goldfishKnown;
    private boolean treesKnown;
    private boolean carsKnown;
    private boolean perfumesKnown;

    public static Aunt fromDescription(String description) {
        assert description != null;

        final String[] words = description.substring(0, description.toLowerCase().indexOf(':')).split(" ");
        final Aunt aunt = new Aunt(words[0].trim(), Integer.parseInt(words[1].trim()));
        return setProperties(aunt,  description.substring(description.indexOf(':') + 2));
    }

    public static Aunt setProperties(Aunt aunt, String properties) {
        for (String property : properties.split(", ")) {
            aunt.setProperty(property);
        }

        return aunt;
    }

    public Aunt(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String name() { return name; }
    public int number() { return number; }

    public int children() { return children; }
    public int cats() { return cats; }
    public int samoyeds() { return samoyeds; }
    public int pomeranians() { return pomeranians; }
    public int akitas() { return akitas; }
    public int vizslas() { return vizslas; }
    public int goldfish() { return goldfish; }
    public int trees() { return trees; }
    public int cars() { return cars; }
    public int perfumes() { return perfumes; }

    public boolean childrenKnown() { return childrenKnown; }
    public boolean catsKnown() { return catsKnown; }
    public boolean samoyedsKnown() { return samoyedsKnown; }
    public boolean pomeraniansKnown() { return pomeraniansKnown; }
    public boolean akitasKnown() { return akitasKnown; }
    public boolean vizslasKnown() { return vizslasKnown; }
    public boolean goldfishKnown() { return goldfishKnown; }
    public boolean treesKnown() { return treesKnown; }
    public boolean carsKnown() { return carsKnown; }
    public boolean perfumesKnown() { return perfumesKnown; }

    public void setProperty(String propertyValue) {
        assert propertyValue != null;

        final String[] words = propertyValue.split(":");
        if (words.length != 2)
            throw new IllegalArgumentException("Improperly formatted property definition '" + propertyValue + "'");

        final String property = words[0].toLowerCase().trim();
        final int value = Integer.parseInt(words[1].trim());

        switch (property) {
            case "children":
                children = value;
                childrenKnown = true;
                break;
            case "cats":
                cats = value;
                catsKnown = true;
                break;
            case "samoyeds":
                samoyeds = value;
                samoyedsKnown = true;
                break;
            case "pomeranians":
                pomeranians = value;
                pomeraniansKnown = true;
                break;
            case "akitas":
                akitas = value;
                akitasKnown = true;
                break;
            case "vizslas":
                vizslas = value;
                vizslasKnown = true;
                break;
            case "goldfish":
                goldfish = value;
                goldfishKnown = true;
                break;
            case "trees":
                trees = value;
                treesKnown = true;
                break;
            case "cars":
                cars = value;
                carsKnown = true;
                break;
            case "perfumes":
                perfumes = value;
                perfumesKnown = true;
                break;
            default:
                throw new IllegalArgumentException("Invalid property description '" + propertyValue + "'");
        }
    }
}

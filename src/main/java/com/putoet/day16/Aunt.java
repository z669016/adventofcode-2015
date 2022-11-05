package com.putoet.day16;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;

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

    /**
     * Create an Aunt object from a description string with a possibly incomplete list of properties. The description
     * could look like "Sue 24: goldfish: 7, pomeranians: 9, akitas: 4". Correct formatting of the description is vital.
     * The description ia asserted to start with a name, followed by a space, a number and a semicolon
     * @param description String
     * @return Aunt with name, number and the provided properties set
     */
    public static Aunt fromDescription(String description) {
        assert description != null && description.length() > 2 && description.indexOf(':') > 0;

        final String[] words = description.substring(0, description.toLowerCase().indexOf(':')).split(" ");
        if (words.length != 2)
            throw new IllegalArgumentException("Improperly formatted aunt decription '" + description + "'");

        final Aunt aunt = new Aunt(words[0].trim(), Integer.parseInt(words[1].trim()));
        return setProperties(aunt,  description.substring(description.indexOf(':') + 2));
    }

    /**
     * Set the provided properties for the aunt. Properties is a comma seperated list with a property name, a
     * semicolon and an integer property value, for example "goldfish: 7, pomeranians: 9, akitas: 4".
     * @param aunt Aunt
     * @param properties Comma seperated Property descriptions
     * @return Aunt the updated aunt object
     */
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

    protected void setProperty(String propertyValue) {
        assert propertyValue != null;

        final String[] words = propertyValue.split(":");
        if (words.length != 2)
            throw new IllegalArgumentException("Improperly formatted property definition '" + propertyValue + "'");

        final String property = words[0].toLowerCase().trim();
        final int value = Integer.parseInt(words[1].trim());

        switch (property) {
            case "children" -> {
                children = value;
                childrenKnown = true;
            }
            case "cats" -> {
                cats = value;
                catsKnown = true;
            }
            case "samoyeds" -> {
                samoyeds = value;
                samoyedsKnown = true;
            }
            case "pomeranians" -> {
                pomeranians = value;
                pomeraniansKnown = true;
            }
            case "akitas" -> {
                akitas = value;
                akitasKnown = true;
            }
            case "vizslas" -> {
                vizslas = value;
                vizslasKnown = true;
            }
            case "goldfish" -> {
                goldfish = value;
                goldfishKnown = true;
            }
            case "trees" -> {
                trees = value;
                treesKnown = true;
            }
            case "cars" -> {
                cars = value;
                carsKnown = true;
            }
            case "perfumes" -> {
                perfumes = value;
                perfumesKnown = true;
            }
            default -> throw new IllegalArgumentException("Invalid property description '" + propertyValue + "'");
        }
    }

    /**
     * Compare all properties (excluding name and number) to see if the aunts could be the same (match). If a property
     * on 'this' is unknown it means it's not 0 but the actual value is unknown. So in that case there could be a match
     * if for the unknown aunt (for which all properties are known) the value of that property is non 0. If a property
     * of 'this' is known, it's value must match the value of the unknown aunt.
     *
     * @param unknownAunt Aunt with all properties known
     * @return boolean indicating there could be a match (at least no violation of properties comparison rules)
     */
    public boolean couldMatch(Aunt unknownAunt) {
        final BiPredicate<Integer, Integer> equal = Objects::equals;
        final BiPredicate<Integer, Integer> greaterThan = Objects::equals;
        final BiPredicate<Integer, Integer> lessThan = Objects::equals;

        return match(unknownAunt, equal, greaterThan, lessThan);
    }

    public boolean retroencabulatorMatch(Aunt unknownAunt) {
        final BiPredicate<Integer, Integer> equal = Objects::equals;
        final BiPredicate<Integer, Integer> greaterThan = (v1, v2) -> v1 > v2;
        final BiPredicate<Integer, Integer> lessThan = (v1, v2) -> v1 < v2;

        return match(unknownAunt, equal, greaterThan, lessThan);
    }

    private boolean match(Aunt unknownAunt, BiPredicate<Integer, Integer> equal, BiPredicate<Integer, Integer> greaterThan, BiPredicate<Integer, Integer> fewerThan) {
        return matchProperty(this, unknownAunt, Aunt::childrenKnown, Aunt::children, equal)
                && matchProperty(this, unknownAunt, Aunt::catsKnown, Aunt::cats, greaterThan)
                && matchProperty(this, unknownAunt, Aunt::samoyedsKnown, Aunt::samoyeds, equal)
                && matchProperty(this, unknownAunt, Aunt::pomeraniansKnown, Aunt::pomeranians, fewerThan)
                && matchProperty(this, unknownAunt, Aunt::akitasKnown, Aunt::akitas, equal)
                && matchProperty(this, unknownAunt, Aunt::vizslasKnown, Aunt::vizslas, equal)
                && matchProperty(this, unknownAunt, Aunt::goldfishKnown, Aunt::goldfish, fewerThan)
                && matchProperty(this, unknownAunt, Aunt::treesKnown, Aunt::trees, greaterThan)
                && matchProperty(this, unknownAunt, Aunt::carsKnown, Aunt::cars, equal)
                && matchProperty(this, unknownAunt, Aunt::perfumesKnown, Aunt::perfumes, equal);
    }

    private boolean matchProperty(Aunt knownAunt, Aunt unknownAunt, Function<Aunt, Boolean> known, Function<Aunt,Integer> value, BiPredicate<Integer,Integer> compare) {
        if (known.apply(knownAunt)) {
            return compare.test(value.apply(knownAunt), (value.apply(unknownAunt)));
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aunt{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", children=" + children +
                ", cats=" + cats +
                ", samoyeds=" + samoyeds +
                ", pomeranians=" + pomeranians +
                ", akitas=" + akitas +
                ", vizslas=" + vizslas +
                ", goldfish=" + goldfish +
                ", trees=" + trees +
                ", cars=" + cars +
                ", perfumes=" + perfumes +
                ", childrenKnown=" + childrenKnown +
                ", catsKnown=" + catsKnown +
                ", samoyedsKnown=" + samoyedsKnown +
                ", pomeraniansKnown=" + pomeraniansKnown +
                ", akitasKnown=" + akitasKnown +
                ", vizslasKnown=" + vizslasKnown +
                ", goldfishKnown=" + goldfishKnown +
                ", treesKnown=" + treesKnown +
                ", carsKnown=" + carsKnown +
                ", perfumesKnown=" + perfumesKnown +
                '}';
    }
}

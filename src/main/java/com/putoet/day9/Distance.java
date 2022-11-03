package com.putoet.day9;

import java.util.Objects;

public class Distance {
    private final String from;
    private final String to;
    private final int distance;

    public Distance(String from, String to, int distance) {
        assert from != null && from.length() > 0;
        assert to != null && to.length() > 0;
        assert distance > 0;

        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    /**
     * Create a Distance instance of a distance description like "Faerun to Tristram = 65"
     *
     * @param description A String containing a well formatted description
     * @return a new valid Distance object for the description
     */
    public static Distance fromDescription(String description) {
        assert description != null;

        final String[] parts = description.split(" ");
        if (parts.length != 5)
            throw new IllegalArgumentException("Invalid description '" + description + "'");

        return new Distance(parts[0], parts[2], Integer.parseInt(parts[4]));
    }

    public String from() {
        return from;
    }

    public String to() {
        return to;
    }

    public int distance() {
        return distance;
    }

    public boolean between(String city1, String city2) {
        assert city1 != null;
        assert city2 != null;

        return (from.equalsIgnoreCase(city1) && to.equalsIgnoreCase(city2)) || ((from.equalsIgnoreCase(city2) && to.equalsIgnoreCase(city1)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Distance distance1)) return false;
        return distance == distance1.distance &&
                from.equals(distance1.from) &&
                to.equals(distance1.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, distance);
    }

    @Override
    public String toString() {
        return String.format("%s to %s = %d", from, to, distance);
    }
}

package com.putoet.day9;

record Distance(String from, String to, int distance) {

    public Distance{
        assert from != null && !from.isEmpty();
        assert to != null && !to.isEmpty();
        assert distance > 0;
    }

    public static Distance of(String description) {
        assert description != null;

        final var parts = description.split(" ");
        if (parts.length != 5)
            throw new IllegalArgumentException("Invalid description '" + description + "'");

        return new Distance(parts[0], parts[2], Integer.parseInt(parts[4]));
    }

    public boolean between(String city1, String city2) {
        assert city1 != null;
        assert city2 != null;

        return (from.equalsIgnoreCase(city1) && to.equalsIgnoreCase(city2)) || ((from.equalsIgnoreCase(city2) && to.equalsIgnoreCase(city1)));
    }
}

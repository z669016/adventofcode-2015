package com.putoet.day9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DistanceMapTest {
    private static final List<String> list = List.of(
            "Faerun to Tristram = 65",
            "Faerun to Arbre = 149",
            "Tristram to Tambi = 63",
            "Tambi to Norrath = 68",
            "Norrath to Snowdin = 8",
            "Snowdin to Straylight = 101",
            "Straylight to AlphaCentauri = 107",
            "AlphaCentauri to Arbre = 46",
            "Tristram to Arbre = 11");
    private DistanceMap distanceMap = null;

    @Test
    void distance() {
        assertEquals(65, distanceMap.distance("Faerun", "Tristram").orElseThrow());
        assertEquals(63, distanceMap.distance("Tristram", "Tambi").orElseThrow());
        assertEquals(107, distanceMap.distance("AlphaCentauri", "Straylight").orElseThrow());

        assertFalse(distanceMap.distance("Tambi", "Tambi").isPresent());
    }

    @Test
    public void cities() {
        assertEquals(Set.of("Faerun", "Tristram", "Tambi", "Norrath", "Snowdin", "Straylight", "AlphaCentauri", "Arbre"), distanceMap.cities());
    }

    @Test
    public void routeDistance() {
        final var route = List.of("Faerun", "Tristram", "Arbre", "Faerun");
        assertEquals(65 + 11 + 149, distanceMap.routeDistance(route));
    }

    @Test
    public void invallidRouteDistance() {
        final var route = List.of("Faerun", "Tristram", "Arbre", "Bla", "Faerun");
        assertThrows(IllegalArgumentException.class, () -> distanceMap.routeDistance(route));
    }

    @BeforeEach
    public void fromDescriptions() {
        distanceMap = DistanceMap.fromDescriptions(list);
    }
}
package com.putoet.day13;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.paukov.combinatorics3.Generator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HappinessMapTest {
    private HappinessMap map = null;

    @BeforeEach
    void setup() {
        final var descriptions = ResourceLines.list("/day13.txt");
        map = HappinessMap.of(descriptions);
    }

    @Test
    void persons() {
        final var names = map.persons();
        assertEquals(Set.of("Alice", "Bob", "Carol", "David", "Eric", "Frank", "George", "Mallory"), names);
    }

    @Test
    void hapiness() {
        final var seatingPlan = List.of("Alice", "Bob", "Carol", "David", "Eric", "Frank", "George", "Mallory");
        // Mallory (39) - Alice - Bob (2)
        // Alice (40) - Bob - Carol (-61)
        // Bob (-99) - Carol - David (-51)
        // Carol (-65) - David - Eric (-18)
        // David (51) - Eric - Frank (21)
        // Eric (97) - Frank - George (-9)
        // Frank (18) - George - Mallory (97)
        // George (-89) - Mallory - Alice (92)
        // 39 + 2 + 40 - 61 - 99 - 51 -65 -18 + 51 + 21 + 97 - 9 + 18 + 97 - 89 + 92
        assertEquals((39 + 2 + 40 - 61 - 99 - 51 - 65 - 18 + 51 + 21 + 97 - 9 + 18 + 97 - 89 + 92), map.happiness(seatingPlan));
    }

    @Test
    void fromList() {
        final var seatingPlan = new String[] {"Carol", "David", "Alice", "Bob"};
        final var descriptions = List.of(
                "Alice would gain 54 happiness units by sitting next to Bob.",
                "Alice would lose 79 happiness units by sitting next to Carol.",
                "Alice would lose 2 happiness units by sitting next to David.",
                "Bob would gain 83 happiness units by sitting next to Alice.",
                "Bob would lose 7 happiness units by sitting next to Carol.",
                "Bob would lose 63 happiness units by sitting next to David.",
                "Carol would lose 62 happiness units by sitting next to Alice.",
                "Carol would gain 60 happiness units by sitting next to Bob.",
                "Carol would gain 55 happiness units by sitting next to David.",
                "David would gain 46 happiness units by sitting next to Alice.",
                "David would lose 7 happiness units by sitting next to Bob.",
                "David would gain 41 happiness units by sitting next to Carol.");
        final var map = HappinessMap.of(descriptions);
        final var optimized =  Generator.permutation(seatingPlan).simple().stream().map(map::happiness)
                .mapToInt(i -> i)
                .max()
                .orElseThrow();

        assertEquals(330, optimized);
        assertEquals(330, map.happiness(Arrays.stream(seatingPlan).toList()));
    }

    @Test
    void fromListIncludingMyself() {
        final var mapIncludingMyself = HappinessMap.ofIncludingMyself(ResourceLines.list("/day13.txt"));

        final var persons = new HashSet<>(mapIncludingMyself.persons());
        persons.remove(HappinessMap.MYSELF);

        final Person myself = mapIncludingMyself.person(HappinessMap.MYSELF);
        persons.forEach( name -> {
            final Person neighbour = mapIncludingMyself.person(name);
            assertEquals(0, myself.happiness(neighbour));
            assertEquals(0, neighbour.happiness(myself));
        });
    }
}
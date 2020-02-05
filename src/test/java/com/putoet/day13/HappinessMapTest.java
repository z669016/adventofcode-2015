package com.putoet.day13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Permutator;
import utilities.ResourceLines;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HappinessMapTest {
    private HappinessMap map = null;

    @BeforeEach
    public void setup() {
        final List<String> descriptions = ResourceLines.list("/day13.txt");
        map = HappinessMap.fromList(descriptions);
    }

    @Test
    public void persons() {
        final Set<String> names = map.persons();
        assertEquals(Set.of("Alice", "Bob", "Carol", "David", "Eric", "Frank", "George", "Mallory"), names);
    }

    @Test
    public void hapiness() {
        List<String> seatingPlan = List.of("Alice", "Bob", "Carol", "David", "Eric", "Frank", "George", "Mallory");
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
    public void fromList() {
        final List<String> seatingPlan = List.of("Carol", "David", "Alice", "Bob");
        final List<String> descriptions = List.of(
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
        final HappinessMap map = HappinessMap.fromList(descriptions);

        final Permutator<String> permutator = new Permutator<>();
        final List<List<String>> permutations = permutator.permute(seatingPlan);
        int optimized =  permutations.stream().map(map::happiness)
                .mapToInt(i -> i)
                .max().getAsInt();

        assertEquals(330, optimized);
        assertEquals(330, map.happiness(seatingPlan));
    }

}
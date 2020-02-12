package utilities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PermutatorTest {

    @Test
    void permute() {
        final Permutator<Integer> permutator = new Permutator<>();
        final List<List<Integer>> list = permutator.permute(List.of(1, 2, 3));
        assertEquals(List.of(
                List.of(1, 2, 3),
                List.of(1, 3, 2),
                List.of(2, 1, 3),
                List.of(2, 3, 1),
                List.of(3, 1, 2),
                List.of(3, 2, 1)), list);
    }

    @Test
    void combinations() {
        final Permutator<Integer> permutator = new Permutator<>();
        final List<Integer> list = List.of(1, 2, 3, 4, 5);
        final List<List<Integer>> combinations = permutator.combinations(list);
        assertEquals(List.of(
                List.of(1, 2), List.of(1, 3), List.of(1, 4), List.of(1, 5),
                List.of(2, 3), List.of(2, 4), List.of(2, 5),
                List.of(3, 4), List.of(3, 5),
                List.of(4, 5)
        ), combinations);
    }
}
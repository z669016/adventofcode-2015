package utilities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntListUtilsTest {

    @Test
    void exludeMax() {
        assertEquals(List.of(1, 2, 3), IntListUtils.exludeMax(List.of(1, 2, 3, 4)));
        assertEquals(List.of(3, 3), IntListUtils.exludeMax(List.of(3, 3, 3)));
    }
}
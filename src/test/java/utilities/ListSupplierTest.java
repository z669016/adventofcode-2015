package utilities;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class ListSupplierTest {

    @Test
    void get() {
        final List<Integer> list = List.of(1, 2, 3);
        final Supplier<Integer> supplier = new ListSupplier<>(list);

        assertEquals(1, supplier.get());
        assertEquals(2, supplier.get());
        assertEquals(3, supplier.get());
        assertNull(supplier.get());
    }
}
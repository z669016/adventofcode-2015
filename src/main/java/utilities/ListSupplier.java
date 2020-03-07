package utilities;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class ListSupplier<T> implements Supplier<T> {
    private final Iterator<T> iterator;

    public ListSupplier(List<T> list) {
        this.iterator = list.iterator();
    }

    @Override
    public T get() {
        return iterator.hasNext() ? iterator.next() : null;
    }
}

package utilities;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IntListUtils {
    public static List<Integer> exludeMax(List<Integer> list) {
        assert list != null;
        assert list.size() > 0;

        if (list.size() == 1)
            return List.of();

        final int max = list.stream().mapToInt(i -> i).max().getAsInt();
        final Predicate<Integer> filterOnce = new Predicate<Integer>() {
            boolean filtered = false;

            @Override
            public boolean test(Integer i) {
                if (filtered)
                    return true;

                if (i == max) {
                    filtered = true;
                    return false;
                }

                return true;
            }
        };

        return list.stream().filter(filterOnce).collect(Collectors.toList());
    }
}

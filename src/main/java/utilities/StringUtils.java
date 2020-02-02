package utilities;

import java.util.List;

public class StringUtils {
    private static final String VOWELS = "aeiou";

    public static long vowels(String text) {
        assert text != null;

        return text.toLowerCase().chars().filter(c -> VOWELS.contains("" + (char) c)).count();
    }

    public static boolean containsDouble(String text) {
        assert text != null;

        if (text.length() < 2)
            return false;

        int idx = 0;
        while (idx < text.length() - 1)
            if (text.charAt(idx) == text.charAt(idx + 1))
                return true;
            else
                idx++;

        return false;
    }

    public static boolean containsAny(String text, List<String> any) {
        assert text != null;

        if (any.size() == 0)
            return false;

        for (String anyElement : any)
            if (text.contains(anyElement))
                return true;

        return false;
    }

    public static boolean containsPairWithoutOverlap(String text) {
        assert text != null;

        if (text.length() < 4)
            return false;

        int idx = 0;
        while (idx < text.length() - 1) {
            final String pair = text.substring(idx, idx + 2);
            final String rest = text.substring(idx + 2);
            if (rest.contains(pair))
                return true;

            idx++;
        }

        return false;
    }

    public static boolean containsSeperatedDouble(String text) {
        assert text != null;

        if (text.length() < 3)
            return false;

        int idx = 0;
        while (idx < text.length() - 2)
            if (text.charAt(idx) == text.charAt(idx + 2))
                return true;
            else
                idx++;

        return false;
    }


}

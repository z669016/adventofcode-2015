package utilities;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static final String VOWELS = "aeiou";

    /**
     * Encode the text as String, which means escape any double quotes in the text, escape any backslash,
     * and add double quotes around the text.
     *
     * @param text The String to encode
     * @return String the encoded text
     */
    public static String encode(String text) {
        text = text.replace("\\", "\\\\");
        text = text.replace("\"", "\\\"");
        return "\"" + text + "\"";
    }

    /**
     * If the string is a text between double-quotes, it removes the starting and ending double-quote,
     * otherwose it returns the text unchanged.
     * @param text A String to remove the double quotes from
     * @return String the "un-quoted" text
     */
    public static String removeQuotes(String text) {
        assert text != null;

        if (text.length() < 2)
            return text;

        if (text.startsWith("\"") && text.endsWith("\""))
            return text.substring(1, text.length() - 1);

        return text;
    }

    /**
     * If a text contains escaped-double quotes (\") those will be replaced with a single double quote. Next, andy
     * hex-code characters will be replaced with the actual character.
     * @param text The String to be sanitized
     * @return String the sanitized text
     */
    public static String sanitize(String text) {
        assert text != null;

        return sanitizeSlash(sanitizeHexCodes(sanitizeQuotes(removeQuotes(text))));
    }

    private static String sanitizeQuotes(String text) {
        return text.replace("\\\"", "\"");
    }

    private static String sanitizeSlash(String text) {
        return text.replace("\\\\", "\\");
    }

    private static final Pattern PATTERN = Pattern.compile("(.*)(\\\\x[0-9a-fA-F][0-9a-fA-F])(.*)");
    private static String sanitizeHexCodes(String text) {
        Matcher matcher = PATTERN.matcher(text);
        while (matcher.matches()) {
            text = matcher.group(1) + (char) Integer.parseInt(matcher.group(2).substring(2), 16) + matcher.group(3);
            matcher = PATTERN.matcher(text);
        }

        return text;
    }

    /**
     * Counts the number of vowels (aeiou) in the provided text, if one vowel occurs multiple times,
     * it is counted separately each time.
     * @param text A String to check
     * @return int The number of vowels in the text
     */
    public static long vowels(String text) {
        assert text != null;

        return text.toLowerCase().chars().filter(c -> VOWELS.contains("" + (char) c)).count();
    }

    /**
     * Checks if the text contains two consecutive identical characters, e.g. abccde (double 'c')
     * @param text A String to check
     * @return boolean
     */
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

    /**
     * Returns true if the provided text contains any of the strings provided in the list
     * @param text A String to check
     * @param any A List of Strings to be searched for
     * @return boolean
     */
    public static boolean containsAny(String text, List<String> any) {
        assert text != null;

        if (any.size() == 0)
            return false;

        for (String anyElement : any)
            if (text.contains(anyElement))
                return true;

        return false;
    }

    /**
     * Checks if a string contaisn two identical pairs without overlap, e.g. "aabcdaa" (contains pair "aa"),
     * but not "baaab" (contains two pairs "aa" which one overlapping character"
     *
     * @param text A String to test
     * @return boolean
     */
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

    /**
     * Checks if a string has two identical characters seperated by no more than one other character
     * e.g. 'axa' (double a separated by one x), or even 'aaa' (double a separated by another a)
     * @param text A String to test
     * @return boolean
     */
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

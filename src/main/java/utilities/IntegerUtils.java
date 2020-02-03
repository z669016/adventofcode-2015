package utilities;

public class IntegerUtils {
    public static Integer unsigned16Bit(Integer i) {
        assert i != null;
        return i & 0xffff;
    }
}

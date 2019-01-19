package joker.persona.ngrocken.kngdancetrack.util;

public class StringUtils {

    public static boolean isEmpty(String string) {
        if(string == null) {
            return true;
        }
        if(string.trim().length() == 0) {
            return true;
        }
        return false;
    }

}

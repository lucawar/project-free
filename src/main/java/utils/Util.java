package utils;

public class Util {

    // Trasforma in maiuscolo la prima lettera della stringa
    public static String capitalizeFirstLetter(String str) {
        assert str != null;
        if (str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}

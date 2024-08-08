package utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringFormatter {

    // Trasforma in maiuscolo la prima lettera della stringa
    public static String capitalizeFirstLetter(String input) {
        if (input != null && !input.isEmpty()) {
            return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }
        return null;
    }

    // Formatta nome o cognome (lUCa = Luca)
    public static String formatNameOrSurname(String input) {
        if (input != null && !input.isEmpty()) {
            return Arrays.stream(input.split(" "))
                    .filter(part -> !part.isEmpty())
                    .map(StringFormatter::capitalizeFirstLetter)
                    .collect(Collectors.joining(" "));
        }
        return null;
    }
}

package helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    public static boolean isStringRegular(String str, Pattern pattern){
        return pattern.matcher(str).find();
    }
}

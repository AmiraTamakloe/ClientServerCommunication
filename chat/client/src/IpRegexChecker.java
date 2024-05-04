//We're doing Regex to check if the Ip is valid cuz we're cool and hip
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpRegexChecker {
    private static final String IPV4_PATTERN =
            "(\\d{1,2}|(0|1)\\"
                    + "d{2}|2[0-4]\\d|25[0-5])" +"\\." +
                    "(\\d{1,2}|(0|1)\\"
                    + "d{2}|2[0-4]\\d|25[0-5])"+"\\." +
                    "(\\d{1,2}|(0|1)\\"
                    + "d{2}|2[0-4]\\d|25[0-5])" +"\\." +
                    "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])$";

    private static final Pattern pattern = Pattern.compile(IPV4_PATTERN);
    public boolean isValid(String ipToBeCheck) {
        Matcher matcher = pattern.matcher(ipToBeCheck);
        return matcher.matches();
    }
}
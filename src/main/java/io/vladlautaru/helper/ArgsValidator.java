package io.vladlautaru.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsValidator {
    public static boolean validateIpv4Address(String address) {
        String IPV4_PATTERN = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";
        Pattern pattern = Pattern.compile(IPV4_PATTERN);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    public static boolean validateDestPort(int port) {
        int FIRST_PORT = 1024;
        int LAST_PORT = 65536;
        return port >= FIRST_PORT && port <= LAST_PORT;
    }
}

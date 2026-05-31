package io.vladlautaru.helper;

import java.util.Map;

public class ArgsProvider {
    public static String getHost(Map<String, String> args) throws IllegalArgumentException {
        if (!args.containsKey("-h")) {
            return "127.0.0.1";
        }

        String host = args.get("-h");

        if (!ArgsValidator.validateIpv4Address(host)) {
            throw new IllegalArgumentException("error: invalid host address provided");
        }

        return host;
    }
}

package io.vladlautaru.helper;

import java.util.Map;

public class ArgsProvider {
    public static String getHost(Map<String, String> args) throws IllegalArgumentException {
        if (!args.containsKey("-h")) {
            System.out.println("warning: no host argument provided, defaulting to 127.0.0.1");
            return "127.0.0.1";
        }

        String host = args.get("-h");

        if (!ArgsValidator.validateIpv4Address(host)) {
            throw new IllegalArgumentException("error: invalid host address provided");
        }

        return host;
    }

    public static int getPort(Map<String, String> args) throws IllegalArgumentException {
        if (!args.containsKey("-p")) {
            System.out.println("warning: no port argument provided, defaulting to 2000");
            return 2000;
        }

        int port;
        try {
            port = Integer.parseInt(args.get("-p"));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("error: port must be an integer between 1024 and 65536");
        }

        if (!ArgsValidator.validateDestPort(port)) {
            throw new IllegalArgumentException("error: port must be an integer between 1024 and 65536");
        }

        return port;
    }
}

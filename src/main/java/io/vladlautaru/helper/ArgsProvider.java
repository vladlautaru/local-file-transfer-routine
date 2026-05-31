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

    public static int getTimeoutInMilliseconds(Map<String, String> args) throws IllegalArgumentException {
        if (args.containsKey("-s") && args.containsKey("-m")) {
            throw new IllegalArgumentException("error: cannot specify timeout in both seconds and minutes");
        }

        if (args.containsKey("-s")) {
            int timeoutSeconds;
            try {
                timeoutSeconds = Integer.parseInt(args.get("-s"));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("error: timeout (seconds) must be an integer between 60 and 2147460");
            }

            if (!ArgsValidator.validateTimeoutInSeconds(timeoutSeconds)) {
                throw new IllegalArgumentException("error: timeout (seconds) must be an integer between 60 and 2147460");
            }

            int MILLISECONDS_IN_A_SECOND = 1000;

            System.out.println("Timeout set to " + timeoutSeconds + " seconds");

            return timeoutSeconds * MILLISECONDS_IN_A_SECOND;
        }

        int MILLISECONDS_IN_A_MINUTE = 60000;

        if (args.containsKey("-m")) {
            int timeoutMinutes;
            try {
                timeoutMinutes = Integer.parseInt(args.get("-m"));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("error: timeout (minutes) must be an integer between 1 and 35791");
            }

            if (!ArgsValidator.validateTimeoutInMinutes(timeoutMinutes)) {
                throw new IllegalArgumentException("error: timeout (minutes) must be an integer between 1 and 35791");
            }

            System.out.println("Timeout set to " + timeoutMinutes + " minutes");

            return timeoutMinutes * MILLISECONDS_IN_A_MINUTE;
        }

        System.out.println("warning: no timeout provided, defaulting to 1 minute");
        return MILLISECONDS_IN_A_MINUTE;
    }
}

package io.vladlautaru.client;

import io.vladlautaru.helper.ArgsParser;
import io.vladlautaru.helper.ArgsValidator;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

public class ClientMain {
    private static String getHost(Map<String, String> args) throws IllegalArgumentException {
        if (!args.containsKey("-h")) {
            return "127.0.0.1";
        }

        String host = args.get("-h");

        if (!ArgsValidator.validateIpv4Address(host)) {
            throw new IllegalArgumentException("error: invalid host address provided");
        }

        return host;
    }

    public static void main(String[] args) {
        Map<String, String> argsMap;

        try {
            argsMap = ArgsParser.parse(args);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("This is the client program");

        String host = "127.0.0.1";

        try {
            host = getHost(argsMap);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try (Socket socket = new Socket(host, 2000)){
            System.out.println("Client socket running at " +
                    socket.getLocalAddress() +
                    " on port " +
                    socket.getLocalPort()
            );
        } catch (UnknownHostException e) {
            System.out.println("error: unknown host " + host);
        } catch (IOException e) {
            System.out.println("error: client socket: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("error: invalid port");
        }
    }
}

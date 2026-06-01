package io.vladlautaru.client;

import io.vladlautaru.helper.ArgsParser;
import io.vladlautaru.helper.ArgsProvider;

import java.util.Map;

public class ClientMain {
    public static void main(String[] args) {
        String host;
        int port;

        try {
            Map<String, String> argsMap = ArgsParser.parse(args);
            host = ArgsProvider.getHost(argsMap);
            port = ArgsProvider.getPort(argsMap);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        ClientSocket socket;

        try {
            socket = new ClientSocket(host, port);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            socket.closeSocket();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}

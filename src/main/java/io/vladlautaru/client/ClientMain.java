package io.vladlautaru.client;

import io.vladlautaru.helper.ArgsParser;
import io.vladlautaru.helper.ArgsProvider;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

public class ClientMain {
    public static void main(String[] args) {
        Map<String, String> argsMap;

        try {
            argsMap = ArgsParser.parse(args);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String host;
        try {
            host = ArgsProvider.getHost(argsMap);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        int port;
        try {
            port = ArgsProvider.getPort(argsMap);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        try (Socket socket = new Socket(host, port)){
            String confirmConnectionMessage =
                    String.format(
                            "Server at %s:%d found. Client started on port %d",
                            host,
                            port,
                            socket.getLocalPort()
                    );

            System.out.println(confirmConnectionMessage);
        } catch (UnknownHostException e) {
            System.out.println("error: unknown host " + host);
        } catch (IOException e) {
            String error = String.format(
                    "error: couldn't connect to server at %s:%d",
                    host,
                    port
            );

            System.out.println(error);
        } catch (IllegalArgumentException e) {
            System.out.println("error: invalid port");
        }
    }
}

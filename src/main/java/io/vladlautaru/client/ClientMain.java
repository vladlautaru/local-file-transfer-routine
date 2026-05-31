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

        System.out.println("This is the client program");

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

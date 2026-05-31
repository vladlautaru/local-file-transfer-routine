package io.vladlautaru.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class ClientMain {
    private static String getHost(String[] args) throws IllegalArgumentException{
        List<String> argsList = Arrays.asList(args);
        int flagIndex = argsList.indexOf("-h");

        if (flagIndex == -1) {
            return "127.0.0.1";
        }

        int hostIndex = flagIndex + 1;
        if (hostIndex >= argsList.size()) {
            throw new IllegalArgumentException("error: invalid host address provided");
        }

        return argsList.get(hostIndex);
    }

    public static void main(String[] args) {
        System.out.println("This is the client program");

        String host = "127.0.0.1";

        try {
            host = getHost(args);
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

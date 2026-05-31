package io.vladlautaru.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        System.out.println("This is the server program");

        try (ServerSocket serverSocket = new ServerSocket(2000)) {
            System.out.println("Awaiting connection...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected to client socket at " +
                    clientSocket.getInetAddress() +
                    " on port " +
                    clientSocket.getLocalPort()
            );
        } catch (IOException e) {
            System.out.println("error: server socket: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("error: invalid port");
        }
    }
}

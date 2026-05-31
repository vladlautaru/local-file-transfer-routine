package io.vladlautaru.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerMain {
    public static void main(String[] args) {
        System.out.println("This is the server program");

        int timeout = 60000;

        try (ServerSocket serverSocket = new ServerSocket(2000)) {
            serverSocket.setSoTimeout(timeout);
            System.out.println("Timeout set to " + (timeout / 60000) + " minute(s).");

            System.out.println("Awaiting client connection...");
            Socket clientSocket = serverSocket.accept();

            System.out.println("Connected to client socket at " +
                    clientSocket.getInetAddress() +
                    " on port " +
                    clientSocket.getLocalPort()
            );
        } catch (SocketTimeoutException e) {
            System.out.println("Server socket timed out.");
        } catch (IOException e) {
            System.out.println("error: server socket: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("error: invalid port");
        }
    }
}

package io.vladlautaru.server;

import io.vladlautaru.helper.ArgsParser;
import io.vladlautaru.helper.ArgsProvider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Map;

public class ServerMain {
    public static void main(String[] args) {
        Map<String, String> argsMap;

        try {
            argsMap = ArgsParser.parse(args);
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

        int timeout;
        try {
            timeout = ArgsProvider.getTimeoutInMilliseconds(argsMap);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server running on port " + port);
            serverSocket.setSoTimeout(timeout);

            System.out.print("Awaiting client connection...");
            Socket clientSocket = serverSocket.accept();

            System.out.print(" Client connected.");
        } catch (SocketTimeoutException e) {
            System.out.println(" Server socket timed out.");
        } catch (IOException e) {
            System.out.println("error: couldn't start server socket on port " + port);
        } catch (IllegalArgumentException e) {
            System.out.println("error: invalid port");
        }
    }
}

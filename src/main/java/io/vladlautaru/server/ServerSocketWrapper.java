package io.vladlautaru.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerSocketWrapper {
    private final int port;
    private final int timeout;

    private final ServerSocket serverSocket;

    private Socket clientSocket;

    private boolean isActive = true;

    public ServerSocketWrapper(int port, int timeout) throws RuntimeException {
        this.port = port;
        this.timeout = timeout;

        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("Server running on port " + port);
            serverSocket.setSoTimeout(timeout);
        } catch (IOException e) {
            throw new RuntimeException("error: couldn't start server socket on port " + port);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("error: invalid port");
        }
    }

    public void acceptConnection() throws RuntimeException {
        try {
            System.out.print("Awaiting client connection...");
            this.clientSocket = serverSocket.accept();
            System.out.println(" Client connected.");
        } catch (SocketTimeoutException e) {
            this.isActive = false;
            throw new RuntimeException(" Server socket timed out.");
        } catch (IOException e) {
            throw new RuntimeException("error: couldn't start server socket on port " + port);
        }
    }

    public void closeClientSocket() throws RuntimeException {
        try {
            this.clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("error: couldn't close client socket");
        }
    }

    public void closeServerSocket() throws RuntimeException {
        try {
            this.isActive = false;
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("error: couldn't close client socket");
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

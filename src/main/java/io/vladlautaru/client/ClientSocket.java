package io.vladlautaru.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {
    private final String host;
    private final int port;

    private final Socket socket;

    public ClientSocket(String host, int port) throws RuntimeException {
        this.host = host;
        this.port = port;

        try {
            socket = new Socket(this.host, this.port);
            String confirmConnectionMessage =
                    String.format(
                            "Server at %s:%d found. Client started on port %d",
                            this.host,
                            this.port,
                            socket.getLocalPort()
                    );
            System.out.println(confirmConnectionMessage);
        } catch (UnknownHostException e) {
            throw new RuntimeException("error: unknown host " + this.host);
        } catch (IOException e) {
            String error = String.format(
                    "error: couldn't connect to server at %s:%d",
                    this.host,
                    this.port
            );
            throw new RuntimeException(error);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("error: invalid port " + this.port);
        }
    }

    public void closeSocket() throws RuntimeException {
        try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException("error: couldn't close client socket");
        }
    }
}

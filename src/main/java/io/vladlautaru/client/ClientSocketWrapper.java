package io.vladlautaru.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocketWrapper {
    private final Socket socket;

    public ClientSocketWrapper(String host, int port) throws RuntimeException {

        try {
            this.socket = new Socket(host, port);
            String confirmConnectionMessage =
                    String.format(
                            "Server at %s:%d found. Client started on port %d",
                            host,
                            port,
                            this.socket.getLocalPort()
                    );
            System.out.println(confirmConnectionMessage);
        } catch (UnknownHostException e) {
            throw new RuntimeException("error: unknown host " + host);
        } catch (IOException e) {
            String error = String.format(
                    "error: couldn't connect to server at %s:%d",
                    host,
                    port
            );
            throw new RuntimeException(error);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("error: invalid port " + port);
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

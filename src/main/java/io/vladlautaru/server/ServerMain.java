package io.vladlautaru.server;

import io.vladlautaru.helper.ArgsParser;
import io.vladlautaru.helper.ArgsProvider;

import java.util.Map;

public class ServerMain {
    public static void main(String[] args) {
        int port;
        int timeout;

        try {
            Map<String, String> argsMap = ArgsParser.parse(args);
            port = ArgsProvider.getPort(argsMap);
            timeout = ArgsProvider.getTimeoutInMilliseconds(argsMap);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        ServerSocketWrapper serverSocketWrapper;

        try {
            serverSocketWrapper = new ServerSocketWrapper(port, timeout);
            serverSocketWrapper.acceptConnection();
            serverSocketWrapper.closeClientSocket();
            serverSocketWrapper.closeServerSocket();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}

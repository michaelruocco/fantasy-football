package uk.co.mruoc.fantasyfootball.app;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;

public class PortProvider {

    public static int getFreePort() {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
package com.batsworks.core;

import com.batsworks.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerCore extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerCore.class);
    private int port;
    private String webroot;
    private ServerSocket server;

    public ServerListenerCore(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        server = new ServerSocket(this.port);
    }


    @Override
    public void run() {
        try {
            while (server.isBound() && !server.isClosed()) {
                Socket socket = server.accept();
                LOGGER.info(" * Connection Adress: " + socket.getInetAddress());

                HttpConnectorWorkerThread connectorWorkerThread = new HttpConnectorWorkerThread(socket);
                connectorWorkerThread.start();
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                server.close();
            } catch (Exception e) {
            }
        }
    }
}

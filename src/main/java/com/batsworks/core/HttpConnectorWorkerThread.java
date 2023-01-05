package com.batsworks.core;

import com.batsworks.exceptions.HttpConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectorWorkerThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectorWorkerThread.class);
    private Socket socket;

    public HttpConnectorWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            int _byte;

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            while ((_byte = inputStream.read()) >= 0) {
                System.out.println((char) _byte);
            }
            String html = "<html><head><title>simple teste</title><body><h1>Testando de novo servidor</h1></body></head></html>";

            String CRLF = "\n\r";
            String response =
                    "HTTP/1.1 200 OK" + CRLF +
                            "Content-Length" + html.getBytes().length + CRLF +
                            CRLF + html + CRLF + CRLF;

            outputStream.write(response.getBytes());


            try {
                Thread.sleep(5_000);
            } catch (Exception e) {
                LOGGER.info("Problem wirh connection", e);
            }
            LOGGER.info("Connection Processing finished");
        } catch (Exception e) {
            throw new HttpConfigurationException(e);
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (Exception e) {
            }
        }
    }
}

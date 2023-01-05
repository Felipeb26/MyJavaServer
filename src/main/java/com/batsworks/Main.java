package com.batsworks;

import com.batsworks.config.Configuration;
import com.batsworks.config.ConfigurationManager;
import com.batsworks.core.ServerListenerCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {

    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("System starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration config = ConfigurationManager.getInstance().getConfiguration();

        LOGGER.info("System using port: " + config.getPort());
        LOGGER.info("System using webroot: " + config.getWebroot());

        try {
            ServerListenerCore listenerCore = new ServerListenerCore(config.getPort(), config.getWebroot());
            listenerCore.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
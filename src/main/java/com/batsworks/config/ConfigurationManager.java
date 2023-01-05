package com.batsworks.config;


import com.batsworks.exceptions.HttpConfigurationException;
import com.batsworks.utils.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private static ConfigurationManager configurationManager;
    private static Configuration configuration;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (configurationManager == null) configurationManager = new ConfigurationManager();
        return configurationManager;
    }

    /*
     * Run application using a file provided
     */
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        while (true) {
            try {
                if (!((i = fileReader.read()) != -1)) break;
            } catch (IOException e) {
                throw new HttpConfigurationException(e);
            }
            sb.append((char) i);
        }
        JsonNode node = null;
        try {
            node = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error pasing the configuration file", e);
        }
        try {
            configuration = Json.fromJson(node, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Internal Error pasing the configuration file", e);
        }

    }

    /*
     * Run application using a file default
     */
    public Configuration getConfiguration() {
        if (configuration == null) {
            throw new HttpConfigurationException("No configuration set!");
        }
        return configuration;
    }
}

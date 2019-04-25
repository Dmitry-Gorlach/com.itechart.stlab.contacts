package com.itechart.stlab.contacts.manager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public enum ConfigurationManager {

    LOCALE_EN("Locale", new Locale("en", "US")),
    LOCALE_RU("Locale", new Locale("ru", "RU")),
    PAGE_PATH("/page/page"),
    DATABASE("/database/database"),
    PROPERTIES("/email/mail");


    private static final Logger LOGGER= LogManager.getLogger(ConfigurationManager.class);
    private ResourceBundle bundle;

    public ResourceBundle getBundle() {
        return bundle;
    }

    private ConfigurationManager(String baseName, Locale locale) {
        try{
            this.bundle = ResourceBundle.getBundle(baseName, locale);
        }catch (MissingResourceException e){
            LogManager.getLogger().log(Level.ERROR, "Properties not found");
            throw new RuntimeException("Properties file not found", e);
        }
    }

    private ConfigurationManager(String baseName){
        try{
            this.bundle = ResourceBundle.getBundle(baseName);
        } catch(MissingResourceException e) {
            LogManager.getLogger().log(Level.ERROR, "Properties file not found");
            throw new RuntimeException("Properties file not found", e);
        }
    }

    public String getProperty(String key) {
        return bundle.getString(key);
    }
}

/**
 * 
 * Author: xiaolu@coinport.com (Xiaolu Wu)
 */

package com.jbst.common;

public class ConfigLoader {
    private static JbstConfig config;

    static {
        config = ConfigFactory.getConfigFromResource(JbstConfig.class, "json/config.json");
    }

    public static JbstConfig getConfig() {
        return config;
    }

    public static JbstConfig getConfig(String fileName) {
        return ConfigFactory.getConfigFromResource(JbstConfig.class, fileName);
    }

    public static <T> T getConfig(Class<T> clazz, String fileName) {
        return ConfigFactory.getConfigFromResource(clazz, fileName);
    }
}

package com.shinian.pay.util;

/** Builds V免签 API URLs without discarding a configured HTTPS scheme or port. */
public final class ServerUrl {
    private ServerUrl() {
    }

    public static String baseUrl(String host) {
        String value = host == null ? "" : host.trim();
        while (value.endsWith("/")) {
            value = value.substring(0, value.length() - 1);
        }
        if (value.startsWith("http://") || value.startsWith("https://")) {
            return value;
        }
        if (value.endsWith(":5443")) {
            return "https://" + value;
        }
        return "http://" + value;
    }

    public static String api(String host, String pathAndQuery) {
        String path = pathAndQuery.startsWith("/") ? pathAndQuery : "/" + pathAndQuery;
        return baseUrl(host) + path;
    }

    /** Accepts legacy "host:port/key" and "https://host:port/key" data. */
    public static String[] parseConfig(String config) {
        String value = config == null ? "" : config.trim();
        int separator = value.lastIndexOf('/');
        if (separator <= 0 || separator == value.length() - 1) {
            return null;
        }
        String host = value.substring(0, separator).trim();
        String key = value.substring(separator + 1).trim();
        if (host.length() == 0 || key.length() == 0) {
            return null;
        }
        return new String[] {host, key};
    }
}

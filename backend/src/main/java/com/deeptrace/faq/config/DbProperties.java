package com.deeptrace.faq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.db")
public class DbProperties {

    private String provider = "local";
    private ConnectionProperties local = new ConnectionProperties();
    private ConnectionProperties rds = new ConnectionProperties();

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public ConnectionProperties getLocal() {
        return local;
    }

    public void setLocal(ConnectionProperties local) {
        this.local = local;
    }

    public ConnectionProperties getRds() {
        return rds;
    }

    public void setRds(ConnectionProperties rds) {
        this.rds = rds;
    }

    public static class ConnectionProperties {
        private String url;
        private String username;
        private String password;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}


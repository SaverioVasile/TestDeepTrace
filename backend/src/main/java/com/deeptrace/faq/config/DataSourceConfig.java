package com.deeptrace.faq.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Locale;

@Configuration
@EnableConfigurationProperties(DbProperties.class)
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(DbProperties dbProperties) {
        String provider = normalizeProvider(dbProperties.getProvider());
        DbProperties.ConnectionProperties selected = switch (provider) {
            case "local" -> dbProperties.getLocal();
            case "rds" -> dbProperties.getRds();
            default -> throw new IllegalArgumentException("Provider DB non supportato: " + provider + ". Usa 'local' o 'rds'.");
        };

        validate(provider, selected);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(selected.getUrl());
        dataSource.setUsername(selected.getUsername());
        dataSource.setPassword(selected.getPassword());
        return dataSource;
    }

    private String normalizeProvider(String provider) {
        if (provider == null || provider.isBlank()) {
            return "local";
        }
        return provider.toLowerCase(Locale.ROOT);
    }

    private void validate(String provider, DbProperties.ConnectionProperties selected) {
        if (selected == null) {
            throw new IllegalArgumentException("Configurazione DB mancante per provider: " + provider);
        }
        if (selected.getUrl() == null || selected.getUrl().isBlank()) {
            throw new IllegalArgumentException("URL database mancante per provider: " + provider);
        }
        if (selected.getUsername() == null || selected.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username database mancante per provider: " + provider);
        }
        if (selected.getPassword() == null || selected.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password database mancante per provider: " + provider);
        }
    }
}


package com.gpsolutions.marklogic_demo.configuration;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MarkLogicDemoApplicationConfiguration {
    @Value("${marklogic.host}")
    private String host;
    @Value("${marklogic.port}")
    private int port;
    @Value("${marklogic.database}")
    private String database;
    @Value("${marklogic.user}")
    private String username;
    @Value("${marklogic.password}")
    private String password;
    @Value("${view.start_year}")
    private int startYear;

    @Bean
    public DatabaseClient databaseClient() {
        return DatabaseClientFactory.newClient(host, port, digestAuthContext(), DatabaseClient.ConnectionType.GATEWAY);
    }

    private DatabaseClientFactory.DigestAuthContext digestAuthContext() {
        return new DatabaseClientFactory.DigestAuthContext(username, password);
    }

    @Bean
    public List<Integer> years() {
        final int currentYear = LocalDate.now(ZoneId.systemDefault()).getYear();
        final List<Integer> years = new ArrayList<>(currentYear - startYear + 1);
        for (int i = currentYear; i >= startYear; i--) {
            years.add(i);
        }
        return years;
    }
}

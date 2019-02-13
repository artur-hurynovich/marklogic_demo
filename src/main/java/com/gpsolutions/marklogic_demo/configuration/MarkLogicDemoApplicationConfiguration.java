package com.gpsolutions.marklogic_demo.configuration;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public DatabaseClient databaseClient() {
        return DatabaseClientFactory.newClient(host, port, digestAuthContext(), DatabaseClient.ConnectionType.GATEWAY);
    }

    private DatabaseClientFactory.DigestAuthContext digestAuthContext() {
        return new DatabaseClientFactory.DigestAuthContext(username, password);
    }
}

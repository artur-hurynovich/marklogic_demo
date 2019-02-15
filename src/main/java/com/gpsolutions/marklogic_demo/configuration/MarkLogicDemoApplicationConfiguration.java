package com.gpsolutions.marklogic_demo.configuration;

import com.gpsolutions.marklogic_demo.entity.AbstractEntity;
import com.gpsolutions.marklogic_demo.util.EntityClassField;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.pojo.PojoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    @SuppressWarnings("unchecked")
    @Bean
    public <E extends AbstractEntity, I extends Serializable> PojoRepository<E, I> pojoRepository(final DependencyDescriptor descriptor) {
        final DatabaseClient databaseClient = DatabaseClientFactory.newClient(host, port, database,
                digestAuthContext(), DatabaseClient.ConnectionType.GATEWAY);
        final Class entityClass = descriptor.getResolvableType().getGeneric(0).resolve();
        final Class idClass = descriptor.getResolvableType().getGeneric(1).resolve();
        return databaseClient.newPojoRepository(entityClass, idClass);
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

    /*@Bean
    public Set<EntityClassField> entityClassFieldSet() {
        final Set<EntityClassField> entityClassFieldSet = new LinkedHashSet<>();
        entityClassFieldSet.add(new EntityClassField("mark", "Mark", String.class));
        entityClassFieldSet.add(new EntityClassField("model", "Model", String.class));
        entityClassFieldSet.add(new EntityClassField("productionYear", "Production year", Integer.class));
        entityClassFieldSet.add(new EntityClassField("engineType", "Engine type", Enum.class));
        entityClassFieldSet.add(new EntityClassField())
        return entityClassFieldSet;
    }*/
}

package ru.tinkoff.edu.scrapper.environment;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.DirectoryResourceAccessor;
import org.junit.ClassRule;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;

@ContextConfiguration(classes = IntegrationEnvironment.IntegrationEnvironmentConfiguration.class)
public abstract class IntegrationEnvironment {

    @Configuration
    public static class IntegrationEnvironmentConfiguration {

        @Bean
        public DataSource testDataSource() {
            return DataSourceBuilder.create()
                    .url(POSTGRESQL_CONTAINER.getJdbcUrl())
                    .username(POSTGRESQL_CONTAINER.getUsername())
                    .password(POSTGRESQL_CONTAINER.getPassword())
                    .build();
        }

        @Bean
        public JdbcTemplate jdbcTemplate(DataSource dataSource) {
            return new JdbcTemplate(dataSource);
        }

        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new JdbcTransactionManager(dataSource);
        }
    }

    static protected final PostgreSQLContainer POSTGRESQL_CONTAINER;

    static {
        String username = "postgres";
        String password = "qwerty";
        String dbName = "postgres";
        Integer dbPort = 5432;
        POSTGRESQL_CONTAINER = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
                .withDatabaseName(dbName)
                .withUsername(username)
                .withPassword(password)
                .withExposedPorts(dbPort);
        POSTGRESQL_CONTAINER.start();
    }

    public static void runMigration() {
        try {
            String url = POSTGRESQL_CONTAINER.getJdbcUrl();
            String username = POSTGRESQL_CONTAINER.getUsername();
            String password = POSTGRESQL_CONTAINER.getPassword();
            Connection connection = DriverManager.getConnection(url, username, password);
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Path changeLogPath = new File(".").toPath().toAbsolutePath().getParent().getParent()
                    .resolve("migrations");
            Liquibase liquibase = new liquibase.Liquibase("master.xml",
                    new DirectoryResourceAccessor(changeLogPath), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

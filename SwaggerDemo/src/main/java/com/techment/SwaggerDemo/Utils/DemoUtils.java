package com.techment.SwaggerDemo.Utils;

public class DemoUtils {
//dummy
}

-------------------delete code-----------------
  package twodatasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.example.oracle.repository",
    entityManagerFactoryRef = "oracleEntityManagerFactory",
    transactionManagerRef = "oracleTransactionManager"
)
public class OracleConfig {

    @Bean(name = "oracleDataSource")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:oracle:thin:@//hostname:port/service")
            .username("oracleUsername")
            .password("oraclePassword")
            .driverClassName("oracle.jdbc.OracleDriver")
            .build();
    }

    @Bean(name = "oracleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("oracleDataSource") DataSource dataSource) {
        return builder
            .dataSource(dataSource)
            .packages("com.example.oracle.entity")
            .persistenceUnit("oracle")
            .build();
    }

    @Bean(name = "oracleTransactionManager")
    public PlatformTransactionManager oracleTransactionManager(
            @Qualifier("oracleEntityManagerFactory") LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory) {
        return new JpaTransactionManager(oracleEntityManagerFactory.getObject());
    }
}
-----------------------------
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.example.azure.repository",
    entityManagerFactoryRef = "azureEntityManagerFactory",
    transactionManagerRef = "azureTransactionManager"
)
public class AzureConfig {

    @Bean(name = "azureDataSource")
    public DataSource azureDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:sqlserver://hostname:port;databaseName=database")
            .username("azureUsername")
            .password("azurePassword")
            .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
            .build();
    }

    @Bean(name = "azureEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean azureEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("azureDataSource") DataSource dataSource) {
        return builder
            .dataSource(dataSource)
            .packages("com.example.azure.entity")
            .persistenceUnit("azure")
            .build();
    }

    @Bean(name = "azureTransactionManager")
    public PlatformTransactionManager azureTransactionManager(
            @Qualifier("azureEntityManagerFactory") LocalContainerEntityManagerFactoryBean azureEntityManagerFactory) {
        return new JpaTransactionManager(azureEntityManagerFactory.getObject());
    }
}


@Service
public class DataService {

    @PersistenceContext(unitName = "oracle")
    private EntityManager oracleEntityManager;

    @PersistenceContext(unitName = "azure")
    private EntityManager azureEntityManager;

    public Object callOracleStoredProcedure(String param) {
        StoredProcedureQuery query = oracleEntityManager
            .createStoredProcedureQuery("oracle_procedure_name")
            .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
            .setParameter(1, param);
        
        query.execute();
        return query.getSingleResult();
    }

    public Object callAzureStoredProcedure(String param) {
        StoredProcedureQuery query = azureEntityManager
            .createStoredProcedureQuery("azure_procedure_name")
            .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
            .setParameter(1, param);

        query.execute();
        return query.getSingleResult();
    }
}


<dependencies>
<!-- Spring Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Oracle JDBC Driver -->
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc8</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Azure SQL JDBC Driver -->
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
    <artifactId>mssql-jdbc</artifactId>
    <scope>runtime</scope>
</dependency>
</dependencies>



package edu.tamu.ctv.data;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.support.ClasspathScanningPersistenceUnitPostProcessor;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "edu.tamu.ctv.repository" })
@ComponentScan(basePackages = { "edu.tamu.ctv.entity", "edu.tamu.ctv.repository" })
@PropertySource("classpath:/application.properties")
public class DataConfig {
    @Autowired
    private Environment env;

    @SuppressWarnings("unchecked")
    @Bean
    @Autowired
    DataSource getDataSource(Environment env)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<? extends java.sql.Driver>) Class.forName(env.getProperty("db.driver")));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean(name = "entityManagerFactory", destroyMethod = "destroy")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("edu.tamu.ctv.entity");
        // added for scanning hbm files
        ClasspathScanningPersistenceUnitPostProcessor classpathScanningPersistenceUnitPostProcessor = new ClasspathScanningPersistenceUnitPostProcessor(
                "edu.tamu.ctv.entity");
        classpathScanningPersistenceUnitPostProcessor.setMappingFileNamePattern("*.hbm.xml");
        entityManagerFactoryBean.setPersistenceUnitPostProcessors(classpathScanningPersistenceUnitPostProcessor);

        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        entityManagerFactoryBean.setJpaProperties(properties);

        return entityManagerFactoryBean;
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

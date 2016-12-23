package com.example;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/22 16:47.
 */

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "com.example.dao.ds2", entityManagerFactoryRef = "entityManagerFactory2", transactionManagerRef = "transactionManager")
public class JpaConfigDs2 {

    @Value("${spring.datasource.driver-class-name2}")
    String driverClass;
    @Value("${spring.datasource.url2}")
    String url;
    @Value("${spring.datasource.username2}")
    String userName;
    @Value("${spring.datasource.password2}")
    String passWord;


    @Bean(name = "dataSource2", initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() {
        System.out.println("dataSource2 init");
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(url);
        mysqlXaDataSource.setPassword(passWord);
        mysqlXaDataSource.setUser(userName);
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("dataSource2");
        xaDataSource.setMinPoolSize(10);
        xaDataSource.setPoolSize(10);
        xaDataSource.setMaxPoolSize(30);
        xaDataSource.setBorrowConnectionTimeout(60);
        xaDataSource.setReapTimeout(20);
        xaDataSource.setMaxIdleTime(60);
        xaDataSource.setMaintenanceInterval(60);

        return xaDataSource;
    }

    @Bean(name = "jpaVendorAdapter2")
    public JpaVendorAdapter jpaVendorAdapter() {
        System.out.println("jpaVendorAdapter2 init");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabase(Database.MYSQL);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Bean(name = "entityManagerFactory2")
    @DependsOn({"atomikosJtaPlatfom"})
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        System.out.println("entityManagerFactory2 init");
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();


        entityManager.setJpaVendorAdapter(jpaVendorAdapter());
        entityManager.setPackagesToScan("com.example.model.ds2");// entity package
        entityManager.setJtaDataSource(dataSource());

        Properties properties = new Properties();
        properties.put("hibernate.transaction.jta.platform","com.example.AtomikosJtaPlatfom");

        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.current_session_context_class", "jta");
        properties.put("hibernate.transaction.factory_class", "org.hibernate.engine.transaction.internal.jta.CMTTransactionFactory");

        entityManager.setJpaProperties(properties);
        return entityManager;

    }


}

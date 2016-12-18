package com.example;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/15 18:01.
 */

@Configuration
//@PropertySource({"classpath:/application.properties"})
//启用注解事务管理，使用CGLib代理
@EnableTransactionManagement(proxyTargetClass = true)
//@EnableJpaRepositories(basePackages = "com.at.mul.repository.customer", entityManagerFactoryRef = "customerEntityManager", transactionManagerRef = "transactionManager")
public class DataSourceConfig {

    @Value("${spring.datasource.driver-class-name}")
    String driverClass;
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String passWord;


    @Value("${spring.datasource.driver-class-name2}")
    String driverClass2;
    @Value("${spring.datasource.url2}")
    String url2;
    @Value("${spring.datasource.username2}")
    String userName2;
    @Value("${spring.datasource.password2}")
    String passWord2;


    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
    public TransactionManager atomikosTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    @Bean(name = "transactionManager")
    @DependsOn({ "userTransaction", "atomikosTransactionManager" })
    public PlatformTransactionManager transactionManager() throws Throwable {
        UserTransaction userTransaction = userTransaction();
        TransactionManager atomikosTransactionManager = atomikosTransactionManager();
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager(userTransaction, atomikosTransactionManager);
        jtaTransactionManager.setAllowCustomIsolationLevels(true);
        return jtaTransactionManager;
    }

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() {
        System.out.println("dataSource init");
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(url);
        mysqlXaDataSource.setPassword(passWord);
        mysqlXaDataSource.setUser(userName);
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("dataSource");
        xaDataSource.setMinPoolSize(10);
        xaDataSource.setPoolSize(10);
        xaDataSource.setMaxPoolSize(30);
        xaDataSource.setBorrowConnectionTimeout(60);
        xaDataSource.setReapTimeout(20);
        xaDataSource.setMaxIdleTime(60);
        xaDataSource.setMaintenanceInterval(60);

        return xaDataSource;
    }

    @Bean(name = "dataSource2", initMethod = "init", destroyMethod = "close")
    public DataSource dataSource2() {
        System.out.println("dataSource2 init");
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(url2);
        mysqlXaDataSource.setPassword(passWord2);
        mysqlXaDataSource.setUser(userName2);
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



    //@Bean(name = "dataSource")
    //public DataSource dataSource() {
    //
    //    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    //    dataSource.setDriverClassName(driverClass);
    //    dataSource.setUrl(url);
    //    dataSource.setUsername(userName);
    //    dataSource.setPassword(passWord);
    //    return dataSource;
    //}

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    //@Bean(name = "transactionManager")
    //public DataSourceTransactionManager transactionManager() {
    //    return new DataSourceTransactionManager(dataSource());
    //}

    //@Bean(name = "dataSource2")
    //public DataSource dataSource2() {
    //    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    //    dataSource.setDriverClassName(driverClass2);
    //    dataSource.setUrl(url2);
    //    dataSource.setUsername(userName2);
    //    dataSource.setPassword(passWord2);
    //    System.out.println(url2);
    //    return dataSource;
    //}

    @Bean(name = "jdbcTemplate2")
    public JdbcTemplate jdbcTemplate2(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource2());
        return jdbcTemplate;
    }

    //@Bean(name = "transactionManager2")
    //public DataSourceTransactionManager transactionManager2() {
    //    return new DataSourceTransactionManager(dataSource2());
    //}
}

package com.example;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.example.hibernate.CP_HibernateDAO;
import com.example.hibernate.impl.CP_Hibernate4DAOImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.util.Properties;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/15 18:01.
 */

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
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
        System.out.println();
        UserTransaction userTransaction = userTransaction();
        TransactionManager atomikosTransactionManager = atomikosTransactionManager();
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager(userTransaction, atomikosTransactionManager);
        jtaTransactionManager.setAllowCustomIsolationLevels(true);

        return jtaTransactionManager;
    }

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() {
        System.out.println();

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
        System.out.println();
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


    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean localSessionFactoryBean() {
        System.out.println("sessionFactory");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        String[] packagesToScan = new String[] { "com.example.model.ds1" };
        sessionFactory.setPackagesToScan(packagesToScan);

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");

        hibernateProperties.setProperty("hibernate.current_session_context_class", "jta");
		hibernateProperties.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JTATransactionFactory");

        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;

    }

    @Bean(name = "sessionFactory2")
    public LocalSessionFactoryBean localSessionFactoryBean2() {
        System.out.println("sessionFactory2");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource2());
        String[] packagesToScan = new String[] { "com.example.model.ds2" };
        sessionFactory.setPackagesToScan(packagesToScan);

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");

        hibernateProperties.setProperty("hibernate.current_session_context_class", "jta");
        hibernateProperties.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JTATransactionFactory");

        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;

    }

    @Bean(name = "hibernateDAO")
    public CP_HibernateDAO hibernate4Dao() {
        System.out.println("hibernateDAO");
        CP_Hibernate4DAOImpl dao = new CP_Hibernate4DAOImpl();
        dao.setSessionFactory(localSessionFactoryBean().getObject());
        return dao;
    }

    @Bean(name = "hibernateDAO2")
    public CP_HibernateDAO hibernate4Dao2() {
        System.out.println("hibernateDAO2");
        CP_Hibernate4DAOImpl dao = new CP_Hibernate4DAOImpl();
        dao.setSessionFactory(localSessionFactoryBean2().getObject());
        return dao;
    }




}

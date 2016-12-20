package com.example;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/19 16:10.
 */

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class MyBatisConfig {

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

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/ds1/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() {
        return new SqlSessionTemplate(sqlSessionFactoryBean());
    }

    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactoryBean2() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource2());
        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    @Bean(name = "sqlSessionTemplate2")
    public SqlSessionTemplate sqlSessionTemplate2() {
        return new SqlSessionTemplate(sqlSessionFactoryBean2());
    }






}

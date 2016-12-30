package com.example.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/30 17:01.
 */

@Configuration
@EnableMongoRepositories(basePackages = {"com.example.mongo.dao"},mongoTemplateRef = "mongoTemplate")
public class MongoConfig {

    @Value("${spring.data.mongodb.host}")
    String mongoHost;
    @Value("${spring.data.mongodb.uri}")
    String mongoUrl;

    @Bean
    public MongoClient mongoClient() {
        MongoClient mongoClient = new MongoClient(new ServerAddress(mongoHost));
        return mongoClient;
    }

    @Bean
    public MongoDbFactory mongoDbFactory(){
        String database = new MongoClientURI(mongoUrl).getDatabase();
        return new SimpleMongoDbFactory(mongoClient(),database);
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoDbFactory());
    }
}

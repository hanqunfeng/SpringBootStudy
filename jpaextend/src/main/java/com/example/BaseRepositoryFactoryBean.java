package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/25 12:05.
 */


public class BaseRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>   extends JpaRepositoryFactoryBean<T,S,ID> {

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager)    {
        return new BaseRepositoryFactory(entityManager);
    }
}

class BaseRepositoryFactory extends JpaRepositoryFactory {
    public BaseRepositoryFactory(EntityManager entityManager){
        super(entityManager);
    }



    @Override
    protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        BaseJpaRepositoryImpl customRepository = new BaseJpaRepositoryImpl<T,ID>((Class<T>)information.getDomainType(),entityManager);
        return customRepository;

    }


    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return BaseJpaRepositoryImpl.class;
    }
}

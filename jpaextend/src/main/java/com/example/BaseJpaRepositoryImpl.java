package com.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/25 10:58.
 */


public class BaseJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseJpaRepository<T, ID> {

    private final EntityManager entityManager;



    public BaseJpaRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public Page<T> findByAuto(T example, Pageable pageable) {
        return findAll(BaseSpecs.byAuto(entityManager,example),pageable);
    }

    @Override
    public List<Object[]> listBySQL(String sql) {
        return entityManager.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object[]> listByHQL(String hql) {
        return entityManager.createQuery(hql).getResultList();
    }
}

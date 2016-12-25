package com.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/25 10:56.
 */


public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {

    Page<T> findByAuto(T example, Pageable pageable);
}

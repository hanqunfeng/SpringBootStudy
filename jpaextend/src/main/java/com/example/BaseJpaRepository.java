package com.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/25 10:56.
 */

@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {

    //根据查询条件的分页查询，查询对象的属性如果是字符串切不为空，则模糊匹配，其它类型不为空则精确匹配
    Page<T> findByAuto(T example, Pageable pageable);

    //则接传递一个sql进行查询
    List<Object[]> listBySQL(String sql);

    //则接传递一个hql进行查询
    List<Object[]> listByHQL(String hql);
}

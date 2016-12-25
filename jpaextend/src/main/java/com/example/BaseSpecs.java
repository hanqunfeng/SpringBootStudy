package com.example;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/10/23 21:47.
 */


public class BaseSpecs {

    public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example){
        final Class<T> type = (Class<T>) example.getClass();
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();
                EntityType<T> entityType = entityManager.getMetamodel().entity(type);

                for(Attribute<T,?> attribute : entityType.getDeclaredAttributes()){
                    Object attrValue = getValue(example,attribute);
                    if(attrValue != null){
                        if(attribute.getJavaType() == String.class){
                            if(!StringUtils.isEmpty(attrValue)){
                                predicateList.add(criteriaBuilder.like(root.get(attribute(entityType,attribute.getName(),String.class)),pattern((String)attrValue)));
                            }
                        }else{
                            predicateList.add(criteriaBuilder.equal(root.get(attribute(entityType,attribute.getName(),attrValue.getClass())),attrValue));
                        }
                    }
                }
                return predicateList.isEmpty()?criteriaBuilder.conjunction():criteriaBuilder.and(toArray(predicateList));
            }

            private <T> Object getValue(T example,Attribute<T,?> attr){
                return ReflectionUtils.getField((Field)attr.getJavaMember(),example);
            }

            private <E,T> SingularAttribute<T,E> attribute(EntityType<T> entityType,String fieldName,Class<E> fieldClass){
                return entityType.getDeclaredSingularAttribute(fieldName,fieldClass);
            }

            private Predicate[] toArray(List<Predicate> predicateList){
                Predicate[] array = predicateList.toArray(new Predicate[predicateList.size()]);
                return array;
            }
        };
    }

    static private String pattern(String str){
        return "%" + str + "%";
    }
}

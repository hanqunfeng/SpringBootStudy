package com.example.pojo;

import javax.persistence.Transient;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/20 15:02.
 */


public class BaseEntity {


    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}

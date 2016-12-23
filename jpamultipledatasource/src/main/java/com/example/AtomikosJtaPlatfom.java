package com.example;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/23 10:50.
 */


public class AtomikosJtaPlatfom extends AbstractJtaPlatform {

    private static UserTransaction ut;
    private static TransactionManager tm;
    @Override
    protected TransactionManager locateTransactionManager() {
        return tm;
    }

    @Override
    protected UserTransaction locateUserTransaction() {
        return ut;
    }

    public UserTransaction getUt() {
        return ut;
    }

    public void setUt(UserTransaction ut) {
        AtomikosJtaPlatfom.ut = ut;
    }

    public TransactionManager getTm() {
        return tm;
    }

    public void setTm(TransactionManager tm) {
        AtomikosJtaPlatfom.tm = tm;
    }
}

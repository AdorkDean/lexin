package com.sysbcjzh.mysql;

public class JDBCQueryBean {
    private final String sql;
    private final Object[] parameters;

    public JDBCQueryBean(String sql, Object[] parameters) {
        this.sql = sql;
        this.parameters = parameters;
    }

    public String getSql() {
        return this.sql;
    }

    public Object[] getParameters() {
        return this.parameters;
    }
}
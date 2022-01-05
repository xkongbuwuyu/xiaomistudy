package com.xk.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;


public class DBUtils {
    //私有属性
    private static DBUtils dbUtils;
    private DataSource ds;
    private QueryRunner qr;

    //私有构造
    private DBUtils(){
        //获取到连接池的数据源对象
        ds = new ComboPooledDataSource();
        //获取到dbutils操作数据库核心类
        qr = new QueryRunner(ds);
    }

    //提供一个共有的方法 多线程安全
    public static synchronized DBUtils getInstance(){
        if (dbUtils == null){
            dbUtils = new DBUtils();
        }
        return dbUtils;
    }

    //提供一个方法返回qr对象
    public QueryRunner getQrDb(){
        return qr;
    }
}

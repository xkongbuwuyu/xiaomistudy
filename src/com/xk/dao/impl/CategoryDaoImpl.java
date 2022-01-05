package com.xk.dao.impl;

import com.xk.dao.CategoryDao;
import com.xk.entity.Category;
import com.xk.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> selectCategory(int pageSize) {
        List<Category> categoryList = null;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql="select * from category limit ?";
            categoryList = qr.query(sql, new BeanListHandler<Category>
                    (Category.class), pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}

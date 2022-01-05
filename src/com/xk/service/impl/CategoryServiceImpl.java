package com.xk.service.impl;

import com.xk.dao.CategoryDao;
import com.xk.dao.impl.CategoryDaoImpl;
import com.xk.entity.Category;
import com.xk.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> selectCategory(int pageSize) {
        return categoryDao.selectCategory(pageSize);
    }
}

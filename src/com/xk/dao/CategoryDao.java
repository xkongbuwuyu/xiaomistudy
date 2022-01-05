package com.xk.dao;

import com.xk.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> selectCategory(int pageSize);
}


package com.xk.service;

import com.xk.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> selectCategory(int pageSize);
}

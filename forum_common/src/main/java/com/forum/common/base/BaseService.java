package com.forum.common.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forum.common.entity.Posts;

import java.util.List;

public interface BaseService<T> {
    T findOne(int key);
    int save(T entity);
    void delete(Object key);
    List<T> findAll();

}

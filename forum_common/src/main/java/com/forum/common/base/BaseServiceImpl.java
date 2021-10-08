package com.forum.common.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class BaseServiceImpl <E extends BaseMapper, T> implements BaseService<T>{
    @Autowired
    protected E repository;


    @Override
    public T findOne(int key) {
        return (T)repository.selectById(key);
    }

    @Override
    public int save(T entity) {
        return repository.updateById(entity);
    }

    @Override
    public void delete(Object key) {
        repository.deleteById(key);
    }

    @Override
    public List<T> findAll() {
        return repository.selectList(null);
    }
}

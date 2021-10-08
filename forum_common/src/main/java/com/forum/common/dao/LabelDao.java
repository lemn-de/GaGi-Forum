package com.forum.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper; //提供了各种CRUD方法
import com.forum.common.entity.Label;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelDao extends BaseMapper<Label> {


}




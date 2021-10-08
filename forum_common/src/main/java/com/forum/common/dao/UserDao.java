package com.forum.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forum.common.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<User>{

}

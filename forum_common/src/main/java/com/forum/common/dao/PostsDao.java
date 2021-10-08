package com.forum.common.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forum.common.entity.Label;
import com.forum.common.entity.Posts;
import com.forum.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;


@Repository
public interface PostsDao extends BaseMapper<Posts> {
//    IPage<Posts> getUserPaging(Page<Posts> page);


    @Select("select * from forum_posts as p inner join forum_label as l\n" +
            "on p.label_id = l.id\n" +
            "inner join forum_user as u\n" +
            "on p.user_id = u.id")
    Page<Posts> selectPostsPage(Page<Posts> page);
//
//    Page<Posts> findByLabel(Label label, Pageable pageable);
//    @Select("select * from forum_posts where id = 1")
//    Posts selectPost();
}

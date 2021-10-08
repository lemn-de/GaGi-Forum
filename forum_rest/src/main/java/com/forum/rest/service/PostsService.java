package com.forum.rest.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forum.common.base.BaseService;
import com.forum.common.entity.Label;
import com.forum.common.entity.Posts;
import com.forum.common.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.print.Pageable;
import java.util.List;

public interface PostsService extends BaseService<Posts> {


    /**
     * 分页查询帖子
     *
     * @param type   //帖子类型 top, good
     * @param search //查询条件
     * @param pageNo //页码
     * @param length //返回结果数量
     * @return
     */
    Page<Posts> getPostByPage(String type, String search, int pageNo, int length);

    IPage<Posts> getPostPaging(Posts posts);

    List<Posts> getPostsByUser(User user);

//    @Select("select * from forum_posts as p inner join forum_label as l\n" +
//            "on p.label_id = l.id\n" +
//            "inner join forum_user as u\n" +
//            "on p.user_id = u.id"
//    )
//    List<Posts> selectPostsPage(Page<Posts> page);


    Page<Posts> getPostsByLabel(Label label, int pageNo, int length);


    @Select("select * from forum_posts where id = 1")
    Posts selectPost();



}

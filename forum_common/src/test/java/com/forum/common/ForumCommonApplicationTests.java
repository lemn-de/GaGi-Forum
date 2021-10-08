package com.forum.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forum.common.dao.LabelDao;
import com.forum.common.dao.PostsDao;
import com.forum.common.entity.Label;
import com.forum.common.entity.Posts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ForumCommonApplicationTests {

    @Autowired
    LabelDao labelDao;

    @Test
    public void contextLoads() {
    }





}

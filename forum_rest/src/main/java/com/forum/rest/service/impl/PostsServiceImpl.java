package com.forum.rest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forum.common.base.BaseServiceImpl;
import com.forum.common.dao.PostsDao;
import com.forum.common.entity.Label;
import com.forum.common.entity.Posts;
import com.forum.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.forum.rest.service.PostsService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostsServiceImpl extends BaseServiceImpl<PostsDao, Posts> implements PostsService {

    @Autowired
    PostsDao postsDao;
    @Autowired
    PostsService postsService;

    @Override
    public Page<Posts> getPostByPage(String type, String search, int pageNo, int length) {
        Page<Posts> page = new Page<>(pageNo, length);
        QueryWrapper<Posts> queryWrapper = new QueryWrapper<>();
        if (search == null) {

        }
        if (type != null && type.equals("good")) queryWrapper.eq("good", 1);
        if (type != null && type.equals("top")) queryWrapper.eq("top", 1);
        if (search != null && search != "") queryWrapper.like("title", search);
//        queryWrapper.eq(type, type);
        User user = new User();

        Page<Posts> postsPage = repository.selectPostsPage(page);
        long total = postsPage.getTotal();
        System.out.println("总记录数" +total);
        postsPage.getRecords().forEach(posts -> System.out.println("posts=" + posts));


        return postsPage;
    }


//    @Override
//    public Page<Posts> getPostByPage(String type, String search, int pageNo, int length) {
//        List<Sort.Order> orders = new ArrayList<>();
//        orders.add(new Sort.Order(Sort.Direction.DESC, "top"));
//        orders.add(new Sort.Order(Sort.Direction.DESC, "id"));
//
//
//        Sort sort = new Sort(orders);
//        PageRequest pageable = new PageRequest(pageNo, length, sort);
//
//        Specification<Posts> specification = new Specification<Posts>() {
//            @Override
//            public Predicate toPredicate(Root<Posts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                Path<Boolean> $top = root.get("top");
//                Path<Boolean> $good = root.get("good");
//                Path<String> $title = root.get("title");
//                ArrayList<Predicate> list = new ArrayList<>();
//                if (type != null && type.equals("good")) list.add(criteriaBuilder.equal($good, true));
//                if (type != null && type.equals("top")) list.add(criteriaBuilder.equal($top, true));
//                if (search != null && search != "") list.add(criteriaBuilder.like($title, "%" + search + "%"));
//
//                Predicate predicate = criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
//                return predicate;
//            }
//        };
//        Page<Posts> page = repository.selectPage();
//
//        return page;
//    }

    @Override
    public IPage<Posts> getPostPaging(Posts posts) {
//        Page<Posts> page = new Page<>(1, 20);
//        IPage<Posts> page = super

        return null;
    }

    @Override
    public List<Posts> getPostsByUser(User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", user.getId());
        List<Posts> page = repository.selectList(queryWrapper);
        return page;

    }

//    @Override
//    public List<Posts> selectPostsPage(Page<Posts> page) {
////        Page<Posts> page = new Page<>(1,10);
//        List<Posts> posts = postsDao.selectPostsPage(page);
//        return posts;
//    }

    @Override
    public Page<Posts> getPostsByLabel(Label label, int pageNo, int length) {
        return null;
    }

//    @Override
//    public Page<Posts> getPostsByLabel(Label label, int pageNo, int length) {
//        List<Sort.Order> orders = new ArrayList<>();
//        orders.add(new Sort.Order(Sort.Direction.DESC, "top"));
//        orders.add(new Sort.Order(Sort.Direction.DESC, "id"));
//        Sort sort = new Sort(orders);
//        Pageable pageable = new PageRequest(pageNo, length, sort);
//        Page<Posts> posts = repository.findByLabel(label, pageable);
//        return posts;
//    }

    @Override
    public Posts selectPost(){
        return null;
    }

}

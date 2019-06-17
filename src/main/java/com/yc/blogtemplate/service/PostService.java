package com.yc.blogtemplate.service;


import com.yc.blogtemplate.domain.Post;
import javafx.geometry.Pos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    Post savePost(Post post);

    long count();

    void updatePostContent(Post post);

    void removePost(Long id);

    Post getPostById(Long id);

    List<Post> getAllPost();

    Page<Post> getPostsByTitleLike(String title, Pageable pageable);

    Page<Post> getPartField(Pageable pageable);


}

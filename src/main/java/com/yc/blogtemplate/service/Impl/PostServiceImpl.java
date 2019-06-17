package com.yc.blogtemplate.service.Impl;

import com.yc.blogtemplate.domain.Post;
import com.yc.blogtemplate.repository.PostRepository;
import com.yc.blogtemplate.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void updatePostContent(Post post) {
        postRepository.updateContent(post.getTitle(),
                post.getSummary(),
                post.getContentMd(),
                post.getContentHtml(),
                post.getId());
    }

    @Override
    public void removePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.getOne(id);
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> getPostsByTitleLike(String title, Pageable pageable) {
        title = "%" + title + "%";
        return postRepository.findAllByTitleLike(title, pageable);
    }

    @Override
    public long count(){
        return postRepository.count();
    }

    @Override
    public Page<Post> getPartField(Pageable pageable) {
        return postRepository.getPartFieldPage(pageable);
    }
}

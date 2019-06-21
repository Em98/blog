package com.yc.blogtemplate.controller;


import com.yc.blogtemplate.domain.Post;
import com.yc.blogtemplate.service.PostService;
import com.yc.blogtemplate.vo.Response;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {

    private static final int PAGE_SIZE = 6;

    @Autowired
    private PostService postService;

    @GetMapping("/newPost")
    public String toWritePage(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "blog/blogEdit";
    }

    @GetMapping("/edit/{id}")
    public String toEditPage(@PathVariable(value = "id", required = false) Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "blog/blogEdit";
    }

    @PostMapping("/publishNewBlog")
    public ResponseEntity<?> newBlog2(@RequestBody @Valid Post post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer message = new StringBuffer();
            for (ObjectError error : bindingResult.getAllErrors()) {
                message.append(error.getDefaultMessage());
            }
            Response response = new Response(false, message.toString());
            return ResponseEntity.badRequest().body(response);
        } else {
            if (post.getId() == null) {
                System.out.println(post.getId());
                Date now = new Date();
                post.setPublishData(now);
                postService.savePost(post);
            } else {
                System.out.println(post.getContentMd());
                postService.updatePostContent(post);
            }
            Response response = new Response(true, "upload success.", "博客上传成功！");
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/view/{id}")
    public String viewPost(@PathVariable("id") Long id, Model model) {
        try {
            Post post = postService.getPostById(id);
            SimpleDateFormat publishFormat = new SimpleDateFormat("yyyy-MM-dd");
            String publishDate = publishFormat.format(post.getPublishData());
            String publishInfo = "Published by 98 on " + publishDate;
            System.out.println(publishInfo);
            System.out.println(post.getSummary());
            model.addAttribute("post", post);
            model.addAttribute("pubInfo", publishInfo);
            return "blog/articleTestPage";
        } catch (EntityNotFoundException e) {
            return "index";
        }
    }

    @RequestMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id, Model model) {
        try {
            postService.removePost(id);
            List<Post> posts = postService.getAllPost();
            model.addAttribute("posts", posts);
            return "admin/postList";
        } catch (EntityNotFoundException e) {
            return "index";
        }
    }

    @GetMapping("/list/{index}")
    @ResponseBody
    public Object getPostList(@PathVariable("index") int index, Model model) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(index, 6, sort);
        Page<Post> page = postService.getPartField(pageable);
        List<Post> posts = page.getContent();
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("msg", "");
        data.put("count", posts.size());
        data.put("data", posts);
        return data;
    }

    @GetMapping("/list")
    @ResponseBody
    public Object getPostList() {
        List<Post> posts = postService.getPartField();
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("msg", "");
        data.put("count", posts.size());
        data.put("data", posts);
        return data;
    }

}

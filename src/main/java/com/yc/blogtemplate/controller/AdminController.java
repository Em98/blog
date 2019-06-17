package com.yc.blogtemplate.controller;

import com.yc.blogtemplate.domain.Post;
import com.yc.blogtemplate.service.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PostService postService;

    @GetMapping("/postsList")
    public String getPostsList(@RequestParam(value = "async", required = false) boolean async,
                                   @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                   @RequestParam(value = "title", defaultValue = "") String title,
                                   Model model){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Post> page = postService.getPostsByTitleLike(title, pageable);
        List<Post> posts = page.getContent();
        model.addAttribute("posts",posts);

        return async==true ? "admin/postList" : "admin/postList";

    }

    @GetMapping("/adminPostList")
    public String goPostList(){
        return "admin/adminPostList";
    }


    @GetMapping("/index")
    public String toAdminIndex() {
        return "admin/adminIndex";
    }

}

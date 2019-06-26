package com.yc.blogtemplate.controller;

import com.yc.blogtemplate.domain.Post;
import com.yc.blogtemplate.service.PostService;
import com.yc.blogtemplate.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    @Autowired
    private PostService postService;

    private static final int PAGE_SIZE = 6;


    public void getOnePage(Model model, int pageIndex){
        Sort sort = new Sort(Sort.Direction.DESC, "publishData");
        Pageable pageable = PageRequest.of(pageIndex, PAGE_SIZE,sort);
        Page<Post> page = postService.getPostsByTitleLike("", pageable);
        List<Post> posts = page.getContent();
        model.addAttribute("posts", posts);
        model.addAttribute("index", pageIndex);
    }

    @GetMapping(value = {"", "/index"})
    public String IndexMapping(Model model){
//        Pageable pageable = PageRequest.of(0, PAGE_SIZE);
////        Sort sort = new Sort(Sort.Direction.DESC, "publishData");
////        Page<Post> page = postService.getPostsByTitleLike("", pageable);
////        List<Post> posts = page.getContent();
////        model.addAttribute("posts", posts);
////        model.addAttribute("index", 0);
        getOnePage(model, 0);
        return "index";
    }

    @GetMapping(value = "/page/{pageIndex}")
    public String pageMapping(@PathVariable(value = "pageIndex") int pageIndex, Model model){
        getOnePage(model, pageIndex);
        return "index";
    }


    @PostMapping("/newBlog")
    public ResponseEntity<?> newBlog(@RequestBody Map<String, String> request){
        String title = request.get("title");
        String summary = request.get("summary");
        String contentMd = request.get("contentMd");
        String contentHtml = request.get("contentHtml");
        System.out.println(contentMd);
        if (title == null || title.length() == 0){
            Response response = new Response(false, "system error.");
            return ResponseEntity.badRequest().body(response);
        }
        else {
            Response response = new Response(true, "upload success.", "博客上传成功！");
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/testPage")
    public String testPage(){
        return "admin/adminBase";
    }

    @GetMapping("/testArticle")
    public String testArticle(){
        return "articleTestPage";
    }

    @GetMapping("/testList")
    public String testList(){
        return "admin/adminPostList";
    }

//    @PostMapping("/newBlog2")
//    public ResponseEntity<?> newBlog2(@RequestBody @Valid Post post, BindingResult bindingResult){
//        System.out.println(post.getTitle());
//        if (bindingResult.hasErrors()){
//            StringBuffer message = new StringBuffer();
//            for(ObjectError error : bindingResult.getAllErrors()){
//                message.append(error.getDefaultMessage());
//            }
//            Response response = new Response(false, message.toString());
//            return ResponseEntity.badRequest().body(response);
//        }
//        else {
//            Response response = new Response(true, "upload success.", "博客上传成功！");
//            return ResponseEntity.ok(response);
//        }
//    }

}

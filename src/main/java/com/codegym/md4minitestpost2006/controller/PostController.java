package com.codegym.md4minitestpost2006.controller;

import com.codegym.md4minitestpost2006.model.Post;
import com.codegym.md4minitestpost2006.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {

    @Autowired
    public IPostService postService;

    @GetMapping
    public ResponseEntity<Iterable<Post>> findAllStudent(@PageableDefault(size = 2) Pageable pageable) {
        Page<Post> posts = postService.findAll(pageable);

        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Post> add(@RequestBody Post post) {
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/findByTitle")
//    public ResponseEntity<Iterable<Post>> findAllByTitleContaining(@RequestParam String title, @RequestParam String from, @RequestParam String to) {
//        List<Post> posts = (List<Post>) postService.findAllByContent(title);
//        List<Post> posts2 = new ArrayList<>();
//        List<Post> postList = new ArrayList<>();
//        if (from.equals("") & to.equals("")) {
//            postList = posts;
//        } else {
//            posts2 = (List<Post>) postService.findAllByCreateAtBetween(LocalDateTime.parse(from), LocalDateTime.parse(to));
//        }
//        for (int i = 0; i < posts.size(); i++) {
//            for (int j = 0; j < posts2.size(); j++) {
//                if (posts.get(i).getId() == posts2.get(j).getId()) {
//
//                    postList.add(posts.get(i));
//                }
//            }
//        }
//        if (posts.size() == 0) {
//            postList = posts2;
//        }
//        if (posts2.size() == 0) {
//            postList = posts;
//        }
//        return new ResponseEntity<>(postList, HttpStatus.OK);
//    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Post>> search(@RequestParam String title, @RequestParam String dateFrom, @RequestParam String dateTo) {
        if ((dateFrom.equals("") && dateTo.equals(""))) {
            dateFrom = "1900-01-01T00:00:00";
            dateTo = String.valueOf(LocalDateTime.now());
        }
//        Iterable<Post> posts = postService.findByTitle('%' + title + '%', LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo));
        return new ResponseEntity<>(postService.findByTitle('%' + title + '%', LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo)), HttpStatus.OK);
    }
    @GetMapping("/searchByCreateAtOrderBy")
    public ResponseEntity<Iterable<Post>> searchByCreateAtOrderBy(@RequestParam String dateFrom, @RequestParam String dateTo) {
//        Iterable<Post> posts = postService.findByTitle('%' + title + '%', LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo));
        return new ResponseEntity<>(postService.findAllOrderByCreateAt( LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo)), HttpStatus.OK);
    }

    @GetMapping("/findByTitleAndCreateAtOrderByCreateAt")
    public ResponseEntity<Iterable<Post>> findByTitleAndCreateAtOrderByCreateAt(@RequestParam String title, @RequestParam String dateFrom, @RequestParam String dateTo) {
        if ((dateFrom.equals("") && dateTo.equals(""))) {
            dateFrom = "1900-01-01T00:00:00";
            dateTo = String.valueOf(LocalDateTime.now());
        }
//        Iterable<Post> posts = postService.findByTitle('%' + title + '%', LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo));
        return new ResponseEntity<>(postService.findByTitleAndCreateAtOrderByCreateAt('%' + title + '%', LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo)), HttpStatus.OK);
    }
}

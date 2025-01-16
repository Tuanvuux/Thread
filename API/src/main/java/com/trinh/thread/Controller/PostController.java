package com.trinh.thread.Controller;

import com.trinh.thread.Entity.Post;
import com.trinh.thread.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")  // Đây là đường dẫn đúng
    public ResponseEntity<?> createPost(
            @RequestParam("userId") int userId,
            @RequestParam(value = "caption", required = false) String caption,
            @RequestParam(value = "files", required = false) List<MultipartFile> files) {  // files là tên tham số
        try {
            Post post = postService.createPost(userId, caption, files);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating post: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") int id) {
        try {
            Post post = postService.getPostById(id);
            if (post == null) {
                return ResponseEntity.status(404).body("Post not found");
            }
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching post: " + e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching posts: " + e.getMessage());
        }
    }
}

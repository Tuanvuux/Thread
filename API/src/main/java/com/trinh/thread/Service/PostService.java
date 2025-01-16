package com.trinh.thread.Service;
import com.trinh.thread.Entity.Image;
import com.trinh.thread.Entity.Post;
import com.trinh.thread.Repository.ImageRepository;
import com.trinh.thread.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImageRepository imageRepository;

    private final String uploadDir = "uploads/";

    public Post createPost(int userId, String caption, List<MultipartFile> files) throws IOException {
        // Lưu thông tin bài post
        Post post = new Post();
        post.setUserId(userId);
        post.setCaption(caption);
        post.setCreatedAt(LocalDateTime.now());
        post.setDeleted(false);
        Post savedPost = postRepository.save(post);

        // Lưu ảnh liên quan
        if (files != null && !files.isEmpty()) {
            List<Image> images = new ArrayList<>();
            for (MultipartFile file : files) {
                String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Files.copy(file.getInputStream(), uploadPath.resolve(fileName));

                // Tạo đối tượng Image và lưu vào database
                Image image = new Image();
                image.setPostId(savedPost.getPostId());
                image.setImageUrl(fileName);
                image.setDeleted(false);
                images.add(image);
            }
            imageRepository.saveAll(images);
        }

        return savedPost;
    }

    public Post getPostById(int id) {
        // Lấy bài đăng từ cơ sở dữ liệu (ví dụ sử dụng repository hoặc DAO)
        Optional<Post> post = postRepository.findById(id); // Sử dụng findById nếu bạn dùng JPA
        return post.orElse(null);
    }

    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();  // Lấy tất cả bài đăng
        for (Post post : posts) {
            // Lấy tất cả ảnh của bài đăng
            List<Image> images = imageRepository.findByPostId(post.getPostId());
            post.setImages(images);  // Giả sử bạn thêm trường `images` vào Post Entity
        }
        return posts;
    }
}

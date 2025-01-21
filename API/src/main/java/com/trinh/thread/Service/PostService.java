package com.trinh.thread.Service;
import com.trinh.thread.DTO.Account;
import com.trinh.thread.DTO.PostDTO;
import com.trinh.thread.Entity.Image;
import com.trinh.thread.Entity.Post;
import com.trinh.thread.Entity.User;
import com.trinh.thread.Repository.ImageRepository;
import com.trinh.thread.Repository.PostRepository;
import com.trinh.thread.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
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
    @Autowired
    private UserRepository userRepository;

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
    public String setCreatedTime(Post post) {
        LocalDateTime currentTime = LocalDateTime.now();
        String createdTime;
        Duration duration = Duration.between(post.getCreatedAt(), currentTime); // Đổi vị trí để tính từ quá khứ đến hiện tại

        if (duration.toDays() > 0) {
            createdTime = duration.toDays() + " ngày";
        } else if (duration.toHours() > 0) {
            createdTime = duration.toHours() + " giờ";
        } else if (duration.toMinutes() > 0) {
            createdTime = duration.toMinutes() + " phút";
        } else {
            createdTime = "Vừa mới";
        }
        return createdTime;
    }

    public List<PostDTO> getAllPosts() {
        List<PostDTO> listPost = new  ArrayList<>();
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            List<Image> images = imageRepository.findByPostId(post.getPostId());
            Optional<User> user = userRepository.findById(post.getUserId());
            PostDTO postDTO = new PostDTO();
            postDTO.setPostId(post.getPostId());
            postDTO.setCaption(post.getCaption());
            postDTO.setImages(images);
            postDTO.setPostedTime(setCreatedTime(post));
            postDTO.setUserId(post.getUserId());
            postDTO.setAvatarUrl(user.get().getAvatarUrl());
            postDTO.setDisplayName(user.get().getDisplayName());
            listPost.add(postDTO);
        }
        return listPost;
    }
}

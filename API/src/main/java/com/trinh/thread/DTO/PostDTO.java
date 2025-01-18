package com.trinh.thread.DTO;

import com.trinh.thread.Entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    int userId;
    String displayName;
    String avatarUrl;
    int postId;
    int postedTime;
    String caption;
    List<Image> images;
}

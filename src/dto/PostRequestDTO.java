package dto;

import java.time.LocalDateTime;
import java.util.Date;

public class PostRequestDTO {
    private int postId;
    private int userId;
    private String postContents;
    private LocalDateTime postRegTime;
    private String postTitle;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostContents() {
        return postContents;
    }

    public void setPostContents(String postContents) {
        this.postContents = postContents;
    }

    public LocalDateTime getPostRegTime() {
        return postRegTime;
    }

    public void setPostRegTime(LocalDateTime postRegTime) {
        this.postRegTime = postRegTime;
    }

    public String getPostTitle(){return postTitle;}

    public void setPostTitle(String postTitle){this.postTitle = postTitle;}

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", postTitle='" + postTitle + '\'' +
                ", postContents='" + postContents + '\'' +
                ", postRegTime=" + postRegTime +
                '}';
    }
}

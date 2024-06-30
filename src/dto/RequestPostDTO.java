package dto;

import java.util.Date;

public class RequestPostDTO {
    private int postId;
    private int userId;
    private String postContents;
    private Date postRegTime;
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

    public Date getPostRegTime() {
        return postRegTime;
    }

    public void setPostRegTime(Date postRegTime) {
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

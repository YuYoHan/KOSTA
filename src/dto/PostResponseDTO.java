package dto;

import java.time.LocalDateTime;
import java.util.Date;

public class PostResponseDTO {
    private int postId;
    private String nickname;
    private String postContents;
    private Date postRegTime;
    private String postTitle;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    @Override
    public String toString() {
        return "PostResponseDTO{" +
                "postId=" + postId +
                ", nickname='" + nickname + '\'' +
                ", postContents='" + postContents + '\'' +
                ", postRegTime=" + postRegTime +
                ", postTitle='" + postTitle + '\'' +
                '}';
    }
}


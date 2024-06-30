package dto;


import java.sql.Date;
import java.time.LocalDateTime;

public class CommentDTO {
    private int commentId;
    private String commentContents;
    private LocalDateTime regDate;
    private int postId;
    private int userId;

    public CommentDTO() {}
    public CommentDTO(int commentId,String commentContents,LocalDateTime regDate, int postId,int userId) {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getCommentContents() {
        return commentContents;
    }

    public void setCommentContents(String commentContents) {
        this.commentContents = commentContents;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "userId=" + userId +
                ", postId=" + postId +
                ", commentContents='" + commentContents + '\'' +
                ", commentId=" + commentId +
                ", regDate=" + regDate +
                '}';
    }
}

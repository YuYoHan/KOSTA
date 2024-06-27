package dto;

import java.util.Date;

public class CommentDTO {
    private int userId;
    private int postId;
    private String commentContents;
    private int commentId;
    private Date regDate;

    public CommentDTO() {}

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

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
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

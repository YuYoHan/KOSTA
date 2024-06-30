package dto;

import java.time.LocalDateTime;

public class CommentComponentDTO {
    private String nickname;
    private LocalDateTime regDate;
    private String contents;
    private int userId;

    @Override
    public String toString() {
        return "CommentComponentDTO{" +
                "nickname='" + nickname + '\'' +
                ", regDate=" + regDate +
                ", contents='" + contents + '\'' +
                ", userId=" + userId +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public CommentComponentDTO(String nickname, LocalDateTime regDate, String contents, int userId) {
        this.nickname = nickname;
        this.regDate = regDate;
        this.contents = contents;
        this.userId = userId;
    }
}

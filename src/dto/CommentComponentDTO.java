package dto;

import java.time.LocalDateTime;

public class CommentComponentDTO {
    private String nickname;
    private LocalDateTime regDate;
    private String contents;

    public CommentComponentDTO() {

    }
    @Override
    public String toString() {
        return "CommentComponentDTO{" +
                "nickname='" + nickname + '\'' +
                ", regDate=" + regDate +
                ", contents='" + contents + '\'' +
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

    public CommentComponentDTO(String nickname, LocalDateTime regDate, String contents) {
        this.nickname = nickname;
        this.regDate = regDate;
        this.contents = contents;
    }
}

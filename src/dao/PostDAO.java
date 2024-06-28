package dao;

import dto.RequestPostDTO;

import java.sql.*;

public class PostDAO {

    public static Connection connection;
    public static PreparedStatement preparedStatement;
    public static ResultSet rs;

    // 게시물 등록  PostDTO post
    public static int insert(RequestPostDTO post) {
        String sql = "INSERT INTO post (post_title, post_contents, user_id) values (?, ?, ?)";
        return UserDAO.connectDB(sql, post.getPostTitle(), post.getPostContents(), post.getUserId());
    }


}
package dao;

import config.JDBCConfig;
import dto.PostRequestDTO;
import dto.PostResponseDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    public static Connection connection;
    public static PreparedStatement preparedStatement;
    public static ResultSet rs;

    // 게시물 등록  PostDTO post
    public static int insert(PostRequestDTO post) {
        String sql = "INSERT INTO post (post_title, post_contents, user_id) values (?, ?, ?)";
        return UserDAO.connectDB(sql, post.getPostTitle(), post.getPostContents(), post.getUserId());
    }

    // 게시물 전체 조회
    public static List<PostResponseDTO> selectAll(){
        String sql = "SELECT * FROM post, users where users.user_id= post.user_id";
        List<PostResponseDTO> posts = new ArrayList<>();
        try {
            connection = JDBCConfig.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                PostResponseDTO post = new PostResponseDTO();
                post.setPostId(rs.getInt("post_id"));
                post.setPostTitle(rs.getString("post_title"));
                post.setPostContents(rs.getString("post_contents"));
                post.setNickname(rs.getString("nickname"));
                post.setPostRegTime(rs.getTimestamp("post_regtime").toLocalDateTime());
                posts.add(post);
            }
        } catch (SQLException e) {
            System.out.println("에러 발생 : " + e.getMessage());
        } finally {
            try{
                JDBCConfig.close(rs, preparedStatement, connection);
            } catch (Exception e){
                System.out.println("에러 발생 : " + e.getMessage());
            }
        }
        return posts;
    }
}
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
                post.setPostRegTime(rs.getTimestamp("post_regtime"));
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

    // 제목이나 닉네임으로 게시물 조회(여러 개의 포스트 반환)
    public static ArrayList<PostResponseDTO> searchPostByTitleNickname(String searchTxt){
        ArrayList<PostResponseDTO> result = new ArrayList<>();

        try{
            connection = JDBCConfig.getConnection();
            String sql = "select POST_ID, POST_TITLE, (" +
                    "    select NICKNAME from USERS" +
                    "    where users.USER_ID = POST.USER_ID" +
                    ") NICKNAME, POST_REGTIME, POST_CONTENTS " +
                    "from POST " +
                    "where post_title like ? or user_id in (" +
                    "    select user_id from users where nickname like ?" +
                    ") order by post_regtime desc";

            //S: 작업 1 - pstmt 에서 작업 조회 시도하여 ResultSet 으로 가져온다
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchTxt + "%");
            preparedStatement.setString(2, "%" + searchTxt + "%");
            rs = preparedStatement.executeQuery();
            //E: 작업 1

            //S: 작업 2 - PostDTO를 구성으로 가진 ArrayList를 만든다.
            while(rs.next()){
                result.add(new PostResponseDTO());
                result.getLast().setPostId(rs.getInt("post_id"));
                result.getLast().setNickname(rs.getString("nickname"));
                result.getLast().setPostTitle(rs.getString("post_title"));
                result.getLast().setPostContents(rs.getString("post_contents"));
                result.getLast().setPostRegTime(rs.getTimestamp("post_regtime"));
            }
            //E: 작업 2
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }finally {
            JDBCConfig.close(rs, preparedStatement, connection);
        }
        //위에서 만든 ArrayList 반환
        return result;
    }

    //한 개의 포스트 조회
    public static PostResponseDTO getPostReadDTO(int postId){
        PostResponseDTO postDTO = new PostResponseDTO();
        try{
            connection = JDBCConfig.getConnection();
            String sql = "select POST_ID, POST_TITLE, (" +
                    "    select NICKNAME from USERS" +
                    "    where USERS.USER_ID = POST.USER_ID" +
                    ") NICKNAME, POST_REGTIME, POST_CONTENTS " +
                    "from POST " +
                    "where POST_ID = ?";

            //S: 작업 1 - pstmt 에서 작업 조회 시도하여 ResultSet 으로 가져온다
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, postId);
            rs = preparedStatement.executeQuery();
            //E: 작업 1

            //S: 작업 2 - PostDTO를 구성으로 가진 ArrayList를 만든다.
            if(rs.next()){
                postDTO.setPostId(rs.getInt("post_id"));
                postDTO.setNickname(rs.getString("nickname"));
                postDTO.setPostTitle(rs.getString("post_title"));
                postDTO.setPostContents(rs.getString("post_contents"));
                postDTO.setPostRegTime(rs.getTimestamp("post_regtime"));
            }
            //E: 작업 2
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }finally {
            JDBCConfig.close(rs, preparedStatement, connection);
        }
        return postDTO;
    }
}
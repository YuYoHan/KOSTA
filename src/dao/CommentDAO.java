package dao;
import config.JDBCConfig;
import dto.CommentDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommentDAO {
    public final static String driver = "oracle.jdbc.driver.OracleDriver";
    public final static String url = "jdbc:oracle:thin:@localhost:1521:XE";
    public final static String username = "c##madang";
    public final static String password = "madang";
    public static Connection connection = null;
    public static PreparedStatement preparedStatement=null;
    // 댓글 작성
    public static int insertComment(CommentDTO commentDTO) {
        String sql="insert into comments (comment_contents, post_id, user_id) values(?,?,?)";
        int res=0;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,commentDTO.getCommentContents());
            preparedStatement.setInt(2,commentDTO.getPostId());
            preparedStatement.setInt(3,commentDTO.getUserId());

            res = preparedStatement.executeUpdate();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }catch (Exception e2){
                System.out.println(e2.getMessage());
            }
            }
        return res;
    }

    // 포스트에 해당하는 댓글만 가져오기
//    public ArrayList<CommentDTO> listComment(int postId) {
//        ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
//        String sql="select * from comments where postid=?";
//        ResultSet resultSet=null;
//        try {
//            Class.forName(driver);
//            connection = DriverManager.getConnection(url,username,password);
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1,postId);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                LocalDateTime regDate = resultSet.getTimestamp(5).toLocalDateTime();
//                list.add(new CommentDTO(resultSet.getInt(1),
//                                        resultSet.getInt(2),
//                                        resultSet.getInt(3),
//                                        resultSet.getString(4),
//                                        regDate));
//            }
//
//        } catch (Exception e) {
//        }finally {
//            try {
//
//            } catch (Exception e2) {
//            }
//        }
//
//        return list;
//    }

    public static void main(String[] args) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentContents("안녕");
        commentDTO.setPostId(1);
        commentDTO.setUserId(2);
        System.out.println(commentDTO);

        int re = insertComment(commentDTO);
        System.out.println(re);
    }
}

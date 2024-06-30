package dao;

import dto.CommentDTO;
import java.sql.*;

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
        int result=0;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,commentDTO.getCommentContents());
            preparedStatement.setInt(2,commentDTO.getPostId());
            preparedStatement.setInt(3,commentDTO.getUserId());
            result = preparedStatement.executeUpdate();

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
        return result;
    }

    public static int deleteComment(int commentID) {
        String sql = "DELETE FROM COMMENTS WHERE comment_id = ?";
        int result=0;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, commentID);
            result = preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
        return result;
    }

//    public static int updateComment(CommentDTO commentDTO) {
//        String sql = "UPDATE COMMENTS SET COMMENT_CONTENTS=? WHERE COMMENT_ID=?";
//        int result=0;
//        try {
//            Class.forName(driver);
//            connection = DriverManager.getConnection(url, username, password);
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, commentDTO.getCommentContents());
//            preparedStatement.setInt(2,commentDTO.getCommentId());
//            result = preparedStatement.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }finally {
//            try {
//
//                if (connection != null) {
//                    connection.close();
//                }
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//            } catch (Exception e2) {
//                System.out.println(e2.getMessage());
//            }
//        }
//        return result;
//    }

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

//        // INSERT TEST
//        commentDTO.setCommentContents("안녕");
//        commentDTO.setPostId(1);
//        commentDTO.setUserId(2);
//        System.out.println(commentDTO);
//
//        int re = insertComment(commentDTO);
//        System.out.println(re);

        // DELETE TEST
        commentDTO.setCommentId(59);
        System.out.println(commentDTO);
        int result = deleteComment(commentDTO.getCommentId());
        System.out.println(result);
    }
}

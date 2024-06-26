package dao;

import dto.CommentComponentDTO;
import dto.CommentDTO;
import java.sql.*;
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

    // 댓글 삭제
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

    // 댓글 수정
    public static int updateComment(CommentDTO commentDTO) {
        String sql = "UPDATE COMMENTS SET COMMENT_CONTENTS=? WHERE COMMENT_ID=?";
        int result=0;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, commentDTO.getCommentContents());
            preparedStatement.setInt(2,commentDTO.getCommentId());
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
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

//     포스트에 해당하는 댓글만 가져오기
    public static ArrayList<CommentDTO> listComment(int postId) {
        ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
        String sql="select COMMENT_ID,COMMENT_CONTENTS,COMMENT_REGTIME,POST_ID,USER_ID from comments where post_id=? ORDER BY COMMENT_REGTIME DESC";
        ResultSet resultSet=null;
        CommentDTO commentDTO=null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,postId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                commentDTO = new CommentDTO();
                commentDTO.setCommentId(resultSet.getInt(1));
                commentDTO.setCommentContents(resultSet.getString(2));
                commentDTO.setRegDate(resultSet.getTimestamp(3).toLocalDateTime());
                commentDTO.setPostId(postId);
                commentDTO.setUserId(resultSet.getInt(5));
                list.add(commentDTO);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
        return list;
    }

    // comment gui에 필요한 데이터만 가져오는 메서드
    public static String getNickname(int postid, int userid) {
        String nickname = "";
        String sql = "select U.NICKNAME from users u, comments c where u.USER_ID=c.USER_ID and C.POST_ID=? and C.USER_ID=? ORDER BY COMMENT_REGTIME DESC";
        CommentComponentDTO commentComponentDTO = null;
        ResultSet resultSet=null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,postid);
            preparedStatement.setInt(2,userid);
            resultSet= preparedStatement.executeQuery();

            while (resultSet.next()) {
                nickname=resultSet.getString(1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
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
        return nickname;
    }

    public static void main(String[] args) {
//        String nickname = getNickname(2,1);
//        System.out.println(nickname);
        ArrayList<CommentDTO> list = listComment(30);
        for (CommentDTO commentDTO : list) {
            System.out.println(commentDTO);
        }
    }

}

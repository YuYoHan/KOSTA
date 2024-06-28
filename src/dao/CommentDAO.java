package dao;

import config.JDBCConfig;
import dto.CommentDTO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommentDAO {
    public final static String driver = "oracle.jdbc.driver.OracleDriver";
    public final static String url = "jdbc:oracle:thin:@localhost:1521:XE";
    public final static String username = "c##dotori";
    public final static String password = "dotori";
    Connection connection = null;
    PreparedStatement preparedStatement=null;
    // 댓글 작성
    public int InsertComment(CommentDTO commentDTO) {
        String sql="insert into comments values(?,?,?,?,?)";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,commentDTO.getUserId());
            preparedStatement.setInt(2,commentDTO.getPostId());
            preparedStatement.setInt(3,commentDTO.getCommentId());
            preparedStatement.setString(4,commentDTO.getCommentContents());
//            preparedStatement.setDate(5,commentDTO.getRegDate()); //TODO:commentDTO java.sql 뭐시기로 변경하기

            int res = preparedStatement.executeUpdate(sql);

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
        return 0;
    }

    // 포스트에 해당하는 댓글만 가져오기
    public ArrayList<CommentDTO> listComment(int postId) {
        ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
        String sql="select * from comments where postid=?";
        ResultSet resultSet=null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,postId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new CommentDTO(resultSet.getInt(1),
                                        resultSet.getInt(2),
                                        resultSet.getInt(3),
                                        resultSet.getString(4),
                                        resultSet.getDate(5)));
            }

        } catch (Exception e) {
        }finally {
            try {

            } catch (Exception e2) {
            }
        }

        return list;
    }
}

package gui.component.comment;

import config.SessionManager;
import dao.CommentDAO;
import dao.UserDAO;
import dto.CommentDTO;
import gui.component.global.CustomStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Comment extends JPanel {
    JTextArea commentTxt;
    String nickname;
    String contents;
    String date;
    public Comment(CommentDTO commentDTO){
        nickname=CommentDAO.getNickname(commentDTO.getPostId(),commentDTO.getUserId());
        contents=commentDTO.getCommentContents();
        date=commentDTO.getRegDate().toLocalDate()+"";

        setBackground(CustomStyle.white);
        setBorder(BorderFactory.createMatteBorder(0,0,1,0, CustomStyle.borderColor)
        );
        setLayout(new BorderLayout());

        JPanel commentItemContent = new JPanel();
        commentItemContent.setBackground(CustomStyle.white);
        commentItemContent.setLayout(new BorderLayout());
        commentItemContent.setBorder(new EmptyBorder(16,16,16,16));
        add(commentItemContent);

        // top panel left : nickname    right : buttons
        JPanel topWrap = new JPanel();
        topWrap.setBackground(CustomStyle.white);
        topWrap.setLayout(new BoxLayout(topWrap,BoxLayout.X_AXIS));
        commentItemContent.add(topWrap, BorderLayout.NORTH);

        JPanel commentNicknameWrap = new JPanel();
        commentNicknameWrap.setBackground(CustomStyle.white);
        commentNicknameWrap.setLayout(new FlowLayout(FlowLayout.LEFT));
        topWrap.add(commentNicknameWrap);
        topWrap.add(Box.createHorizontalGlue());
        JPanel commentButtonWrap = new JPanel();
        commentButtonWrap.setBackground(CustomStyle.white);
        commentButtonWrap.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topWrap.add(commentButtonWrap);


        JLabel commentNickname = new JLabel(nickname);
        commentNickname.setFont(CustomStyle.setCutomFont(14, 'b'));

        commentNicknameWrap.add(commentNickname);

        JPanel commentDateWrap = new JPanel();
        commentDateWrap.setBackground(CustomStyle.white);
        commentDateWrap.setBorder(new EmptyBorder(-4, 0, 0, 0));
        commentDateWrap.setLayout(new FlowLayout(FlowLayout.LEFT));
        commentItemContent.add(commentDateWrap, BorderLayout.CENTER);

        JLabel commentDate = new JLabel(date);
        commentDate.setFont(CustomStyle.setCutomFont(14, 'n'));
        commentDate.setForeground(CustomStyle.black50);
        commentDateWrap.add(commentDate);

        JPanel commentTxtWrap = new JPanel();
        commentTxtWrap.setBackground(CustomStyle.white);
        commentTxtWrap.setLayout(new BorderLayout());
        commentTxtWrap.setBorder(new EmptyBorder(0, 4, 0, 4));
        commentItemContent.add(commentTxtWrap, BorderLayout.SOUTH);

        commentTxt = new JTextArea();
        commentTxt.setText(contents);
        commentTxt.setFont(CustomStyle.setCutomFont(16, 'n'));
        commentTxt.setLineWrap(true);
        commentTxt.setWrapStyleWord(true);
        commentTxt.setEnabled(false);
        commentTxtWrap.add(commentTxt,BorderLayout.CENTER);

        if (nickname.equals(SessionManager.getCurrentUser())){
            JButton editButton = new JButton("수정");
            editButton.setBackground(CustomStyle.white);
            editButton.setFont(CustomStyle.setCutomFont(13,'n'));
            editButton.setBorderPainted(false);
            JButton deleteButton = new JButton("삭제");
            deleteButton.setBackground(CustomStyle.white);
            deleteButton.setFont(CustomStyle.setCutomFont(13,'n'));
            deleteButton.setBorderPainted(false);
            commentButtonWrap.add(editButton);
            commentButtonWrap.add(deleteButton);

            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    commentTxt.setEnabled(true);
                    commentTxt.setBackground(Color.WHITE); // 편집 가능 시 배경색 설정
                    commentTxt.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // 테두리 설정
                    commentTxt.setForeground(Color.BLACK); // 편집 가능 시 글자색 설정
                    commentTxt.requestFocus();  // 텍스트 필드에 포커스 설정
                    JButton commentWriteButton = new JButton("입력");
                    commentTxtWrap.add(commentWriteButton,BorderLayout.EAST);
                    updateUI();

                    commentWriteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            commentDTO.setCommentContents(commentTxt.getText());
                            CommentDAO.updateComment(commentDTO);
                            commentTxt.setText(contents);
                            commentTxt.setBorder(null);
                            commentTxt.setFont(CustomStyle.setCutomFont(16, 'n'));
                            commentTxt.setLineWrap(true);
                            commentTxt.setWrapStyleWord(true);
                            commentTxt.setEnabled(false);
                            contents=commentDTO.getCommentContents();
                            commentWriteButton.setVisible(false);
                            updateUI();
                        }
                    });
                }
            });
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CommentDAO.deleteComment(commentDTO.getCommentId());
                    updateUI();
                    setVisible(false);
                }
            });
        }
    }
    public void setCommentWidth(int frameWidth){
        commentTxt.setSize(frameWidth - (CustomStyle.DISPLAY_MARGIN*2 + 48 + 16+2+32), 20);
    }
}

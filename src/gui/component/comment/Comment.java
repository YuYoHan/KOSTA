package gui.component.comment;

import config.SessionManager;
import gui.CustomStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Comment extends JPanel {
    JTextArea commentTxt;
    public Comment(String nickname, String date, String txt){

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

        if (nickname==SessionManager.getCurrentUser()){
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
        }

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
        commentTxt.setText(txt);
        commentTxt.setFont(CustomStyle.setCutomFont(16, 'n'));
        commentTxt.setLineWrap(true);
        commentTxt.setWrapStyleWord(true);
        commentTxt.setEnabled(false);
        commentTxtWrap.add(commentTxt);

    }
    public void setCommentWidth(int frameWidth){
        commentTxt.setSize(frameWidth - (CustomStyle.DISPLAY_MARGIN*2 + 48 + 16+2+32), 20);
    }
}

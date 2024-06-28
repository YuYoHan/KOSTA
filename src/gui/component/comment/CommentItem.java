package gui.component.comment;

import gui.CustomStyle;
import gui.component.post.PostRead;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CommentItem extends JPanel {
    JTextArea commentTxt;
    public CommentItem(String nickname, String date, String txt){

        setBackground(CustomStyle.white);
        setBorder(BorderFactory.createMatteBorder(0,0,1,0, CustomStyle.borderColor)
        );
        setLayout(new BorderLayout());

        JPanel commentItemContent = new JPanel();
        commentItemContent.setBackground(CustomStyle.white);
        commentItemContent.setLayout(new BorderLayout());
        commentItemContent.setBorder(new EmptyBorder(16,16,16,16));
        add(commentItemContent);

        JPanel commentNicknameWrap = new JPanel();
        commentNicknameWrap.setBackground(CustomStyle.white);
        commentNicknameWrap.setLayout(new FlowLayout(FlowLayout.LEFT));
        commentItemContent.add(commentNicknameWrap, BorderLayout.NORTH);

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

        /*
        PostRead.this.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e){
                //S: comment 내용 크기 변경
                //width: 창 크기 - (창 마진 + post 자체 마진 + textArea 마진 + 부모 선 마진)
                commentTxt.setSize(PostRead.this.getWidth() - (CustomStyle.DISPLAY_MARGIN*2 + 48 + 16+2+32), 20);
                //E: comment 내용 크기 변경
            }
        });*/
    }
    public void setCommentWidth(int frameWidth){
        commentTxt.setSize(frameWidth - (CustomStyle.DISPLAY_MARGIN*2 + 48 + 16+2+32), 20);
    }
}

package gui;

import gui.component.buttons.RoundButtons;
import gui.component.buttons.TextButton;
import gui.component.global.Header;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PostRead extends JFrame {

    PostRead(){
        setLayout(new BorderLayout());

        //S: header
        Header header = new Header();
        header.getButtonPostList().setIsCurrent(true);
        header.getButtonLogout().setVisible(false);
        header.getButtonSignUp().setVisible(false);
        add(header, BorderLayout.NORTH);
        //E: header

        //S: body
        JScrollPane bodyScrollWrap = new JScrollPane();
        bodyScrollWrap.setBorder(null);
        JPanel body = new JPanel();

        bodyScrollWrap.setViewportView(body);
        bodyScrollWrap.setBackground(CustomStyle.white);
        body.setBackground(CustomStyle.white);
        body.setLayout(new GridLayout(2,1));
        body.setBorder(new EmptyBorder(0, 96, 60, 96));
        add(bodyScrollWrap);
        //E: body

        //S:Post Area
        JPanel postArea = new JPanel();
        postArea.setBackground(CustomStyle.white);
        postArea.setBackground(CustomStyle.white);
        postArea.setLayout(new BorderLayout());
        body.add(postArea, BorderLayout.CENTER);
        //E:Post Area
        //S:ButtonTop Area
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new FlowLayout(FlowLayout.LEFT));
        postArea.add(buttonArea, BorderLayout.NORTH);
        RoundButtons buttonBack = new TextButton("< 뒤로가기");
        buttonArea.setBackground(CustomStyle.white);
        buttonArea.add(buttonBack);
        //E:ButtonTop Area
        //S: PostTitleArea
        JPanel postTitleArea = new JPanel();
        postTitleArea.setBackground(new Color(247, 247, 250));
        postTitleArea.setBorder(BorderFactory.createMatteBorder(2,0,0,0, CustomStyle.mainColor)
        );
        postTitleArea.setBorder(new EmptyBorder(24, 24, 24, 24));
        postArea.add(postTitleArea, BorderLayout.NORTH);
        //E: PostTitleArea
        //S: postTitleWrap
        JPanel postTitleWrap = new JPanel();
        //JLabel postTitle = new JLabel("이번 사태를 보면서 느낀 점 한가지가 있음");
        //postTitle.setFont(CustomStyle.setCutomFont(24, 'n'));
        //postTitleArea.add(postTitle);
        //E: postTitleWrap





        //S:comment Area
        JPanel commentArea = new JPanel();
        body.add(commentArea, BorderLayout.SOUTH);
        //E:comment Area

        //S: JFrame Setting
        setBackground(CustomStyle.white);
        setSize(1440, 800);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //E: JFrame Setting
    }

    public static void main(String[] args) {
        new PostRead();
    }
}

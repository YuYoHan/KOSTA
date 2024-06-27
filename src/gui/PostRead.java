package gui;

import gui.component.buttons.*;
import gui.component.global.Header;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PostRead extends JFrame {

    PostRead(){
        setLayout(new BorderLayout());
        UIManager.put("TextArea.inactiveForeground", Color.BLACK);//TextArea enabled(false)인 상태에서도 글씨색 그대로
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
        //bodyScrollWrap.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        bodyScrollWrap.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        bodyScrollWrap.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel body = new JPanel();

        bodyScrollWrap.setViewportView(body);
        bodyScrollWrap.setBackground(CustomStyle.white);
        body.setBackground(CustomStyle.white);
        body.setLayout(new BorderLayout());
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
        body.add(buttonArea, BorderLayout.NORTH);
        //E:ButtonTop Area
        //S: PostTitleArea
        JPanel postTitleArea = new JPanel();
        postTitleArea.setLayout(new BorderLayout());
        postTitleArea.setBorder(BorderFactory.createMatteBorder(2,0,0,0, CustomStyle.mainColor)
        );
        JPanel postTitleContent = new JPanel();
        postTitleContent.setBorder(new EmptyBorder(24, 24, 16, 24));
        postTitleContent.setBackground(new Color(247, 247, 250));
        postTitleContent.setLayout(new GridLayout(2,1));
        postTitleArea.add(postTitleContent);

        JTextArea postTitle = new JTextArea("이번 사태를 보면서 느낀 점 한가지가 있음. 이 제목이 길어지면 자연스럽게 텍스트가 내려가야함.이번 사태를 보면서 느낀 점 한가지가 있음. 이 제목이 길어지면 자연스럽게 텍스트가 내려가야함.");
        postTitle.setBackground(new Color(247, 247, 250));
        postTitle.setLineWrap(true);
        postTitle.setWrapStyleWord(true);
        postTitle.setEnabled(false);
        

        postTitle.setFont(CustomStyle.setCutomFont(24, 'n'));
        postTitleContent.add(postTitle);

        JPanel etcPostInfo = new JPanel();
        etcPostInfo.setLayout(new FlowLayout(FlowLayout.LEFT));
        etcPostInfo.setBackground(CustomStyle.transparency);
        postTitleContent.add(etcPostInfo);

        JLabel nickname = new JLabel("뜨거운 감자가 조아");
        nickname.setFont(CustomStyle.setCutomFont(16, 'n'));
        nickname.setForeground(CustomStyle.black50);
        etcPostInfo.add(nickname);
        JLabel postWriteDate = new JLabel("2023.12.20");
        postWriteDate.setFont(CustomStyle.setCutomFont(16, 'n'));
        postWriteDate.setForeground(CustomStyle.black50);
        postWriteDate.setBorder(new EmptyBorder(0, 8, 0, 0));
        etcPostInfo.add(postWriteDate);
        etcPostInfo.setBorder(new EmptyBorder(4, 0, 0, 0));
        postArea.add(postTitleArea, BorderLayout.NORTH);
        //E: PostTitleArea
        //S: PostContentArea
        JPanel postContent = new JPanel();
        postArea.add(postContent, BorderLayout.CENTER);

        postContent.setBorder(BorderFactory.createMatteBorder(1,0,0,0, CustomStyle.borderColor)
        );
        postContent.setLayout(new BorderLayout());
        JPanel postContentWrap = new JPanel();
        postContentWrap.setBackground(CustomStyle.white);
        postContentWrap.setBorder(new EmptyBorder(24, 24, 24, 24));
        postContentWrap.setLayout(new BorderLayout());

        postContent.add(postContentWrap, BorderLayout.CENTER);

        
        JTextArea postContentTxt = new JTextArea("대통령은 법률이 정하는 바에 의하여 훈장 기타의 영전을 수여한다. 비상계엄하의 군사재판은 군인·군무원의 범죄나 군사에 관한 간첩죄의 경우와 초병·초소·유독음식물공급·포로에 관한 죄중 법률이 정한 경우에 한하여 단심으로 할 수 있다. 다만, 사형을 선고한 경우에는 그러하지 아니하다." +
                "헌법재판소의 장은 국회의 동의를 얻어 재판관중에서 대통령이 임명한다. 군인 또는 군무원이 아닌 국민은 대한민국의 영역안에서는 중대한 군사상 기밀·초병·초소·유독음식물공급·포로·군용물에 관한 죄중 법률이 정한 경우와 비상계엄이 선포된 경우를 제외하고는 군사법원의 재판을 받지 아니한다." +
                "\n\n" +
                "대통령은 법률이 정하는 바에 의하여 훈장 기타의 영전을 수여한다. 비상계엄하의 군사재판은 군인·군무원의 범죄나 군사에 관한 간첩죄의 경우와 초병·초소·유독음식물공급·포로에 관한 죄중 법률이 정한 경우에 한하여 단심으로 할 수 있다. 다만, 사형을 선고한 경우에는 그러하지 아니하다." +
                "헌법재판소의 장은 국회의 동의를 얻어 재판관중에서 대통령이 임명한다. 군인 또는 군무원이 아닌 국민은 대한민국의 영역안에서는 중대한 군사상 기밀·초병·초소·유독음식물공급·포로·군용물에 관한 죄중 법률이 정한 경우와 비상계엄이 선포된 경우를 제외하고는 군사법원의 재판을 받지 아니한다." +
                "대통령은 법률이 정하는 바에 의하여 훈장 기타의 영전을 수여한다. 비상계엄하의 군사재판은 군인·군무원의 범죄나 군사에 관한 간첩죄의 경우와 초병·초소·유독음식물공급·포로에 관한 죄중 법률이 정한 경우에 한하여 단심으로 할 수 있다. 다만, 사형을 선고한 경우에는 그러하지 아니하다." +
                "\n\n" +
                "헌법재판소의 장은 국회의 동의를 얻어 재판관중에서 대통령이 임명한다. 군인 또는 군무원이 아닌 국민은 대한민국의 영역안에서는 중대한 군사상 기밀·초병·초소·유독음식물공급·포로·군용물에 관한 죄중 법률이 정한 경우와 비상계엄이 선포된 경우를 제외하고는 군사법원의 재판을 받지 아니한다." +
                "대통령은 법률이 정하는 바에 의하여 훈장 기타의 영전을 수여한다. 비상계엄하의 군사재판은 군인·군무원의 범죄나 군사에 관한 간첩죄의 경우와 초병·초소·유독음식물공급·포로에 관한 죄중 법률이 정한 경우에 한하여 단심으로 할 수 있다. 다만, 사형을 선고한 경우에는 그러하지 아니하다." +
                "헌법재판소의 장은 국회의 동의를 얻어 재판관중에서 대통령이 임명한다. 군인 또는 군무원이 아닌 국민은 대한민국의 영역안에서는 중대한 군사상 기밀·초병·초소·유독음식물공급·포로·군용물에 관한 죄중 법률이 정한 경우와 비상계엄이 선포된 경우를 제외하고는 군사법원의 재판을 받지 아니한다." +
                "\n\n" +
                "대통령은 법률이 정하는 바에 의하여 훈장 기타의 영전을 수여한다. 비상계엄하의 군사재판은 군인·군무원의 범죄나 군사에 관한 간첩죄의 경우와 초병·초소·유독음식물공급·포로에 관한 죄중 법률이 정한 경우에 한하여 단심으로 할 수 있다. 다만, 사형을 선고한 경우에는 그러하지 아니하다." +
                "헌법재판소의 장은 국회의 동의를 얻어 재판관중에서 대통령이 임명한다. 군인 또는 군무원이 아닌 국민은 대한민국의 영역안에서는 중대한 군사상 기밀·초병·초소·유독음식물공급·포로·군용물에 관한 죄중 법률이 정한 경우와 비상계엄이 선포된 경우를 제외하고는 군사법원의 재판을 받지 아니한다.");
        postContentTxt.setLineWrap(true);
        postContentTxt.setWrapStyleWord(true);

        postContentTxt.setFont(CustomStyle.setCutomFont(16, 'n'));
        postContentTxt.setForeground(CustomStyle.black);
        postContentTxt.setEnabled(false);
        postContentWrap.add(postContentTxt);
        //E: PostContentArea
        //S: PostAllowButtons
        JPanel postButtonsArea = new JPanel();
        postButtonsArea.setBorder(BorderFactory.createMatteBorder(1,0,0,0, CustomStyle.borderColor));
        postButtonsArea.setLayout(new BorderLayout());
        postContent.add(postButtonsArea, BorderLayout.SOUTH);

        JPanel postBottomButtons = new JPanel();
        postBottomButtons.setBackground(CustomStyle.white);
        postBottomButtons.setLayout(new FlowLayout());
        postBottomButtons.setBorder(new EmptyBorder(24, 24, 28, 24));
        postButtonsArea.add(postBottomButtons);

        DefaultButton toListButton = new DefaultButton("            목록으로            ");
        postBottomButtons.add(toListButton);

        //S: 포스트 작성자가 글을 조회했을때 보이는 버튼들
        DefaultButton toPostEdit = new DefaultButton("           게시글 수정           ");
        postBottomButtons.add(toPostEdit);
        DangerButton toPostDelete = new DangerButton("           게시글 삭제           ");
        postBottomButtons.add(toPostDelete);
        //E: 포스트 작성자가 글을 조회했을때 보이는 버튼들

        //E: PostAllowButtons
        //S: comment Area
        JPanel commentArea = new JPanel();
        commentArea.setBackground(CustomStyle.white);
        commentArea.setLayout(new BorderLayout());
        body.add(commentArea, BorderLayout.SOUTH);
        //E: comment Area
        //S: commentTotalArea
        JPanel commentTotalArea = new JPanel();
        commentTotalArea.setLayout(new FlowLayout(FlowLayout.LEFT));
        commentTotalArea.setBackground(CustomStyle.white);
        commentArea.add(commentTotalArea, BorderLayout.NORTH);

        JLabel defaultLabel = new JLabel("댓글 ");
        defaultLabel.setFont(CustomStyle.setCutomFont(16, 'n'));
        commentTotalArea.add(defaultLabel);

        JLabel commentNumLabel = new JLabel("234");
        commentNumLabel.setForeground(CustomStyle.mainColor);
        commentNumLabel.setFont(CustomStyle.setCutomFont(16, 'b'));
        commentTotalArea.add(commentNumLabel);
        //E: commentTotalArea
        //S: commentWriteArea
        JPanel commentWriteArea = new JPanel();
        commentWriteArea.setBackground(CustomStyle.white);
        commentWriteArea.setLayout(new BorderLayout());
        commentArea.add(commentWriteArea, BorderLayout.CENTER);
        //E: commentWriteArea

        //S: commentWriteTop
        JPanel commentWriteTop = new JPanel();
        commentWriteTop.setBackground(CustomStyle.white);
        commentWriteTop.setBorder(new CustomStyle.DrowSquare(CustomStyle.borderColor,CustomStyle.white));
        commentWriteTop.setLayout(new BorderLayout());
        commentWriteArea.add(commentWriteTop);

        JPanel commentWriteWrap = new JPanel();
        commentWriteWrap.setBackground(CustomStyle.white);
        commentWriteWrap.setBorder(new EmptyBorder(8,0,8,0));
        commentWriteTop.add(commentWriteWrap);

        JTextArea commentWriteField = new JTextArea(3, 10);
        commentWriteField.setEnabled(true);
        commentWriteField.setFont(CustomStyle.setCutomFont(16, 'n'));
        commentWriteField.setLineWrap(true);
        commentWriteField.setWrapStyleWord(true);
        commentWriteWrap.add(commentWriteField);
        //E: commentWriteTop
        //S: commentWriteBottom
        JPanel commentWriteBottom = new JPanel();
        commentWriteBottom.setBackground(CustomStyle.white);
        commentWriteBottom.setLayout(new BorderLayout());
        commentWriteBottom.setBorder(BorderFactory.createMatteBorder(0,1,1,1, CustomStyle.borderColor));
        commentWriteArea.add(commentWriteBottom, BorderLayout.SOUTH);

        JPanel commentWriteBottomContent = new JPanel();
        commentWriteBottomContent.setBackground(new Color(240, 240,245));
        commentWriteBottomContent.setBorder(new EmptyBorder(8, 24, 8, 24));
        commentWriteBottomContent.setLayout(new BorderLayout());
        commentWriteBottom.add(commentWriteBottomContent);

        JPanel commentCharLengthWrap = new JPanel();
        commentCharLengthWrap.setBackground(new Color(240, 240,245));
        commentCharLengthWrap.setLayout(new FlowLayout(FlowLayout.LEFT));
        commentCharLengthWrap.setBorder(new EmptyBorder(12, 4, 12, 4));

        commentWriteBottom.add(commentCharLengthWrap, BorderLayout.WEST);


        JLabel commentCharLength = new JLabel("12");
        commentCharLength.setForeground(CustomStyle.black50);
        commentCharLength.setFont(CustomStyle.setCutomFont(12, 'n'));
        commentCharLengthWrap.add(commentCharLength);


        JLabel commentLengthSeparator = new JLabel(" / ");
        commentLengthSeparator.setForeground(CustomStyle.black50);
        commentLengthSeparator.setFont(CustomStyle.setCutomFont(12, 'n'));
        commentCharLengthWrap.add(commentLengthSeparator);

        JLabel commentCharlimitLangth = new JLabel("300");
        commentCharlimitLangth.setForeground(CustomStyle.black50);
        commentCharlimitLangth.setFont(CustomStyle.setCutomFont(12, 'n'));
        commentCharLengthWrap.add(commentCharlimitLangth);

        //여기서만 쓰는 버튼이므로 새로 정의한다.
        JButton commentWriteButton = new JButton();
        commentWriteButton.setFocusPainted(false);
        commentWriteButton.setBorderPainted(false);
        commentWriteButton.setContentAreaFilled(false);
        commentWriteButton.setLayout(new BorderLayout());
        commentWriteButton.setMargin(new Insets(-4,0,-4,-4));
        JPanel commentWriteButtonPanel = new JPanel();

        commentWriteButton.add(commentWriteButtonPanel);
        commentWriteButtonPanel.setBorder(new CustomStyle.DrawRoundSquare(0, CustomStyle.mainColor, CustomStyle.mainColor));
        JLabel commentWriteButtonLabel = new JLabel("      등록      ");
        commentWriteButtonLabel.setBorder(new EmptyBorder(4, 0, 0, 0));
        commentWriteButtonLabel.setFont(CustomStyle.setCutomFont(14, 'b'));
        commentWriteButtonLabel.setForeground(CustomStyle.white);
        commentWriteButtonPanel.add(commentWriteButtonLabel);
        commentWriteBottom.add(commentWriteButton, BorderLayout.EAST);
        commentWriteButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                commentWriteButtonPanel.setBorder(new CustomStyle.DrawRoundSquare(0, CustomStyle.primaryactiveColor, CustomStyle.primaryactiveColor));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                commentWriteButtonPanel.setBorder(new CustomStyle.DrawRoundSquare(0, CustomStyle.primaryhoverColor, CustomStyle.primaryhoverColor));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                commentWriteButtonPanel.setBorder(new CustomStyle.DrawRoundSquare(0, CustomStyle.primaryhoverColor, CustomStyle.primaryhoverColor));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                commentWriteButtonPanel.setBorder(new CustomStyle.DrawRoundSquare(0, CustomStyle.mainColor, CustomStyle.mainColor));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        //E: commentWriteBottom
        //S: commentList
        JPanel commentList = new JPanel();
        commentList.setBorder(new EmptyBorder(16, 0, 0, 0));
        commentList.setBackground(CustomStyle.white);
        commentList.setLayout(new BorderLayout());
        commentArea.add(commentList, BorderLayout.SOUTH);
        //E: commentList
        //S: commentListWrap
        Box commentListWrap = Box.createVerticalBox();//수직으로 배치하는 박스레이아웃
        commentListWrap.setBackground(CustomStyle.white);
        commentList.add(commentListWrap);

        ArrayList<CommentItem> commentItems = new ArrayList<>();
        commentItems.add(new CommentItem("1","1","1"));
        commentItems.add(new CommentItem("2","2","2"));

        commentItems.stream().forEach(
                item -> commentListWrap.add(item)
        );
        //E: commentListWrap
        //S: commentPagenation

        Pagenation commentPagenation = new Pagenation(24);
        commentList.add(commentPagenation, BorderLayout.SOUTH);
        //E: commentPagenation



        //S: JFrame Setting

        addComponentListener(new ComponentAdapter() {
            //창크기가 조절되면 textArea의 크기를 조절하기
            public void componentResized(ComponentEvent e) {

                Dimension frameSize = e.getComponent().getSize();
                //S: 포스트 제목 크기 변경
                postTitle.setSize(frameSize.width - (CustomStyle.DISPLAY_MARGIN*2 + 48 + 16), 20);
                //E: 포스트 제목 크기 변경
                //S: 포스트 내용 크기 변경
                //width: 창 크기 - (창 마진 + post 자체 마진 + textArea 마진)
                postContentTxt.setSize(frameSize.width - (CustomStyle.DISPLAY_MARGIN*2 + 48 + 16), 20);
                //E: 포스트 내용 크기 변경

                //S: comment TextField 크기 변경
                //width: 창 크기 - (창 마진 + post 자체 마진 + textArea 마진 + 부모 선 마진)
                commentWriteField.setSize(frameSize.width - (CustomStyle.DISPLAY_MARGIN*2 + 48 + 16+2), 20);
                //E: comment TextField 크기 변경

            }
        });
        setBackground(CustomStyle.white);
        setSize(1440, 800);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //E: JFrame Setting
    }
    
    //코멘트의 코드가 복잡하고 몇개가 될지 모르므로 따로 클래스로 분리
    public class CommentItem extends JPanel {
        CommentItem(String nickname, String date, String txt){

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

            JTextArea commentTxt = new JTextArea();
            commentTxt.setText(txt);
            commentTxt.setFont(CustomStyle.setCutomFont(16, 'n'));
            commentTxt.setLineWrap(true);
            commentTxt.setWrapStyleWord(true);
            commentTxt.setEnabled(false);
            commentTxtWrap.add(commentTxt);

            PostRead.this.addComponentListener(new ComponentAdapter(){
                public void componentResized(ComponentEvent e){
                    //S: comment 내용 크기 변경
                    //width: 창 크기 - (창 마진 + post 자체 마진 + textArea 마진 + 부모 선 마진)
                    commentTxt.setSize(PostRead.this.getWidth() - (CustomStyle.DISPLAY_MARGIN*2 + 48 + 16+2+32), 20);
                    //E: comment 내용 크기 변경
                }
            });
        }
    }

    public static void main(String[] args) {
        new PostRead();
    }
}

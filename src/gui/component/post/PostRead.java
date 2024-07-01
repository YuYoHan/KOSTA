package gui.component.post;

import config.SessionManager;
import dao.CommentDAO;
import dao.UserDAO;
import dto.CommentComponentDTO;
import dto.CommentDTO;
import dto.PostResponseDTO;
import dto.UserDTO;
import gui.component.global.CustomStyle;
import gui.component.buttons.*;
import gui.component.comment.Comment;
import gui.component.global.Header;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PostRead extends JFrame {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DefaultButton toListButton;
    private DefaultButton toPostEdit;
    private DangerButton toPostDelete;
    private PostResponseDTO postDTO;

    ArrayList<Comment> comments;
    String commentCnt;

    public PostResponseDTO getPostDTO() {
        return postDTO;
    }

    public ArrayList<Comment> createCommentsList() {
        comments.clear();
        // 해당 게시글 번호를 참조한 댓글 리스트로 댓글 생성
        for (CommentDTO comment : CommentDAO.listComment(30)) {
            comments.add(new Comment(comment));
        }
        return comments;
    }
    public PostRead(PostResponseDTO postDTO){
        this.postDTO = postDTO;
        comments = new ArrayList<>();
        setLayout(new BorderLayout());
        UIManager.put("TextArea.inactiveForeground", Color.BLACK);//TextArea enabled(false)인 상태에서도 글씨색 그대로
        //S: header
        Header header = new Header(this);
        header.getButtonPostList().setIsCurrent(true);
        header.getButtonLogout().setVisible(false);
        header.getButtonSignUp().setVisible(false);
        add(header, BorderLayout.NORTH);
        //E: header

        //S: body
        JScrollPane bodyScrollWrap = new JScrollPane();
        bodyScrollWrap.setBorder(null);
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
        // 뒤로가기 버튼
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PostList();
            }
        });
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

        JTextArea postTitle = new JTextArea(postDTO.getPostTitle());
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

        JLabel nickname = new JLabel(postDTO.getNickname());
        nickname.setFont(CustomStyle.setCutomFont(16, 'n'));
        nickname.setForeground(CustomStyle.black50);
        etcPostInfo.add(nickname);
        JLabel postWriteDate = new JLabel(dateFormat.format(postDTO.getPostRegTime()));
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

        
        JTextArea postContentTxt = new JTextArea(postDTO.getPostContents());
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

        toListButton = new DefaultButton("            목록으로            ");
        postBottomButtons.add(toListButton);

        // 목록으로 버튼
        toListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PostList();
            }
        });

        toPostEdit = new DefaultButton("           게시글 수정           ");
        postBottomButtons.add(toPostEdit);
        toPostDelete = new DangerButton("           게시글 삭제           ");
        postBottomButtons.add(toPostDelete);

        //S: 포스트 작성자가 글을 조회했을때 보이는 버튼들
        if(!(SessionManager.getCurrentUser().equals(postDTO.getNickname()))){
            toPostEdit.setVisible(false);
            toPostDelete.setVisible(false);
        }
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

        // 해당 게시글 번호를 참조한 댓글 리스트로 댓글 생성
        createCommentsList();
        commentCnt=comments.size()+"";

        JLabel commentNumLabel = new JLabel(commentCnt);
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
            public void mouseClicked(MouseEvent e) {
                UserDTO userDTO = UserDAO.selectByNickName(SessionManager.getCurrentUser());
                int userId = userDTO.getUserId();
                System.out.println("userId = " + userId);
                String contents=commentWriteField.getText();
                System.out.println("contents = " + contents);
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setCommentContents(contents);
                commentDTO.setPostId(30);
                commentDTO.setUserId(userId);
                int re=CommentDAO.insertComment(commentDTO);
                System.out.println(re);
                commentWriteField.setText(null);
                createCommentsList();
                repaint();
            }

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

        comments.stream().forEach(
                item -> commentListWrap.add(item)
        );
//        //E: commentListWrap
//        //S: commentPagenation

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

                //S: comment 내용 크기 변경
                comments.stream().forEach(
                        item -> item.setCommentWidth(frameSize.width)
                );
                //E: comment 내용 크기 변경
            }
        });
        setBackground(CustomStyle.white);
        setSize(1440, 800);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //E: JFrame Setting
    }
}

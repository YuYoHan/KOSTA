package gui.component.post;

import dao.PostDAO;
import dto.PostResponseDTO;
import gui.component.buttons.DefaultButton;
import gui.component.buttons.PrimaryButton;
import gui.component.global.CustomStyle;
import gui.component.global.Header;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


// 게시글 생성 클래스
public class EditPost extends JFrame {
    private JTextField titleField;
    private JTextArea contentField;
    private PostResponseDTO postDTO;

    public PostResponseDTO getPostDTO() {
        return postDTO;
    }

    public void setPostDTO(PostResponseDTO postDTO) {
        this.postDTO = postDTO;
    }

    public EditPost(JFrame mainPage) {
        Header header = new Header(this);
        header.getButtonLogin().setVisible(false);
        header.getButtonSignUp().setVisible(false);
        add(header, BorderLayout.NORTH);

        // panel 에 제목 panel2에 내용, panel3에 버튼
        JPanel main = new JPanel();
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        main.setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);
        panel2.setBackground(Color.WHITE);
        panel3.setBackground(Color.WHITE);

        main.setLayout(new BorderLayout());
        add(main, BorderLayout.CENTER);
        main.add(panel, BorderLayout.NORTH);
        main.add(panel2, BorderLayout.CENTER);
        main.add(panel3, BorderLayout.SOUTH);

        // 스크롤 제공하는 컨테이너
        JScrollPane bodyScrollWrap = new JScrollPane();
        // 테두리 제거
        bodyScrollWrap.setBorder(null);
        // 수직 스크롤바를 필요 시에만 스크롤 뜨게 설정
        bodyScrollWrap.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        // 수평 스크롤바를 표시하지 않음
        bodyScrollWrap.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // main 판넬을 스크롤 영역으로 지정
        bodyScrollWrap.setViewportView(main);
        add(bodyScrollWrap, BorderLayout.CENTER);


        // 제목, 내용 필드 추가
        titleField = new JTextField();
        titleField.setHorizontalAlignment(JTextField.LEFT);
        titleField.setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.GRAY)
        );
        titleField.setPreferredSize(new Dimension(1200,50));
        panel.add(titleField);


        contentField = new JTextArea();
        contentField.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.GRAY)
        );

        panel2.add(contentField);

        // 자동 줄바꿈 설정
        contentField.setLineWrap(true);
        // 단어 반위 줄바꿈 설정
        contentField.setWrapStyleWord(true);

        // 등록, 취소 버튼 추가
        JButton addButton = new PrimaryButton("수정");
        JButton cancelButton = new DefaultButton("취소");

        // 버튼 크기 설정
        addButton.setPreferredSize(new Dimension(130,60));
        cancelButton.setPreferredSize(new Dimension(130,60));

        // 버튼 배치
        panel3.add(addButton);
        panel3.add(cancelButton);
        panel3.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 15));

        // 창 끝에 딱 붙지 않게 간격 조정
        panel3.add(Box.createHorizontalStrut(110));


        // JFrame 설정
        setSize(1440, 800);
        setVisible(true);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension frameSize = e.getComponent().getSize();
                contentField.setSize(frameSize.width - (CustomStyle.DISPLAY_MARGIN*2 + 48), 20);
            }
        });

        //포스터 수정 버튼
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PostDAO.postUpdate(postDTO.getPostId(), titleField.getText(), contentField.getText());
                dispose();
                postDTO.setPostTitle(titleField.getText());
                postDTO.setPostContents(contentField.getText());
                new PostRead(postDTO);
                mainPage.dispose();
            }
        });

        //취소버튼
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // 현재창 닫기
                mainPage.setVisible(true);  // 메인 창으로 복귀
            }
        });
    }
    public JTextField getTitleField(){
        return titleField;
    }
    public JTextArea getContentField(){
        return contentField;
    }

    public void setContentField(String contentsTxt) {
        this.contentField.setText(contentsTxt);
    }

    public void setTitleField(String titleTxt){
        this.titleField.setText(titleTxt);
    }
}
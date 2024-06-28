package gui;

import gui.component.buttons.DefaultButton;
import gui.component.buttons.PrimaryButton;
import gui.component.global.Header;
import gui.component.global.HintTextArea;
import gui.component.global.HintTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class CreatePost extends JFrame {
    private HintTextField titleField;
    private HintTextArea contentField;

    public CreatePost(Index mainPage) {
        Header header = new Header();
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
        titleField = new HintTextField("제목을 입력하세요");
        titleField.setHorizontalAlignment(JTextField.LEFT);
        titleField.setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.GRAY)
        );
        titleField.setPreferredSize(new Dimension(1200,50));
        panel.add(titleField);


        contentField = new HintTextArea("내용을 입력하세요");
        contentField.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.GRAY)
        );

        panel2.add(contentField);

        // 자동 줄바꿈 설정
        contentField.setLineWrap(true);
        // 단어 반위 줄바꿈 설정
        contentField.setWrapStyleWord(true);

        // 등록, 취소 버튼 추가
        JButton addButton = new PrimaryButton("등록");
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // 현재창 닫기
                mainPage.setVisible(true);  // 메인 창으로 복귀
            }
        });

    }
    public static void main(String[] args){
        new CreatePost(new Index());

    }
}


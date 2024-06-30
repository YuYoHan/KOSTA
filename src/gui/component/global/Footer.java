package gui.component.global;

import gui.CustomStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Footer extends JPanel{
    public Footer(){
        // 텍스트라벨 담을 패널 생성
        setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel panelRight = new JPanel();
        panelRight.setLayout(new GridLayout(5,1));
        panelRight.setBackground(Color.white);

        // 왼쪽 로고라벨 생성
        ImageIcon logoImage = new ImageIcon(getClass().getClassLoader().getResource("img/footer_logo.png"));
        Image getlogoImage = logoImage.getImage();
        Image changelogoImage = getlogoImage.getScaledInstance(100, 25, Image.SCALE_SMOOTH);
        ImageIcon changelogoImageIcon = new ImageIcon(changelogoImage);
        JLabel logoLabel = new JLabel(changelogoImageIcon);
        logoLabel.setBorder(new EmptyBorder(0, CustomStyle.DISPLAY_MARGIN, 60, CustomStyle.DISPLAY_MARGIN));

        // 텍스트 라벨 생성 후 배경색 화이트로 변경
        JLabel kosta = new JLabel("©KOSTA.");
        kosta.setFont(CustomStyle.setCutomFont(13,'n'));
        kosta.setOpaque(true);
        kosta.setBackground(Color.white);
        JLabel ceo = new JLabel("대표이사 : 유요한 사업자 | 등록 번호 : 120-81-47521");
        ceo.setFont(CustomStyle.setCutomFont(13,'n'));
        ceo.setOpaque(true);
        ceo.setBackground(Color.white);
        JLabel address = new JLabel("주소 :  서울 종로구 우정국로2길 21 대왕빌딩");
        address.setFont(CustomStyle.setCutomFont(13,'n'));
        address.setOpaque(true);
        address.setBackground(Color.white);
        JLabel tel = new JLabel("고객센터 : 1544-3377");
        tel.setFont(CustomStyle.setCutomFont(13,'n'));
        tel.setOpaque(true);
        tel.setBackground(Color.white);
        JLabel email = new JLabel("이메일 : zxzz7608@gmail.com");
        email.setFont(CustomStyle.setCutomFont(13,'n'));
        email.setOpaque(true);
        email.setBackground(Color.white);

        // 로고와 텍스트 담기
        panelRight.add(kosta);
        panelRight.add(ceo);
        panelRight.add(address);
        panelRight.add(tel);
        panelRight.add(email);

        // footer 패널에 담기
        add(logoLabel);
        add(panelRight);
        setBackground(Color.white);

    }
}

package gui.component.user;


import config.PasswordEncode;
import config.SessionManager;
import dao.UserDAO;
import dto.UserDTO;
import gui.Index;
import gui.component.global.HintPasswordField;
import gui.component.global.HintTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends JFrame {
    private HintTextField userLoginId;
    private HintPasswordField userPassword;

    public Login(Index mainPage) {
        // 로그인 구현할 패널 생성
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // 메인 페이지 이동
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel toMainPage = new JLabel("<HTML><U>메인 페이지</U></HTML>");
        toMainPage.setForeground(Color.BLUE);
        toMainPage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        topPanel.add(toMainPage);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        toMainPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
                setVisible(false);
            }
        });

        // 타이틀 설정
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        // 제목 추가
        JLabel titleLabel = new JLabel("BELOG");
        titleLabel.setFont(new Font("Spoqa Sans", Font.BOLD, 24));
        // 가운데 정렬
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);
        // 공백 추가
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // 회원 가입할 때 입력할 칸
        JPanel fieldPanel =
                new JPanel(new GridBagLayout());
        fieldPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // 여백 설정

        userLoginId = new HintTextField("아이디");
        // 예시로 너비 170, 높이 30 설정
        userLoginId.setPreferredSize(new Dimension(170, 30));
        gbc.gridwidth = 2; // 아이디 필드는 2개의 열을 차지하도록 설정
        fieldPanel.add(userLoginId, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1; // 다음 요소는 한 열만 차지하도록 다시 설정

        // 아이디에 포커스안가지게 막음
        userLoginId.setFocusable(false);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        userPassword = new HintPasswordField("비밀번호");
        userPassword.setPreferredSize(new Dimension(170, 30));
        fieldPanel.add(userPassword, gbc);


        userPassword.setFocusable(false);

        // 다시 클릭하면 포커스가 가지게 설정
        userLoginId.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Java Swing 컴포넌트에서 포커스를 요청하는 메서드입니다.
                // 이 메서드를 호출하면 해당 컴포넌트가 포커스를 얻으려 시도합니다
                userLoginId.requestFocusInWindow();
                userLoginId.setFocusable(true);
                userPassword.setFocusable(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                userLoginId.requestFocusInWindow();
                userLoginId.setFocusable(true);
                userPassword.setFocusable(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });



        mainPanel.add(fieldPanel, BorderLayout.CENTER);

        // 로그인 버튼 추가
        JButton LoginButton = new JButton("로그인");
        LoginButton.setBackground(new Color(128, 0, 128)); // 보라색
        LoginButton.setForeground(Color.WHITE);
        LoginButton.setFocusPainted(false);
        LoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(LoginButton);


        JLabel label = new JLabel();
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        footerPanel.add(label);

        // 패널을 프레임에 추가
        add(mainPanel);
        add(footerPanel, BorderLayout.SOUTH);

        // JFrame 설정
        setTitle("로그인");
        setSize(300, 300);
        setLocationRelativeTo(null); // 화면 가운데 정렬
        setVisible(true);

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 각 필드에서 데이터 가져오기
                    String id = userLoginId.getText();
                    // char[]로 가져온 비밀번호를 String으로 변환
                    String password = new String(userPassword.getPassword());


                    if (!check(id)) {
                        JOptionPane.showMessageDialog(null, "아이디가 잘못되었습니다.");
                        return;
                    }


                    // 비밀번호 조건 검사
                    if (!checkPw(password)) {
                        JOptionPane.showMessageDialog(null, "비밀번호는 대소문자, 숫자, 특수문자를 포함하여 8글자 이상 20글자 이하여야 합니다.");
                        return; // 비밀번호 조건 충족하지 않으면 메서드 종료
                    }

                    // 외부 클래스에 인코드 작업해서 암호화
                    String hashPassword = PasswordEncode.encode(password);
                    System.out.println("암호화 비밀번호 : " + hashPassword);
                    password = hashPassword;


                    // DB에 insert문
                    UserDTO findUser = UserDAO.select(id);
                    System.out.println("아이디 체크 : " + findUser);

                    if (findUser != null) {
                        JOptionPane.showMessageDialog(null, "로그인 성공");
                        SessionManager.loginUser(findUser.getNickName());
                        String currentUser = SessionManager.getCurrentUser();
                        System.out.println(currentUser);
                        dispose(); // 현재 창 닫기
                        new Index();
                        mainPage.setVisible(false);

                    } else {
                        JOptionPane.showMessageDialog(null, "로그인 실패");
                        SessionManager.loginUser(null);
                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    // 해당 아이디가 있는지 그리고 조건에 맞는지 체크
    private static boolean check(String id) {
        try {
            // DB에 해당 아이디가 있는지 조회
            UserDTO findUser = UserDAO.select(id);
            System.out.println(findUser);
            // 영어와 숫자로만 아이디를 작성하게 정규식 설정
            String regex = "^[a-zA-Z0-9]{5,30}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(id);
            System.out.println("아이디 검증 : " + matcher.matches());

            if (findUser.getUserLoginID() != null) {
                return matcher.matches();
            }
            return false;
        } catch (Exception e) {
            System.out.println("이미 존재하는 회원인지 체크 실패 : " + e.getMessage());
            return false;
        }
    }

    // 소문자, 대문자, 0~9 숫자, 특수문자 8자리 이상!(소문자, 대문자 같이 안써도 됨!)
    // 위에서 비밀번호를 생성하기위한 조건을 주기위해서 메소드 만들었다!
    private static boolean checkPw(String userpw) {
        String passwordPolicy = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$";
        System.out.println("비밀번호 체크 : " + passwordPolicy);
        Pattern pattern_pwd = Pattern.compile(passwordPolicy);
        Matcher matcher_pwd = pattern_pwd.matcher(userpw);
        return matcher_pwd.matches();
    }

    public static void main(String[] args) {
        new Login(new Index());
    }
}

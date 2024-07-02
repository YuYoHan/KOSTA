package gui.component.user;


import config.PasswordEncode;
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

public class SignUp extends JFrame {
    // 로그인 유저 아이디
    private HintTextField userLoginId;
    // 비밀번호
    private HintPasswordField userPassword;
    // 비밀번호 확인
    private HintPasswordField confirmPasswordField;
    // 유저 이메일
    private HintTextField userEmail;
    // 유저 닉네임
    private HintTextField userNickName;
    // 아이디와 이메일 체크시 확인하는 변수들
    private boolean idAvailable = false;
    private boolean emailAvailable = false;

    public SignUp(JFrame mainPage) {
        // 회원가입 구현할 패널 생성
        JPanel mainPanel = new JPanel();
        // 세로로 쌓이도록 설정
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // 로그인 부분
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);
        JLabel loginLabel = new JLabel("<HTML><U>로그인 화면</U></HTML>");
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        topPanel.add(loginLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);


        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Login(mainPage);
            }
        });
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
        JButton checkIdButton = new JButton("중복 검사");
        checkIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = userLoginId.getText();
                System.out.println("아이디 체크 : " + id);
                if (check(id)) {
                    JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
                    idAvailable = true;
                } else if (id == null || id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
                    idAvailable = false;
                } else {
                    JOptionPane.showMessageDialog(null, "사용할 수 없는 아이디입니다.");
                    idAvailable = false;
                }
            }
        });
        // 아이디에 포커스안가지게 막음
        userLoginId.setFocusable(false);

        // 다시 클릭하면 포커스가 가지게 설정
        userLoginId.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Java Swing 컴포넌트에서 포커스를 요청하는 메서드입니다.
                // 이 메서드를 호출하면 해당 컴포넌트가 포커스를 얻으려 시도합니다
                userLoginId.requestFocusInWindow();
                userLoginId.setFocusable(true);

            }

            @Override
            public void mousePressed(MouseEvent e) {
                userLoginId.requestFocusInWindow();
                userLoginId.setFocusable(true);
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


        checkIdButton.setBackground(new Color(128, 0, 128));
        checkIdButton.setForeground(Color.WHITE);
        checkIdButton.setFocusPainted(false);
        fieldPanel.add(checkIdButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        userPassword = new HintPasswordField("비밀번호");
        userPassword.setPreferredSize(new Dimension(170, 30));
        fieldPanel.add(userPassword, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        confirmPasswordField = new HintPasswordField("비밀번호 확인");
        confirmPasswordField.setPreferredSize(new Dimension(170, 30));
        fieldPanel.add(confirmPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        userEmail = new HintTextField("이메일");
        userEmail.setPreferredSize(new Dimension(170, 30));
        fieldPanel.add(userEmail, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        JButton checkEmailButton = new JButton("중복 검사");
        checkEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = userEmail.getText();
                if (checkEmail(email)) {
                    JOptionPane.showMessageDialog(null, "사용가능한 이메일입니다.");
                    emailAvailable = true;
                } else if (email == null || email.isEmpty()) {
                    System.out.println(email);
                    JOptionPane.showMessageDialog(null, "이메일을 입력하세요");
                    emailAvailable = false;
                } else if (email.matches("^[가-힣]*$") ||
                        !(email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
                    JOptionPane.showMessageDialog(null, "이메일 형식을 맞춰주세요.");
                    emailAvailable = false;
                } else {
                    JOptionPane.showMessageDialog(null, "이미 존재하는 이메일입니다.");
                    emailAvailable = false;
                }
            }
        });
        checkEmailButton.setBackground(new Color(128, 0, 128));
        checkEmailButton.setForeground(Color.WHITE);
        checkEmailButton.setFocusPainted(false);
        fieldPanel.add(checkEmailButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        userNickName = new HintTextField("닉네임");
        userNickName.setPreferredSize(new Dimension(170, 30));
        fieldPanel.add(userNickName, gbc);


        mainPanel.add(fieldPanel, BorderLayout.CENTER);


        // 확인 버튼 추가
        JButton signUpButton = new JButton("등록");
        signUpButton.setBackground(new Color(128, 0, 128)); // 보라색
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(signUpButton);

        JLabel label = new JLabel();
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        footerPanel.add(label);

        // 패널을 프레임에 추가
        add(mainPanel);
        add(footerPanel, BorderLayout.SOUTH);

        // JFrame 설정
        setTitle("회원가입");
        setSize(400, 500);
        setLocationRelativeTo(null); // 화면 가운데 정렬
        setVisible(true);

        // 회원가입
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 각 필드에서 데이터 가져오기
                    String id = userLoginId.getText();
                    // char[]로 가져온 비밀번호를 String으로 변환
                    String password = new String(userPassword.getPassword());
                    String confirmPassword = new String(confirmPasswordField.getPassword());
                    String email = userEmail.getText();
                    String nickName = userNickName.getText();
                    System.out.println(id + " : " + password + ", " + confirmPassword + ", " + email + ", " + nickName);


                    if (!checkNickName(nickName)) {
                        JOptionPane.showMessageDialog(null, "한글로 4~30글자 이내로 해주세요");
                        return;
                    }

                    if (idAvailable == false || emailAvailable == false) {
                        JOptionPane.showMessageDialog(null, "회원가입 실패했습니다.");
                        return;
                    }


                    // 비밀번호 조건 검사
                    if (!checkPw(password) || !checkPw(confirmPassword)) {
                        JOptionPane.showMessageDialog(null, "비밀번호는 대소문자, 숫자, 특수문자를 포함하여 8글자 이상 20글자 이하여야 합니다.");
                        return; // 비밀번호 조건 충족하지 않으면 메서드 종료
                    }

                    // 비밀번호 일치 여부 확인
                    if (!password.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
                        return; // 비밀번호 불일치 시 메서드 종료
                    }


                    // 외부 클래스에 인코드 작업해서 암호화
                    String hashPassword = PasswordEncode.encode(password);
                    System.out.println("암호화 비밀번호 : " + hashPassword);
                    password = hashPassword;

                    System.out.println("회원 가입 성공");

                    UserDTO user = new UserDTO();
                    user.setUserLoginID(id);
                    user.setUserPw(password);
                    user.setUserEmail(email);
                    user.setNickName(nickName);
                    System.out.println(user);

                    // DB에 insert문
                    int insert = UserDAO.insert(user);

                    if (insert > 0) {
                        JOptionPane.showMessageDialog(null, "회원 가입 성공");
                        dispose(); // 현재 창 닫기
                        mainPage.setVisible(true); // 메인 창 다시 보이기
                    } else {
                        JOptionPane.showMessageDialog(null, "회원 가입 실패");
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

            if (findUser.getUserLoginID() == null) {
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

    // 닉네임 검사
    private static boolean checkNickName(String nickName) {
        String regex = "^[a-zA-Z0-9가-힣]{4,30}$";
        System.out.println("닉네임 체크 : " + nickName);
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(nickName);
        System.out.println(matcher);

        UserDTO findNickName = UserDAO.selectByNickName(nickName);
        System.out.println(findNickName);

        if (findNickName.getNickName() == null) {
            return matcher.matches();
        }
        return false;
    }


    // 이메일 검사
    private static boolean checkEmail(String email) {
        System.out.println("이메일 체크 : " + email);
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern patternEmail = Pattern.compile(emailPattern);
        Matcher matcherEmail = patternEmail.matcher(email);
        // 해당 이메일이 있는지 체크
        UserDTO findEmail = UserDAO.selectByEmail(email);
        System.out.println("이메일 조회 : " + findEmail);
        System.out.println(matcherEmail.matches());


        if (findEmail.getUserEmail() == null) {
            return matcherEmail.matches();
        }
        return false;
    }

    public static void main(String[] args) {
        new SignUp(new Index());
    }

}

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
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfo extends JFrame {
    private JTextField userLoginId;
    private HintPasswordField userPassword;
    private JTextField userEmail;
    private boolean emailAvailable;
    private JTextField userNickName;


    private Vector<String> colNames;
    private Vector<Vector<String>> rowData;
    private JTable table;
    private String selectedId;

    public UserInfo(JFrame mainPage) {
        // 회원수정 패널
        JPanel mainPanel = new JPanel();
        // 세로로 쌓이게
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.white);

        // 메인 페이지로
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
                new Index();
                mainPage.setVisible(false);
            }
        });

        // 타이틀 구성
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

        // 글씨를 보여줄 패널
        JPanel fieldPanel = new JPanel(new GridBagLayout());
        fieldPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // 여백 설정

        loadUser();

        // 네모칸 크기 조절
        userLoginId = new JTextField(15);
        // userLoginId 필드를 수정 불가능하게 설정
        userLoginId.setEditable(false);
        // 크기 조절
        userLoginId.setPreferredSize(new Dimension(170, 30));
        userLoginId.setText(selectedId);
        userLoginId.setBackground(Color.WHITE);
        // 네모칸 선줄을 검은색으로 수정
        userLoginId.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridwidth = 2;
        System.out.println("가져온 아이디 : " + selectedId);
        fieldPanel.add(userLoginId, gbc);


        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        userPassword = new HintPasswordField("비밀번호");
        userPassword.setPreferredSize(new Dimension(170, 30));
        fieldPanel.add(userPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        userEmail = new HintTextField("이메일");
        userEmail.setPreferredSize(new Dimension(170, 30));
        fieldPanel.add(userEmail, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;

        // 이메일 중복검사 버튼
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
                        !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
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

        // 닉네임
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        userNickName = new HintTextField("닉네임");
        userNickName.setPreferredSize(new Dimension(170, 30));
        fieldPanel.add(userNickName, gbc);

        mainPanel.add(fieldPanel, BorderLayout.CENTER);


        // 수정하기 버튼
        JButton btnUpdate = new JButton("수정");
        btnUpdate.setBackground(new Color(128, 0, 128)); // 보라색
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
        // 삭제하기 버튼
        JButton btnRemove = new JButton("삭제");
        btnRemove.setBackground(new Color(128, 0, 128)); // 보라색
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFocusPainted(false);
        btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel footerPanel = new JPanel();
        footerPanel.add(btnUpdate);
        footerPanel.add(btnRemove);
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        add(mainPanel);
        add(footerPanel, BorderLayout.SOUTH);


        loadUser();

        // JFrame 설정
        setTitle("회원수정");
        setSize(650, 450);
        setLocationRelativeTo(null); // 화면 가운데 정렬
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // 수정
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUser();
                String userId = userLoginId.getText();
                System.out.println("수정 유저 아이디 : " + userId);
                UserDTO select = UserDAO.select(userId);


                String encodePw = PasswordEncode.encode(String.valueOf(userPassword.getPassword()));
                System.out.println("인코딩 비번 체크 : " + encodePw);

                if (!String.valueOf(userPassword.getPassword()).isEmpty() &&
                        !checkPw(String.valueOf(userPassword.getPassword()))) {
                    System.out.println("비밀번호는 대소문자, 숫자, 특수문자를 포함하여 8글자 이상 20글자 이하여야 합니다.");
                    JOptionPane.showMessageDialog(null, "비밀번호는 대소문자, 숫자, 특수문자를 포함하여 8글자 이상 20글자 이하여야 합니다.");
                    return;
                }

                if (!String.valueOf(userPassword.getPassword()).isEmpty()) {
                    if (encodePw.equals(select.getUserPw())) {
                        System.out.println("동일한 비밀번호이기 때문에 바꿀 수 없습니다.");
                        JOptionPane.showMessageDialog(null, "기존에 동일한 비밀번호로 바꿀 수 없습니댜.");
                        return;
                    }
                }

                String email = userEmail.getText();
                System.out.println("1" + email);
                if (!email.isEmpty()) {
                    if (!emailAvailable) {
                        JOptionPane.showMessageDialog(null, "이메일 중복을 확인해주세요.");
                        return; // 중복되는 이메일일 경우 회원가입 중지
                    }
                }

                select.setUserPw(encodePw.isEmpty() ? select.getUserPw() : encodePw);
                select.setUserEmail(userEmail.getText().isEmpty() ?
                        select.getUserEmail() : userEmail.getText());
                select.setNickName(userNickName.getText().isEmpty() ?
                        select.getNickName() : userNickName.getText());
                System.out.println(select);

                int update = UserDAO.update(select, select.getUserId());
                if (update > 0) {
                    JOptionPane.showMessageDialog(null, "유저 정보를 수정했습니다.");
                    dispose(); // 현재 창 닫기
                    new Index();
                    mainPage.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "유저 정보를 수정 실패했습니다.");
                }
            }
        });

        // 회원탈퇴
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUser();
                String userId = userLoginId.getText();
                System.out.println("유저 아이디 : " + userId);

                // 사용자가 삭제를 확인하기 위한 Dialog 창을 생성합니다.
                int option = JOptionPane.showConfirmDialog(
                        null,
                        "정말로 삭제하시겠습니까?",
                        "사용자 삭제",
                        JOptionPane.YES_NO_OPTION);

                // 사용자가 예(YES_OPTION)를 선택했을 때만 삭제 작업을 진행합니다.
                if (option == JOptionPane.YES_OPTION) {
                    // 비밀번호 입력을 다시 받기 위해서 Dialog 창을 생성
                    JPasswordField passwordField = new JPasswordField();
                    Object[] message = {"비밀번호를 다시 입력하세요:", passwordField};
                    int passwordOption = JOptionPane.showConfirmDialog(
                            null,
                            message,
                            "비밀번호 확인",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE);

                    // 사용자가 확인을 눌렀고 비밀번호가 일치하면 삭제 작업을 진행합니다.
                    if (passwordOption == JOptionPane.OK_OPTION) {
                        String password = new String(passwordField.getPassword());
                        System.out.println("비밀번호 체크 : " + password);
                        String encodePw = PasswordEncode.encode(password);

                        UserDTO select = UserDAO.select(userId);
                        // 저장된 비밀번호와 사용자가 입력한 비밀번호를 비교합니다.
                        if (encodePw.equals(select.getUserPw())) {
                            int delete = UserDAO.delete(select.getUserId());
                            if (delete > 0) {
                                System.out.println("삭제 성공");
                                JOptionPane.showMessageDialog(null, "삭제 성공했습니다.");
                                dispose(); // 현재 창 닫기
                                new Index();
                                mainPage.setVisible(false);
                            }
                        } else {
                            // 비밀번호가 일치하지 않을 경우 메시지를 출력합니다.
                            JOptionPane.showMessageDialog(
                                    null,
                                    "비밀번호가 일치하지 않습니다.",
                                    "비밀번호 오류",
                                    JOptionPane.ERROR_MESSAGE);
                            setVisible(true);
                        }
                    }
                }
            }
        });
    }

    // 회원정보 가져오기 메서드
    public void loadUser() {
        String loginId = SessionManager.getCurrentUser();
        System.out.println(loginId);
        System.out.println("로그인 중인 아이디 체크 : " + loginId);

        UserDTO select = UserDAO.selectByNickName(loginId);
        System.out.println(select);

        selectedId = select.getUserLoginID();
        System.out.println("아이디 조회 : " + selectedId);
    }

    // 소문자, 대문자, 0~9 숫자, 특수문자 8자리 이상!(소문자, 대문자 같이 안써도 됨!)
    // 위에서 비밀번호를 생성하기위한 조건을 주기위해서 메소드 만들었다!
    public boolean checkPw(String userpw) {
        String passwordPolicy = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$";

        Pattern pattern_pwd = Pattern.compile(passwordPolicy);
        Matcher matcher_pwd = pattern_pwd.matcher(userpw);

        return matcher_pwd.matches();
    }

    // 이메일 검사
    public boolean checkEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern patternEmail = Pattern.compile(emailPattern);
        Matcher matcherEmail = patternEmail.matcher(email);
        UserDTO userDTO = UserDAO.selectByEmail(email);
        System.out.println("이메일 체크 : " + userDTO);

        if (matcherEmail.matches() && userDTO.getUserEmail() == null) {
            return true;
        }
        return false;
    }
}

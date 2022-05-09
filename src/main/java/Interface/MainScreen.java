package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 프로그램 실행 시 가장 처음 나오는 화면
public class MainScreen extends JFrame {
    public static JLabel modeJLabel; // 게임 모드를 나타내는 라벨

    public static int size; // 타겟의 사이즈 변수
    public static int speed; // 타겟의 속도 변수
    public static int frequency; // 타겟의 빈도 변수
    public static int time; // 게임의 시간 변수

    public MainScreen() {
        size = 5;   // 타겟 사이즈 변수, 초기값 5
        speed = 5; // 타겟 속도 변수, 초기값 5
        frequency = 5; // 타겟 빈도 변수, 초기값 5
        time = 10; // 게임 시간 변수, 초기값 10

        Color backGroundColor = new Color(86,187,241); // 배경색 설정
        Color buttonColor = new Color(77,119,255); // 버튼색 설정

        setTitle("AimSimulator"); // 프로그램 타이틀 "AimSimulator"
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫으면 프로그램 종료

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null); // 컨텐트팬의 배치관리자 없음

        // ====제목==========================================================================
        JLabel title = new JLabel("AimSimulator"); // title 라벨 문자열과 함께 생성
        title.setFont(new Font("Slab Serif",Font.ITALIC,80)); // 폰트 설정
        title.setForeground(Color.WHITE); // title 라벨의 글씨 하얀색 설정
        title.setLocation(50,50); // title 라벨의 위치
        title.setSize(600,100); // title 라벨의 크기
        container.add(title); // 컨텐트팬에 title 라벨 부착

        // ====[버젼]==========================================================================
        JLabel version = new JLabel("version_3.5"); // version 라벨 문자열과 함께 생성
        version.setFont(new Font("Slab Serif",Font.ITALIC,20)); // 폰트 설정
        version.setForeground(Color.WHITE); // version 라벨의 글씨 하얀색 설정
        version.setLocation(50,150); // version 라벨의 위치
        version.setSize(150,25); // version 라벨의 크기
        container.add(version); // 컨텐트팬에 version 라벨 부착

        // ==[Mode_라벨]========================================================================
        modeJLabel = new JLabel("Mode : Select"); // modeJLabel 라벨 문자열과 함께 생성
        modeJLabel.setFont(new Font("Slab Serif",Font.BOLD,25));  // 폰트 설정
        modeJLabel.setForeground(Color.WHITE); // modeJLabel 라벨의 글씨색 설정
        modeJLabel.setLocation(50,275); // modeJLabel 라벨의 위치 설정
        modeJLabel.setSize(200,50); // modeJLabel 라벨의 크기 설정
        container.add(modeJLabel); // 컨텐트팬에 modeJLabel 라벨 부착

        // ==[Start_버튼]=======================================================================
        JButton startButton = new JButton("Start"); // startButton 버튼 문자열과 함께 생성
        startButton.setFont(new Font("Slab Serif",Font.BOLD,50)); // 폰트 설정
        startButton.setForeground(Color.WHITE); // startButton 버튼의 글씨 하얀색 설정
        startButton.setBackground(buttonColor); // startButton 버튼의 배경색 설정
        startButton.setLocation(50,325); // startButton 버튼의 위치
        startButton.setSize(200,100); // startButton 버튼의 크기
        container.add(startButton); // 컨텐트팬에 startButton 버튼 부착

        // Start 버튼 클릭 시 StartScreen 실행, 모드를 설정 안하면 경고창 발생 후 확인 시 SettingScreen 실행
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modeJLabel.getText() == "Mode : Select") {
                    JOptionPane.showMessageDialog(null,"Select Mode!","ERROR", JOptionPane.ERROR_MESSAGE);
                    new SettingScreen();
                    // 모드 선택 안한 상태로 Start 버튼 누르면 경고창 발생.
                } else {
                    new StartScreen();
                }
            }
        });

        // ==[Read 버튼]======================================================================
        JButton readMeButton = new JButton("Read"); // readMeButton 버튼 문자열과 함께 생성
        readMeButton.setFont(new Font("Slab Serif",Font.BOLD,40)); // 폰트 설정
        readMeButton.setForeground(Color.WHITE); // readMeButton 버튼의 글씨 하얀색 설정
        readMeButton.setBackground(buttonColor); // readMeButton 버튼의 배경색 설정
        readMeButton.setLocation(575,150); // readMeButton 버튼의 위치 설정
        readMeButton.setSize(175,75); // readMeButton 버튼의 크기 설정
        container.add(readMeButton); // 컨텐트팬에 readMeButton 버튼 부착

        // Read 버튼 클릭 시 ReadScreen 클래스 실행
        readMeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReadScreen();
            }
        });

        // ==[Setting_버튼]=======================================================================
        JButton settingButton = new JButton("Setting"); // settingButton 버튼 문자열과 함께 생성
        settingButton.setFont(new Font("Slab Serif",Font.BOLD,40)); // 폰트 설정
        settingButton.setForeground(Color.WHITE); // settingButton 버튼의 글씨색 설정
        settingButton.setBackground(buttonColor); // settingButton 버튼의 배경색 설정
        settingButton.setLocation(575,250); // settingButton 버튼의 위치 설정
        settingButton.setSize(175,75); // settingButton 버튼의 크기 설정
        container.add(settingButton); // 컨텐트팬에 settingButton 버튼 부착

        // Setting 버튼을 클릭 시 SettingScreen 클래스 실행
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingScreen();
            }
        });

        // ==[Quit_버튼]==========================================================================
        JButton quitButton = new JButton("Quit"); // quitButton 버튼 문자열과 함께 생성
        quitButton.setFont(new Font("Slab Serif",Font.BOLD,50)); // 폰트 설정
        quitButton.setForeground(Color.WHITE); // quitButton 버튼의 글씨색 설정
        quitButton.setBackground(buttonColor); // quitButton 버튼의 배경색 설정
        quitButton.setLocation(575,350); // quitButton 버튼의 위치 설정
        quitButton.setSize(175,75); // quitButton 버튼의 크기 설정
        container.add(quitButton); // 컨텐트팬에 quitButton 버튼 부착

        // Quit 버튼을 클릭 시 시스템 종료
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setSize(800,500); // 창의 크기 설정
        setResizable(false); // 창의 크기 고정
        setLocationRelativeTo(null); // 창이 가운데에 나타남
        setVisible(true); // 화면에 창을 나타냄
    }

    /**
    // ==[Setting_버튼]=======================================================================
    JButton settingButton = new JButton("Setting"); // settingButton 버튼 문자열과 함께 생성
        settingButton.setFont(new Font("Slab Serif",Font.BOLD,40)); // 폰트 설정
        settingButton.setForeground(Color.WHITE); // settingButton 버튼의 글씨색 설정
        settingButton.setBackground(buttonColor); // settingButton 버튼의 배경색 설정
        settingButton.setLocation(575,250); // settingButton 버튼의 위치 설정
        settingButton.setSize(175,75); // settingButton 버튼의 크기 설정
        container.add(settingButton); // 컨텐트팬에 settingButton 버튼 부착

    public void createButton(String buttonName, int locationX, int locationY, int sizeX, int sizeY) {
        JButton buttonName = new JButton(buttonName);
    }
    **/

    public static void main(String[] args) {
        new MainScreen(); // 메인화면 호출
    }
}

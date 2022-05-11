package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 프로그램 실행 시 가장 처음 나오는 화면
public class MainScreen extends JFrame {
    public static Color backGroundColor = new Color(86,187,241); // 배경색 설정
    public static Color buttonColor = new Color(77,119,255); // 버튼색 설정

    public static JLabel modeJLabel; // 게임 모드를 나타내는 라벨

    public static int size, speed, frequency, time, frequencySliderShow; // 게임 속성 변수

    public MainScreen() {
        size = 5;   // 타겟 사이즈 변수, 초기값 5
        speed = 5; // 타겟 속도 변수, 초기값 5
        frequency = 5; // 타겟 빈도 변수, 초기값 5
        frequencySliderShow = 5;

        time = 10; // 게임 시간 변수, 초기값 10

        setTitle("AimSimulator"); // 프로그램 타이틀 "AimSimulator"
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫으면 프로그램 종료

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null); // 컨텐트팬의 배치관리자 없음

        // ====[제목]==========================================================================
        JLabel title = new JLabel("AimSimulator"); // title 라벨 문자열과 함께 생성
        settingItalicLabel(title,50,50,600,100,80);
        container.add(title);

        // ====[버젼]==========================================================================
        JLabel version = new JLabel("version_test"); // version 라벨 문자열과 함께 생성
        settingItalicLabel(version,50,150,150,25,20);
        container.add(version); // 컨텐트팬에 version 라벨 부착

        // ====[Mode_라벨]========================================================================
        modeJLabel = new JLabel("Mode : Select"); // modeJLabel 라벨 문자열과 함께 생성
        settingBoldLabel(modeJLabel,50,275,200,50,25);
        container.add(modeJLabel);

        // ====[Start 버튼]======================================================================
        JButton startButton = new JButton("Start");
        settingButton(startButton, 50,325,200,100,50);
        container.add(startButton);
        clickEvent(startButton, "Start"); // Start 버튼 클릭 시 StartScreen 실행, 모드를 설정 안하면 경고창 발생 후 확인 시 SettingScreen 실행

        // ====[Read 버튼]======================================================================
        JButton readButton = new JButton("Read"); // readMeButton 버튼 문자열과 함께 생성
        settingButton(readButton,575,150,175,75,40);
        container.add(readButton); // 컨텐트팬에 readMeButton 버튼 부착
        clickEvent(readButton,"Read"); // Read 버튼 클릭 시 ReadScreen 클래스 실행

        // ====[Setting 버튼]=======================================================================
        JButton settingButton = new JButton("Setting"); // settingButton 버튼 문자열과 함께 생성
        settingButton(settingButton,575,250,175,75,40);
        container.add(settingButton); // 컨텐트팬에 settingButton 버튼 부착
        clickEvent(settingButton,"Setting"); // Setting 버튼을 클릭 시 SettingScreen 클래스 실행

        // ====[Quit 버튼]==========================================================================
        JButton quitButton = new JButton("Quit"); // quitButton 버튼 문자열과 함께 생성
        settingButton(quitButton,575,350,175,75,50);
        container.add(quitButton); // 컨텐트팬에 quitButton 버튼 부착
        clickEvent(quitButton, "Quit"); // Quit 버튼을 클릭 시 시스템 종료

        settingScreen(800,500,false,null,true);
    }

    public void settingScreen(int sizeX, int sizeY, boolean resizable, Component locationRelativeTo, boolean visible) {
        setSize(sizeX,sizeY); // 창의 x,y 크기 인자로 받아 설정
        setResizable(resizable); // 창의 크기 변경 여부 인자로 받아 설정
        setLocationRelativeTo(locationRelativeTo); // 창의 상대적 위치 인자로 받아 설정
        setVisible(visible); // 창을 띄우는 여부 인자로 받아 설정
    }

    public static void settingButton(JButton buttonName, int locationX, int locationY, int sizeX, int sizeY,int fontSize){
        buttonName.setFont(new Font("Slab Serif", Font.BOLD, fontSize)); // 폰트 설정, 폰트 크기 인자로 받아 설정
        buttonName.setForeground(Color.WHITE); // 글씨 색 설정
        buttonName.setBackground(buttonColor); // 버튼 배경 색 설정
        buttonName.setLocation(locationX, locationY); // 버튼의 위치 인자로 받아 설정
        buttonName.setSize(sizeX, sizeY); // 버큰의 크기 인자로 받아 설정
    }

    public static void settingPlainLabel(JLabel labelName, int locationX, int locationY, int sizeX, int sizeY, int fontSize) {
        labelName.setFont(new Font("Slab Serif",Font.PLAIN,fontSize)); // 폰트 설정 - PLAIN, 폰트 크기 인자로 받아 설정
        labelName.setForeground(Color.WHITE); // 글씨 색 하얀색 설정
        labelName.setLocation(locationX,locationY); // 라벨의 위치 인자로 받아 설정
        labelName.setSize(sizeX,sizeY); // 라벨의 크기 인자로 받아 설정
    }

    public static void settingBoldLabel(JLabel labelName, int locationX, int locationY, int sizeX, int sizeY, int fontSize) {
        labelName.setFont(new Font("Slab Serif",Font.BOLD,fontSize)); // 폰트 설정 - BOLD, 폰트 크기 인자로 받아 설정
        labelName.setForeground(Color.WHITE); // 글씨 색 하얀색 설정
        labelName.setLocation(locationX,locationY); // 라벨의 위치 인자로 받아 설정
        labelName.setSize(sizeX,sizeY); // 라벨의 크기 인자로 받아 설정
    }
    public static void settingItalicLabel(JLabel labelName, int locationX, int locationY, int sizeX, int sizeY, int fontSize) {
        labelName.setFont(new Font("Slab Serif",Font.ITALIC,fontSize)); // 폰트 설정 - ITALIC, 폰트 크기 인자로 받아 설정
        labelName.setForeground(Color.WHITE); // 글씨 색 하얀색 설정
        labelName.setLocation(locationX,locationY); // 라벨의 위치 인자로 받아 설정
        labelName.setSize(sizeX,sizeY); // 라벨의 크기 인자로 받아 설정
    }

    // 테스트용 이벤트리스너
    public void clickEvent(JButton buttonName, String className) {
        buttonName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (className) { // className String 인자로 받아 설정
                    case "Setting": // className이면 "Setting" 이면
                        new SettingScreen(); break; // SettingScreen 객체 생성
                    case "Read": // className이 Read이면
                        new ReadScreen(); break; // ReadScreen 객체 생성
                    case "Quit": // className이 "Quit" 이면
                        System.exit(0); break; // 시스템 종료
                    case "Start": // className이 "Start" 이면
                        if (modeJLabel.getText() == "Mode : Select") { // modeJLabel의 텍스트가 "Mode : Select" 일 경우
                            // 경고창 생성 후 SettingScreen 객체 생성
                            JOptionPane.showMessageDialog(null,"Select Mode!","ERROR", JOptionPane.ERROR_MESSAGE);
                            new SettingScreen();
                        } else new StartScreen(); // StartScreen 객체 생성
                        break;
                        //
                    default: System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args) {
        new MainScreen(); // 메인화면 호출
    }
}

package Interface;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static Interface.Controller.*;

// 프로그램 실행 시 가장 처음 나오는 화면
public class MainScreen extends JFrame {
    public static JLabel title, version, modeJLabel; // 게임 모드를 나타내는 라벨
    public static JButton startButton, readButton, settingButton, quitButton;

    public static int listIndex = 2018041089;

    public MainScreen() {
        setTitle("AimSimulator"); // 프로그램 타이틀 "AimSimulator"
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫으면 프로그램 종료

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null); // 컨텐트팬의 배치관리자 없음

        // ====[제목]====================================================================================================
        title = new JLabel("AimSimulator"); // title 라벨 문자열과 함께 생성
        settingItalicLabel(title,50,50,600,100,80);
        container.add(title);

        // ====[버젼]====================================================================================================
        version = new JLabel("version_test"); // version 라벨 문자열과 함께 생성
        settingItalicLabel(version,50,150,150,25,20);
        container.add(version); // 컨텐트팬에 version 라벨 부착

        // ====[Mode_라벨]===============================================================================================
        modeJLabel = new JLabel("Mode : Select"); // modeJLabel 라벨 문자열과 함께 생성
        settingBoldLabel(modeJLabel,50,275,200,50,25);
        container.add(modeJLabel);

        // ====[Start 버튼]==============================================================================================
        startButton = new JButton("Start");
        settingButton(startButton, 50,325,200,100,50);
        container.add(startButton);
        clickEvent(startButton, "Start"); // Start 버튼 클릭 시 StartScreen 실행, 모드를 설정 안하면 경고창 발생 후 확인 시 SettingScreen 실행

        // ====[Read 버튼]===============================================================================================
        readButton = new JButton("Read"); // readMeButton 버튼 문자열과 함께 생성
        settingButton(readButton,575,150,175,75,40);
        container.add(readButton); // 컨텐트팬에 readMeButton 버튼 부착
        clickEvent(readButton,"Read"); // Read 버튼 클릭 시 ReadScreen 클래스 실행

        // ====[Setting 버튼]============================================================================================
        settingButton = new JButton("Setting"); // settingButton 버튼 문자열과 함께 생성
        settingButton(settingButton,575,250,175,75,40);
        container.add(settingButton); // 컨텐트팬에 settingButton 버튼 부착
        clickEvent(settingButton,"Setting"); // Setting 버튼을 클릭 시 SettingScreen 클래스 실행

        // ====[Quit 버튼]===============================================================================================
        quitButton = new JButton("Quit"); // quitButton 버튼 문자열과 함께 생성
        settingButton(quitButton,575,350,175,75,50);
        container.add(quitButton); // 컨텐트팬에 quitButton 버튼 부착
        clickEvent(quitButton, "Quit"); // Quit 버튼을 클릭 시 시스템 종료

        switch (listIndex) {
            case 0:
                modeJLabel.setText("Mode : Custom"); break;
            case 1:
                modeJLabel.setText("Mode : Easy"); break;
            case 2:
                modeJLabel.setText("Mode : Normal"); break;
            case 3:
                modeJLabel.setText("Mode : Hard"); break;
            default: break;
        }


        settingScreen(800,500,false,null,true);
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
                        if (modeJLabel.getText().equals("Mode : Select")) { // modeJLabel의 텍스트가 "Mode : Select" 일 경우
                            // 경고창 생성 후 SettingScreen 객체 생성
                            JOptionPane.showMessageDialog(null,"Select Mode!","ERROR", JOptionPane.ERROR_MESSAGE);
                            new SettingScreen();
                        } else {
                            new StartScreen(); // StartScreen 객체 생성
                        }
                        break;
                    default: System.exit(0);
                }
            }
        });
    }

    public void settingScreen(int sizeX, int sizeY, boolean resizable, Component locationRelativeTo, boolean visible) {
        setSize(sizeX,sizeY); // 창의 x,y 크기 인자로 받아 설정
        setResizable(resizable); // 창의 크기 변경 여부 인자로 받아 설정
        setLocationRelativeTo(locationRelativeTo); // 창의 상대적 위치 인자로 받아 설정
        setVisible(visible); // 창을 띄우는 여부 인자로 받아 설정
    }

    public static void main(String[] args) {
        new MainScreen(); // 메인화면 호출
    }
}

package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Interface.Controller.*;
import static Interface.Controller.settingButton;

public class GameOverScreen extends JFrame {
    public static int hitCount, missCount, totalCount, score; // 카운트 변수2
    public static double percent; // 타겟을 맞춘 퍼센트 변수
    public static String percentToString; // 퍼센트 변수를 문자열로 변환하여 저장하는 변수

    public GameOverScreen() {
        setTitle("Game Over"); // 프로그램 타이틀 "Score"
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창을 닫으면 창이 사라짐

        Container container = getContentPane(); // 프레임에 부착된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨텐트팬의 색 backGroundColor 설정
        container.setLayout(null); // 컨텐트팬의 배치관리자 없음

        JLabel gameoverLabel = new JLabel("Game Over");
        settingBoldLabel(gameoverLabel,100,100,600,100,110);
        container.add(gameoverLabel);

        JButton mainButton = new JButton("Main");
        settingButton(mainButton,150,300,200,100,50);
        container.add(mainButton);
        clickEvent(mainButton,"Main");

        JButton replayButton = new JButton("Replay");
        settingButton(replayButton,450,300,200,100,50);
        container.add(replayButton);
        clickEvent(replayButton,"Replay");

        settingScreen(800,500,false,null,true);
    }

    public void settingScreen(int sizeX, int sizeY, boolean resizable, Component locationRelativeTo, boolean visible) {
        setSize(sizeX,sizeY);
        setResizable(resizable);
        setLocationRelativeTo(locationRelativeTo);
        setVisible(visible);
    }

    public void clickEvent(JButton buttonName, String className) {
        buttonName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (className) {
                    case "Main":
                        dispose();
                        break;
                    case "Replay":
                        dispose();
                        new StartScreen();
                        break;
                    default:
                        System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args) {
        new GameOverScreen();
    }
}

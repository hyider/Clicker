package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Interface.MainScreen.backGroundColor;
import static Interface.MainScreen.settingButton;

public class SettingModeScreen extends JFrame {

    SettingModeScreen() {
        setTitle("Mode");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null); // 배치관리자는 없음

        // 좌표값 알아내기////////////////////////////////////////
        JLabel la = new JLabel();
        container.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                la.setText("("+e.getX()+","+e.getY()+")");
                la.setLocation(e.getX(),e.getY());
                la.setSize(100,50);
                container.add(la);
                container.repaint();
            }
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        /////////////////////////////////////////////////////////

        JButton saveModeButton = new JButton("Save"); // saveButton 버튼 문자열과 함께 생성
        settingButton(saveModeButton,600,380,150,50,30);
        container.add(saveModeButton); // 컨텐트팬에 saveButton 버튼 부착


        settingScreen(800,500,false,null,true);
    }
    public void settingScreen(int sizeX, int sizeY, boolean resizable, Component locationRelativeTo, boolean visible) {
        setSize(sizeX,sizeY); // 창의 x,y 크기 인자로 받아 설정
        setResizable(resizable); // 창의 크기 변경 여부 인자로 받아 설정
        setLocationRelativeTo(locationRelativeTo); // 창의 상대적 위치 인자로 받아 설정
        setVisible(visible); // 창을 띄우는 여부 인자로 받아 설정
    }

    public static void main(String[] args) {
        new SettingModeScreen();
    }
}

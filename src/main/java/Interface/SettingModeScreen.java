package Interface;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Interface.ContentSetting.*;

public class SettingModeScreen extends JFrame {
    JLabel valueOfSpeed;
    public static boolean mode1Selected ;
    int speed = ContentSetting.speed;
    SettingModeScreen() {
        mode1Selected = ContentSetting.mode1Selected;

        setTitle("Mode");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null); // 배치관리자는 없음

        // ====[Title_Label]==========================================================================
        JLabel title = new JLabel("Mode"); // title 라벨 문자열과 함께 생성
        settingItalicLabel(title,20,15,190,75,60);
        container.add(title); // 컨텐트팬에 title 라벨 부착

        JButton mode1 = new JButton("Move");
        settingButton(mode1,200,30,100,50,20);
        if(mode1Selected) mode1.setBackground(Color.BLUE); else mode1.setBackground(buttonColor);
        container.add(mode1);


        // ====[Time_Slider]============================================================================================
        JSlider speedSlider = new JSlider(1,10, speed);
        settingSlider(speedSlider,220,120,550,20, 5);
        speedSlider.setName("speedSlider");
        speedSlider.setEnabled(ContentSetting.mode1Selected);
        container.add(speedSlider);
        changeEventSlider(speedSlider);

        JLabel speedLabel = new JLabel("Speed");
        settingPlainLabel(speedLabel,10,100,150,50,40); // 타임라벨 위치 재선정
        container.add(speedLabel);
        valueOfSpeed = new JLabel(String.valueOf(speed));
        settingPlainLabel(valueOfSpeed,150,100,50,50,40); // 타임라벨 위치 재선정
        container.add(valueOfSpeed);

        mode1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode1Selected = !mode1Selected;
                if(mode1Selected == true) {
                    mode1.setBackground(Color.BLUE);
                    speedSlider.setEnabled(true);
                } else {
                    mode1.setBackground(buttonColor);
                    speedSlider.setEnabled(false);
                    speedSlider.setValue(0);
                }
            }
        });

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
        saveModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContentSetting.speed = speed;
                ContentSetting.mode1Selected = mode1Selected;
                dispose();
            }
        });

        settingScreen(800,500,false,null,true);
    }
    public void settingScreen(int sizeX, int sizeY, boolean resizable, Component locationRelativeTo, boolean visible) {
        setSize(sizeX,sizeY); // 창의 x,y 크기 인자로 받아 설정
        setResizable(resizable); // 창의 크기 변경 여부 인자로 받아 설정
        setLocationRelativeTo(locationRelativeTo); // 창의 상대적 위치 인자로 받아 설정
        setVisible(visible); // 창을 띄우는 여부 인자로 받아 설정
    }

    public void settingSlider (JSlider sliderName, int locationX, int locationY, int sizeX, int sizeY, int tickSpacing) {
        sliderName.setBackground(backGroundColor); // JSlider의 배경색 설정
        sliderName.setPaintTicks(false); // JSlider의 눈금 보이게 설정
        sliderName.setPaintTrack(true); // JSlider의 슬라이드바 보이게 설정
        sliderName.setPaintLabels(false); // JSlider의 숫자 보이게 설정
        sliderName.setMajorTickSpacing(tickSpacing); // JSlider의 눈금 간격 인자로 받아 설정
        sliderName.setLocation(locationX,locationY); // JSlider의 위치 인자로 받아 설정
        sliderName.setSize(sizeX,sizeY); // JSlider의 크기 인자로 받아 설정
    }

    public void changeEventSlider (JSlider sliderName) {
        sliderName.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (sliderName.getName()) { //
                    case "speedSlider": // sliderName의 이름이 sizeSlider 일 때
                        speed = sliderName.getValue(); // size에 sliderName의 현재 값 전달
                        valueOfSpeed.setText(String.valueOf(speed)); // valueOfSize의 텍스트 수정
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public static void main(String[] args) {
        new SettingModeScreen();
    }
}

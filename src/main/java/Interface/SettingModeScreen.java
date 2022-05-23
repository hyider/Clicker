package Interface;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Interface.Controller.*;

public class SettingModeScreen extends JFrame {
    JLabel valueOfSpeed;
    JLabel valueOfMode2;
    JLabel valueOfMode3;
    public static boolean mode1Selected;
    public static boolean mode2Selected;
    public static boolean mode3Selected;
    int speed = Controller.speed;
    int mode2 = Controller.mode2;
    int mode3 = Controller.mode3;
    SettingModeScreen() {
        mode1Selected = Controller.mode1Selected;
        mode2Selected = Controller.mode2Selected;
        mode3Selected = Controller.mode3Selected;

        setTitle("Mode");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null); // 배치관리자는 없음

        // ====[Title_Label]==========================================================================
        JLabel title = new JLabel("Mode"); // title 라벨 문자열과 함께 생성
        settingItalicLabel(title,20,15,190,75,60);
        container.add(title); // 컨텐트팬에 title 라벨 부착

        /**
         * 첫번쨰 모드
         */
        JButton mode1 = new JButton("Move");
        settingButton(mode1,200,30,100,50,20);
        if(mode1Selected) mode1.setBackground(Color.BLUE); else mode1.setBackground(buttonColor);
        container.add(mode1);

        JSlider speedSlider = new JSlider(1,10, speed);
        settingSlider(speedSlider,220,120,550,20, 5);
        speedSlider.setName("speedSlider");
        speedSlider.setEnabled(mode1Selected);
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

        /**
         * 두번째 모드
         */
        JButton mode2Button = new JButton("Mode2");
        settingButton(mode2Button,350,30,100,50,20);
        if(mode2Selected) mode2Button.setBackground(Color.BLUE); else mode2Button.setBackground(buttonColor);
        container.add(mode2Button);

        JSlider mode2Slider = new JSlider(1,10, mode2);
        settingSlider(mode2Slider,220,170,550,20, 5);
        mode2Slider.setName("mode2Slider");
        mode2Slider.setEnabled(mode2Selected);
        container.add(mode2Slider);
        changeEventSlider(mode2Slider);
        JLabel mode2Label = new JLabel("Mode2");
        settingPlainLabel(mode2Label,10,150,150,50,40); // 타임라벨 위치 재선정
        container.add(mode2Label);
        valueOfMode2 = new JLabel(String.valueOf(mode2));
        settingPlainLabel(valueOfMode2,150,150,50,50,40); // 타임라벨 위치 재선정
        container.add(valueOfMode2);

        mode2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode2Selected = !mode2Selected;
                if(mode2Selected == true) {
                    mode2Button.setBackground(Color.BLUE);
                    mode2Slider.setEnabled(true);
                } else {
                    mode2Button.setBackground(buttonColor);
                    mode2Slider.setEnabled(false);
                    mode2Slider.setValue(0);
                }
            }
        });

        /**
         * 세번째 모드
         */
        JButton mode3Button = new JButton("Mode3");
        settingButton(mode3Button,500, 30, 100, 50, 20);
        if(mode3Selected) mode3Button.setBackground(Color.BLUE); else mode3Button.setBackground(buttonColor);
        container.add(mode3Button);

        JSlider mode3Slider = new JSlider(1,10, speed);
        settingSlider(mode3Slider,220,220,550,20, 5);
        mode3Slider.setName("mode3Slider");
        mode3Slider.setEnabled(mode1Selected);
        container.add(mode3Slider);
        changeEventSlider(mode3Slider);
        JLabel mode3Label = new JLabel("Mode3");
        settingPlainLabel(mode3Label,10,200,150,50,40); // 타임라벨 위치 재선정
        container.add(mode3Label);
        valueOfMode3 = new JLabel(String.valueOf(mode3));
        settingPlainLabel(valueOfMode3,150,200,50,50,40); // 타임라벨 위치 재선정
        container.add(valueOfMode3);

        mode3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode3Selected = !mode3Selected;
                if(mode3Selected == true) {
                    mode3Button.setBackground(Color.BLUE);
                    mode3Slider.setEnabled(true);
                } else {
                    mode3Button.setBackground(buttonColor);
                    mode3Slider.setEnabled(false);
                    mode3Slider.setValue(0);
                }
            }
        });

        JButton saveModeButton = new JButton("Save"); // saveButton 버튼 문자열과 함께 생성
        settingButton(saveModeButton,600,380,150,50,30);
        container.add(saveModeButton); // 컨텐트팬에 saveButton 버튼 부착
        saveModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.speed = speed;
                Controller.mode1Selected = mode1Selected;
                Controller.mode2 = mode2;
                Controller.mode2Selected = mode2Selected;
                Controller.mode3 = mode3;
                Controller.mode3Selected = mode3Selected;
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

    public void changeEventSlider (JSlider sliderName) {
        sliderName.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (sliderName.getName()) { //
                    case "speedSlider": // sliderName의 이름이 sizeSlider 일 때
                        speed = sliderName.getValue(); // size에 sliderName의 현재 값 전달
                        valueOfSpeed.setText(String.valueOf(speed)); // valueOfSize의 텍스트 수정
                        break;
                    case "mode2Slider":
                        mode2 = sliderName.getValue();
                        valueOfMode2.setText(String.valueOf(mode2));
                        break;
                    case "mode3Slider":
                        mode3 = sliderName.getValue();
                        valueOfMode3.setText(String.valueOf(mode3));
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

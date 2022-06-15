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
    JLabel valueOfDefense;
    JLabel valueOfItem;
    public static boolean movingModeSelected;
    public static boolean shieldModeSelected;
    public static boolean itemModeSelected;
    int speed = Controller.speed;
    int defense = Controller.defense;
    int item = Controller.item;
    SettingModeScreen() {
        movingModeSelected = Controller.movingModeSelected;
        shieldModeSelected = Controller.shieldModeSelected;
        itemModeSelected = Controller.itemModeSelected;

        setTitle("Mode");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null); // 배치관리자는 없음

        // ====[Title_Label]==========================================================================
        JLabel title = new JLabel("Mode"); // title 라벨 문자열과 함께 생성
        settingItalicLabel(title,20,15,190,75,60);
        container.add(title); // 컨텐트팬에 title 라벨 부착

        /**
         * 첫번쨰 모드 : Moving_Mode
         */
        JButton movingMode = new JButton("Move");
        settingButton(movingMode,200,30,100,50,20);
        if(movingModeSelected) movingMode.setBackground(Color.BLUE); else movingMode.setBackground(buttonColor);
        container.add(movingMode);

        JSlider speedSlider = new JSlider(1,10, speed);
        settingSlider(speedSlider,240,120,530,20, 5);
        speedSlider.setName("speedSlider");
        speedSlider.setEnabled(movingModeSelected);
        container.add(speedSlider);
        changeEventSlider(speedSlider);

        JLabel speedLabel = new JLabel("Speed");
        settingPlainLabel(speedLabel,10,100,150,50,30); // 타임라벨 위치 재선정
        container.add(speedLabel);

        if(movingModeSelected) {
            valueOfSpeed = new JLabel(String.valueOf(speed));
        } else valueOfSpeed = new JLabel("X");

        settingPlainLabel(valueOfSpeed,200,100,50,50,30); // 타임라벨 위치 재선정
        container.add(valueOfSpeed);

        /**
         * 두번째 모드 : Shield_Mode
         */
        JButton shieldModeButton = new JButton("Shield");
        settingButton(shieldModeButton,350,30,100,50,20);
        if(shieldModeSelected) shieldModeButton.setBackground(Color.BLUE); else shieldModeButton.setBackground(buttonColor);
        container.add(shieldModeButton);

        JSlider defenseSlider = new JSlider(1,10, defense);
        settingSlider(defenseSlider,240,170,530,20, 5);
        defenseSlider.setName("defenseSlider");
        defenseSlider.setEnabled(shieldModeSelected);
        container.add(defenseSlider);
        changeEventSlider(defenseSlider);
        JLabel shieldModeLabel = new JLabel("Defense");
        settingPlainLabel(shieldModeLabel,10,150,150,50,30); // 타임라벨 위치 재선정
        container.add(shieldModeLabel);
        if(shieldModeSelected) {
            valueOfDefense = new JLabel(String.valueOf(defense));
        } else valueOfDefense = new JLabel("X");

        settingPlainLabel(valueOfDefense,200,150,50,50,30); // 타임라벨 위치 재선정
        container.add(valueOfDefense);

        /**
         * 세번째 모드
         */
        JButton itemModeButton = new JButton("Item");
        settingButton(itemModeButton,500, 30, 100, 50, 20);
        if(itemModeSelected) itemModeButton.setBackground(Color.BLUE); else itemModeButton.setBackground(buttonColor);
        container.add(itemModeButton);

        JSlider itemSlider = new JSlider(1,10, item);
        settingSlider(itemSlider,240,220,530,20, 5);
        itemSlider.setName("itemSlider");
        itemSlider.setEnabled(itemModeSelected);
        container.add(itemSlider);
        changeEventSlider(itemSlider);
        JLabel itemModeLabel = new JLabel("Item");
        settingPlainLabel(itemModeLabel,10,200,150,50,30); // 타임라벨 위치 재선정
        container.add(itemModeLabel);
        if(itemModeSelected) {
            valueOfItem = new JLabel(String.valueOf(item));
        } else valueOfItem = new JLabel("X");
        settingPlainLabel(valueOfItem,200,200,50,50,30); // 타임라벨 위치 재선정
        container.add(valueOfItem);

        JLabel noti = new JLabel("Select only one mode.");
        settingBoldLabel(noti,30,280,500,50,40);
        container.add(noti);

        movingMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingModeSelected = !movingModeSelected;
                if(movingModeSelected) {
                    movingMode.setBackground(Color.BLUE);
                    speedSlider.setEnabled(true);
                    valueOfSpeed.setText(String.valueOf(speed));
                    shieldModeButton.setBackground(buttonColor);
                    defenseSlider.setEnabled(false);
                    defenseSlider.setValue(0);
                    valueOfDefense.setText("X");
                    itemModeButton.setBackground(buttonColor);
                    itemSlider.setEnabled(false);
                    itemSlider.setValue(0);
                    valueOfItem.setText("X");
                    shieldModeSelected = false;
                    itemModeSelected = false;
                } else {
                    movingMode.setBackground(buttonColor);
                    speedSlider.setEnabled(false);
                    speedSlider.setValue(0);
                    valueOfSpeed.setText("X");
                }
            }
        });

        shieldModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shieldModeSelected = !shieldModeSelected;
                if(shieldModeSelected) {
                    shieldModeButton.setBackground(Color.BLUE);
                    defenseSlider.setEnabled(true);
                    valueOfDefense.setText(String.valueOf(defense));
                    movingMode.setBackground(buttonColor);
                    speedSlider.setEnabled(false);
                    speedSlider.setValue(0);
                    valueOfSpeed.setText("X");
                    itemModeButton.setBackground(buttonColor);
                    itemSlider.setEnabled(false);
                    itemSlider.setValue(0);
                    valueOfItem.setText("X");
                    movingModeSelected = false;
                    itemModeSelected = false;
                } else {
                    shieldModeButton.setBackground(buttonColor);
                    defenseSlider.setEnabled(false);
                    defenseSlider.setValue(0);
                    valueOfDefense.setText("X");
                }
            }
        });

        itemModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemModeSelected = !itemModeSelected;
                if(itemModeSelected) {
                    itemModeButton.setBackground(Color.BLUE);
                    itemSlider.setEnabled(true);
                    valueOfItem.setText(String.valueOf(item));
                    movingMode.setBackground(buttonColor);
                    speedSlider.setEnabled(false);
                    speedSlider.setValue(0);
                    valueOfSpeed.setText("X");
                    shieldModeButton.setBackground(buttonColor);
                    defenseSlider.setEnabled(false);
                    defenseSlider.setValue(0);
                    valueOfDefense.setText("X");
                    movingModeSelected = false;
                    shieldModeSelected = false;
                } else {
                    itemModeButton.setBackground(buttonColor);
                    itemSlider.setEnabled(false);
                    itemSlider.setValue(0);
                    valueOfItem.setText("X");
                }
            }
        });

        //shieldModeButton.setEnabled(false);
        //itemModeButton.setEnabled(false);

        JButton saveModeButton = new JButton("Save");
        settingButton(saveModeButton,600,380,150,50,30);
        container.add(saveModeButton); // 컨텐트팬에 saveButton 버튼 부착
        saveModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.speed = speed;
                Controller.movingModeSelected = movingModeSelected;
                Controller.defense = defense;
                Controller.shieldModeSelected = shieldModeSelected;
                Controller.item = item;
                Controller.itemModeSelected = itemModeSelected;
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
                    case "defenseSlider":
                        defense = sliderName.getValue();
                        valueOfDefense.setText(String.valueOf(defense));
                        break;
                    case "itemSlider":
                        item = sliderName.getValue();
                        valueOfItem.setText(String.valueOf(item));
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

package Interface;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

import static Interface.MainScreen.*;

public class SettingScreen extends JFrame {
    public static int size;
    public static int speed;
    public static int frequency;
    public static int time;

    public static JLabel valueOfSize;
    public static JLabel valueOfSpeed;
    public static JLabel valueOfFrequency;
    public static JLabel valueOfTime;

    public static JLabel customLabel;

    public static JButton saveButton;

    public static JSlider sizeSlider;
    public static JSlider speedSlider;
    public static JSlider frequencySlider;
    public static JSlider timeSlider;

    public SettingScreen() {
        size = MainScreen.size;
        speed = MainScreen.speed;
        frequency = frequencySliderShow;
        time = MainScreen.time;

        setTitle("Setting");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null); // 배치관리자는 없음

        // ====[Title_Label]==========================================================================
        JLabel title = new JLabel("Setting"); // title 라벨 문자열과 함께 생성
        title.setFont(new Font("Slab Serif",Font.ITALIC,60)); // 폰트 설정
        title.setForeground(Color.WHITE); // title 라벨의 글씨 하얀색 설정
        title.setLocation(35,15); // title 라벨의 위치
        title.setSize(200,75); // title 라벨의 크기
        container.add(title); // 컨텐트팬에 title 라벨 부착

        // ====[Mode_Label]==========================================================================
        customLabel = new JLabel(modeJLabel.getText()); // customLabel 라벨 문자열과 함께 생성
        settingPlainLabel(customLabel,400,15,300,75,40);
        container.add(customLabel); // 컨텐트팬에 customLabel 라벨 부착

        // ====[Easy_Button]=======================================================================
        JButton easyButton = new JButton("Easy"); // easyButton 버튼 문자열과 함께 생성
        settingButton(easyButton,35,115,150,50,30);
        container.add(easyButton); // 컨텐트팬에 easyButton 버튼 부착
        clickEvent(easyButton,"Mode : Easy", 10, 1, 1, false);

        // ====[Normal_Button]=======================================================================
        JButton normalButton = new JButton("Normal"); // normalButton 버튼 문자열과 함께 생성
        settingButton(normalButton,35,175,150,50,30);
        container.add(normalButton); // 컨텐트팬에 normalButton 버튼 부착
        clickEvent(normalButton,"Mode : Normal",5,5,5,false);

        // ====[Hard_Button]=======================================================================
        JButton hardButton = new JButton("Hard"); // hardButton 버튼 문자열과 함께 생성
        settingButton(hardButton,35,235,150,50,30);
        container.add(hardButton); // 컨텐트팬에 hardButton 버튼 부착
        clickEvent(hardButton,"Mode : Hard", 1, 10, 10, false);

        // ====[Custom_Button]=======================================================================
        JButton customButton = new JButton("Custom"); // customButton 버튼 문자열과 함께 생성
        settingButton(customButton,35,295,150,50,30);
        container.add(customButton); // 컨텐트팬에 customButton 버튼 부착
        clickEvent(customButton,"Mode : Custom", size, speed, frequency, true);

        // ==[Save_Button]=======================================================================
        saveButton = new JButton("Save"); // saveButton 버튼 문자열과 함께 생성
        settingButton(saveButton,35,355,150,50,30);
        container.add(saveButton); // 컨텐트팬에 saveButton 버튼 부착
        if (modeJLabel.getText() == "Mode : Select")
            saveButton.setEnabled(false);
        else
            saveButton.setEnabled(true);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainScreen.size = size;
                MainScreen.speed = speed;
                frequencySliderShow = frequency;
                MainScreen.frequency = 10 - (frequency-1);
                MainScreen.time = time;
                dispose();
            }
        });

        // ====[Size_Slider]=================================================================================
        sizeSlider = new JSlider(1,10,size);
        settingSlider(sizeSlider,265,165,485,50,1);
        container.add(sizeSlider);
        sizeSlider.setName("sizeSlider");
        changeEventSlider(sizeSlider);
        // ====[Size_Label]===================================================================================
        valueOfSize = new JLabel("Size : " + String.valueOf(size));
        settingPlainLabel(valueOfSize,265,95,200,75,30);
        container.add(valueOfSize); // 컨텐트팬에 valueOfSize 라벨 부착

        // ====[Speed_Slider]=================================================================================
        speedSlider = new JSlider(1,10,speed);
        settingSlider(speedSlider,265,215,485,50,1);
        container.add(speedSlider);
        speedSlider.setName("speedSlider");
        changeEventSlider(speedSlider);
        // ====[Speed_Label]===================================================================================
        valueOfSpeed = new JLabel("Speed : " + String.valueOf(speed));
        settingPlainLabel(valueOfSpeed,395,95,200,75,30);
        container.add(valueOfSpeed); // 컨텐트팬에 valueOfSpeed 라벨 부착

        // ====[Frequency_Slider]===================================================================================
        frequencySlider = new JSlider(1,10,frequency);
        settingSlider(frequencySlider,265,265,485,50,1);
        container.add(frequencySlider);
        frequencySlider.setName("frequencySlider");
        changeEventSlider(frequencySlider);
        // ====[Frequency_Label]===================================================================================
        valueOfFrequency = new JLabel("Frequency : " + String.valueOf(frequency));
        settingPlainLabel(valueOfFrequency,545,95,200,75,30);
        container.add(valueOfFrequency); // 컨텐트팬에 valueOfFrequency 라벨 부착

        // ====[Time_Slider]===================================================================================
        timeSlider = new JSlider(10,60,time);
        settingSlider(timeSlider,265,315,485,50, 5);
        timeSlider.setName("timeSlider");
        timeSlider.setMajorTickSpacing(5);
        timeSlider.setMinorTickSpacing(1);
        container.add(timeSlider);
        changeEventSlider(timeSlider);
        // ====[Time_Label]===================================================================================
        valueOfTime = new JLabel("Time : " + String.valueOf(frequency));
        settingPlainLabel(valueOfTime,545,95,200,75,30); // 타임라벨 위치 재선정
        container.add(valueOfTime); // 컨텐트팬에 valueOfTime 라벨 부착

        // modeJLabel의 텍스트가 "Mode : Custom" 이거나 "Mode : Select" 일 떄
        // Mode를 설정하지 않았거나 커스텀 모드일 때
        if(modeJLabel.getText() == "Mode : Custom" || modeJLabel.getText() == "Mode : Select") {
            // JSlider 3개 활성화
            sizeSlider.setEnabled(true);
            speedSlider.setEnabled(true);
            frequencySlider.setEnabled(true);
        } else {
            // JSlider 3개 비활성화
            sizeSlider.setEnabled(false);
            speedSlider.setEnabled(false);
            frequencySlider.setEnabled(false);
        }

        // 창 설정
        settingScreen(800,500,false,null,true);
    }

    public void settingScreen(int sizeX, int sizeY, boolean resizable, Component locationRelativeTo, boolean visible) {
        setSize(sizeX,sizeY); // 창의 x,y 크기 인자로 받아 설정
        setResizable(resizable); // 창의 크기 변경 여부 인자로 받아 설정
        setLocationRelativeTo(locationRelativeTo); // 창의 상대적 위치 인자로 받아 설정
        setVisible(visible); // 창을 띄우는 여부 인자로 받아 설정
    }

    public void clickEvent (JButton buttonName, String modeSet, int sizeValue, int speedValue, int frequencyValue, boolean enabled) {
        buttonName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeJLabel.setText(modeSet); // modeJLabel의 텍스트 인자로 받아 설정
                customLabel.setText(modeSet); // customLabel의 텍스트 인자로 받아 설정

                sizeSlider.setValue(sizeValue); // sizeSlider의 값 인자로 받아 설정
                speedSlider.setValue(speedValue); // speedSlider의 값 인자로 받아 설정
                frequencySlider.setValue(frequencyValue); // frequency의 값 인자로 받아 설정

                sizeSlider.setEnabled(enabled); // sizeSlider 가동 여부 인자로 받아 설정
                speedSlider.setEnabled(enabled); // speedSlider 가동 여부 인자로 받아 설정
                frequencySlider.setEnabled(enabled); // frequencySlider 가동 여부 인자로 받아 설정

                saveButton.setEnabled(true); // saveButton 가동 여부 인자로 받아 설정
            }
        });
    }

    public void settingSlider (JSlider sliderName, int locationX, int locationY, int sizeX, int sizeY, int tickSpacing) {
        sliderName.setBackground(backGroundColor); // JSlider의 배경색 설정
        sliderName.setPaintTicks(true); // JSlider의 눈금 보이게 설정
        sliderName.setPaintTrack(true); // JSlider의 슬라이드바 보이게 설정
        sliderName.setPaintLabels(true); // JSlider의 숫자 보이게 설정
        sliderName.setMajorTickSpacing(tickSpacing); // JSlider의 눈금 간격 인자로 받아 설정
        sliderName.setLocation(locationX,locationY); // JSlider의 위치 인자로 받아 설정
        sliderName.setSize(sizeX,sizeY); // JSlider의 크기 인자로 받아 설정
    }

    public void changeEventSlider (JSlider sliderName) {
        sliderName.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (sliderName.getName()) { //
                    case "sizeSlider": // sliderName의 이름이 sizeSlider 일 때
                        size = sliderName.getValue(); // size에 sliderName의 현재 값 전달
                        valueOfSize.setText("Size : " + String.valueOf(size)); // valueOfSize의 텍스트 수정
                        break;
                    case "speedSlider": // sliderName의 이름이 speedSlider 일 때
                        speed = sliderName.getValue(); // speed에 sliderName의 현재 값 전달
                        valueOfSpeed.setText("Speed : " + String.valueOf(speed)); // valueOfSpeed의 텍스트 수정
                        break;
                    case "frequencySlider": // sliderName의 이름이 frequencySlider 일 때
                        frequency = sliderName.getValue();  // frequency에 sliderName의 현재 값 전달
                        valueOfFrequency.setText("Frequency : " + String.valueOf(frequency)); // valueOfFrequency의 텍스트 수정
                        break;
                    case "timeSlider": // sliderName의 이름이 timeSlider 일 때
                        time = sliderName.getValue(); // time에 sliderName의 현재 값 전달
                        valueOfTime.setText("Time : " + String.valueOf(time)); // valueOfTime의 텍스트 수정
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public static void main(String[] args) {
        new SettingScreen();
    }
}
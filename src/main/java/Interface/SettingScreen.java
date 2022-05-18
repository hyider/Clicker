package Interface;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Interface.MainScreen.*;

public class SettingScreen extends JFrame {

    public static int size;
    public static int frequency;
    public static int time;

    public static String [] modeList = {"Custom","Easy","Normal","Hard"};
    public static int listIndex = 0;

    public static JLabel valueOfSize;
    public static JLabel valueOfFrequency;
    public static JLabel valueOfTime;

    public static JButton saveButton;

    public static JSlider sizeSlider;
    public static JSlider frequencySlider;
    public static JSlider timeSlider;

    SettingScreen() {
        size = MainScreen.size;
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
        title.setLocation(15,15); // title 라벨의 위치
        title.setSize(190,75); // title 라벨의 크기
        container.add(title); // 컨텐트팬에 title 라벨 부착

        JLabel modeLabel = new JLabel(modeList[listIndex]);
        settingPlainLabel(modeLabel,395, 40,200,60,50);
        modeLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(modeLabel);
        
        JButton nextButton = new JButton("▶");
        settingButton(nextButton,645,40,50,50,15);
        container.add(nextButton);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listIndex++;
                if (listIndex>3)
                    listIndex = 0;
                modeLabel.setText(modeList[listIndex]);
                switch (listIndex) {
                    case 1:
                        sizeSlider.setValue(10); frequencySlider.setValue(1);
                        sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                        break;
                    case 2:
                        sizeSlider.setValue(5); frequencySlider.setValue(5);
                        sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                        break;
                    case 3:
                        sizeSlider.setValue(1); frequencySlider.setValue(10);
                        sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                        break;
                    case 0:
                        sizeSlider.setEnabled(true); frequencySlider.setEnabled(true);
                        break;
                    default: break;
                }
            }
        });
        JButton preButton = new JButton("◀");
        settingButton(preButton,295,40,50,50,15);
        container.add(preButton);
        preButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listIndex--;
                if (listIndex<0)
                    listIndex = 3;
                modeLabel.setText(modeList[listIndex]);
                switch (listIndex) {
                    case 1:
                        sizeSlider.setValue(10); frequencySlider.setValue(1);
                        sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                        break;
                    case 2:
                        sizeSlider.setValue(5); frequencySlider.setValue(5);
                        sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                        break;
                    case 3:
                        sizeSlider.setValue(1); frequencySlider.setValue(10);
                        sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                        break;
                    case 0:
                        sizeSlider.setEnabled(true); frequencySlider.setEnabled(true);
                        break;
                    default: break;
                }
            }
        });

        // ====[Size_Slider]============================================================================================
        sizeSlider = new JSlider(1,10,size);
        settingSlider(sizeSlider,220,170,550,20,1);
        container.add(sizeSlider);
        changeEventSlider(sizeSlider);
        // ====[Size_Label]=============================================================================================
        JLabel sizeLabel = new JLabel("Size");
        settingPlainLabel(sizeLabel,10,145,150,50,40);
        container.add(sizeLabel);
        valueOfSize = new JLabel(String.valueOf(size));
        settingPlainLabel(valueOfSize,150,150,50,50,40);
        container.add(valueOfSize);

        // ====[Frequency_Slider]=======================================================================================
        frequencySlider = new JSlider(1,10,frequency);
        settingSlider(frequencySlider,220,250,550,20,1);
        container.add(frequencySlider);
        changeEventSlider(frequencySlider);
        // ====[Frequency_Label]========================================================================================
        JLabel freqLabel = new JLabel("Freq");
        settingPlainLabel(freqLabel,10,225,150,50,40);
        container.add(freqLabel);
        valueOfFrequency = new JLabel(String.valueOf(frequency));
        settingPlainLabel(valueOfFrequency,150,230,50,50,40);
        container.add(valueOfFrequency);

        // ====[Time_Slider]============================================================================================
        timeSlider = new JSlider(10,60,time);
        settingSlider(timeSlider,220,330,550,20, 5);
        container.add(timeSlider);
        changeEventSlider(timeSlider);
        // ====[Time_Label]=============================================================================================
        JLabel timeLabel = new JLabel("Time");
        settingPlainLabel(timeLabel,10,305,150,50,40); // 타임라벨 위치 재선정
        container.add(timeLabel);
        valueOfTime = new JLabel(String.valueOf(time));
        settingPlainLabel(valueOfTime,150,310,50,50,40); // 타임라벨 위치 재선정
        container.add(valueOfTime);

        JButton selectModeButton = new JButton("Mode");
        settingButton(selectModeButton,400, 380,150,50,30);
        container.add(selectModeButton);
        selectModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingModeScreen();
            }
        });

        saveButton = new JButton("Save"); // saveButton 버튼 문자열과 함께 생성
        settingButton(saveButton,600,380,150,50,30);
        container.add(saveButton); // 컨텐트팬에 saveButton 버튼 부착

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainScreen.size = size;
                frequencySliderShow = frequency; // Freq의 값을 보여지는 부분
                MainScreen.frequency = 10 - (frequency-1); // 실제로 전달되는 Freq의 값
                // Freq가 높아질수록 Target 쓰레드 sleep()의 인자로 전달되는 값이 작아져야 하기 때문
                MainScreen.time = time;
                modeJLabel.setText("Mode : " + modeLabel.getText());
                dispose();
            }
        });

        switch (listIndex) {
            case 1:
                sizeSlider.setValue(10); frequencySlider.setValue(1);
                sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                break;
            case 2:
                sizeSlider.setValue(5); frequencySlider.setValue(5);
                sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                break;
            case 3:
                sizeSlider.setValue(1); frequencySlider.setValue(10);
                sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                break;
            case 0:
                sizeSlider.setValue(size); frequencySlider.setValue(frequency);
                sizeSlider.setEnabled(true); frequencySlider.setEnabled(true);
                break;
            default: break;
        }

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
        if (locationY == 170 );
        switch (locationY) {
            case 170:
                sliderName.setName("sizeSlider"); break;
            case 250:
                sliderName.setName("frequencySlider"); break;
            case 330:
                sliderName.setName("timeSlider"); break;
        }
    }

    public void changeEventSlider (JSlider sliderName) {
        sliderName.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (sliderName.getName()) { //
                    case "sizeSlider": // sliderName의 이름이 sizeSlider 일 때
                        size = sliderName.getValue(); // size에 sliderName의 현재 값 전달
                        valueOfSize.setText(String.valueOf(size)); // valueOfSize의 텍스트 수정
                        break;
                    case "frequencySlider": // sliderName의 이름이 frequencySlider 일 때
                        frequency = sliderName.getValue();  // frequency에 sliderName의 현재 값 전달
                        valueOfFrequency.setText(String.valueOf(frequency)); // valueOfFrequency의 텍스트 수정
                        break;
                    case "timeSlider": // sliderName의 이름이 timeSlider 일 때
                        time = sliderName.getValue(); // time에 sliderName의 현재 값 전달
                        valueOfTime.setText(String.valueOf(time)); // valueOfTime의 텍스트 수정
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

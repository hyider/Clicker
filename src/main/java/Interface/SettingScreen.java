package Interface;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

import static Interface.MainScreen.modeJLabel;

public class SettingScreen extends JFrame {
    public static int size;
    public static int speed;
    public static int frequency;

    public static JLabel valueOfSize;
    public static JLabel valueOfSpeed;
    public static JLabel valueOfFrequency;

    public static JLabel customLabel;

    public static JButton saveButton;

    public static JSlider sizeSlider;
    public static JSlider speedSlider;
    public static JSlider frequencySlider;

    public SettingScreen() {
        size = MainScreen.size;
        speed = MainScreen.speed;
        frequency = MainScreen.frequency;

        setTitle("Setting");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Color backGroundColor = new Color(86,187,241); // 배경색 설정
        Color buttonColor = new Color(77,119,255); // 버튼색 설정

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null); // 배치관리자는 없음

        // ====제목==========================================================================
        JLabel title = new JLabel("Setting"); // title 라벨 문자열과 함께 생성
        title.setFont(new Font("Slab Serif",Font.ITALIC,60)); // 폰트 설정
        title.setForeground(Color.WHITE); // title 라벨의 글씨 하얀색 설정
        title.setLocation(35,15); // title 라벨의 위치
        title.setSize(200,75); // title 라벨의 크기
        container.add(title); // 컨텐트팬에 title 라벨 부착

        // ===[Mode_Label]==========================================================================
        customLabel = new JLabel(modeJLabel.getText()); // customLabel 라벨 문자열과 함께 생성
        customLabel.setFont(new Font("Slab Serif",Font.PLAIN,40)); // 폰트 설정
        customLabel.setForeground(Color.WHITE); // customLabel 라벨의 글씨 하얀색 설정
        customLabel.setLocation(400,15); // customLabel 라벨의 위치
        customLabel.setSize(300,75); // customLabel 라벨의 크기
        container.add(customLabel); // 컨텐트팬에 customLabel 라벨 부착



        // ==[Easy_Button]=======================================================================
        JButton easyButton = new JButton("Easy"); // easyButton 버튼 문자열과 함께 생성
        easyButton.setFont(new Font("Slab Serif",Font.BOLD,30)); // 폰트 설정
        easyButton.setForeground(Color.WHITE); // easyButton 버튼의 글씨 하얀색 설정
        easyButton.setBackground(buttonColor); // easyButton 버튼의 배경색 설정
        easyButton.setLocation(35,115); // easyButton 버튼의 위치
        easyButton.setSize(150,50); // easyButton 버튼의 크기
        container.add(easyButton); // 컨텐트팬에 easyButton 버튼 부착

        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeJLabel.setText("Mode : Easy");
                customLabel.setText("Mode : Easy");
                sizeSlider.setValue(10);
                sizeSlider.setEnabled(false);
                speedSlider.setValue(1);
                speedSlider.setEnabled(false);
                frequencySlider.setValue(1);
                frequencySlider.setEnabled(false);
                saveButton.setEnabled(true);
            }
        });

        // ==[Normal_Button]=======================================================================
        JButton normalButton = new JButton("Normal"); // normalButton 버튼 문자열과 함께 생성
        normalButton.setFont(new Font("Slab Serif",Font.BOLD,30)); // 폰트 설정
        normalButton.setForeground(Color.WHITE); // normalButton 버튼의 글씨 하얀색 설정
        normalButton.setBackground(buttonColor); // normalButton 버튼의 배경색 설정
        normalButton.setLocation(35,175); // normalButton 버튼의 위치
        normalButton.setSize(150,50); // normalButton 버튼의 크기
        container.add(normalButton); // 컨텐트팬에 normalButton 버튼 부착

        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeJLabel.setText("Mode : Normal");
                customLabel.setText("Mode : Normal");
                sizeSlider.setValue(5);
                sizeSlider.setEnabled(false);
                speedSlider.setValue(5);
                speedSlider.setEnabled(false);
                frequencySlider.setValue(5);
                frequencySlider.setEnabled(false);
                saveButton.setEnabled(true);
            }
        });

        // ==[Hard_Button]=======================================================================
        JButton hardButton = new JButton("Hard"); // hardButton 버튼 문자열과 함께 생성
        hardButton.setFont(new Font("Slab Serif",Font.BOLD,30)); // 폰트 설정
        hardButton.setForeground(Color.WHITE); // hardButton 버튼의 글씨 하얀색 설정
        hardButton.setBackground(buttonColor); // hardButton 버튼의 배경색 설정
        hardButton.setLocation(35,235); // hardButton 버튼의 위치
        hardButton.setSize(150,50); // hardButton 버튼의 크기
        container.add(hardButton); // 컨텐트팬에 hardButton 버튼 부착

        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeJLabel.setText("Mode : Hard");
                customLabel.setText("Mode : Hard");
                sizeSlider.setValue(1);
                sizeSlider.setEnabled(false);
                speedSlider.setValue(10);
                speedSlider.setEnabled(false);
                frequencySlider.setValue(10);
                frequencySlider.setEnabled(false);
                saveButton.setEnabled(true);
            }
        });

        // ==[Custom_Button]=======================================================================
        JButton customButton = new JButton("Custom"); // customButton 버튼 문자열과 함께 생성
        customButton.setFont(new Font("Slab Serif",Font.BOLD,30)); // 폰트 설정
        customButton.setForeground(Color.WHITE); // customButton 버튼의 글씨 하얀색 설정
        customButton.setBackground(buttonColor); // customButton 버튼의 배경색 설정
        customButton.setLocation(35,295); // customButton 버튼의 위치
        customButton.setSize(150,50); // customButton 버튼의 크기
        container.add(customButton); // 컨텐트팬에 customButton 버튼 부착

        customButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeJLabel.setText("Mode : Custom");
                customLabel.setText("Mode : Custom");
                saveButton.setEnabled(true);
                sizeSlider.setEnabled(true);
                speedSlider.setEnabled(true);
                frequencySlider.setEnabled(true);
            }
        });

        // ==[Save_Button]=======================================================================
        saveButton = new JButton("Save"); // saveButton 버튼 문자열과 함께 생성
        saveButton.setFont(new Font("Slab Serif",Font.BOLD,30)); // 폰트 설정
        saveButton.setForeground(Color.WHITE); // saveButton 버튼의 글씨 하얀색 설정
        saveButton.setBackground(buttonColor); // saveButton 버튼의 배경색 설정
        saveButton.setLocation(35,355); // saveButton 버튼의 위치
        saveButton.setSize(150,50); // saveButton 버튼의 크기
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
                MainScreen.frequency = frequency;
                dispose();
            }
        });






        /**
        JSlider [] valueSlider = new JSlider[3];
        for(int i=0; i<valueSlider.length(); i++) {
            valueSlider[i] = new JSlider(0,10,5);
            valueSlider[i].setBackground(backGroundColor);
            valueSlider[i].setPaintTicks(true);
            valueSlider[i].setPaintTrack(true);
            valueSlider[i].setPaintLabels(true);
            valueSlider[i].setMajorTickSpacing(1);
            valueSlider[i].setLocation(265,165+(i*100));
            valueSlider[i].setSize(485,50);
            valueSlider[i].addChangeListener(new MyChangeListener());
            container.add(valueSlider[i]);
        }

        size = valueSlider[0].getValue();
        speed = valueSlider[1].getValue();
        frequency = valueSlider[2].getValue();
        **/




        /** Size Slider And Label **/
        sizeSlider = new JSlider(1,10,size);
        sizeSlider.setBackground(backGroundColor);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setMajorTickSpacing(1);
        sizeSlider.setPaintTrack(true);
        sizeSlider.setPaintLabels(true);
        sizeSlider.setLocation(265,165);
        sizeSlider.setSize(485,50);
        container.add(sizeSlider);
        //size = sizeSlider.getValue();

        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                size = sizeSlider.getValue();
                valueOfSize.setText("Size : " + String.valueOf(size));
            }
        });

        valueOfSize = new JLabel("Size : " + String.valueOf(size));
        valueOfSize.setFont(new Font("Slab Serif",Font.PLAIN,30)); // 폰트 설정
        valueOfSize.setForeground(Color.WHITE); // valueOfSize 라벨의 글씨 하얀색 설정
        valueOfSize.setLocation(265,95); // valueOfSize 라벨의 위치
        valueOfSize.setSize(200,75); // valueOfSize 라벨의 크기
        container.add(valueOfSize); // 컨텐트팬에 valueOfSize 라벨 부착

        /** Speed Slider And Label **/
        speedSlider = new JSlider(1,10,speed);
        speedSlider.setBackground(backGroundColor);
        speedSlider.setPaintTicks(true);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTrack(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setLocation(265,265);
        speedSlider.setSize(485,50);
        container.add(speedSlider);
        //speed = speedSlider.getValue();

        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                speed = speedSlider.getValue();
                valueOfSpeed.setText("Speed : " + String.valueOf(speed));
            }
        });

        valueOfSpeed = new JLabel("Speed : " + String.valueOf(speed));
        valueOfSpeed.setFont(new Font("Slab Serif",Font.PLAIN,30)); // 폰트 설정
        valueOfSpeed.setForeground(Color.WHITE); // valueOfSpeed 라벨의 글씨 하얀색 설정
        valueOfSpeed.setLocation(395,95); // valueOfSpeed 라벨의 위치
        valueOfSpeed.setSize(200,75); // valueOfSpeed 라벨의 크기
        container.add(valueOfSpeed); // 컨텐트팬에 valueOfSpeed 라벨 부착


        /** Frequency Slider And Label **/
        frequencySlider = new JSlider(1,10,frequency);
        frequencySlider.setBackground(backGroundColor);
        frequencySlider.setPaintTicks(true);
        frequencySlider.setMajorTickSpacing(1);
        frequencySlider.setPaintTrack(true);
        frequencySlider.setPaintLabels(true);
        frequencySlider.setLocation(265,365);
        frequencySlider.setSize(485,50);
        container.add(frequencySlider);
        //frequency = frequencySlider.getValue();

        frequencySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                frequency = frequencySlider.getValue();
                valueOfFrequency.setText("Frequency : " + String.valueOf(frequency));
            }
        });

        valueOfFrequency = new JLabel("Frequency : " + String.valueOf(frequency));
        valueOfFrequency.setFont(new Font("Slab Serif",Font.PLAIN,30)); // 폰트 설정
        valueOfFrequency.setForeground(Color.WHITE); // valueOfFrequency 라벨의 글씨 하얀색 설정
        valueOfFrequency.setLocation(545,95); // valueOfFrequency 라벨의 위치
        valueOfFrequency.setSize(200,75); // valueOfFrequency 라벨의 크기
        container.add(valueOfFrequency); // 컨텐트팬에 valueOfFrequency 라벨 부착

        if(modeJLabel.getText() == "Mode : Custom" || modeJLabel.getText() == "Mode : Select") {
            sizeSlider.setEnabled(true);
            speedSlider.setEnabled(true);
            frequencySlider.setEnabled(true);
        } else {
            sizeSlider.setEnabled(false);
            speedSlider.setEnabled(false);
            frequencySlider.setEnabled(false);
        }

        setSize(800,500); // 창의 크기 설정
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true); // 화면에 창을 나타냄


    }

    /**
    class MyChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            size = valueSlider[0].getValue();
            speed = valueSlider[1].getValue();
            frequency = valueSlider[2].getValue();
        }
    }
     **/

    public static void main(String[] args) {
        new SettingScreen();
    }
}
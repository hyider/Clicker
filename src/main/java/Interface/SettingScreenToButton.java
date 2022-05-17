package Interface;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import static Interface.MainScreen.*;

public class SettingScreenToButton extends JFrame {

    public static int size;
    public static int speed;
    public static int frequency;
    public static int time;

    public static String [] modeList = {"Custom","Easy","Normal","Hard"};
    public static int listIndex = 0;

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

    SettingScreenToButton() {
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
        title.setLocation(15,15); // title 라벨의 위치
        title.setSize(190,75); // title 라벨의 크기
        container.add(title); // 컨텐트팬에 title 라벨 부착


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

        /*
        LinkedList<String> modeList = new LinkedList<String>();
        modeList.add("Easy"); modeList.add("Normal");
        modeList.add("Hard"); modeList.add("Custom");
        ListIterator<String> setMode = modeList.listIterator();
        */

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
        /*
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!setMode.hasNext()){
                    for(int i=0;i<3;i++)
                        setMode.previous();
                    modeLabel.setText(setMode.previous());
                }
                modeLabel.setText(setMode.next());
                switch (modeLabel.getText()) {
                    case "Easy":
                        sizeSlider.setValue(10); frequencySlider.setValue(1);
                        sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                        break;
                    case "Normal":
                        sizeSlider.setValue(5); frequencySlider.setValue(5);
                        sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                        break;
                    case "Hard":
                        sizeSlider.setValue(1); frequencySlider.setValue(10);
                        sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                        break;
                    case "Custom":
                        sizeSlider.setEnabled(true); frequencySlider.setEnabled(true);
                        break;
                    default: break;
                }
                saveButton.setEnabled(true);
            }

        });
        */
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
        /*
        preButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!setMode.hasPrevious()){
                    for(int i=0;i<3;i++)
                        setMode.next();
                    modeLabel.setText(setMode.next());
                }
                modeLabel.setText(setMode.previous());
                switch (modeLabel.getText()) {
                    case "Easy":
                        sizeSlider.setValue(10); frequencySlider.setValue(1);
                        sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                        break;
                    case "Normal":
                        sizeSlider.setValue(5); frequencySlider.setValue(5);
                        sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                        break;
                    case "Hard":
                        sizeSlider.setValue(1); frequencySlider.setValue(10);
                        sizeSlider.setEnabled(false); frequencySlider.setEnabled(false);
                        break;
                    case "Custom":
                        sizeSlider.setEnabled(true); frequencySlider.setEnabled(true);
                        break;
                    default: break;
                }
                saveButton.setEnabled(true);
            }
        });
        */


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

        saveButton = new JButton("Save"); // saveButton 버튼 문자열과 함께 생성
        settingButton(saveButton,600,380,150,50,30);
        container.add(saveButton); // 컨텐트팬에 saveButton 버튼 부착

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainScreen.size = size;
                MainScreen.speed = speed;
                frequencySliderShow = frequency;
                MainScreen.frequency = 10 - (frequency-1);
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
                    case "speedSlider": // sliderName의 이름이 speedSlider 일 때
                        speed = sliderName.getValue(); // speed에 sliderName의 현재 값 전달
                        valueOfSpeed.setText(String.valueOf(speed)); // valueOfSpeed의 텍스트 수정
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
        new SettingScreenToButton();
    }
}

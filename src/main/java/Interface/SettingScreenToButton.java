package Interface;

import javax.swing.*;
import java.awt.*;

import static Interface.MainScreen.backGroundColor;
import static Interface.MainScreen.frequencySliderShow;

public class SettingScreenToButton extends JFrame {

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
        title.setLocation(35,15); // title 라벨의 위치
        title.setSize(200,75); // title 라벨의 크기
        container.add(title); // 컨텐트팬에 title 라벨 부착

    }

    public static void main(String[] args) {
        new SettingScreenToButton();
    }
}

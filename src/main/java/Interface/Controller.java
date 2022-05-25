package Interface;

import javax.swing.*;
import java.awt.*;

public class Controller extends JFrame {
    public static Color backGroundColor = new Color(86,187,241); // 배경색 설정
    public static Color buttonColor = new Color(77,119,255); // 버튼색 설정

    public static int size = 1;
    public static int frequency = 1;
    public static int time = 10;
    public static int frequencySliderShow = 1;
    public static int speed = 1;
    public static int mode2 = 1;
    public static int mode3 = 1;
    public static boolean mode1Selected = false;
    public static boolean mode2Selected = false;
    public static boolean mode3Selected = false;

    public static void settingButton(JButton buttonName, int locationX, int locationY, int sizeX, int sizeY,int fontSize){
        buttonName.setFont(new Font("Slab Serif", Font.BOLD, fontSize)); // 폰트 설정, 폰트 크기 인자로 받아 설정
        buttonName.setForeground(Color.WHITE); // 글씨 색 설정
        buttonName.setBackground(buttonColor); // 버튼 배경 색 설정
        buttonName.setLocation(locationX, locationY); // 버튼의 위치 인자로 받아 설정
        buttonName.setSize(sizeX, sizeY); // 버큰의 크기 인자로 받아 설정
    }

    public static void settingPlainLabel(JLabel labelName, int locationX, int locationY, int sizeX, int sizeY, int fontSize) {
        labelName.setFont(new Font("Slab Serif",Font.PLAIN,fontSize)); // 폰트 설정 - PLAIN, 폰트 크기 인자로 받아 설정
        labelName.setForeground(Color.WHITE); // 글씨 색 하얀색 설정
        labelName.setLocation(locationX,locationY); // 라벨의 위치 인자로 받아 설정
        labelName.setSize(sizeX,sizeY); // 라벨의 크기 인자로 받아 설정
    }

    public static void settingBoldLabel(JLabel labelName, int locationX, int locationY, int sizeX, int sizeY, int fontSize) {
        labelName.setFont(new Font("Slab Serif",Font.BOLD,fontSize)); // 폰트 설정 - BOLD, 폰트 크기 인자로 받아 설정
        labelName.setForeground(Color.WHITE); // 글씨 색 하얀색 설정
        labelName.setLocation(locationX,locationY); // 라벨의 위치 인자로 받아 설정
        labelName.setSize(sizeX,sizeY); // 라벨의 크기 인자로 받아 설정
    }
    public static void settingItalicLabel(JLabel labelName, int locationX, int locationY, int sizeX, int sizeY, int fontSize) {
        labelName.setFont(new Font("Slab Serif",Font.ITALIC,fontSize)); // 폰트 설정 - ITALIC, 폰트 크기 인자로 받아 설정
        labelName.setForeground(Color.WHITE); // 글씨 색 하얀색 설정
        labelName.setLocation(locationX,locationY); // 라벨의 위치 인자로 받아 설정
        labelName.setSize(sizeX,sizeY); // 라벨의 크기 인자로 받아 설정
    }

    public static void settingSlider (JSlider sliderName, int locationX, int locationY, int sizeX, int sizeY, int tickSpacing) {
        sliderName.setBackground(backGroundColor); // JSlider의 배경색 설정
        sliderName.setPaintTicks(false); // JSlider의 눈금 보이게 설정
        sliderName.setPaintTrack(true); // JSlider의 슬라이드바 보이게 설정
        sliderName.setPaintLabels(false); // JSlider의 숫자 보이게 설정
        sliderName.setMajorTickSpacing(tickSpacing); // JSlider의 눈금 간격 인자로 받아 설정
        sliderName.setLocation(locationX,locationY); // JSlider의 위치 인자로 받아 설정
        sliderName.setSize(sizeX,sizeY); // JSlider의 크기 인자로 받아 설정
    }

    public static void createTarget(JButton targetButton) {
        // 이미지 등록
        ImageIcon targetImage = new ImageIcon("src/main/resources/Target.png");
        ImageIcon targetImageRollover = new ImageIcon("src/main/resources/TargetRollover.png");
        ImageIcon targetImagePressed = new ImageIcon("src/main/resources/TargetPressed.png");
        // 이미지를 버튼 크기에 맞춤
        Image targetScale = (targetImage.getImage()).getScaledInstance((Controller.size+10)*5,(Controller.size+10)*5,Image.SCALE_SMOOTH);
        Image targetRolloverScale = (targetImageRollover.getImage()).getScaledInstance((Controller.size+10)*5,(Controller.size+10)*5,Image.SCALE_SMOOTH);
        Image targetPressedScale = (targetImagePressed.getImage()).getScaledInstance((Controller.size+10)*5, (Controller.size+10)*5, Image.SCALE_SMOOTH);
        // 버튼 크기에 맞춘 이미지아이콘 생성
        ImageIcon target = new ImageIcon(targetScale);
        ImageIcon targetRollover = new ImageIcon(targetRolloverScale);
        ImageIcon targetPressed = new ImageIcon(targetPressedScale);

        targetButton.setIcon(target);
        targetButton.setRolloverIcon(targetRollover);
        targetButton.setPressedIcon(targetPressed);
        targetButton.setSize((Controller.size + 10) * 5, (Controller.size + 10) * 5);
        targetButton.setBorderPainted(false);
        targetButton.setContentAreaFilled(false);
    }

}

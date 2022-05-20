package Interface;

import javax.swing.*;
import java.awt.*;

public class ContentSetting extends JFrame {
    public static Color backGroundColor = new Color(86,187,241); // 배경색 설정
    public static Color buttonColor = new Color(77,119,255); // 버튼색 설정

    public static int size = 1, frequency = 1, time = 10, frequencySliderShow = 1, speed = 1; // 게임 속성 변수
    public static boolean mode1Selected = false;
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

}

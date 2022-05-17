package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Test extends JFrame {
    public Test() {



        Color backGroundColor = new Color(86,187,241); // 배경색 설정
        Color buttonColor = new Color(77,119,255); // 버튼색 설정

        setTitle("Score");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container container = getContentPane();

        container.setBackground(backGroundColor);

        container.setLayout(null);

        JButton test1 = new JButton("Test1");
        test1.setBackground(buttonColor);
        test1.setForeground(Color.WHITE);
        test1.setFont(new Font("Slab Serif",Font.BOLD,20));
        test1.setLocation(100,100);
        test1.setSize(100,50);
        container.add(test1);

        JButton test2 = new JButton("test2");
        test2.setBackground(buttonColor);
        test2.setForeground(Color.WHITE);
        test2.setFont(new Font("Slab Serif",Font.BOLD,20));
        test2.setLocation(150,125);
        test2.setSize(500,400);
        container.add(test2);




        setSize(800,500); // 창의 크기 설정
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true); // 화면에 창을 나타냄

    }

    public static void main(String[] args) {
        new Test();
    }
}

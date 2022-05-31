package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Interface.Controller.*;
import static Interface.Controller.settingBoldLabel;
import static Interface.MainScreen.start;

public class Test extends JFrame {
    Test() {
        setTitle("Start");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        Container container = getContentPane();
        container.setLayout(null);
        container.setBackground(backGroundColor);

        JButton stop = new JButton("S");
        settingButton(stop,735,0,50,50,20);
        container.add(stop);
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });





        // 화면 구성
        settingScreen(800,800,false,null,true);
    }

    public void settingScreen(int sizeX, int sizeY, boolean resizable, Component locationRelativeTo, boolean visible) {
        setSize(sizeX,sizeY);
        setResizable(resizable);
        setLocationRelativeTo(locationRelativeTo);
        setVisible(visible);
    }

    public static void main(String[] args) {
        new Test();
    }
}

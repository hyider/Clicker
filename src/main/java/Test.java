import Interface.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Test extends JFrame {
    Test() {
        setTitle("Start");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(null);

        /**
         * 이미지 10개 한번에 등록하기
         */
        ImageIcon [] shieldTargetImage = new ImageIcon[10];
        Image [] shieldTargetScale = new Image[10];
        ImageIcon [] shieldTarget = new ImageIcon[10];

        for(int i=0;i<10;i++) {
            shieldTargetImage[i] = new ImageIcon("src/main/resources/TargetNumber" + (i+1) +".png");
            shieldTargetScale[i] = (shieldTargetImage[i].getImage()).getScaledInstance(100,100,Image.SCALE_SMOOTH);
            shieldTarget[i] = new ImageIcon(shieldTargetScale[i]);
        }

        // 이 변수를 defense로 컨트롤러에서 값을 변경 -> JSlider 사용하여 변경
        int count = 10;

        int [] level = {1,2,3,4,5,6,7,8,9,10};

        Stack stack = new Stack();
        int stackCount = 0;
        while(count>0) {
            stack.push(level[stackCount]);
            stackCount++;
            count--;
        }
        System.out.println();
        JButton [] shieldTargetButton = new JButton[10];
        while(!stack.isEmpty()) {
            int stackNum = (int) stack.pop() - 1;
            shieldTargetButton[stackNum] = new JButton(shieldTarget[stackNum]);
            shieldTargetButton[stackNum].setSize(100,100);
            shieldTargetButton[stackNum].setLocation(100,100);
            container.add(shieldTargetButton[stackNum]);
            shieldTargetButton[stackNum].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    container.remove(shieldTargetButton[stackNum]);
                    container.repaint();
                }
            });
        }






        //==============================================================================================================
        /**
        JButton [] shieldTargetButton = new JButton[10];

        for(int i=0;i<10;i++) {
            shieldTargetButton[i] = new JButton();
            shieldTargetButton[i].setIcon(shieldTarget[i]);
            shieldTargetButton[i].setSize(100,100);
            shieldTargetButton[i].setLocation(i*10,i*10);
            container.add(shieldTargetButton[i]);
        }
        */

        setSize(1000,1000);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Test();
    }
}

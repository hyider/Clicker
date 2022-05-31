import Interface.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Test extends JFrame {
    Test() {
        setTitle("Start");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(null);

        /**
         * 이미지 10개 한번에 등록하기
         */
        ArrayList<ImageIcon> aShieldTargetImage = new ArrayList<>(10);
        ArrayList<Image> aShieldTargetScale = new ArrayList<>(10);
        ArrayList<ImageIcon> aShieldTarget = new ArrayList<>(10);
        ArrayList<JButton> aShieldTargetButton = new ArrayList<>(10);

        int imageCount = 1;
        while(imageCount < 11) {
            aShieldTargetImage.add(new ImageIcon("src/main/resources/TargetNumber" + imageCount + ".png"));
            aShieldTargetScale.add(((aShieldTargetImage.get(imageCount-1)).getImage()).getScaledInstance(100,100,Image.SCALE_SMOOTH));
            aShieldTarget.add(new ImageIcon(aShieldTargetScale.get(imageCount-1)));
            aShieldTargetButton.add(new JButton(aShieldTarget.get(imageCount-1)));
            imageCount++;
        }

        // 이 변수를 defense로 컨트롤러에서 값을 변경 -> JSlider 사용하여 변경
        int defense = 10;
        int [] defenseArray = {1,2,3,4,5,6,7,8,9,10};

        Stack stack = new Stack();
        int stackCount = 0;
        while(defense>0) {
            stack.push(defenseArray[stackCount]);
            stackCount++;
            defense--;
        }

        while(!stack.isEmpty()) {
            int stackNum = (int) stack.pop() - 1;
            aShieldTargetButton.get(stackNum).setSize(100,100);
            aShieldTargetButton.get(stackNum).setLocation(100,100);
            container.add(aShieldTargetButton.get(stackNum));
            aShieldTargetButton.get(stackNum).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    container.remove(aShieldTargetButton.get(stackNum));
                    container.repaint();
                }
            });
        }

        setSize(1000,1000);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Test();
    }
}

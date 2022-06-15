package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Interface.Controller.*;

public class ReadScreen extends JFrame {
    public ReadScreen(){
        setTitle("Read");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null);

        JLabel howLabel = new JLabel("How To Use"); // howLabel 라벨 문자열과 함께 생성
        settingBoldLabel(howLabel,10,0,300,50,40);
        container.add(howLabel); // 컨텐트팬에 howLabel 라벨 부착

        String howText = """
                Set the size and frequency of the target.
                
                [1] Default mode.
                Click on a stopped target to get a score.
                                
                [2] Moving mode.
                Set the speed of the target.
                Click on the moving target to get a score.
                
                [3] Shied Mode.
                Set the defense of the target.
                Click on the target's shield as much as the defense and get a score.
                
                [4] Item mode.
                Set the item to drop speed of the target.
                Click on the items and get effect.
                
                The red hourglass increases three seconds.
                The blue hourglass decreases three seconds.
                Bomb is display the Game Over screen.
                (The score screen and the game over screen are different!)
                """;

        String howText1 = "Set the size and frequency of the target.\n" +
                "\n" +
                "[1] Default mode.\n" +
                "Click on a stopped target to get a score.\n" +
                "\n" +
                "[2] Moving mode.\n" +
                "Set the speed of the target.\n" +
                "Click on the moving target to get a score.\n" +
                "\n" +
                "[3] Shied Mode.\n" +
                "Set the defense of the target.\n" +
                "Click on the target's shield as much as the defense and get a score.\n" +
                "\n" +
                "[4] Item mode.\n" +
                "Set the item to drop speed of the target.\n" +
                "Click on the items and get effect.\n" +
                "\n" +
                "The red hourglass increases three seconds.\n" +
                "The blue hourglass decreases three seconds.\n" +
                "Bomb is display the Game Over screen.\n" +
                "(The score screen and the game over screen are different!)";

        JTextArea how = new JTextArea();
        how.setText(howText1); // JTextArea의 텍스트 howText로 전달
        how.setFont(new Font("Slab Serif",Font.BOLD,13));
        how.setEditable(false); // JTextArea 수정 불가능

        how.setLocation(10,40); // JTextArea의 위치 설정
        how.setSize(370,350); // JTextArea의 크기 설정
        container.add(how);

        JScrollPane howScroll = new JScrollPane(how);
        howScroll.setLocation(10,40);
        howScroll.setSize(370,350);
        container.add(howScroll);

        JLabel patchNoteLabel = new JLabel("Patch Note"); // patchNoteLabel 라벨 문자열과 함께 생성
        settingBoldLabel(patchNoteLabel,400,0,300,50,40);
        container.add(patchNoteLabel); // 컨텐트팬에 patchNoteLabel 라벨 부착

        String patchNoteText = """
                [0.1]
                - Default Mode
                
                [0.2]
                - Moving Mode
                
                [0.3]
                - Shield Mode
                
                [0.4]
                - Item Mode
                
                [Alpha - 1.0]
                - Alpha Test
                - Add Three Mode - Moving / Shield / Item
                """;
        JTextArea patchNoteTextArea = new JTextArea();
        patchNoteTextArea.setText(patchNoteText); // JTextArea의 텍스트 howText로 전달
        patchNoteTextArea.setFont(new Font("Slab Serif",Font.BOLD,13));
        patchNoteTextArea.setEditable(false); // JTextArea 수정 불가능
        patchNoteTextArea.setLocation(400,40); // JTextArea의 위치 설정
        patchNoteTextArea.setSize(375,350); // JTextArea의 크기 설정
        container.add(patchNoteTextArea);

        JButton preButton = new JButton("Pre");
        settingButton(preButton,650,400,100,50,40);
        container.add(preButton); // 컨텐트팬에 preButton 버튼 부착

        preButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        setSize(800,500); // 창의 크기 설정
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true); // 화면에 창을 나타냄
    }
}

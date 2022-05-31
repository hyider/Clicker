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

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null);

        JLabel howLabel = new JLabel("How To Use"); // howLabel 라벨 문자열과 함께 생성
        settingBoldLabel(howLabel,10,0,300,50,40);
        container.add(howLabel); // 컨텐트팬에 howLabel 라벨 부착

        String howText = """
                [1] Default mode.
                Set the size and frequency of the target
                Click on a stopped target to get a score.
                                
                [2] Moving mode.
                Set the size, frequency, and speed of the target
                Click on the moving target to get a score.
                """;
        JTextArea how = new JTextArea();
        how.setText(howText); // JTextArea의 텍스트 howText로 전달
        how.setFont(new Font("Slab Serif",Font.BOLD,15));
        how.setEditable(false); // JTextArea 수정 불가능
        how.setLocation(10,40); // JTextArea의 위치 설정
        how.setSize(370,350); // JTextArea의 크기 설정
        container.add(how);

        JLabel patchNoteLabel = new JLabel("Patch Note"); // patchNoteLabel 라벨 문자열과 함께 생성
        settingBoldLabel(patchNoteLabel,400,0,300,50,40);
        container.add(patchNoteLabel); // 컨텐트팬에 patchNoteLabel 라벨 부착

        String patchNoteText = """
                [0.1]
                - Default Mode
                
                [0.2]
                - Moving Mode
                """;
        JTextArea patchNoteTextArea = new JTextArea();
        patchNoteTextArea.setText(patchNoteText); // JTextArea의 텍스트 howText로 전달
        patchNoteTextArea.setFont(new Font("Slab Serif",Font.BOLD,10));
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

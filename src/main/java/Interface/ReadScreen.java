package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Interface.MainScreen.backGroundColor;

public class ReadScreen extends JFrame {
    public ReadScreen(){
        setTitle("Setting");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null);

        JLabel howLabel = new JLabel("How To Use"); // howLabel 라벨 문자열과 함께 생성
        MainScreen.settingBoldLabel(howLabel,10,0,300,50,40);
        container.add(howLabel); // 컨텐트팬에 howLabel 라벨 부착

        String howText = """
                How to using the AimSimulation?
                How to using the AimSimulation?
                How to using the AimSimulation?
                """;
        JTextArea how = new JTextArea();
        how.setText(howText); // JTextArea의 텍스트 howText로 전달
        how.setEditable(false); // JTextArea 수정 불가능
        how.setLocation(10,40); // JTextArea의 위치 설정
        how.setSize(370,350); // JTextArea의 크기 설정
        container.add(how);

        JLabel patchNoteLabel = new JLabel("Patch Note"); // patchNoteLabel 라벨 문자열과 함께 생성
        MainScreen.settingBoldLabel(patchNoteLabel,400,0,300,50,40);
        container.add(patchNoteLabel); // 컨텐트팬에 patchNoteLabel 라벨 부착

        String patchNoteText = """
                How to using the AimSimulation?
                How to using the AimSimulation?
                How to using the AimSimulation?
                """;
        JTextArea patchNoteTextArea = new JTextArea();
        patchNoteTextArea.setText(patchNoteText); // JTextArea의 텍스트 howText로 전달
        patchNoteTextArea.setEditable(false); // JTextArea 수정 불가능
        patchNoteTextArea.setLocation(400,40); // JTextArea의 위치 설정
        patchNoteTextArea.setSize(375,350); // JTextArea의 크기 설정
        container.add(patchNoteTextArea);

        JButton preButton = new JButton("Pre");
        MainScreen.settingButton(preButton,650,400,100,50,40);
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

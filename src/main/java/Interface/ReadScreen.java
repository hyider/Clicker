package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadScreen extends JFrame {
    public ReadScreen(){
        setTitle("Setting");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Color backGroundColor = new Color(86,187,241); // 배경색 설정
        Color buttonColor = new Color(77,119,255); // 버튼색 설정

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null);

        JLabel howLabel = new JLabel("How To Use"); // howLabel 라벨 문자열과 함께 생성
        howLabel.setFont(new Font("Slab Serif",Font.BOLD,40)); // 폰트 설정
        howLabel.setForeground(Color.WHITE); // howLabel 라벨의 글씨 하얀색 설정
        howLabel.setLocation(10,0); // customLabel 라벨의 위치
        howLabel.setSize(300,50); // howLabel 라벨의 크기
        container.add(howLabel); // 컨텐트팬에 howLabel 라벨 부착

        String howText = """
                How to using the AimSimulation?
                How to using the AimSimulation?
                How to using the AimSimulation?
                """;
        JTextArea how = new JTextArea();
        how.setText(howText);
        how.setEditable(false);
        how.setLocation(10,40);
        how.setSize(370,350);
        container.add(how);

        JLabel patchNoteLabel = new JLabel("Patch Note"); // patchNoteLabel 라벨 문자열과 함께 생성
        patchNoteLabel.setFont(new Font("Slab Serif",Font.BOLD,40)); // 폰트 설정
        patchNoteLabel.setForeground(Color.WHITE); // patchNoteLabel 라벨의 글씨 하얀색 설정
        patchNoteLabel.setLocation(400,0); // customLabel 라벨의 위치
        patchNoteLabel.setSize(300,50); // patchNoteLabel 라벨의 크기
        container.add(patchNoteLabel); // 컨텐트팬에 patchNoteLabel 라벨 부착

        String patchNoteText = """
                How to using the AimSimulation?
                How to using the AimSimulation?
                How to using the AimSimulation?
                """;
        JTextArea patchNoteTextArea = new JTextArea();
        patchNoteTextArea.setText(howText);
        patchNoteTextArea.setEditable(false);
        patchNoteTextArea.setLocation(400,40);
        patchNoteTextArea.setSize(375,350);
        container.add(patchNoteTextArea);

        JButton preButton = new JButton("Pre");
        preButton.setFont(new Font("Slab Serif",Font.BOLD,40)); // 폰트 설정
        preButton.setForeground(Color.WHITE); // preButton 버튼의 글씨색 설정
        preButton.setBackground(buttonColor); // preButton 버튼의 배경색 설정
        preButton.setLocation(650,400); // preButton 버튼의 위치 설정
        preButton.setSize(100,50); // preButton 버튼의 크기 설정
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

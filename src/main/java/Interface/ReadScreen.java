package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Interface.MainScreen.backGroundColor;

public class ReadScreen extends JFrame {
    public ReadScreen(){
        setTitle("Read");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container container = getContentPane(); // 프레임에 부탁된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨탠트팬의 색 backGroundColor 설정
        container.setLayout(null);

        JLabel howLabel = new JLabel("How To Use"); // howLabel 라벨 문자열과 함께 생성
        MainScreen.settingBoldLabel(howLabel,10,0,300,50,40);
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
        MainScreen.settingBoldLabel(patchNoteLabel,400,0,300,50,40);
        container.add(patchNoteLabel); // 컨텐트팬에 patchNoteLabel 라벨 부착

        String patchNoteText = """
                4.0
                                
                - 오류 : setLocationRelativeTo(null) -> 중앙에 배치되지 않음
                -> 해결 : setSize 메서드 밑에 두기.
                - ReadScreen 구현
                - SettingScreen Save버튼 기능 구현
                - MainScreen Quit버튼 기능 구현
                                
                - SettingScreen 구현안 (버튼2개로 모드변경)
                - Easy/Normal/Hard 없애는 방안
                                
                - Mode : Select 상태에서 값을 정하는 버그 -> Save 버튼 비활성화 및 JSlider 비활성화 하기'
                                
                - 난수생성기 구현
                                
                [버그]
                - 타겟 생성 범위 설정
                - Target 스레드 종료
                                
                [추가]
                - 타겟 근처 클릭 시 miss 뜨게 만들기
                - 타겟 클릭 시 hit 뜨게 만들기
                - 고스트모드
                                
                (JSlider)
                - 타겟 동시 출현 수량
                --- > 나중에 다시 생각
                - 타이머 시간 설정 , 단위 : 초
                                
                5.0
                                
                - timeSlider 등 Time 관하여 설정 할 수 있도록 추가
                - missCount 추가, 버튼이 아닌 곳을 추가하면 카운트됨.
                                
                [고민]
                - missCount 발생 시 targetButton 제거?
                                
                [추가]
                - missCount가 1이라도 발생하면 게임 오버되는 모드
                - missCount를 1이상의 숫자로 설정하게 하고 0이되면 게임 오버되는 모드
                - hitCount와 missCount를 합쳐 퍼센테이지 추가 및 등급이나 점수 생성
                                
                6.0
                                
                [버그]
                - 타겟 생성 1개 안되는 버그 -> 해결, 타이머 0 조건문 상단으로 이동
                - 커스텀 모드 슬라이더 설정 오류 (값 안옮겨짐) -> 해결, slider.getName() 과 slider.setName() 사용해서 수정
                - frequency slider 값 제대로 전달 안됨 -> 해결, 변수 하나 더 만들어서 연산 이전의 값을 MainScreen으로 옮김
                                
                [수정]
                - 메서드 중복된거 오버로딩으로 클래스 만들어보기 (예정) -> 간결화
                                
                [추가]
                - StartScreen에 일시정지 버튼이랑 정지 버튼 2개 추가?
                - SettingScreen 에 챌린지모드 버튼 구현?
                - StartScreen의 창을 전체로 할건지 그냥 창모드로 할건지 정하기?
                                
                7.0 -> 게임 실행 부분 (중간 발표)
                """;
        JTextArea patchNoteTextArea = new JTextArea();
        patchNoteTextArea.setText(patchNoteText); // JTextArea의 텍스트 howText로 전달
        patchNoteTextArea.setFont(new Font("Slab Serif",Font.BOLD,10));
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

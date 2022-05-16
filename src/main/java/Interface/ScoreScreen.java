package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreScreen extends JFrame {
    public static int hitCount, missCount, totalCount; // 카운트 변수2
    public static double percent; // 타겟을 맞춘 퍼센트 변수
    public static String percentToString; // 퍼센트 변수를 문자열로 변환하여 저장하는 변수

    public ScoreScreen() {
        setTitle("Score"); // 프로그램 타이틀 "Score"
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창을 닫으면 창이 사라짐

        Container container = getContentPane(); // 프레임에 부착된 컨텐트팬 알아내기
        container.setBackground(MainScreen.backGroundColor); // 컨텐트팬의 색 backGroundColor 설정
        container.setLayout(null); // 컨텐트팬의 배치관리자 없음

        // ===[Title_Label]==================================================================================
        JLabel scoreTitle = new JLabel("Score"); // scroreTitle 라벨 문자열과 함께 생성
        MainScreen.settingItalicLabel(scoreTitle,35,35,300,65,80);
        container.add(scoreTitle); // 컨텐트팬에 scoreTitle 라벨 부착

        // ===[Time_Label]==================================================================================
        JLabel timeLabel = new JLabel("Time"); // timeLabel 라벨 문자열과 함께 생성
        MainScreen.settingBoldLabel(timeLabel,5,110,200,50,40);
        container.add(timeLabel);

        // ====[TimeValue_Label]===============================================================================
        JLabel timeValueLabel = new JLabel(String.valueOf(MainScreen.time));
        MainScreen.settingBoldLabel(timeValueLabel,200,110,200,50,40);
        container.add(timeValueLabel);

        // ====[TotalCount_Label]================================================================================
        JLabel totalCountLabel = new JLabel("Total"); // 나타난 타겟을 카운트로 잡아서 변수로 넣어서 옮기기
        MainScreen.settingBoldLabel(totalCountLabel,5,160,200,50,40);
        container.add(totalCountLabel);

        // ====[TotalCountValue_Label]===========================================================================
        JLabel totalValueLabel = new JLabel(String.valueOf(totalCount));
        MainScreen.settingBoldLabel(totalValueLabel,200,160,200,50,40);
        container.add(totalValueLabel);

        JLabel hitCountLabel = new JLabel("Hit");
        MainScreen.settingBoldLabel(hitCountLabel,5,210,200,50,40);
        container.add(hitCountLabel);

        JLabel hitCountValueLabel = new JLabel(String.valueOf(hitCount));
        MainScreen.settingBoldLabel(hitCountValueLabel,200,210,200,50,40);
        container.add(hitCountValueLabel);

        JLabel missCountLabel = new JLabel("Miss");
        MainScreen.settingBoldLabel(missCountLabel,5,260,200,50,40);
        container.add(missCountLabel);

        JLabel missCountValueLabel = new JLabel(String.valueOf(missCount));
        MainScreen.settingBoldLabel(missCountValueLabel,200,260,200,50,40);
        container.add(missCountValueLabel);

        percentToString = String.format("%.2f",percent) + "%";
        JLabel resultLabel = new JLabel(percentToString);
        MainScreen.settingBoldLabel(resultLabel,70,340,250,50,60);
        container.add(resultLabel);

        JLabel gradeLabel = new JLabel();
        MainScreen.settingBoldLabel(gradeLabel,400,65,300,160,200);
        container.add(gradeLabel);

        switch ((int) (percent/5)) {
            case 20: gradeLabel.setText("S+"); gradeLabel.setForeground(Color.YELLOW); break;
            case 19: gradeLabel.setText("S"); gradeLabel.setForeground(Color.YELLOW); break;
            case 18: gradeLabel.setText("A+"); gradeLabel.setForeground(Color.RED); break;
            case 17: gradeLabel.setText("A"); gradeLabel.setForeground(Color.RED); break;
            case 16: gradeLabel.setText("B+"); gradeLabel.setForeground(Color.BLUE); break;
            case 15: gradeLabel.setText("B"); gradeLabel.setForeground(Color.BLUE); break;
            case 14: gradeLabel.setText("C+"); gradeLabel.setForeground(Color.GREEN); break;
            case 13: gradeLabel.setText("C"); gradeLabel.setForeground(Color.GREEN); break;
            case 12: gradeLabel.setText("D+"); gradeLabel.setForeground(Color.GRAY); break;
            case 11: gradeLabel.setText("D"); gradeLabel.setForeground(Color.GRAY); break;
            default: gradeLabel.setText("F"); gradeLabel.setForeground(Color.DARK_GRAY); break;
        }

        JButton mainButton = new JButton("Main");
        MainScreen.settingButton(mainButton,350,350,175,75,40);
        container.add(mainButton);
        clickEvent(mainButton,"Main");

        JButton replayButton = new JButton("Replay");
        MainScreen.settingButton(replayButton,570,350,175,75,40);
        container.add(replayButton);
        clickEvent(replayButton,"Replay");

        settingScreen(800,500,false,null,true);
    }

    public void settingScreen(int sizeX, int sizeY, boolean resizable, Component locationRelativeTo, boolean visible) {
        setSize(sizeX,sizeY);
        setResizable(resizable);
        setLocationRelativeTo(locationRelativeTo);
        setVisible(visible);
    }

    public void clickEvent(JButton buttonName, String className) {
        buttonName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (className) {
                    case "Main":
                        dispose();
                        break;
                    case "Replay":
                        dispose();
                        new StartScreen();
                        break;
                    default:
                        System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args) {
        new ScoreScreen();
    }
}

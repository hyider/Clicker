package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScoreScreen extends JFrame {
    public static int hitCount; // 타겟을 클릭한 횟수 변수
    public static int missCount; // 타겟 외의 곳을 클릭한 횟수 변수
    public static int totalCount; // 타겟이 나타난 전체 횟수 변수

    public static double percent; // 타겟을 맞춘 퍼센트 변수
    public static String percentToString; // 퍼센트 변수를 문자열로 변환하여 저장하는 변수
    public static String grade; // 퍼센트에 따른 등급 변수

    public ScoreScreen() {
        Color backGroundColor = new Color(86,187,241); // 배경색 설정
        Color buttonColor = new Color(77,119,255); // 버튼색 설정

        setTitle("Score"); // 프로그램 타이틀 "Score"
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창을 닫으면 창이 사라짐

        Container container = getContentPane(); // 프레임에 부착된 컨텐트팬 알아내기
        container.setBackground(backGroundColor); // 컨텐트팬의 색 backGroundColor 설정
        container.setLayout(null); // 컨텐트팬의 배치관리자 없음

        // 좌표값 알아내기////////////////////////////////////////
        JLabel la = new JLabel();
        container.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                la.setText("("+e.getX()+","+e.getY()+")");
                la.setLocation(e.getX(),e.getY());
                la.setSize(100,50);
                container.add(la);
                container.repaint();
            }
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        /////////////////////////////////////////////////////////

        // ===[ Title ]=====================================================================
        JLabel scoreTitle = new JLabel("Score"); // scroreTitle 라벨 문자열과 함께 생성
        scoreTitle.setFont(new Font("Slab Serif",Font.ITALIC,80)); // 폰트 설정
        scoreTitle.setForeground(Color.WHITE); // scoreTitle 라벨의 글씨 하얀색 설정
        scoreTitle.setLocation(35,35); // scoreTitle 라벨의 위치
        scoreTitle.setSize(300,65); // scoreTitle 라벨의 크기
        container.add(scoreTitle); // 컨텐트팬에 scoreTitle 라벨 부착

        // ===[ Time ]=====================================================================
        JLabel timeLabel = new JLabel("Time"); // timeLabel 라벨 문자열과 함께 생성
        MainScreen.settingItalicLabel(timeLabel,5,110,200,50,40);
        /*
        timeLabel.setForeground(Color.WHITE); // timeLabel 라벨의 글씨 하얀색 설정
        timeLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        timeLabel.setLocation(5, 110);
        timeLabel.setSize(200, 50);
        container.add(timeLabel);
         */

        JLabel timeValueLabel = new JLabel(String.valueOf(MainScreen.time));
        timeValueLabel.setForeground(Color.WHITE);
        timeValueLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        timeValueLabel.setLocation(200, 110);
        timeValueLabel.setSize(200, 50);
        container.add(timeValueLabel);


        JLabel totalCountLabel = new JLabel("Total"); // 나타난 타겟을 카운트로 잡아서 변수로 넣어서 옮기기
        totalCountLabel.setForeground(Color.WHITE);
        totalCountLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        totalCountLabel.setLocation(5, 160);
        totalCountLabel.setSize(200, 50);
        container.add(totalCountLabel);

        JLabel totalValueLabel = new JLabel(String.valueOf(totalCount));
        totalValueLabel.setForeground(Color.WHITE);
        totalValueLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        totalValueLabel.setLocation(200, 160);
        totalValueLabel.setSize(200, 50);
        container.add(totalValueLabel);

        JLabel hitCountLabel = new JLabel("Hit");
        hitCountLabel.setForeground(Color.WHITE);
        hitCountLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        hitCountLabel.setLocation(5, 210);
        hitCountLabel.setSize(200, 50);
        container.add(hitCountLabel);

        JLabel hitCountValueLabel = new JLabel(String.valueOf(hitCount));
        hitCountValueLabel.setForeground(Color.WHITE);
        hitCountValueLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        hitCountValueLabel.setLocation(200, 210);
        hitCountValueLabel.setSize(200, 50);
        container.add(hitCountValueLabel);

        JLabel missCountLabel = new JLabel("Miss");
        missCountLabel.setForeground(Color.WHITE);
        missCountLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        missCountLabel.setLocation(5, 260);
        missCountLabel.setSize(200, 50);
        container.add(missCountLabel);

        JLabel missCountValueLabel = new JLabel(String.valueOf(missCount));
        missCountValueLabel.setForeground(Color.WHITE);
        missCountValueLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        missCountValueLabel.setLocation(200, 260);
        missCountValueLabel.setSize(200, 50);
        container.add(missCountValueLabel);


        percentToString = String.format("%.2f",percent) + "%";
        JLabel resultLabel = new JLabel(percentToString);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Slab Serif", Font.BOLD, 60));
        resultLabel.setLocation(70, 340);
        resultLabel.setSize(225, 50);
        container.add(resultLabel);
        /**
        if (percent >= 95) {
            grade = "S+";
        } else if (percent >= 90) {
            grade = "S";
        } else if (percent >= 85) {
            grade = "A+";
        } else if (percent >= 80) {
            grade
        }
         **/
        switch ((int) (percent/5)) {
            case 20:
            case 19: grade = "S+"; break;
            case 18: grade = "S"; break;
            case 17: grade = "A+"; break;
            case 16: grade = "A"; break;
            case 15: grade = "B+"; break;
            case 14: grade = "B"; break;
            case 13: grade = "C+"; break;
            case 12: grade = "C"; break;
            case 11: grade = "D+"; break;
            default: grade = "F"; break;
        }

        JLabel gradeLabel = new JLabel(grade);
        gradeLabel.setForeground(Color.YELLOW);
        gradeLabel.setFont(new Font("Slab Serif",Font.BOLD, 200));
        gradeLabel.setLocation(400,65);
        gradeLabel.setSize(300,160);
        container.add(gradeLabel);

        /** 포기 안한다. 현수 몰래 넣을 것.
        JLabel saying = new JLabel("There is no substitute for hard work. - Thomas Edison");
        saying.setForeground(Color.WHITE);
        saying.setFont(new Font("Slab Serif",Font.PLAIN,20));
        saying.setLocation(275,290);
        saying.setSize(500,25);
        container.add(saying);
        **/

        JButton mainButton = new JButton("Main");
        mainButton.setBackground(buttonColor);
        mainButton.setForeground(Color.WHITE);
        mainButton.setFont(new Font("Slab Serif",Font.BOLD,40));
        mainButton.setLocation(350,350);
        mainButton.setSize(175,75);
        container.add(mainButton);

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton replayButton = new JButton("Replay");
        replayButton.setBackground(buttonColor);
        replayButton.setForeground(Color.WHITE);
        replayButton.setFont(new Font("Slab Serif",Font.BOLD,40));
        replayButton.setLocation(570,350);
        replayButton.setSize(175,75);
        container.add(replayButton);

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StartScreen();
            }
        });

        setSize(800,500); // 창의 크기 설정
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true); // 화면에 창을 나타냄

    }

    public static void main(String[] args) {
        new ScoreScreen();
    }
}

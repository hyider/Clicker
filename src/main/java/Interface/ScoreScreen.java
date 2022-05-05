package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScoreScreen extends JFrame {
    public static int hitCount;
    public static int missCount;
    public static int totalCount;

    public static double percent;
    public static String percentToString;
    public static String percentResult;

    public ScoreScreen() {

        Color backGroundColor = new Color(86,187,241); // 배경색 설정
        Color buttonColor = new Color(77,119,255); // 버튼색 설정

        setTitle("Score");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container container = getContentPane();

        container.setBackground(backGroundColor);

        container.setLayout(null);

        // 좌표값 알아내기
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

        JLabel scoreTitle = new JLabel("Score");
        scoreTitle.setFont(new Font("Slab Serif",Font.ITALIC,80));
        scoreTitle.setForeground(Color.WHITE);
        scoreTitle.setLocation(35,35);
        scoreTitle.setSize(300,65);
        container.add(scoreTitle);

        JLabel timeLabel = new JLabel("Time");
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        timeLabel.setLocation(5, 110);
        timeLabel.setSize(200, 50);
        container.add(timeLabel);

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

        JLabel grade = new JLabel("S+");
        grade.setForeground(Color.YELLOW);
        grade.setFont(new Font("Slab Serif",Font.BOLD, 200));
        grade.setLocation(400,65);
        grade.setSize(300,160);
        container.add(grade);

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

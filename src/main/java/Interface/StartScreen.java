package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartScreen extends JFrame {
    private MyPanel panel = new MyPanel();

    public static int hitCount;

    public static int targetX;
    public static int targetY;

    public static JLabel hitCountLabel;
    public static JLabel timeCountLabel;

    public static int timeCount = 10;

    public StartScreen() {
        setTitle("Start");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel);
        panel.setLayout(null);

        Color backGroundColor = new Color(86,187,241); // 배경색 설정
        Color buttonColor = new Color(77,119,255); // 버튼색 설정

        panel.setBackground(backGroundColor);

        hitCountLabel = new JLabel();
        hitCountLabel.setText("Score");
        hitCountLabel.setForeground(Color.WHITE);
        hitCountLabel.setFont(new Font("Slab Serif",Font.BOLD,40));
        hitCountLabel.setLocation(0,10);
        hitCountLabel.setSize(200,100);
        panel.add(hitCountLabel);

        timeCountLabel = new JLabel();
        timeCountLabel.setText("Time : " + timeCount);
        timeCountLabel.setForeground(Color.WHITE);
        timeCountLabel.setFont(new Font("Slab Serif",Font.BOLD,40));
        timeCountLabel.setLocation(210,10);
        timeCountLabel.setSize(200,100);
        panel.add(timeCountLabel);

        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (x > 50 && x < 100 && y > 50 && y < 100) {
                    hitCount++;
                    hitCountLabel.setText("Score : " + hitCount);
                    System.out.println(hitCount);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        panel.addMouseListener(mouseListener);

        setSize(1000,1000);
        // ***************************************************************************
        setResizable(false); // 창의 크기 고정
        setLocationRelativeTo(null); // 창이 가운데에 나타남
        // ***************************************************************************
        setVisible(true);

        Target target = new Target(panel);
        target.start();
        Timer timer = new Timer(timeCountLabel);
        timer.start();
    }

    class MyPanel extends JPanel {
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            graphics.setColor(Color.RED);
            graphics.fillRect(targetX, targetY, 50, 50);
        }
    }

    class Timer extends  Thread {
        private JLabel timeCountLabel;
        public Timer(JLabel timeCountLabel){
            this.timeCountLabel = timeCountLabel;
        }
        public void run() {
            while(true) {
                timeCount--;
                timeCountLabel.setText("Time : " + timeCount);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                if (timeCount == 0) {
                    dispose();
                    timeCount=10;
                    return;
                }
            }
        }
    }

    class Target extends Thread {
        private MyPanel panel;
        private Graphics graphics;

        public Target(MyPanel panel) {
            this.panel = panel;
        }

        public void run() {
            while (true) {
                targetX = ((int) (Math.random() * 1000));
                targetY = ((int) (Math.random() * 1000));
                if (timeCount == 0) {
                    return;
                }
                new MyPanel();
                panel.repaint();
                System.out.println(targetX);
                System.out.println(targetY);

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


/**
        Target target = new Target(panel);
        target.run();
    }
    class Target extends Thread {

        private Graphics graphics;
        private Container contentPane;

        public Target(Container contentpane) {
            this.contentPane = contentpane;
        }

        public void paintComponent(Graphics graphics) {
            this.graphics = graphics;
            graphics.setColor(Color.RED);
            graphics.fillRect(targetX, targetY, 50, 50);
        }
        @Override
        public void run() {
            targetX = ((int)(Math.random()*1000));
            targetY = ((int)(Math.random()*1000));
            paintComponent(graphics);
            System.out.print("0");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class MyPanel extends JPanel {
        /**
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            graphics.setColor(Color.RED);
            graphics.fillRect(targetX, targetY, 50, 50);

        }

    }

    public static void main(String[] args) {
        new StartScreen();
    }
}
**/
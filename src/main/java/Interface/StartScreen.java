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

    public StartScreen() {
        setTitle("Start");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel);
        panel.setLayout(null);

        hitCountLabel = new JLabel();
        hitCountLabel.setText("Score");
        hitCountLabel.setLocation(0,0);
        hitCountLabel.setSize(100,50);
        panel.add(hitCountLabel);

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
        target.run();
    }

    class MyPanel extends JPanel {
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            graphics.setColor(Color.RED);
            graphics.fillRect(targetX, targetY, 50, 50);
        }
    }

    class Target extends  Thread {
        private MyPanel panel;
        private Graphics graphics;

        public Target(MyPanel panel) {
            this.panel = panel;
        }

        public void run() {
            while (true) {
                targetX = ((int) (Math.random() * 1000));
                targetY = ((int) (Math.random() * 1000));
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
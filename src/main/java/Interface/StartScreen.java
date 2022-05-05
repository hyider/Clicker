package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartScreen extends JFrame {
    private MyPanel panel = new MyPanel();

    public static int hitCount = 0;

    public static int targetX;
    public static int targetY;

    public static JButton targetButton;

    public static int selectMoveToCrossOrDiagonal;
    public static int selectMoveX;
    public static int selectMoveY;


    public static JLabel hitCountLabel;
    public static JLabel timeCountLabel;

    public static int timeCount = 3;

    public StartScreen() {
        setTitle("Start");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel);
        panel.setLayout(null);

        Color backGroundColor = new Color(86, 187, 241); // 배경색 설정
        Color buttonColor = new Color(77, 119, 255); // 버튼색 설정

        panel.setBackground(backGroundColor);

        hitCountLabel = new JLabel();
        hitCountLabel.setText("Score : " + hitCount);
        hitCountLabel.setForeground(Color.WHITE);
        hitCountLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        hitCountLabel.setLocation(5, 0);
        hitCountLabel.setSize(200, 50);
        panel.add(hitCountLabel);

        timeCountLabel = new JLabel();
        timeCountLabel.setText("Time : " + timeCount);
        timeCountLabel.setForeground(Color.WHITE);
        timeCountLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        timeCountLabel.setLocation(215, 0);
        timeCountLabel.setSize(200, 50);
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

        setSize(800, 800);
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
            //graphics.setColor(Color.RED);
            //graphics.fillRect(targetX, targetY, 50, 50);
        }
    }

    class Timer extends Thread {
        private JLabel timeCountLabel;

        public Timer(JLabel timeCountLabel) {
            this.timeCountLabel = timeCountLabel;
        }


        public void run() {
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
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
                    timeCount = 3;
                    MainScreen.hitCount = hitCount;
                    hitCount = 0;
                    return;
                }
            }
        }
    }

    class RandomMove extends Thread {
        public void run() {
            /**
            selectMoveToCrossOrDiagonal = ((int)(Math.random()*10));
            selectMoveToCrossOrDiagonal = selectMoveToCrossOrDiagonal%2;
            // 0이면 십자, 1이면 대각
            switch (selectMoveToCrossOrDiagonal) {
                case 0:
                    selectMoveX = ((int)(Math.random()*10));
                    selectMoveX = selectMoveX%2;
                    switch (selectMoveX) {
                        case 0:
                            selectMoveY = ((int)(Math.random()*10));
                            selectMoveY = selectMoveY%2;
                            break;
                        case 1:
                            selectMoveY = ((int)(Math.random()*10));
                            selectMoveY = selectMoveY%2;
                            break;
                    }
                    break;
                case 1:
                    selectMoveX = ((int)(Math.random()*10));
                    selectMoveX = selectMoveX%2;
                    switch (selectMoveX) {
                        case 0:
                            selectMoveY = ((int)(Math.random()*10));
                            selectMoveY = selectMoveY%2;
                            break;
                        case 1:
                            selectMoveY = ((int)(Math.random()*10));
                            selectMoveY = selectMoveY%2;
                            break;
                    }
                    break;
            }
            **/

            selectMoveToCrossOrDiagonal = ((int)(Math.random()*10))%2;
            selectMoveX = ((int)(Math.random()*10))%2;
            selectMoveY = ((int)(Math.random()*10))%2;

            while(true) {
                switch (selectMoveToCrossOrDiagonal) {
                    case 0:
                        // X가 0이면 Y축, 1이면 X축
                        // Y가 0이면 양수, 1이면 음수
                        if ( selectMoveX == 0 && selectMoveY == 0) {
                            targetButton.setLocation(targetX,targetY++);
                            panel.repaint();
                        } else if ( selectMoveX == 0 && selectMoveY == 1) {
                            targetButton.setLocation(targetX,targetY--);
                            panel.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 0) {
                            targetButton.setLocation(targetX--,targetY);
                            panel.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 1) {
                            targetButton.setLocation(targetX--,targetY);
                            panel.repaint();
                        }
                    case 1:
                        // 0일 때는 양수 [우상], 1일 때는 음수 [좌하]
                        if ( selectMoveX == 0 && selectMoveY == 0) {
                            targetButton.setLocation(targetX++,targetY++);
                            panel.repaint();
                        } else if ( selectMoveX == 0 && selectMoveY == 1) {
                            targetButton.setLocation(targetX++,targetY--);
                            panel.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 0) {
                            targetButton.setLocation(targetX--,targetY++);
                            panel.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 1) {
                            targetButton.setLocation(targetX--,targetY--);
                            panel.repaint();
                        }
                }

                targetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        hitCount++;
                        hitCountLabel.setText("Score : "+hitCount);
                        panel.remove(targetButton);
                        return;
                    }
                });
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Target extends Thread {
        private MyPanel panel;
        private RandomMove randomMove = new RandomMove();

        public Target(MyPanel panel) {
            this.panel = panel;
        }

        public static JLabel readyLabel;

        public void run() {
            String readyCount = null;

            // 게임 시작 카운트다운 스레드로 만들 수 있으면 만들기
            for (int i = 3; i > 0; i--) {
                switch (i) {
                    case 1:
                        readyCount = " One";
                        break;
                    case 2:
                        readyCount = " Two";
                        break;
                    case 3:
                        readyCount = "Three";
                        break;
                }
                readyLabel = new JLabel(readyCount);
                readyLabel.setFont(new Font("Slab Serif", Font.BOLD, 50));
                readyLabel.setSize(200, 50);
                readyLabel.setLocation(300, 400);
                readyLabel.setForeground(Color.WHITE);
                panel.add(readyLabel);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                panel.remove(readyLabel);
                panel.repaint();
            }
            while (true) {
                targetX = ((int) (Math.random() * 700));
                targetY = ((int) (Math.random() * 700));
                targetButton = new JButton();
                targetButton.setSize((MainScreen.size + 10) * 5, (MainScreen.size + 10) * 5);
                targetButton.setLocation(targetX, targetY);
                panel.add(targetButton);

                randomMove.start();



                if (timeCount == 0) {
                    return;
                }
                //new MyPanel();

                try {
                    sleep(1000 - (11 - MainScreen.frequency) * 50);
                    panel.remove(targetButton);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


/**
 * Target target = new Target(panel);
 * target.run();
 * }
 * class Target extends Thread {
 * <p>
 * private Graphics graphics;
 * private Container contentPane;
 * <p>
 * public Target(Container contentpane) {
 * this.contentPane = contentpane;
 * }
 * <p>
 * public void paintComponent(Graphics graphics) {
 * this.graphics = graphics;
 * graphics.setColor(Color.RED);
 * graphics.fillRect(targetX, targetY, 50, 50);
 * }
 *
 * @Override public void run() {
 * targetX = ((int)(Math.random()*1000));
 * targetY = ((int)(Math.random()*1000));
 * paintComponent(graphics);
 * System.out.print("0");
 * try {
 * sleep(1000);
 * } catch (InterruptedException e) {
 * e.printStackTrace();
 * }
 * }
 * }
 * <p>
 * class MyPanel extends JPanel {
 * /**
 * public void paintComponent(Graphics graphics) {
 * super.paintComponent(graphics);
 * <p>
 * graphics.setColor(Color.RED);
 * graphics.fillRect(targetX, targetY, 50, 50);
 * <p>
 * }
 * <p>
 * }
 * <p>
 * public static void main(String[] args) {
 * new StartScreen();
 * }
 * }
 **/
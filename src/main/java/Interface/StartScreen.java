package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartScreen extends JFrame {
    public static int hitCount = 0;
    public static int missCount = 0;
    public static int totalCount = 0;
    public static int timeCount = MainScreen.time;

    public static int targetX;
    public static int targetY;

    public static int newThread;

    public static JButton targetButton;

    public static int selectMoveToCrossOrDiagonal;
    public static int selectMoveX;
    public static int selectMoveY;

    public static JLabel hitCountLabel;
    public static JLabel missCOuntLabel;
    public static JLabel timeCountLabel;

    public StartScreen() {
        setTitle("Start");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(null);

        Color backGroundColor = new Color(86, 187, 241); // 배경색 설정
        Color buttonColor = new Color(77, 119, 255); // 버튼색 설정

        container.setBackground(backGroundColor);

        hitCountLabel = new JLabel();
        hitCountLabel.setText("Score : " + hitCount);
        hitCountLabel.setForeground(Color.WHITE);
        hitCountLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        hitCountLabel.setLocation(5, 0);
        hitCountLabel.setSize(200, 50);
        container.add(hitCountLabel);

        missCOuntLabel = new JLabel();
        missCOuntLabel.setText("Miss : " + missCount);
        missCOuntLabel.setForeground(Color.WHITE);
        missCOuntLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        missCOuntLabel.setLocation(600,0);
        missCOuntLabel.setSize(200,50);
        container.add(missCOuntLabel);

        timeCountLabel = new JLabel();
        timeCountLabel.setText("Time : " + timeCount);
        timeCountLabel.setForeground(Color.WHITE);
        timeCountLabel.setFont(new Font("Slab Serif", Font.BOLD, 40));
        timeCountLabel.setLocation(215, 0);
        timeCountLabel.setSize(200, 50);
        container.add(timeCountLabel);

        setSize(800, 800);
        // ***************************************************************************
        setResizable(false); // 창의 크기 고정
        setLocationRelativeTo(null); // 창이 가운데에 나타남
        // ***************************************************************************
        setVisible(true);

        Target target = new Target(container);
        target.start();
        Timer timer = new Timer(timeCountLabel);
        timer.start();
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
                    new ScoreScreen();
                    timeCount = MainScreen.time;
                    hitCount = 0;
                    missCount = 0;
                    totalCount = 0;
                    return;
                }
            }
        }
    }

    class MoveTargetTest extends Thread {
        private Container container;
        public MoveTargetTest(Container container) {
            this.container = container;
        }
        @Override
        public void run() {
            while(true) {
                targetButton.setLocation(targetX + 1, targetY + 1);
                container.repaint();
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class RandomMove extends Thread {
        private Container container;
        RandomMove(Container container) {
            this.container = container;
        }
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

            // 변수 이름 재설정 selectMoveX , selectMoveY
            while(true) {
                switch (selectMoveToCrossOrDiagonal) {
                    case 0:
                        // X가 0이면 Y축, 1이면 X축
                        // Y가 0이면 양수, 1이면 음수
                        if ( selectMoveX == 0 && selectMoveY == 0) {
                            targetButton.setLocation(targetX,targetY++);
                            container.repaint();
                        } else if ( selectMoveX == 0 && selectMoveY == 1) {
                            targetButton.setLocation(targetX,targetY--);
                            container.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 0) {
                            targetButton.setLocation(targetX--,targetY);
                            container.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 1) {
                            targetButton.setLocation(targetX--,targetY);
                            container.repaint();
                        }
                    case 1:
                        // 0일 때는 양수 [우상], 1일 때는 음수 [좌하]
                        if ( selectMoveX == 0 && selectMoveY == 0) {
                            targetButton.setLocation(targetX++,targetY++);
                            container.repaint();
                        } else if ( selectMoveX == 0 && selectMoveY == 1) {
                            targetButton.setLocation(targetX++,targetY--);
                            container.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 0) {
                            targetButton.setLocation(targetX--,targetY++);
                            container.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 1) {
                            targetButton.setLocation(targetX--,targetY--);
                            container.repaint();
                        }
                }
                targetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        hitCount++;
                        hitCountLabel.setText("Score : "+hitCount);
                        container.remove(targetButton);
                        return;
                    }
                });
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                newThread = newThread + 50;
                if (newThread == 1000 - (11 - MainScreen.frequency) * 50) {
                    newThread = 0;
                    return;
                }
            }
        }
    }

    class Target extends Thread {
        private Container container;
        private RandomMove randomMove = new RandomMove(container);

        public Target(Container container) {
            this.container = container;
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
                container.add(readyLabel);
                container.repaint();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                container.remove(readyLabel);
                container.repaint();
            }

            MoveTargetTest moveTargetTest = new MoveTargetTest(container);

            container.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    missCount++;
                    missCOuntLabel.setText("Miss : " + missCount);
                    container.repaint();
                }
            });

            while (true) {
                if (timeCount == 0) {
                    ScoreScreen.percent = ((double)hitCount/(double)totalCount)*100;
                    ScoreScreen.totalCount = totalCount;
                    ScoreScreen.hitCount = hitCount;
                    ScoreScreen.missCount = missCount;
                    return;
                }

                targetX = ((int) (Math.random() * 700));
                targetY = ((int) (Math.random() * 700));

                ImageIcon imageIcon = new ImageIcon("src/main/resources/target.png");
                Image image = imageIcon.getImage();
                Image image1 = image.getScaledInstance((MainScreen.size+10)*5,(MainScreen.size+10)*5,Image.SCALE_SMOOTH);
                ImageIcon imageIcon1 = new ImageIcon(image1);

                targetButton = new JButton(imageIcon1);
                targetButton.setSize((MainScreen.size + 10) * 5, (MainScreen.size + 10) * 5);
                targetButton.setBorderPainted(false);
                targetButton.setContentAreaFilled(false);
                targetButton.setLocation(targetX, targetY);
                container.add(targetButton);

                moveTargetTest.start();

                targetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        hitCount++;
                        hitCountLabel.setText("Score : "+hitCount);
                        container.remove(targetButton);
                        container.repaint();
                    }
                });

                totalCount++;

                container.repaint();

                try {
                    sleep(1000 - (11 - MainScreen.frequency) * 50);
                    container.remove(targetButton);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /**
                 JButton backgroundButton = new JButton();
                 backgroundButton.setBackground(backGroundColor);
                 backgroundButton.setForeground(backGroundColor);
                 backgroundButton.setLocation(0,50);
                 backgroundButton.setSize(800,750);
                 panel.add(backgroundButton);
                 backgroundButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                missCount++;
                missCOuntLabel.setText("Miss : "+missCount);
                }
                });

                 targetButton.requestFocus();

                 backgroundButton.setVisible(false);
                 **/
                /**
                 panel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1) {
                missCount++;
                missCOuntLabel.setText("Miss : "+missCount);
                }
                }
                public void mousePressed(MouseEvent e) {}
                public void mouseReleased(MouseEvent e) {

                }
                public void mouseEntered(MouseEvent e) {}
                public void mouseExited(MouseEvent e) {}
                });
                 **/

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
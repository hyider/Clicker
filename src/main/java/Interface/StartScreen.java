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

    public static JButton targetButton;

    public static JLabel hitCountLabel;
    public static JLabel missCOuntLabel;
    public static JLabel timeCountLabel;

    public StartScreen() {
        setTitle("Start");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(null);

        container.setBackground(MainScreen.backGroundColor);

        hitCountLabel = new JLabel("Score : " + hitCount);
        MainScreen.settingBoldLabel(hitCountLabel,5,0,200,50,40);
        container.add(hitCountLabel);

        missCOuntLabel = new JLabel("Miss : " + missCount);
        MainScreen.settingBoldLabel(missCOuntLabel,600,0,200,50,40);
        container.add(missCOuntLabel);

        timeCountLabel = new JLabel("Time : " + timeCount);
        MainScreen.settingBoldLabel(timeCountLabel,215,0,200,50,40);
        container.add(timeCountLabel);

        settingScreen(800,800,false,null,true);

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

    class RandomMove extends Thread {
        private Container container;
        private boolean moveStopThread;
        int selectMoveToCrossOrDiagonal;
        int selectMoveX, selectMoveY;

        RandomMove(Container container) {
            this.container = container;
        }

        public void setMoveStopThread(boolean moveStopThread) {
            this.moveStopThread = moveStopThread;
        }

        public void run() {
            selectMoveToCrossOrDiagonal = ((int)(Math.random()*10))%2;
            selectMoveX = ((int)(Math.random()*10))%2;
            selectMoveY = ((int)(Math.random()*10))%2;
            // 변수 이름 재설정 selectMoveX , selectMoveY
            while(true) {
                if (moveStopThread == true) {
                    moveStopThread = false;
                    return;
                }
                switch (selectMoveToCrossOrDiagonal) {
                    case 0:
                        // X가 0이면 Y축, 1이면 X축
                        // Y가 0이면 양수, 1이면 음수
                        if ( selectMoveX == 0 && selectMoveY == 0) {
                            targetButton.setLocation(targetX,targetY++);
                            container.repaint();
                            System.out.print("1 ");
                        } else if ( selectMoveX == 0 && selectMoveY == 1) {
                            targetButton.setLocation(targetX,targetY--);
                            container.repaint();
                            System.out.print("2 ");
                        } else if ( selectMoveX == 1 && selectMoveY == 0) {
                            targetButton.setLocation(targetX++,targetY);
                            container.repaint();
                            System.out.print("3 ");
                        } else if ( selectMoveX == 1 && selectMoveY == 1) {
                            targetButton.setLocation(targetX--,targetY);
                            container.repaint();
                            System.out.print("4 ");
                        } break;
                    case 1:
                        // 0일 때는 양수 [우상], 1일 때는 음수 [좌하]
                        if ( selectMoveX == 0 && selectMoveY == 0) {
                            targetButton.setLocation(targetX++,targetY++);
                            container.repaint();
                            System.out.print("5 ");
                        } else if ( selectMoveX == 0 && selectMoveY == 1) {
                            targetButton.setLocation(targetX++,targetY--);
                            container.repaint();
                            System.out.print("6 ");
                        } else if ( selectMoveX == 1 && selectMoveY == 0) {
                            targetButton.setLocation(targetX--,targetY++);
                            container.repaint();
                            System.out.print("7 ");
                        } else if ( selectMoveX == 1 && selectMoveY == 1) {
                            targetButton.setLocation(targetX--,targetY--);
                            container.repaint();
                            System.out.print("8 ");
                        } break;
                }
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Target extends Thread {
        private Container container;
        private RandomMove randomMove;

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
                        readyCount = " One"; break;
                    case 2:
                        readyCount = " Two"; break;
                    case 3:
                        readyCount = "Three"; break;
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
                    //RandomMove.moveStopThread = true;
                    randomMove.setMoveStopThread(true);
                    ScoreScreen.percent = ((double)hitCount/(double)totalCount)*100;
                    ScoreScreen.totalCount = totalCount;
                    ScoreScreen.hitCount = hitCount;
                    ScoreScreen.missCount = missCount;
                    return;
                }

                targetX = ((int) (Math.random() * 700));
                targetY = ((int) (Math.random() * 615)+50);
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

                randomMove = new RandomMove(container);
                randomMove.start();

                targetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //RandomMove.moveStopThread = true;
                        randomMove.setMoveStopThread(true);
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
                    //RandomMove.moveStopThread = true;
                    randomMove.setMoveStopThread(true);
                    container.remove(targetButton);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void settingScreen(int sizeX, int sizeY, boolean resizable, Component locationRelativeTo, boolean visible) {
        setSize(sizeX,sizeY);
        setResizable(resizable);
        setLocationRelativeTo(locationRelativeTo);
        setVisible(visible);
    }
}
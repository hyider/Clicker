package Interface;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

import static Interface.Controller.*;
import static Interface.MainScreen.*;

public class StartScreen extends JFrame {
    public static int hitCount = 0;
    public static int missCount = 0;
    public static int totalCount = 0;
    public static int timeCount = Controller.time;
    public static int imageCount = 1;

    public static boolean stopGame = false;
    public static boolean gameOver = false;
    public static boolean dropItem = true;

    public static int defense = Controller.defense;

    public static int targetX, targetY;
    public static int setTargetX, setTargetY;
    public static int itemTargetX = 0, itemTargetY;

    public static JButton targetButton;
    public static JButton itemTargetButton;
    public static JButton stop;

    public static JLabel hitCountLabel;
    public static JLabel missCountLabel;
    public static JLabel timeCountLabel;

    public static ArrayList<ImageIcon> aShieldTargetImage;
    public static ArrayList<Image> aShieldTargetScale;
    public static ArrayList<ImageIcon> aShieldTarget;
    public static ArrayList<JButton> aShieldTargetButton;

    public StartScreen() {
        setTitle("Start");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);

        Container container = getContentPane();
        container.setLayout(null);
        container.setBackground(backGroundColor);

        start.start();

        stop = new JButton("X");
        settingButton(stop,735,0,50,50,20);
        container.add(stop);
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopGame = true;
            }
        });

        // 타겟 클릭 횟수 라벨
        hitCountLabel = new JLabel("Score : " + hitCount);
        settingBoldLabel(hitCountLabel,5,0,200,50,40);
        container.add(hitCountLabel);

        // 타겟 외 클릭 횟수 라벨
        missCountLabel = new JLabel("Miss : " + missCount);
        settingBoldLabel(missCountLabel,400,0,200,50,40);
        container.add(missCountLabel);

        // 시간 라벨
        timeCount = time;
        timeCountLabel = new JLabel("Time : " + timeCount);
        settingBoldLabel(timeCountLabel,215,0,200,50,40);
        container.add(timeCountLabel);

        aShieldTargetImage = new ArrayList<>();
        aShieldTargetScale = new ArrayList<>();
        aShieldTarget = new ArrayList<>();
        aShieldTargetButton = new ArrayList<>();

        while(imageCount < 11) {
            aShieldTargetImage.add(new ImageIcon("src/main/resources/TargetNumber" + imageCount + ".png"));
            aShieldTargetScale.add(((aShieldTargetImage.get(imageCount-1)).getImage()).getScaledInstance((Controller.size+10)*5,(Controller.size+10)*5,Image.SCALE_SMOOTH));
            aShieldTarget.add(new ImageIcon(aShieldTargetScale.get(imageCount-1)));
            aShieldTargetButton.add(new JButton(aShieldTarget.get(imageCount-1)));
            imageCount++;
        }
        imageCount = 1;

        // 화면 구성
        settingScreen(800,800,false,null,true);
        if(itemModeSelected) setSize(1000,800);


        // 타이머 쓰레드 객체 생성 및 쓰레드 실행
        Timer timer = new Timer(timeCountLabel);
        timer.start();

        DefaultModeThread defaultModeThread = new DefaultModeThread();
        defaultModeThread.start();

        MainThread mainThread = new MainThread(timer, defaultModeThread, container, aShieldTargetButton, hit, miss);
        mainThread.start();

    }

    // 타이머 쓰레드
    class Timer extends Thread {
        private JLabel timeCountLabel;
        private boolean timerStop;


        // 타이머 쓰레드 클래스 생성자 - 외부에서 시간 라벨을 가져옴
        public Timer(JLabel timeCountLabel) {
            this.timeCountLabel = timeCountLabel;
        }

        public void setTimerStop(boolean timerStop) {
            this.timerStop = timerStop;
        }

        // 쓰레드 실행 부분
        public void run() {
            /**
             * 처음 시작 카운트 약 3초 대기
             */
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                /**
                 * 약 1초당 1씩 줄여가며 타임카운트 라벨 수정
                 * 타이머 카운트가 0이 되면 타이머 쓰레드 종료
                 */
                timeCount--; // 외부에서 설정된 타임카운트의 수 1줄임
                if (timeCount < 0) timeCountLabel.setText("Time : 0"); // 줄인 숫자로 타임카운트라벨 수정
                else timeCountLabel.setText("Time : " + timeCount); // 줄인 숫자로 타임카운트라벨 수정

                try {
                    sleep(1000); // 약 1초 동안 대기

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 쓰레드 종료 여부 결정
                if (timerStop) {
                    timerStop = false;
                    return;
                }
            }
        }
    }

    class MainThread extends Thread {
        private Timer timer;
        private DefaultModeThread defaultModeThread;
        private MovingModeThread movingModeThread;
        private ShieldModeThread shieldModeThread;
        private ItemModeThread itemModeThread;

        private Container container;
        private JLabel readyLabel;
        private ArrayList<JButton> aShieldTargetButton;

        private Clip hit, miss;

        public MainThread(Timer timer, DefaultModeThread defaultModeThread, Container container,
                          ArrayList<JButton> aShieldTargetButton,
                          Clip hit, Clip miss) {
            this.timer = timer;
            this.defaultModeThread = defaultModeThread;
            this.container = container;
            this.aShieldTargetButton = aShieldTargetButton;
            this.hit = hit;
            this.miss = miss;
        }

        public boolean checkSameLocation(int defaultLocationX, int defaultLocationY, int itemLocationX, int itemLocationY) {
            if(itemLocationX <= defaultLocationX && defaultLocationX <= itemLocationX + (Controller.size+10)*5)
                return true;
            if(itemLocationY <= defaultLocationY && defaultLocationY <= itemLocationY + (Controller.size+10)*5)
                return true;
            return false;
        }

        public void setLocationTarget() {
            setTargetX = targetX;
            setTargetY = targetY;

            if(itemTargetX == 0) return;

            if(itemModeSelected) {
                if(checkSameLocation(setTargetX,setTargetY,itemTargetX,itemTargetY)) {
                    setLocationTarget();
                    return;
                }
            }
        }

        @Override
        public void run() {
            /**
             * 게임 시작 카운트다운 구현 영역
             */
            String readyCount = null;

            for (int i = 3; i > 0; i--) {
                switch (i) {
                    case 1: readyCount = " One"; break;
                    case 2: readyCount = " Two"; break;
                    case 3: readyCount = "Three"; break;
                }
                // 레디 카운트 라벨 생성
                stop.setEnabled(false);
                container.repaint();
                readyLabel = new JLabel(readyCount);
                settingBoldLabel(readyLabel,300,400,200,50,50);
                container.add(readyLabel);
                container.repaint();
                try {
                    sleep(1000); // 약 1초 대기
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                container.remove(readyLabel);
                container.repaint();
            }
            stop.setEnabled(true);
            container.repaint();

            /**
             * 타겟 외의 구역을 클릭 시 missCount가 증가하는 마우스리스너
             * 같은 좌표를 두번 이상 클릭하면 집계안함
             */
            container.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    if(e.getClickCount()>1) {
                    } else {
                        miss.start();
                        miss.setFramePosition(0);
                        missCount++; // 미스 카운트 1 증가
                        missCountLabel.setText("Miss : " + missCount); // 라벨 변경
                        container.repaint();
                    }
                }
            });

            itemModeThread = new ItemModeThread(container);
            if (itemModeSelected) itemModeThread.start();
            while(true) {
                /**
                 * 타이머가 0까지 도달했을 경우 수행
                 */
                if (timeCount <= 0 || stopGame == true) { // 타임카운트가 0이 되면
                    System.out.println("[1] 게임끝!");
                    dispose(); // 창 닫힘
                    dropItem = false;
                    MainScreen.start.setFramePosition(0);
                    ScoreScreen.percent = ((double)hitCount/(double)totalCount)*100; // 맞춘 퍼센트 ScoreScreen에 전달
                    ScoreScreen.totalCount = totalCount; // 총 타겟 출현 갯수 ScoreScreen에 전달
                    ScoreScreen.hitCount = hitCount; // 타겟 클릭 횟수 ScoreScreen에 전달
                    ScoreScreen.missCount = missCount; // 타겟 외 클릭 횟수 ScoreScreen에 전달
                    timeCount = Controller.time; // 타임 카운트에 처음 설정된 타임 값 전달
                    stopGame = false;

                    if (gameOver) {
                        gameOver = false;
                        new ReadScreen();
                    }
                    else new ScoreScreen(); // ScoreScreen 실행
                    hitCount = 0; // 타겟 클릭 횟수 0으로 초기화
                    missCount = 0; // 타겟 외 클릭 횟수 0으로 초기화
                    totalCount = 0; // 총 출현 타겟 갯수 0으로 초기화
                    defense = Controller.defense;
                    timer.setTimerStop(true);
                    defaultModeThread.setDefaultModeStop(true); // DefaultModeThread 쓰레드 종료
                    movingModeThread.setMovingModeStop(true); // MovingModeThread 쓰레드 종료
                    shieldModeThread.setShieldModeStop(true); // ShieldModeThread 쓰레드 종료
                    itemModeThread.setItemModeStop(true);
                    return;
                }

                /**
                 * 아이템 타겟 충돌 검사기 생성
                 */
                //setLocationTarget();
                setTargetX = targetX;
                setTargetY = targetY;

                /**
                 * 모드 실행여부 검사 후 실행
                 * 총 3가지 [Moving_Mode/Shield_Mode/Item_Mode]
                 */

                shieldModeThread = new ShieldModeThread(container, aShieldTargetButton);
                if (shieldModeSelected) shieldModeThread.start();

                /**
                 * Target버튼 속성을 Controller로부터 가져와 부여하여 생성
                 */
                targetButton = new JButton();
                Controller.createTarget(targetButton);
                targetButton.setLocation(setTargetX,setTargetY);
                container.add(targetButton);
                container.repaint(); // 없으면 안됨!

                movingModeThread = new MovingModeThread(container);
                if (movingModeSelected) movingModeThread.start();



                /**
                 * 타겟을 클릭하였을 때 발생하는 이벤트
                 */
                targetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        hit.start();
                        hit.setFramePosition(0);
                        hitCount++;
                        hitCountLabel.setText("Score : " + hitCount);
                        container.remove(targetButton);
                        container.repaint();
                        movingModeThread.setMovingModeStop(true); // MovingModeThread 쓰레드 종료
                    }
                });

                try {
                    if(shieldModeSelected)
                        sleep(shieldFrequency); // 설정한 frequency에 맞춰 시간 설정
                    else
                        sleep(defaultFrequency);
                    totalCount++; // 총 출현 타겟 횟수 증가
                    movingModeThread.setMovingModeStop(true); // 시간 끝나면 MovingModeThread 쓰레드 종료
                    shieldModeThread.setShieldModeStop(true);
                    container.remove(targetButton);
                    container.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class DefaultModeThread extends Thread {
        private boolean defaultModeStop; // 쓰레드 종료 여부 결정 불린 변수

        // MovingModeThread 쓰레드 종료하는 메서드
        public void setDefaultModeStop(boolean defaultModeStop) { this.defaultModeStop = defaultModeStop; }

        // 쓰레드 실행 부분
        public void run() {
            while (true) {
                // 쓰레드 종료 여부 결정
                if (defaultModeStop) {
                    defaultModeStop = false;
                    return;
                }

                /**
                 * 타겟의 위치 난수를 발생하여 설정
                 */
                targetX = ((int)(Math.random() * 500) + 100);
                targetY = ((int)(Math.random() * 450) + 150);
            }
        }
    }

    class MovingModeThread extends Thread {
        private Container container;
        private boolean movingModeStop; // 쓰레드 종료 여부 결정 불린 변수
        int selectMoveToCrossOrDiagonal; // 직선인지 대각선인지 결정하는 변수
        int selectMoveX, selectMoveY; // 양수인지 음수인지 결정하는 변수

        // 외부 컨테이너 연결
        MovingModeThread(Container container) {
            this.container = container;
        }

        // MovingModeThread 쓰레드 종료하는 메서드
        public void setMovingModeStop(boolean movingModeStop) {
            this.movingModeStop = movingModeStop;
        }

        // 쓰레드 실행 부분
        public void run() {
            /**
             * 타겟이 직선으로 또는 대각선으로 이동할 방향 정함
             * 타겟이 x축으로 또는 y축으로 양수 또는 음수로 이동할지 결정
             */
            selectMoveToCrossOrDiagonal = ((int)(Math.random()*10))%2;
            selectMoveX = ((int)(Math.random()*10))%2;
            selectMoveY = ((int)(Math.random()*10))%2;
            while(true) {
                // 쓰레드 종료 여부 결정
                if (movingModeStop) {
                    movingModeStop = false;
                    System.out.println("Kill Moving Mode!");
                    return;
                }
                System.out.println("Moving Mode Thread Run");

                /**
                 * selectMoveToCrossOrDiagonal이 0면 직선, 1이면 대각선
                 */
                switch (selectMoveToCrossOrDiagonal) {
                    /**
                     * 직선으로 이동하는 케이스
                     * selectMoveX에 따라 좌우, selectMoveY에 따라 상하 로 이동
                     * (0,0):상 (0,1):하 (1,0):우 (1,1):좌
                     */
                    case 0:
                        if ( selectMoveX == 0 && selectMoveY == 0) {
                            targetButton.setLocation(setTargetX,setTargetY++);
                            container.repaint();
                        } else if ( selectMoveX == 0 && selectMoveY == 1) {
                            targetButton.setLocation(setTargetX,setTargetY--);
                            container.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 0) {
                            targetButton.setLocation(setTargetX++,setTargetY);
                            container.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 1) {
                            targetButton.setLocation(setTargetX--,setTargetY);
                            container.repaint();
                        } break;
                    /**
                     * 대각선으로 이동하는 케이스
                     * selectMoveX에 따라 좌우 결정 selectMoveY에 따라 상하 결정
                     * (0,0):우상 (0,1):우하 (1,0):좌상 (1,1):좌하
                     */
                    case 1:
                        if ( selectMoveX == 0 && selectMoveY == 0) {
                            targetButton.setLocation(setTargetX++,setTargetY++);
                            container.repaint();
                        } else if ( selectMoveX == 0 && selectMoveY == 1) {
                            targetButton.setLocation(setTargetX++,setTargetY--);
                            container.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 0) {
                            targetButton.setLocation(setTargetX--,setTargetY++);
                            container.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 1) {
                            targetButton.setLocation(setTargetX--,setTargetY--);
                            container.repaint();
                        } break;
                }
                try {
                    /**
                     * 설정한 speed에 따라 스레드의 반복주기가 변하며
                     * 줄어들 수록 더 빠르게 반복하여 빠르게 이동하는 것으로 보임
                     */
                    sleep((long) (21 - ((speed)*1.5)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ShieldModeThread extends Thread {
        private ArrayList<JButton> aShieldTargetButton;
        private Container container;
        private boolean shieldModeStop;
        private Stack stack;
        private int stackCreate;

        public ShieldModeThread(Container container, ArrayList<JButton> aShieldTargetButton) {
            this.container = container;
            this.aShieldTargetButton = aShieldTargetButton;
        }

        public void setShieldModeStop(boolean shieldModeStop) {
            this.shieldModeStop = shieldModeStop;
        }

        @Override
        public void run() {
            // 쓰레드 종료 여부 결정
            if (shieldModeStop) {
                shieldModeStop = false;
                return;
            }

            int [] defenseArray = {1,2,3,4,5,6,7,8,9,10};
            stackCreate = 0;
            defense = Controller.defense;
            stack = new Stack();

            while (defense > 0) {
                stack.push(defenseArray[stackCreate]);
                stackCreate++;
                defense--;
            }

            while (!stack.isEmpty()) {
                int stackNum = (int) stack.pop() - 1;
                aShieldTargetButton.get(stackNum).setBorderPainted(false);
                aShieldTargetButton.get(stackNum).setContentAreaFilled(false);
                aShieldTargetButton.get(stackNum).setSize((Controller.size + 10) * 5, (Controller.size + 10) * 5);
                aShieldTargetButton.get(stackNum).setLocation(setTargetX, setTargetY);
                container.add(aShieldTargetButton.get(stackNum));
                container.repaint();
                aShieldTargetButton.get(stackNum).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        container.remove(aShieldTargetButton.get(stackNum));
                        container.repaint();
                    }
                });
            }

            /**
             * 쉴드타겟 시간지나면 사라지게 고치기
             */
            try {
                sleep(shieldFrequency);
                stackCreate = 0;
                defense = Controller.defense;
                while (defense > 0) {
                    stack.push(defenseArray[stackCreate]);
                    stackCreate++;
                    defense--;
                }
                while(!stack.isEmpty()) {
                    int stackNum = (int) stack.pop() - 1;
                    container.remove(aShieldTargetButton.get(stackNum));
                }
                container.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class ItemModeThread extends Thread {
        private Container container;
        private boolean itemModeStop;
        private int selectItem;

        public ItemModeThread(Container container) {
            this.container = container;
        }

        public void setItemModeStop(boolean itemModeStop) {
            this.itemModeStop = itemModeStop;
        }

        public boolean checkSameLocation(int defaultLocationX, int defaultLocationY, int itemLocationX, int itemLocationY) {
            if((defaultLocationX - (Controller.size+10)*5 < itemLocationX) && (itemLocationX < defaultLocationX))
                return true;
            if((defaultLocationY - (Controller.size+10)*5 < itemLocationY) && (itemLocationY < defaultLocationY))
                return true;
            return false;
        }

        public void setLocationItemTarget() {
            System.out.println("Check Location...");
            itemTargetX = ((int)(Math.random() * 500) + 100);
            itemTargetY = ((int)(Math.random() * 450) + 150);

            if(checkSameLocation(targetX,targetY,itemTargetX,itemTargetY)) {
                System.out.println("Wrong Location!!!");
                //setLocationItemTarget();
            }
        }

        @Override
        public void run() {
            try {
                sleep(defaultFrequency/2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int testTimeCount = 0;
            while(true) {
                if(itemModeStop) {
                    itemModeStop = false;
                    System.out.println("ItemMode Thread Stop");
                    return;
                }
                System.out.println("ItemMode Thread Start");
                dropItem = true;
                // 아이템 위치 잡기


                // 아이템 선택
                selectItem = ((int)(Math.random() * 3));

                System.out.println(testTimeCount);
                testTimeCount++;

                String [] item = {"TimeCountUp","TimeCountDown","GameOver"};

                ImageIcon itemTargetImage = new ImageIcon("src/main/resources/" + item[selectItem] + ".png");
                ImageIcon itemTargetRolloverImage = new ImageIcon("src/main/resources/" + item[selectItem] + "Rollover.png");
                ImageIcon itemTargetPressedImage = new ImageIcon("src/main/resources/" + item[selectItem] + "Pressed.png");

                Image itemTargetScale = (itemTargetImage.getImage()).getScaledInstance((Controller.size+10)*5,(Controller.size+10)*5,Image.SCALE_SMOOTH);
                Image itemTargetRolloverScale = (itemTargetRolloverImage.getImage()).getScaledInstance((Controller.size+10)*5,(Controller.size+10)*5,Image.SCALE_SMOOTH);
                Image itemTargetPressedScale = (itemTargetPressedImage.getImage()).getScaledInstance((Controller.size+10)*5,(Controller.size+10)*5,Image.SCALE_SMOOTH);

                ImageIcon itemTarget = new ImageIcon(itemTargetScale);
                ImageIcon itemTargetRollover = new ImageIcon(itemTargetRolloverScale);
                ImageIcon itemTargetPressed = new ImageIcon(itemTargetPressedScale);

                itemTargetX = 930 - (Controller.size + 10) * 5;
                itemTargetY = 50;

                itemTargetButton = new JButton();
                itemTargetButton.setIcon(itemTarget);
                itemTargetButton.setRolloverIcon(itemTargetRollover);
                itemTargetButton.setPressedIcon(itemTargetPressed);
                itemTargetButton.setSize((Controller.size + 10) * 5, (Controller.size + 10) * 5);
                itemTargetButton.setLocation(itemTargetX,itemTargetY);
                itemTargetButton.setBorderPainted(false);
                itemTargetButton.setContentAreaFilled(false);

                container.add(itemTargetButton);
                container.repaint();



                /**
                 * case 0 -> Time Count Up      => 시간을 3 늘어남
                 * case 1 -> Time Count Down    => 시간을 3 줄어듬
                 * case 2 -> Game Over          => 게임 오버
                 */
                switch (selectItem) {
                    case 0:
                        itemTargetButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                container.remove(itemTargetButton);
                                timeCountUp.start();
                                timeCountUp.setFramePosition(0);
                                timeCount = timeCount + 3;
                                timeCountLabel.setText("Time : " + timeCount);
                                container.repaint();
                                dropItem = false;
                            }
                        });
                        break;
                    case 1:
                        itemTargetButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                container.remove(itemTargetButton);
                                timeCountDown.start();
                                timeCountDown.setFramePosition(0);
                                timeCount = timeCount - 3;
                                if(timeCount < 0) timeCountLabel.setText("Time : 0");
                                else timeCountLabel.setText("Time : " + timeCount);
                                container.repaint();
                                dropItem = false;
                            }
                        });
                        break;
                    case 2:
                        itemTargetButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                container.remove(itemTargetButton);
                                bombGameOver.start();
                                bombGameOver.setFramePosition(0);
                                stopGame = true;
                                gameOver = true;
                                dropItem = false;
                            }
                        });
                        break;
                }
                /**
                 * 아이템 타겟 생성주기 설정
                 */
                System.out.println("ItemMode : Start Drop Item");
                while(dropItem) {
                    System.out.print(".");
                    if(itemTargetY > 600) dropItem = false;
                    itemTargetY++;
                    itemTargetButton.setLocation(itemTargetX,itemTargetY);
                    container.repaint();
                    try {
                        sleep(Controller.item+50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("ItemMode : End Drop Item");
                container.remove(itemTargetButton);
                container.repaint();


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



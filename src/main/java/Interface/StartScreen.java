package Interface;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import static Interface.Controller.*;

public class StartScreen extends JFrame {
    public static int hitCount = 0;
    public static int missCount = 0;
    public static int totalCount = 0;
    public static int timeCount = Controller.time;

    public static Clip hit;
    public static Clip miss;
    public static Clip start;

    public static int targetX, targetY;
    public static int setTargetX, setTargetY;

    public static JButton targetButton;

    public static JLabel hitCountLabel;
    public static JLabel missCountLabel;
    public static JLabel timeCountLabel;

    public StartScreen() {
        setTitle("Start");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(null);
        container.setBackground(backGroundColor);

        // 커넥터 메서드 생각해보기
        try {
            hit = AudioSystem.getClip();
            miss = AudioSystem.getClip();

            File hitSoundFile = new File("src/main/resources/TargetClick.wav");
            File missSoundFile = new File("src/main/resources/BackgroundClick.wav");

            AudioInputStream hitAudioInputStream = AudioSystem.getAudioInputStream(hitSoundFile);
            AudioInputStream missAudioInputStream = AudioSystem.getAudioInputStream(missSoundFile);

            hit.open(hitAudioInputStream);
            miss.open(missAudioInputStream);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainScreen.start.start();

        JButton stop = new JButton("S");
        settingButton(stop,735,0,50,50,20);
        container.add(stop);

        // 타겟 클릭 횟수 라벨
        hitCountLabel = new JLabel("Score : " + hitCount);
        settingBoldLabel(hitCountLabel,5,0,200,50,40);
        container.add(hitCountLabel);

        // 타겟 외 클릭 횟수 라벨
        missCountLabel = new JLabel("Miss : " + missCount);
        settingBoldLabel(missCountLabel,400,0,200,50,40);
        container.add(missCountLabel);

        // 시간 라벨
        timeCount = Controller.time;
        timeCountLabel = new JLabel("Time : " + timeCount);
        settingBoldLabel(timeCountLabel,215,0,200,50,40);
        container.add(timeCountLabel);

        // 화면 구성
        settingScreen(800,800,false,null,true);

        // 타이머 쓰레드 객체 생성 및 쓰레드 실행
        MainThread mainThread = new MainThread(container, hit, miss, start);
        mainThread.start();
        Timer timer = new Timer(timeCountLabel);
        timer.start();
    }

    // 타이머 쓰레드
    class Timer extends Thread {
        private JLabel timeCountLabel;

        // 타이머 쓰레드 클래스 생성자 - 외부에서 시간 라벨을 가져옴
        public Timer(JLabel timeCountLabel) {
            this.timeCountLabel = timeCountLabel;
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
                timeCountLabel.setText("Time : " + timeCount); // 줄인 숫자로 타임카운트라벨 수정
                System.out.println(timeCount);
                try {
                    sleep(1000); // 약 1초 동안 대기
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(timeCount == 0) {
                    System.out.println("End");
                    timeCount = Controller.time; // 타임 카운트에 처음 설정된 타임 값 전달
                    return;
                }
            }
        }
    }

    class MainThread extends Thread {
        private Container container;
        private DefaultModeThread defaultModeThread;
        private MovingModeThread movingModeThread;
        private JLabel readyLabel;

        private Clip hit, miss, start;

        public MainThread(Container container, Clip hit, Clip miss, Clip start) {
            this.container = container;
            this.hit = hit;
            this.miss = miss;
            //this.start = start;
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

            /**
             * 타겟 외의 구역을 클릭 시 missCount가 증가하는 마우스리스너
             */
            container.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    miss.start();
                    miss.setFramePosition(0);
                    missCount++; // 미스 카운트 1 증가
                    missCountLabel.setText("Miss : " + missCount); // 라벨 변경
                    container.repaint();
                }
            });
            defaultModeThread = new DefaultModeThread();
            defaultModeThread.start();
            while(true) {
                /**
                 * 타이머가 0까지 도달했을 경우 수행
                 */
                if (timeCount == 0) { // 타임카운트가 0이 되면
                    MainScreen.start.setFramePosition(0);
                    ScoreScreen.percent = ((double)hitCount/(double)totalCount)*100; // 맞춘 퍼센트 ScoreScreen에 전달
                    ScoreScreen.totalCount = totalCount; // 총 타겟 출현 갯수 ScoreScreen에 전달
                    ScoreScreen.hitCount = hitCount; // 타겟 클릭 횟수 ScoreScreen에 전달
                    ScoreScreen.missCount = missCount; // 타겟 외 클릭 횟수 ScoreScreen에 전달
                    dispose(); // 창 닫힘
                    new ScoreScreen(); // ScoreScreen 실행

                    hitCount = 0; // 타겟 클릭 횟수 0으로 초기화
                    missCount = 0; // 타겟 외 클릭 횟수 0으로 초기화
                    totalCount = 0; // 총 출현 타겟 갯수 0으로 초기화
                    movingModeThread.setMovingModeStop(true); // MovingModeThread 쓰레드 종료
                    return; // 타이머 쓰레드 종료
                }

                /**
                 * Target버튼 속성을 Controller로부터 가져와 부여하여 생성
                 */
                targetButton = new JButton();
                Controller.createTarget(targetButton);
                setTargetX = targetX;
                setTargetY = targetY;
                targetButton.setLocation(setTargetX,setTargetY);
                container.add(targetButton);
                container.repaint(); // 없으면 안됨!

                /**
                 * 모드 실행여부 검사 후 실행
                 * 총 3가지 [Moving_Mode/Shield_Mode/Item_Mode]
                 */
                movingModeThread = new MovingModeThread(container);
                if (mode1Selected) movingModeThread.start();

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
                    sleep(1000 - (11 - Controller.frequency) * 50); // 설정한 frequency에 맞춰 시간 설정
                    totalCount++; // 총 출현 타겟 횟수 증가
                    movingModeThread.setMovingModeStop(true); // 시간 끝나면 MovingModeThread 쓰레드 종료
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
                if (defaultModeStop == true) {
                    defaultModeStop = false;
                    return;
                } else if (timeCount == 0) return; // 타임 카운트가 0이 되면

                /**
                 * 타겟의 위치 난수를 발생하여 설정
                 */
                targetX = ((int)(Math.random() * 700));
                targetY = ((int)(Math.random() * 615) + 50);
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
                if (movingModeStop == true) {
                    movingModeStop = false;
                    return;
                }

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
                    sleep(11 - Controller.speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ShieldModeThread extends Thread {
        private Container container;
        private boolean shieldModeStop;

        public ShieldModeThread(Container container) { this.container = container; }

        public void setShieldModeStop(boolean shieldModeStop) {
            this.shieldModeStop = shieldModeStop;
        }

        @Override
        public void run() {

        }
    }

    public void settingScreen(int sizeX, int sizeY, boolean resizable, Component locationRelativeTo, boolean visible) {
        setSize(sizeX,sizeY);
        setResizable(resizable);
        setLocationRelativeTo(locationRelativeTo);
        setVisible(visible);
    }
}



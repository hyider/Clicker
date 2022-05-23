package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static Interface.Controller.*;

public class StartScreen extends JFrame {
    public static int hitCount = 0;
    public static int missCount = 0;
    public static int totalCount = 0;
    public static int timeCount = Controller.time;

    public static int targetX, targetY;

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

        // 타겟 쓰레드 객체 생성 및 쓰레드 실행
        DefaultModeThread defaultModeThread = new DefaultModeThread(container);
        defaultModeThread.start();
        // 타이머 쓰레드 객체 생성 및 쓰레드 실행
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
            try {
                sleep(3000); // 처음 약 3초 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                timeCount--; // 외부에서 설정된 타임카운트의 수 1줄임
                timeCountLabel.setText("Time : " + timeCount); // 줄인 숫자로 타임카운트라벨 수정
                try {
                    sleep(1000); // 약 1초 동안 대기
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (timeCount == 0) { // 타임카운트가 0이 되면
                    ScoreScreen.percent = ((double)hitCount/(double)totalCount)*100; // 맞춘 퍼센트 ScoreScreen에 전달
                    ScoreScreen.totalCount = totalCount; // 총 타겟 출현 갯수 ScoreScreen에 전달
                    ScoreScreen.hitCount = hitCount; // 타겟 클릭 횟수 ScoreScreen에 전달
                    ScoreScreen.missCount = missCount; // 타겟 외 클릭 횟수 ScoreScreen에 전달
                    dispose(); // 창 닫힘
                    new ScoreScreen(); // ScoreScreen 실행
                    timeCount = Controller.time; // 타임 카운트에 처음 설정된 타임 값 전달
                    hitCount = 0; // 타겟 클릭 횟수 0으로 초기화
                    missCount = 0; // 타겟 외 클릭 횟수 0으로 초기화
                    totalCount = 0; // 총 출현 타겟 갯수 0으로 초기화
                    return; // 타이머 쓰레드 종료
                }
            }
        }
    }

    class DefaultModeThread extends Thread {
        private Container container;
        private MovingModeThread movingModeThread;

        public static JLabel readyLabel;

        // 외부 컨테이너 연결
        public DefaultModeThread(Container container) { this.container = container; }

        // 쓰레드 실행 부분
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
                    missCount++; // 미스 카운트 1 증가
                    missCountLabel.setText("Miss : " + missCount); // 라벨 변경
                    container.repaint();
                }
            });

            while (true) {
                // 타임 카운트가 0이 되면
                if (timeCount == 0) {
                    movingModeThread.setMovingModeStop(true); // MovingModeThread 쓰레드 종료
                    return; // DefaultModeThread 쓰레드 종료
                }

                // 타겟 출현 범위 난수로 설정
                targetX = ((int)(Math.random() * 700));
                targetY = ((int)(Math.random() * 615) + 50);

                /**
                 * Target을 버튼으로 구현
                 * 속성을 ContentSetting으로부터 가져와 부여
                 */
                targetButton = new JButton();
                Controller.createTarget(targetButton);
                targetButton.setLocation(targetX,targetY);
                container.add(targetButton);

                /**
                 * DefaultModeThread에 다른 Mode들을 추가하는 구역
                 * SettingModeScreen에서 선택여부를 검사하여 모드 실행 결정
                 */
                // MovingModeThread 쓰레드 생성 후 실행 -> 쓰레드는 종료되면 다시 사용 불가능 하기 때문에 반복적으로 쓰레드 생성
                movingModeThread = new MovingModeThread(container);
                if (mode1Selected) movingModeThread.start();

                // 타겟 클릭 액션리스너
                targetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        targetClickAudio();
                        movingModeThread.setMovingModeStop(true); // MovingModeThread 쓰레드 종료
                        hitCount++; // 타겟 클릭 횟수 증가
                        hitCountLabel.setText("Score : " + hitCount);
                        container.remove(targetButton);
                        container.repaint();
                    }
                });
                totalCount++; // 총 출현 타겟 횟수 증가
                container.repaint();
                try {
                    sleep(1000 - (11 - Controller.frequency) * 50); // 설정한 frequency에 맞춰 시간 설정
                    movingModeThread.setMovingModeStop(true); // 시간 끝나면 MovingModeThread 쓰레드 종료
                    container.remove(targetButton);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
            // 난수로 0 또는 1을 변수에 전달
            selectMoveToCrossOrDiagonal = ((int)(Math.random()*10))%2;
            selectMoveX = ((int)(Math.random()*10))%2;
            selectMoveY = ((int)(Math.random()*10))%2;
            while(true) {
                // 쓰레드 종료 여부 결정
                if (movingModeStop == true) {
                    movingModeStop = false;
                    return;
                }
                // 대각선인지 직선인지 결정, 0이면 직선 1이면 대각선
                switch (selectMoveToCrossOrDiagonal) {
                    case 0:
                        // (X,Y) -> (0,0):상 (0,1):하 (1,0):우 (1,1):좌
                        if ( selectMoveX == 0 && selectMoveY == 0) {
                            targetButton.setLocation(targetX,targetY++);
                            container.repaint();
                        } else if ( selectMoveX == 0 && selectMoveY == 1) {
                            targetButton.setLocation(targetX,targetY--);
                            container.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 0) {
                            targetButton.setLocation(targetX++,targetY);
                            container.repaint();
                        } else if ( selectMoveX == 1 && selectMoveY == 1) {
                            targetButton.setLocation(targetX--,targetY);
                            container.repaint();
                        } break;
                    case 1:
                        // (X,Y) -> (0,0):우상 (0,1):우하 (1,0):좌상 (1,1):좌하
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
                        } break;
                }
                try {
                    sleep(11 - Controller.speed); // 타겟이 움직이는 속도 결정 !변수로 줄 것
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
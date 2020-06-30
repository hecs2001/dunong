import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Game extends JFrame {
    static Scanner scan;
    int countdown = 20, score = 0, total = 0, attempts = 3, maxQuestions = 7;
    String category, questionID, questionText, answer, details, a, b, c, d;
    Integer[] usedNumber = new Integer[10];
    
    Color darkGRAY = new Color(30, 30, 30);
    
    DecimalFormat hundredFMT = new DecimalFormat("#00");
    
    Font FNT100 = new Font("Century Gothic", 1, 100);
    Font FNT69 = new Font("Century Gothic", 1, 69);
    Font FNT32 = new Font("Century Gothic", 0, 32);
    Font FNT18 = new Font("Century Gothic", 0, 18);
    Font FNT12 = new Font("Century Gothic", 0, 12);
    
    Random generateRandom = new Random();
    Timer timer;

    JButton pauseBTN = new JButton();
    JButton resumeBTN = new JButton("<html><b>RESUME</b></html>");
    JButton changeCategoryBTN = new JButton("<html><b>CHANGE CATEGORY</b></html>");
    JButton quitBTN = new JButton("<html><b>QUIT</b></html>");
    JButton guideBTN = new JButton();
    JButton aBTN = new JButton();
    JButton bBTN = new JButton();
    JButton cBTN = new JButton();
    JButton dBTN = new JButton();
    JButton nextQuestionBTN = new JButton();
    JButton retryBTN = new JButton("TRY AGAIN");
    JButton endBTN = new JButton("QUIT");
    
    JLabel timerLBL = new JLabel("", SwingConstants.CENTER);
    JLabel attemptsLBL = new JLabel("", SwingConstants.RIGHT);
    JLabel scoreLBL = new JLabel("", SwingConstants.RIGHT);
    JLabel questionLBL = new JLabel("", SwingConstants.CENTER);
    JLabel answerPIC = new JLabel();
    JLabel answerLBL = new JLabel();
    JLabel summaryLBL = new JLabel();
    JLabel endedTXT = new JLabel("", SwingConstants.CENTER);
    JLabel endScoreTXT = new JLabel("YOU SCORED");
    JLabel endScoreLBL = new JLabel();
    JLabel endMessageLBL = new JLabel();
    
    JLayeredPane bodyPNL = new JLayeredPane();
    JPanel headerPNL = new JPanel();
    JPanel questionPNL = new JPanel();
    JPanel answerPNL = new JPanel();
    JPanel endPNL = new JPanel();
    JPanel pausePNL = new JPanel();
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Game(String whatCategory){
        category = whatCategory;
        componentSettings();
        generateQA();
        switchPanel(questionPNL);
        revalidate();
    }
    
    public void componentSettings(){
        setTitle("Dunong Game");
        setSize(1920, 1080);
        setUndecorated(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                exitGame(false);
            }
        });
        
        Container frame = getContentPane();
        frame.add(bodyPNL);
        
        bodyPNL.setLayout(null);
        bodyPNL.setBounds(0, 0, 1920, 1080);

        headerPNL.add(pauseBTN);
        headerPNL.add(timerLBL);
        headerPNL.add(attemptsLBL);
        headerPNL.add(scoreLBL);
        headerPNL.setBackground(darkGRAY);
        headerPNL.setBounds(0, 0, 1920, 160);
        headerPNL.setLayout(null);
        
        pauseBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                pauseGame(true);
            }
        });
        pauseBTN.setBounds(50, 30, 100, 100);
        pauseBTN.setBorderPainted(false);
        pauseBTN.setContentAreaFilled(false);
        pauseBTN.setIcon(new ImageIcon(new ImageIcon("src/resources/icons/pause.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        
        timerLBL.setBounds(915, 20, 125, 120);
        timerLBL.setFont(FNT100);
        timerLBL.setForeground(Color.WHITE);
        timerLBL.setText("" + countdown);
        
        attemptsLBL.setBounds(1618, 35, 250, 20);
        attemptsLBL.setFont(FNT18);
        attemptsLBL.setForeground(Color.WHITE);
        attemptsLBL.setText(attempts + " ATTEMPTS LEFT");
        
        scoreLBL.setBounds(1490, 45, 380, 85);
        scoreLBL.setFont(FNT69);
        scoreLBL.setForeground(Color.WHITE);
        scoreLBL.setText(score + "/" + total);

        questionPNL.add(questionLBL);
        questionPNL.add(aBTN);
        questionPNL.add(bBTN);
        questionPNL.add(cBTN);
        questionPNL.add(dBTN);
        questionPNL.setBackground(Color.WHITE);
        questionPNL.setLayout(null);
        questionPNL.setBounds(0, 0, 1920, 1080);
        
        questionLBL.setBounds(280, 220, 1400, 440);
        questionLBL.setFont(FNT69);
        
        aBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                checkANS(aBTN.getText());
            }
        });
        aBTN.setBackground(Color.WHITE);
        aBTN.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        aBTN.setBounds(60, 700, 880, 140);
        aBTN.setFont(FNT32);
        
        bBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                checkANS(bBTN.getText());
            }
        });
        bBTN.setBackground(Color.WHITE);
        bBTN.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        bBTN.setBounds(985, 700, 880, 140);
        bBTN.setFont(FNT32);
        
        cBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                checkANS(cBTN.getText());
            }
        });
        cBTN.setBackground(Color.WHITE);
        cBTN.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        cBTN.setBounds(60, 880, 880, 140);
        cBTN.setFont(FNT32);
        
        dBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                checkANS(dBTN.getText());
            }
        });
        dBTN.setBackground(Color.WHITE);
        dBTN.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        dBTN.setBounds(985, 880, 880, 140);
        dBTN.setFont(FNT32);

        answerPNL.add(answerPIC);
        answerPNL.add(answerLBL);
        answerPNL.add(summaryLBL);
        answerPNL.add(nextQuestionBTN);
        answerPNL.setBackground(Color.WHITE);
        answerPNL.setBounds(0, 0, 1920, 1080);
        answerPNL.setLayout(null);
        
        answerPIC.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        answerPIC.setBounds(100, 220, 600, 800); //ASPECT RATIO OF PHOTOS SHOULD BE 3:4
        
        answerLBL.setBounds(730, 280, 1080, 500);
        answerLBL.setFont(FNT32);
        
        summaryLBL.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2), BorderFactory.createEmptyBorder(0, 20, 0, 20)));
        summaryLBL.setBounds(740, 780, 910, 150);
        summaryLBL.setFont(new Font("Century Gothic", 0, 26));
        
        nextQuestionBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                generateQA();
            }
        });
        nextQuestionBTN.setBorder(BorderFactory.createEmptyBorder());
        nextQuestionBTN.setBounds(1650, 780, 150, 150);
        nextQuestionBTN.setContentAreaFilled(false);
        nextQuestionBTN.setIcon(new ImageIcon(new ImageIcon("src/resources/icons/next-icon.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        nextQuestionBTN.setToolTipText("<html><center><h1>Proceed to the<br>Next Question</center></html>");

        endPNL.add(retryBTN);
        endPNL.add(endBTN);
        endPNL.add(endedTXT);
        endPNL.add(endScoreTXT);
        endPNL.add(endScoreLBL);
        endPNL.add(endMessageLBL);
        endPNL.setBackground(darkGRAY);
        endPNL.setBounds(0, 0, 1920, 1080);
        endPNL.setLayout(null);
        
        endedTXT.setBounds(325, 380, 500, 220);
        endedTXT.setFont(FNT100);
        endedTXT.setForeground(Color.WHITE);

        endScoreTXT.setBounds(1150, 340, 250, 35);
        endScoreTXT.setFont(FNT32);
        endScoreTXT.setForeground(Color.WHITE);

        endScoreLBL.setBounds(1150, 375, 400, 280);
        endScoreLBL.setFont(FNT69);
        endScoreLBL.setForeground(Color.WHITE);

        endMessageLBL.setBounds(1150, 670, 400, 40);
        endMessageLBL.setFont(FNT32);
        endMessageLBL.setForeground(Color.WHITE);

        retryBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                score = 0;
                total = 0;
                Arrays.fill(usedNumber, null);
                generateQA();
                switchPanel(questionPNL);
            }
        });
        retryBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        retryBTN.setBounds(300, 660, 250, 80);
        retryBTN.setContentAreaFilled(false);
        retryBTN.setFont(FNT32);
        retryBTN.setForeground(Color.WHITE);

        endBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                exitGame(true);
            }
        });
        endBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        endBTN.setBounds(600, 660, 250, 80);
        endBTN.setContentAreaFilled(false);
        endBTN.setFont(FNT32);
        endBTN.setForeground(Color.WHITE);

        pausePNL.add(resumeBTN);
        pausePNL.add(changeCategoryBTN);
        pausePNL.add(quitBTN);
        pausePNL.add(guideBTN);
        pausePNL.setBackground(darkGRAY);
        pausePNL.setBounds(0, 0, 1920, 1080);
        pausePNL.setLayout(null);
        
        resumeBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                pauseGame(false);
            }
        });
        resumeBTN.setBackground(darkGRAY);
        resumeBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        resumeBTN.setBounds(700, 340, 500, 100);
        resumeBTN.setForeground(Color.WHITE);
        resumeBTN.setFont(FNT32);
        
        changeCategoryBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                exitGame(true);
            }
        });
        changeCategoryBTN.setBackground(darkGRAY);
        changeCategoryBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        changeCategoryBTN.setBounds(700, 500, 500, 100);
        changeCategoryBTN.setForeground(Color.WHITE);
        changeCategoryBTN.setFont(FNT32);
        
        quitBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                exitGame(false);
            }
        });
        quitBTN.setBackground(darkGRAY);
        quitBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        quitBTN.setBounds(700, 660, 500, 100);
        quitBTN.setForeground(Color.WHITE);
        quitBTN.setFont(FNT32);
        
        guideBTN.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                new Information("guide").setVisible(true);
            };
        });
        guideBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        guideBTN.setBounds(1780, 940, 60, 60);
        guideBTN.setContentAreaFilled(false);
        guideBTN.setIcon(new ImageIcon(new ImageIcon("src/resources/icons/question-icon-2.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        guideBTN.setToolTipText("<html><h3>Show Guide</html>");
        frame.revalidate();
        setLocationRelativeTo(null);
    }
    
    String generateNumber(){
        int number = (1 + generateRandom.nextInt(10));
        
        for(int count = 0; count <= usedNumber.length; count++){
            if((usedNumber.length == 0) || (usedNumber[count] == null)){
                usedNumber[count] = number;
                break;
            } else if(number == usedNumber[count]){
                return generateNumber();
            }
        }
        return hundredFMT.format(number);
    }
    
    void setTimer(){
        countdown = 20;
        timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                timerLBL.setText(""+countdown);
                if(countdown > 5){
                    timerLBL.setForeground(Color.WHITE);
                } else if(countdown >= 0){
                    timerLBL.setForeground(new Color(217, 75, 88));
                } else {
                    timer.stop();
                    checkANS("no time");
                    timerLBL.setText("0");
                    JOptionPane.showMessageDialog(null,"<html><br><br><p style=\"font-size: 150%;\">TIMER RAN OUT</p><br><br></html>", "", JOptionPane.PLAIN_MESSAGE);
                }
                countdown-=1;
            }
        });
    }
    
    void generateQA(){
        String scanNUMBER;
        if((attempts == 0) || (total == maxQuestions)){
            displayScore(score, total);
            switchPanel(endPNL);
            attempts = 3;
            attemptsLBL.setText(attempts + " ATTEMPTS LEFT");
        } else {
            try{
                questionID = generateNumber();
                scan = new Scanner(new File("src/data/questions-"+category+".txt"));
                scan.useDelimiter("[;]");
                while(scan.hasNext()){
                    scanNUMBER = scan.next();
                    questionText = scan.next();
                    if(scanNUMBER.contains(questionID)){
                        break;
                    }
                }
                scan = new Scanner(new File("src/data/answers-"+category+".txt"));
                scan.useDelimiter("[;]");
                while(scan.hasNext()){
                    scanNUMBER = scan.next();
                    answer = scan.next();
                    a = scan.next();
                    b = scan.next();
                    c = scan.next();
                    d = scan.next();
                    if(scanNUMBER.contains(questionID)){
                        break;
                    }
                }
            } catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex, "oOf", JOptionPane.ERROR_MESSAGE);
            }
            setTimer();
            timer.start();
            switchPanel(questionPNL);
            questionLBL.setText(questionText);
            aBTN.setText(a);
            bBTN.setText(b);
            cBTN.setText(c);
            dBTN.setText(d);
            total++;
            scoreLBL.setText(score + "/" + total);
        }
    }
    
    void checkANS(String selectedANS){
        String scanNUMBER;
        timer.stop();
        try{
            scan = new Scanner(new File("src/data/description-"+category+".txt"));
            scan.useDelimiter("[;]");
            while(scan.hasNext()){
                scanNUMBER = scan.next();
                details = scan.next();
                if(scanNUMBER.contains(questionID)){
                    break;
                }
            }
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex, "oOf", JOptionPane.ERROR_MESSAGE);
        }
        if(selectedANS.contentEquals(answer)){
            score++;
            scoreLBL.setText(score + "/" + total);
            summaryLBL.setText("<html><b>YOUR ANSWER IS CORRECT</b><br><b>THE ANSWER:</b> " + answer + "</html>");
        } else {
            attempts--;
            if(selectedANS.contentEquals("no time")){
                summaryLBL.setText("<html><b>TIMER RAN OUT</b><br><b>CORRECT ANSWER:</b> " + answer + "</html>");
            } else {
                summaryLBL.setText("<html><b>YOUR ANSWER:</b> " + selectedANS + "<br><b>CORRECT ANSWER:</b> " + answer + "</html>");
            }
            switch (attempts) {
                case 0:
                    attemptsLBL.setText("OUT OF ATTEMPTS");
                    break;
                case 1:
                    attemptsLBL.setText(attempts + " ATTEMPT LEFT");
                    break;
                default:
                    attemptsLBL.setText(attempts + " ATTEMPTS LEFT");
                    break;
            }
        }
        answerPIC.setIcon(new ImageIcon(new ImageIcon("src/data/images/" + category.toUpperCase().charAt(0) + questionID + ".jpg").getImage().getScaledInstance(600, 800, Image.SCALE_SMOOTH)));
        answerLBL.setText(details);
        switchPanel(answerPNL);
    }
    
    void displayScore(int finalScore, int finalTotal){
        if(attempts == 0){
            endedTXT.setText("<html><center><b>OUT OF<br>ATTEMPTS</b></center></html>");
        } else {
            endedTXT.setText("<html><center><b>GAME<br>ENDED</b></center></html>");
        }
        if(finalScore == maxQuestions){
            endMessageLBL.setText("Awesome!!!");
        } else if(finalScore > (maxQuestions/2.8)) {
            endMessageLBL.setText("Nice, keep it up!!!");
        } else {
            endMessageLBL.setText("Nice Try!!!");
        }
        endScoreLBL.setText("<html><p style=\"font-size: 150%;\">" + finalScore + "/" + finalTotal + "</p></html>");
    }
    
    void switchPanel(JPanel panelToShow){
        bodyPNL.removeAll();
        bodyPNL.add(headerPNL, JLayeredPane.DRAG_LAYER);
        if(panelToShow == answerPNL){
            timerLBL.setEnabled(false);
        } else if(panelToShow == endPNL) {
            headerPNL.setVisible(false);
        } else {
            headerPNL.setVisible(true);
            timerLBL.setEnabled(true);
        }
        bodyPNL.add(panelToShow, JLayeredPane.DEFAULT_LAYER);
        repaint();
    }
    
    void pauseGame(boolean paused){
        if(paused == true){
            add(pausePNL);
            bodyPNL.setVisible(false);
            timer.stop();
        } else {
            remove(pausePNL);
            bodyPNL.setVisible(true);
            if(timerLBL.isEnabled() == true){
                timer.start();
            }
        }
    }
    
    void exitGame(boolean changeCategory){
        if(changeCategory == true){
            this.dispose();
            new Home("category").setVisible(true);
        } else {
            String[] buttons = {"Quit", "Return Home", "Cancel"};
            int response = JOptionPane.showOptionDialog(null, "<html><br><br><p style=\"font-size: 150%;\">Quit Game?</p><br><br></html>", "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[2]);
            switch (response) {
                case 0:
                    System.exit(0);
                case 1:
                    this.dispose();
                    new Home("body").setVisible(true);
                    break;
                default:
                    break;
            }
        }
    }
    
    public static void main(String[] args) {}
}
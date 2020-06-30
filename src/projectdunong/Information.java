import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Information extends JFrame{
    Font FNT22 = new Font("Century Gothic", 0, 22);
    Font FNT18 = new Font("Century Gothic", 0, 18);
    
    JButton mnmzBTN = new JButton();
    JButton exitBTN = new JButton();
    JButton aboutBTN = new JButton();
    JButton guideBTN = new JButton();
    
    JLabel titleTXT = new JLabel();
    JLabel aboutLBL = new JLabel();
    JLabel teamLBL = new JLabel("", SwingConstants.RIGHT);
    JLabel guideLBL1 = new JLabel();
    JLabel guideLBL2 = new JLabel();
    
    JLayeredPane body = new JLayeredPane();
    JPanel aboutPNL = new JPanel();
    JPanel guidePNL = new JPanel();
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Information(String whatPanel){
        componentSettings();
        switchPanel(whatPanel);
    }
    
    public void componentSettings(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setUndecorated(true);
        
        Container frame = getContentPane();
        frame.add(body);
        
        body.add(mnmzBTN, JLayeredPane.DRAG_LAYER);
        body.add(exitBTN, JLayeredPane.DRAG_LAYER);
        body.add(aboutBTN, JLayeredPane.DRAG_LAYER);
        body.add(guideBTN, JLayeredPane.DRAG_LAYER);
        body.add(titleTXT, JLayeredPane.DRAG_LAYER);
        body.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        body.setBackground(new Color(30, 30, 30));
        body.setLayout(null);
        body.setOpaque(true);
        
        mnmzBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                setState(ICONIFIED);
            };
        });
        mnmzBTN.setBorderPainted(false);
        mnmzBTN.setBounds(1180, 0, 50, 45);
        mnmzBTN.setContentAreaFilled(false);
        mnmzBTN.setIcon(new ImageIcon(new ImageIcon("src/resources/icons/minimize-icon-1.png").getImage().getScaledInstance(50, 45, Image.SCALE_SMOOTH)));
        mnmzBTN.setRolloverIcon(new ImageIcon(new ImageIcon("src/resources/icons/minimize-icon-2.png").getImage().getScaledInstance(50, 45, Image.SCALE_SMOOTH)));
        
        exitBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                dispose();
            };
        });
        exitBTN.setBorderPainted(false);
        exitBTN.setBounds(1230, 0, 50, 45);
        exitBTN.setContentAreaFilled(false);
        exitBTN.setIcon(new ImageIcon(new ImageIcon("src/resources/icons/close-icon-1.png").getImage().getScaledInstance(50, 45, Image.SCALE_SMOOTH)));
        exitBTN.setRolloverIcon(new ImageIcon(new ImageIcon("src/resources/icons/close-icon-2.png").getImage().getScaledInstance(50, 45, Image.SCALE_SMOOTH)));
        
        aboutBTN.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                switchPanel("about");
            }
        });
        aboutBTN.setBounds(50, 80, 75, 75);
        aboutBTN.setContentAreaFilled(false);
        aboutBTN.setIcon(new ImageIcon(new ImageIcon("src/resources/icons/info-icon-2.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        
        guideBTN.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                switchPanel("guide");
            }
        });
        guideBTN.setBounds(50, 220, 75, 75);
        guideBTN.setContentAreaFilled(false);
        guideBTN.setIcon(new ImageIcon(new ImageIcon("src/resources/icons/question-icon-2.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        
        titleTXT.setBounds(1170, 150, 80, 550);

        aboutPNL.add(aboutLBL);
        aboutPNL.add(teamLBL);
        aboutPNL.setBounds(0, 45, 1280, 675);
        aboutPNL.setLayout(null);
        aboutPNL.setOpaque(false);
        
        aboutLBL.setBounds(200, 30, 650, 260);
        aboutLBL.setText("<html><i><b>Dunong</b> is a <u>questionnaire-based game</u> that aims to test the general knowledge and information of players about given categories. This game aims to provide additional information to players and allow them to know more about the stuff around us that is sometimes considered \"trivial knowledge\", despite this we wish to allow players, young or old everyday things and what makes them important to us.</i></html>");
        aboutLBL.setFont(FNT22);
        aboutLBL.setForeground(Color.WHITE);
        
        teamLBL.setBounds(460, 320, 650, 350);
        teamLBL.setText("<html><p style=\"font-size: 150%;\"><b>Hackermen Studios</b></p><br><p style=\"text-align: right;\">Serdoncillo, Joshua James<br>Sevillano, Jonas<br>Valdez, Hector Liam<br>Villaluz, Ryan Bryle<html>");
        teamLBL.setFont(FNT18);
        teamLBL.setForeground(Color.WHITE);

        guidePNL.add(guideLBL1);
        guidePNL.add(guideLBL2);
        guidePNL.setBounds(0, 45, 1280, 675);
        guidePNL.setLayout(null);
        guidePNL.setOpaque(false);
        
        guideLBL1.setBounds(220, 50, 400, 500);
        guideLBL1.setText("<html><i><ul><li>there are three categories available: Phlippine History, Science, and Mythology</li><br><li>on each category, it gives you seven [7] random questions</li><br><li>after answering each question, the game will show details of the correct answer</li></ul></i></html>");
        guideLBL1.setFont(FNT22);
        guideLBL1.setForeground(Color.WHITE);
        
        guideLBL2.setBounds(640, 50, 400, 500);
        guideLBL2.setText("<html><i><ul><li>each questions gives you twenty [20] seconds to answer</li><br><li>you are given three attempts on each wrong answers you get. each correct answer contains one [1] point.</li><br><li>the score is displayed if you ANSWERED SEVEN QUESTIONS or RAN OUT OF ATTEMPTS.</li></ul></i></html>");
        guideLBL2.setFont(FNT22);
        guideLBL2.setForeground(Color.WHITE);
        revalidate();
        setLocationRelativeTo(null);
    }
    
    void switchPanel(String panelToShow){
        if(panelToShow.equals("about")){
            setTitle("About");
            body.remove(guidePNL);
            body.add(aboutPNL, JLayeredPane.DEFAULT_LAYER);
            aboutBTN.setEnabled(false);
            guideBTN.setEnabled(true);
            aboutBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            guideBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            titleTXT.setIcon(new ImageIcon("src/resources/title-text-1.png"));
        } else if(panelToShow.equals("guide")) {
            setTitle("How to Play");
            body.remove(aboutPNL);
            body.add(guidePNL, JLayeredPane.DEFAULT_LAYER);
            aboutBTN.setEnabled(true);
            guideBTN.setEnabled(false);
            aboutBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            guideBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            titleTXT.setIcon(new ImageIcon("src/resources/title-text-2.png"));
        }
        repaint();
    }
    
    public static void main(String[] args) {}
}

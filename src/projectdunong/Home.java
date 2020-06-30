import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JFrame{
    Color darkGRAY = new Color(30, 30, 30);
    
    Font FNT34 = new Font("Century Gothic", 0, 34);
    Font FNT32 = new Font("Century Gothic", 0, 32);
    
    ImageIcon background = new ImageIcon(new ImageIcon("src/resources/background.png").getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH));
    ImageIcon icon = new ImageIcon("src/resources/game-icon.png");
    
    JButton mnmzBTN = new JButton();
    JButton exitBTN = new JButton();
    JButton startBTN = new JButton("START");
    JButton informationBTN = new JButton();
    JButton returnBTN = new JButton();
    JButton historyBTN = new JButton("<html><center>Philippine<br>History</center></html>");
    JButton scienceBTN = new JButton("Science");
    JButton mythBTN = new JButton("Mythology");
    
    JLabel bgLBL = new JLabel(background);
    JLabel icnLBL = new JLabel(icon);
    JLabel categoryLBL = new JLabel("<html>Select<br>Category</html>");
    
    JPanel titleBarPNL = new JPanel();
    JPanel bodyPNL = new JPanel();
    JPanel categoryPNL = new JPanel();
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Home(String panel){
        componentSettings();
        switchPanel(panel);
    }
    
    public void componentSettings(){
        setTitle("Dunong");
        setSize(1280, 720);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        bgLBL.setSize(1280, 720);
        
        titleBarPNL.add(mnmzBTN);
        titleBarPNL.add(exitBTN);
        titleBarPNL.setBounds(0, 0, 1280, 45);
        titleBarPNL.setLayout(null);
        titleBarPNL.setOpaque(false);
        
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
                System.exit(0);
            };
        });
        exitBTN.setBorderPainted(false);
        exitBTN.setBounds(1230, 0, 50, 45);
        exitBTN.setContentAreaFilled(false);
        exitBTN.setIcon(new ImageIcon(new ImageIcon("src/resources/icons/close-icon-1.png").getImage().getScaledInstance(50, 45, Image.SCALE_SMOOTH)));
        exitBTN.setRolloverIcon(new ImageIcon(new ImageIcon("src/resources/icons/close-icon-2.png").getImage().getScaledInstance(50, 45, Image.SCALE_SMOOTH)));

        bodyPNL.add(icnLBL);
        bodyPNL.add(startBTN);
        bodyPNL.add(informationBTN);
        bodyPNL.setBackground(darkGRAY);
        bodyPNL.setLayout(null);
        bodyPNL.setOpaque(false);
        bodyPNL.setSize(1280, 720);
        
        icnLBL.setBounds(240, 220, 800, 160);

        startBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                switchPanel("category");
            };
        });
        startBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        startBTN.setBounds(515, 460, 250, 75);
        startBTN.setContentAreaFilled(false);
        startBTN.setFont(FNT32);
        startBTN.setForeground(Color.WHITE);

        informationBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                new Information("about").setVisible(true);
            };
        });
        informationBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        informationBTN.setBounds(1180, 620, 60, 60);
        informationBTN.setContentAreaFilled(false);
        informationBTN.setIcon(new ImageIcon(new ImageIcon("src/resources/icons/info-icon-2.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        informationBTN.setToolTipText("<html><center><h3>Show<br>Information</center></html>");

        categoryPNL.add(returnBTN);
        categoryPNL.add(categoryLBL);
        categoryPNL.add(historyBTN);
        categoryPNL.add(scienceBTN);
        categoryPNL.add(mythBTN);
        categoryPNL.setBounds(0, 0, 1280, 720);
        categoryPNL.setLayout(null);
        categoryPNL.setOpaque(false);
        
        returnBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                switchPanel("body");
            }
        });
        returnBTN.setBorder(BorderFactory.createEmptyBorder());
        returnBTN.setBounds(50, 350, 60, 60);
        returnBTN.setContentAreaFilled(false);
        returnBTN.setIcon(new ImageIcon(new ImageIcon("src/resources/icons/left-icon-1.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        returnBTN.setToolTipText("<html><center><h3>Return<br>Home</center></html>");
        
        categoryLBL.setBounds(130, 325, 160, 100);
        categoryLBL.setFont(FNT34);
        categoryLBL.setForeground(Color.WHITE);

        historyBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                new Game("history").setVisible(true);
                dispose();
            }
        });
        historyBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        historyBTN.setBounds(420, 80, 250, 600);
        historyBTN.setContentAreaFilled(false);
        historyBTN.setFont(FNT32);
        historyBTN.setForeground(Color.WHITE);
        historyBTN.setHorizontalTextPosition(JButton.CENTER);
        historyBTN.setVerticalTextPosition(JButton.CENTER);
        historyBTN.setIcon(new ImageIcon("src/resources/icons/button-background.png"));
        historyBTN.setRolloverIcon(new ImageIcon("src/resources/icons/history-icon.png"));
        
        scienceBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                new Game("science").setVisible(true);
                dispose();
            }
        });
        scienceBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        scienceBTN.setBounds(700, 80, 250, 600);
        scienceBTN.setContentAreaFilled(false);
        scienceBTN.setFont(FNT32);
        scienceBTN.setForeground(Color.WHITE);
        scienceBTN.setHorizontalTextPosition(JButton.CENTER);
        scienceBTN.setVerticalTextPosition(JButton.CENTER);
        scienceBTN.setIcon(new ImageIcon("src/resources/icons/button-background.png"));
        scienceBTN.setRolloverIcon(new ImageIcon("src/resources/icons/science-icon.png"));
        
        mythBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                new Game("mythology").setVisible(true);
                dispose();
            }
        });
        mythBTN.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        mythBTN.setBounds(980, 80, 250, 600);
        mythBTN.setContentAreaFilled(false);
        mythBTN.setFont(FNT32);
        mythBTN.setForeground(Color.WHITE);
        mythBTN.setHorizontalTextPosition(JButton.CENTER);
        mythBTN.setVerticalTextPosition(JButton.CENTER);
        mythBTN.setIcon(new ImageIcon("src/resources/icons/button-background.png"));
        mythBTN.setRolloverIcon(new ImageIcon("src/resources/icons/mythology-icon.png"));
        revalidate();
        setLocationRelativeTo(null);
    }
    
    void switchPanel(String panelToShow){
        getContentPane().removeAll();
        add(titleBarPNL);
        if(panelToShow.equals("body")){
            add(bodyPNL);
        } else if(panelToShow.equals("category")){
            add(categoryPNL);
        }
        add(bgLBL);
        repaint();
    }
    
    public static void main(String[] args) {}
}

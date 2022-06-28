import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuFrame extends JFrame implements ActionListener, Runnable {
    static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);

    JButton startButton;
    JButton exitButton;
    JLabel title;
    //MenuPanel panel;

    MenuFrame(){
        /*panel = new MenuPanel();
		this.add(panel);*/
		this.setTitle("Pong Game");
        this.setPreferredSize(SCREEN_SIZE);
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = 2;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        title = new JLabel("Pong Game");
        this.setForeground(Color.black);
        this.setBackground(Color.black);
        title.setFont(new Font("Arial", Font.BOLD, 70));
        add(title, c);

        c.gridy = 1;
        c.gridx = 2;
        c.fill = GridBagConstraints.BOTH;
        startButton = new JButton("Start");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.addActionListener(this);
        add(startButton, c);
        
        c.gridy = 2;
        c.gridx = 2;
        exitButton = new JButton("Exit");
        exitButton.setBackground(Color.white);
        exitButton.setForeground(Color.black);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.addActionListener(this);
        add(exitButton, c);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        if(arg0.getSource() == startButton){
            dispose();
            GameFrame gameFrame = new GameFrame();
        }
        if(arg0.getSource() == exitButton){
            System.exit(0);
        }
        
    }
    
    
}
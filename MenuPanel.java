import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuPanel extends JFrame implements ActionListener, Runnable {
    static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);

	JButton startButton;
    JButton exitButton;
    JLabel title;

	MenuPanel()
	{
		
		this.setFocusable(true);
		this.setPreferredSize(SCREEN_SIZE);
		setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


		c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        title = new JLabel("Pong Game");
        title.setForeground(Color.black);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        add(title, c);

		c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        startButton = new JButton("Start");
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.black);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.addActionListener((ActionListener) this);
        add(startButton, c);

		c.gridx = 1;
        exitButton = new JButton("Exit");
        exitButton.setBackground(Color.white);
        exitButton.setForeground(Color.black);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.addActionListener((ActionListener) this);
        add(exitButton, c);
		


		Thread gameThread = new Thread((Runnable) this);
		gameThread.start();
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

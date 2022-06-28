import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

	static final int screen_width = 1000;
	static final int screen_height = (int)(screen_width * (0.5555));
	static final Dimension screen_dimension = new Dimension(screen_width,screen_height);
	static final int ball_diameter = 20;
	static final int padle_width = 25;
	static final int padle_height = 100;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	GamePanel(){
		newPaddles();
		newBall();
		score = new Score(screen_width,screen_height);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(screen_dimension);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() {
		random = new Random();
		ball = new Ball((screen_width/2)-(ball_diameter/2),(screen_height/2)-(ball_diameter),ball_diameter,ball_diameter);
	}
	public void newPaddles() {
		paddle1 = new Paddle(0,(screen_height/2)-(padle_height/2),padle_width,padle_height,1);
		paddle2 = new Paddle(screen_width-padle_width,(screen_height/2)-(padle_height/2),padle_width,padle_height,2);
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);

Toolkit.getDefaultToolkit().sync(); //syncronize the screen
	}
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	public void checkCollision() {
		
		//bounce ball off top & bottom window edges
		if(ball.y <=0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.y >= screen_height-ball_diameter) {
			ball.setYDirection(-ball.yVelocity);
		}
		//bounce ball off paddles
		if(ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //optional for more difficulty
			if(ball.yVelocity>0)
				ball.yVelocity++; //optional for more difficulty
			else
				ball.yVelocity--;
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		if(ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //optional for more difficulty
			if(ball.yVelocity>0)
				ball.yVelocity++; //optional for more difficulty
			else
				ball.yVelocity--;
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		//stops paddles at window edges
		if(paddle1.y<=0)
			paddle1.y=0;
		if(paddle1.y >= (screen_height-padle_height))
			paddle1.y = screen_height-padle_height;
		if(paddle2.y<=0)
			paddle2.y=0;
		if(paddle2.y >= (screen_height-padle_height))
			paddle2.y = screen_height-padle_height;
		//give a player 1 point and creates new paddles & ball
		if(ball.x <=0) {
			score.player2++;
			if (score.player2 == 5) {
				JOptionPane.showMessageDialog(null, "Player 2 wins!");
				System.exit(0);
				MenuFrame menu = new MenuFrame();
			}
			newPaddles();
			newBall();
			System.out.println("Player 2: "+score.player2);
		}
		if(ball.x >= screen_width-ball_diameter) {
			score.player1++;
			if(score.player1 == 5){
				JOptionPane.showMessageDialog(null, "Player 1 wins!");	
				System.exit(0);
				MenuFrame menu = new MenuFrame();
			}
			newPaddles();
			newBall();
			System.out.println("Player 1: "+score.player1);
		}
	}
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
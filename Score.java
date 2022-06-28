import java.awt.*;

public class Score extends Rectangle{

	static int screen_width;
	static int screen_height;
	int player1;
	int player2;
	
	Score(int screen_width, int screen_height){
		Score.screen_width = screen_width;
		Score.screen_height = screen_height;
	}
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("Consolas",Font.PLAIN,60));
		
		g.drawLine(screen_width/2, 0, screen_width/2, screen_height);
		g.drawOval(screen_width/2-75, screen_height/2-60, 150, 150);
		
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (screen_width/2)-85, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (screen_width/2)+20, 50);
	}
}
package Game;

 
import java.awt.Graphics;

import javax.swing.JPanel;

public class Rander extends JPanel
{
	

	private static final long serialVersionUID = 6368997770302822022L;

	@Override
		protected  void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			FlappyBird.flappyBird.repaint(g);
			
		}
	}



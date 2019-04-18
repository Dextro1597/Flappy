package Game;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import javax.swing.Timer;

import Database.Mysql;
public class FlappyBird implements ActionListener,MouseListener,KeyListener
{
	
	public static FlappyBird flappyBird;
	
	public static int WIDTH=700,HEIGHT=700;
	 
	public Rander render;
	
	public Rectangle bird;
	
	public int ticks,yMotion,score;
	
	public Random rand;
	
	public boolean gameOver;
	
	public ArrayList<Rectangle> columns;

	private boolean started;
	
	public FlappyBird()
	{
		JFrame jframe = new JFrame();
		Timer timer = new Timer(20, this);
		render = new Rander();
		rand = new Random();
		
		jframe.add(render);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH,HEIGHT);
		jframe.addMouseListener(this);  	
		jframe.addKeyListener(this);
		jframe.setTitle("FLAPPY_BIRD");
		jframe.setResizable(false);
		jframe.setVisible(true);
		
		bird = new Rectangle( WIDTH / 2 - 10, HEIGHT/ 2 - 10, 20, 20);
		
		columns = new ArrayList<Rectangle>();
		
		addColumn(true);   //for the 4 columns
		addColumn(true);
		addColumn(true);
		addColumn(true);
		timer.start();
		
	}
	
	public void addColumn(boolean start)
	{
		int space=300;
		int width=100;
		int height= 50 + rand.nextInt(300);
		
		if(start)
		{
	
	columns.add(new Rectangle(WIDTH + width + (columns.size()*300), HEIGHT - height - 120, width, height));
	columns.add(new Rectangle(WIDTH + width + (columns.size()-1)*300,0,width,HEIGHT-height-space));
	
		}
		else
		{
			columns.add(new Rectangle(columns.get(columns.size()-1).x +600,HEIGHT-height -120 ,width,height));
			columns.add(new Rectangle(columns.get(columns.size()-1).x, 0 , width, HEIGHT-height-space));
		}
	}
	public void jump()
	{
		if(gameOver)
		{
			bird = new Rectangle( WIDTH / 2 - 10, HEIGHT/ 2 - 10, 20, 20);
			columns.clear();
			yMotion=0;
			score=0;         
			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);
		gameOver=false;	
		}
		if(!started)
		{
			started= true;
		}
		else if(!gameOver)
		{
			if(yMotion>0)
			{
				yMotion=0;
			}
			yMotion -=10;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int speed=05;
		ticks++;
		if(started)
		{
		for(int i=0; i< columns.size();i++)
		{
			Rectangle column= columns.get(i);
			column.x -= speed;
		}
		
		if(ticks % 2 == 0 && yMotion <15)
		{
			yMotion+=2;
		}
		
		
		for(int i=0; i< columns.size();i++)
		{
			Rectangle column= columns.get(i);
			if (column.x +column.width < 0)
			{
				columns.remove(column);
				
				if(column.y ==0)
				{
					addColumn(false);
				}
			}
			
		}
		
		bird.y+=yMotion;
		
			for (Rectangle column : columns)
			{
				if (column.y== 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width /2 < column.x +column.width/2+10) //For calculating the score
				{
				score++;	
				}
				if(column.intersects(bird))
				{
					gameOver = true;
					if(bird.x <= column.x)
					{
					
					bird.x= column.x - bird.width;
					}
					else
					{
						if(column.y !=0 )
						{
							bird.y=column.y - bird.height;
						}
						else if(bird.y < column.height)
						{
							bird.y=column.height;
						}
					}
				}
			}
			
			if(bird.y > HEIGHT - 120 || bird.y<0)
			{
				
				gameOver = true;
			}
			if(bird.y + yMotion >= HEIGHT -120)
			{
				bird.y = HEIGHT - 120- bird.height;
			}
		}
			render.repaint();
	}
	
	public void paintColumn(Graphics g, Rectangle column)                    //method for creating columns and setting their color
	{                                                  
		g.setColor(Color.gray.darker().darker());
		g.fillRect(column.x, column.y, column.width, column.height);
		
	}
	public  void repaint(Graphics g)                                          //method for creating backgound,bird,ground,grass and set their color
	{
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.YELLOW.brighter().brighter());
		g.fillRect(0,  HEIGHT-120, WIDTH,120);
		
		g.setColor(Color.GREEN);
		g.fillRect(0,  HEIGHT-120, WIDTH,20);
		
	/*	g.setColor(Color.RED);
		g.fillOval( 350,325,bird.width, bird.height);
		*/
		
		g.setColor(Color.RED);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
    
		for (Rectangle column : columns)
		{
			paintColumn(g,column);
		}
		g.setColor(Color.WHITE);                                         //for displaying the starting msg when game is not started
		g.setFont(new Font("Arial",1,100));
		if(!started)
		{
			g.drawString("Click to start!", 75, HEIGHT/ 2 - 50);
		}
		
		if(gameOver)                                                     //for displaying when game is over
		{
			g.drawString("Game Over!", 75, HEIGHT/ 2 - 50);
			g.drawString(String.valueOf(score),WIDTH /2 -25,450);
			Mysql.add_score(score);
		}
		
	
		if(!gameOver && started)                                          //for displaying the score when the game is going on
		{
			g.drawString(String.valueOf(score),WIDTH /2 -25,100);
		}
	}
public static void main(String[] args)
{
	flappyBird =new FlappyBird();
}



@Override
public void mouseClicked(MouseEvent e) {                      //used as a method to start the game and to make the bird jump
	jump();
	
}

@Override
public void mousePressed(MouseEvent e) {

	
}

@Override
public void mouseReleased(MouseEvent e) {

	
}

@Override
public void mouseEntered(MouseEvent e) {
}

@Override
public void mouseExited(MouseEvent e) {

}

@Override
public void keyTyped(KeyEvent e) {

	
}

@Override
public void keyPressed(KeyEvent e) {

	
}

@Override
public void keyReleased(KeyEvent e)                                        //similar fun as that of mouse
{
if (e.getKeyCode() ==KeyEvent.VK_SPACE)
{
	jump();
}
}
}

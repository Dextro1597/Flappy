package Main;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Database.High_Score;
import Database.Mysql;
import Game.FlappyBird;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Opening {

	private JFrame frame;
	private String score;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Opening window = new Opening();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Opening() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 361);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name;
				name=JOptionPane.showInputDialog("Enter Your Name: ");
				Mysql my=new Mysql();
				my.add_name(name);
			/*	int cncl=JOptionPane.showConfirmDialog(null, "Dont want to play", "No play", JOptionPane.YES_NO_OPTION);
				  if (cncl == JOptionPane.YES_OPTION)
				    {
				      System.exit(0);
				    }*/
				FlappyBird fb=new FlappyBird();
				fb.main(null);
				frame.dispose();
			}

		
			
		});
		btnNewGame.setBounds(127, 82, 117, 25);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnSetting = new JButton("Setting");
		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuperUser su=new SuperUser();
				su.main(null);
				frame.dispose();
			}
		});
		btnSetting.setBounds(127, 136, 117, 25);
		frame.getContentPane().add(btnSetting);
		
		JButton btnCredits = new JButton("Instruction");
		btnCredits.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

			    String msg = "<html>Introduciton:<br> Here we have developed a simple game  its obejctive is to make the square box pass through the passing columns without<br>touching it. If the box touches the columns the game is over.For every column you pass successfully you get one point.<br><br>Use Guide:<br> This guide outlines a set of rules to be used is assigning credit for team member contribuiton." +
			    		"<br><br>Rules:<br>1.You can either play with spacebar/mouse/touchpad<br>2.If the box touches the columns the game is over.";

			    JOptionPane optionPane = new JOptionPane();
			    optionPane.setMessage(msg);
			    optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
			    JDialog dialog = optionPane.createDialog(null, "Credits:");
			    dialog.setVisible(true);	
			}
		});
		btnCredits.setBounds(127, 255, 117, 25);
		frame.getContentPane().add(btnCredits);
		
		JButton btnHighScore = new JButton("High Score");
		btnHighScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				High_Score hs=new High_Score();
				hs.main(null);
				//JOptionPane.showMessageDialog(null,"HighScore is:" +score);
			}
		});
		btnHighScore.setBounds(127, 198, 117, 25);
		frame.getContentPane().add(btnHighScore);
		
	
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String message = "Do you really want to quit?";
				    String title = "Really Quit?";
				    // display the JOptionPane showConfirmDialog
				    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
				    if (reply == JOptionPane.YES_OPTION)
				    {
				      System.exit(0);
				    }
			}
		});
		btnExit.setBounds(127, 310, 117, 25);
		frame.getContentPane().add(btnExit);
		
		
		
	}


}


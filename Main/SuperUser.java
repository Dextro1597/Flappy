package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Database.Mysql;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SuperUser {

	private JFrame frame;
	Connection conn;
	Statement smt,stmt;
	Mysql db = new Mysql ("root","root");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuperUser window = new SuperUser();
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
	public SuperUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 581, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Opening o=new Opening();
				o.main(null);
				frame.dispose();
				
				
			}
		});
		btnBack.setBounds(452, 23, 117, 25);
		frame.getContentPane().add(btnBack);
		
		JButton btnSetupDatabase = new JButton("Setup Database");
		btnSetupDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

				if (JOptionPane.showConfirmDialog(null, "Are you sure?", "ADD DATABASE..!!!",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
				{
				 Statement ps;
			            

			            try {
							 conn = db.getConnection();
						     stmt= conn.createStatement();
							 stmt.executeUpdate("create database FlappyBird;");
							 conn = db.revokeConnection();
							 db.selectDB("FlappyBird");
			            } catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(btnSetupDatabase, "DATABASE ALREAY EXIST...!!!!");
						}

			             

			            Statement stmt = null;
						try {
							conn = db.getConnection();
							stmt =  conn.createStatement();
					 
						stmt.executeUpdate("create table Name(ID int NOT NULL AUTO_INCREMENT,Name varchar(20),primary key(ID));");
						stmt.executeUpdate("alter table Name AUTO_INCREMENT=1;");
						
						stmt.executeUpdate("create table High_Score(ID int NOT NULL AUTO_INCREMENT,High_Score int(20),primary key(ID));");
						stmt.executeUpdate("alter table High_Score AUTO_INCREMENT=1;");
						
						stmt.executeUpdate("create table Credit(cre text);");
				        conn = db.revokeConnection();
				        JOptionPane.showMessageDialog(btnSetupDatabase , "PRESS OK TO CONTINUE", "SETUP DONE..!!!",JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}	
				
			
		});
		btnSetupDatabase.setBounds(49, 66, 172, 25);
		frame.getContentPane().add(btnSetupDatabase);
		
		JButton btnResetDatabase = new JButton("Reset Database");
		btnResetDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure?", "Rest DATABASE..!!!",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
				{
				 Statement stmt = null;
						try {
							conn = db.getConnection();
							stmt =  conn.createStatement();
							stmt.executeUpdate("drop database FlappyBird;");
														conn = db.revokeConnection();
				        JOptionPane.showMessageDialog( btnResetDatabase, " DATA IS RESET..!!!" );
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
			}
		});
		btnResetDatabase.setBounds(294, 66, 183, 25);
		frame.getContentPane().add(btnResetDatabase);
	}
}

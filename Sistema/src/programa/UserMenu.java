package programa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class UserMenu {
	public static void user_menu(String UID) {
	     
	     
	    JFrame f=new JFrame("User Functions"); //Give dialog box name as User functions
	    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit user menu on closing the dialog box
	    JButton view_but=new JButton("View Books");//creating instance of JButton  
	    view_but.setBounds(20,20,120,25);//x axis, y axis, width, height 
	    view_but.addActionListener((ActionListener) new ActionListener() { 
	        public void actionPerformed(ActionEvent e){
	             
	            JFrame f = new JFrame("Books Available"); //View books stored in database
	            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             
	             
	            Connection connection = Connect.connect();
	            String sql="select * from BOOKS"; //Retreive data from database
	            try {
	                Statement stmt = (Statement) connection.createStatement(); //connect to database
	                 ((java.sql.Statement) stmt).executeUpdate("USE LIBRARY"); // use librabry
	                stmt=(Statement) connection.createStatement();
	                ResultSet rs=((java.sql.Statement) stmt).executeQuery(sql);
	                JTable book_list= new JTable(); //show data in table format
	                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
	                  
	                JScrollPane scrollPane = new JScrollPane(book_list); //enable scroll bar
	 
	                f.add(scrollPane); //add scroll bar
	                f.setSize(800, 400); //set dimensions of view books frame
	                f.setVisible(true);
	                f.setLocationRelativeTo(null);
	            } catch (SQLException e1) {
	                // TODO Auto-generated catch block
	                 JOptionPane.showMessageDialog(null, e1);
	            }               
	             
	    }
	    }
	    );
	     
	    JButton my_book=new JButton("My Books");//creating instance of JButton  
	    my_book.setBounds(150,20,120,25);//x axis, y axis, width, height 
	    my_book.addActionListener(new ActionListener() { //Perform action
	        public void actionPerformed(ActionEvent e){
	             
	               
	            JFrame f = new JFrame("My Books"); //View books issued by user
	            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            int UID_int = Integer.parseInt(UID); //Pass user ID
	 
	            //.iid,issued.uid,issued.bid,issued.issued_date,issued.return_date,issued,
	            Connection connection = Connect.connect(); //connect to database
	            //retrieve data
	            String sql="select distinct issued.*,books.bname,books.genre,books.price from issued,books " + "where ((issued.uid=" + UID_int + ") and (books.bid in (select bid from issued where issued.uid="+UID_int+"))) group by iid";
	            String sql1 = "select bid from issued where uid="+UID_int;
	            try {
	                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	                //use database
	                 ((java.sql.Statement) stmt).executeUpdate("USE LIBRARY");
	                stmt=(Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	                //store in array
	                ArrayList books_list = new ArrayList();
	  
	                
	                 
	                ResultSet rs=((java.sql.Statement) stmt).executeQuery(sql);
	                JTable book_list= new JTable(); //store data in table format
	                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
	                //enable scroll bar
	                JScrollPane scrollPane = new JScrollPane(book_list);
	 
	                f.add(scrollPane); //add scroll bar
	                f.setSize(800, 400); //set dimensions of my books frame
	                f.setVisible(true);
	                f.setLocationRelativeTo(null);
	            } catch (SQLException e1) {
	                // TODO Auto-generated catch block
	                 JOptionPane.showMessageDialog(null, e1);
	            }               
	                 
	    }
	    }
	    );
	     
	     
	     
	    f.add(my_book); //add my books
	    f.add(view_but); // add view books
	    f.setSize(300,100);//400 width and 500 height  
	    f.setLayout(null);//using no layout managers  
	    f.setVisible(true);//making the frame visible 
	    f.setLocationRelativeTo(null);
	    }
}

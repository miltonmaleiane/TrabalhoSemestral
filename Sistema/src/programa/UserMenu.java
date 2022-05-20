package programa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class UserMenu {
	public static void user_menu(String UID) {
	     
	     //teste 30/04
	    JFrame f=new JFrame("User Functions"); //Give dialog box name as User functions
	    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit user menu on closing the dialog box
	    JButton view_but=new JButton("View Books");//creating instance of JButton  
	    view_but.setBounds(20,20,120,25);//x axis, y axis, width, height 
	    view_but.addActionListener((ActionListener) new ActionListener() { 
	        public void actionPerformed(ActionEvent e){
	             
	            JFrame f = new JFrame("Books Available"); //View books stored in database
	            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             
	             
	            Connection connection = Connect.connect();
	            String sql="select * from livros"; //Retreive data from database
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
	            String sql="select distinct issued.*,livros.nomel,livros.genero,livros.preco from issued,livros " + "where ((issued.uid=" + UID_int + ") and (livros.bid in (select bid from issued where issued.uid="+UID_int+"))) group by iid";
	            String sql1 = "select bid from issued where uid="+UID_int;
	            try {
	                Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	                //use database
	                 ((java.sql.Statement) stmt).executeUpdate("USE LIBRARY");
	                stmt=(Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	                //store in array
	                ArrayList livros_lista = new ArrayList();
	  
	                
	                 
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
	    JButton add_feedback=new JButton("Adicion feedback"); //creating instance of JButton for adding books
	    add_feedback.setBounds(150,60,120,25); 
	     
	    add_feedback.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	                //set frame wot enter book details
	                JFrame g = new JFrame("Enter Book Details");
	                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                // set labels
	                JLabel l1,l2,l3, lbAutor; 
	               
	                l1=new JLabel("Reclaamacao");  //lebel 1 for book name
	                l1.setBounds(30,15, 100,30); 
	                 
	                 
	                l2=new JLabel("Satisfacao");  //label 2 for fSatisfacao
	                l2.setBounds(30,53, 100,30); 
	                 
	                l3=new JLabel("Sugestao");  //label 2 for fSugestao
	                l3.setBounds(30,90, 100,30); // label para autor
	                //lbAutor = new JLabel ("Autor");
	                 //lbAutor.setBounds(30, 120, 100, 30);
	                //set text field for book name
	                JTextArea F_fReclamacao = new JTextArea();
	                F_fReclamacao.setBounds(110, 15, 200, 30);
	                
	                //set text field for fSatisfacao 
	                 JComboBox <String> cbSatisfacao = new JComboBox(); // combobox para generos
	                 cbSatisfacao.addItem("Muito insatisfeito ");
	                 cbSatisfacao.addItem("Insatisfeito");
	                 cbSatisfacao.addItem("Satisfeito");
	                 cbSatisfacao.addItem("Muito satisfeito");
	                //JTextField F_fSatisfacao=new JTextField();
	                 cbSatisfacao.setBounds(110, 53, 200, 30);
	                //set text field for fSugestao
	                JTextArea F_fSugestao=new JTextArea();
	                F_fSugestao.setBounds(110, 90, 200, 30);
	                         
	                JRadioButton r1=new JRadioButton(" SIM");    
	                JRadioButton r2=new JRadioButton(" NAO"); 
	                JLabel l4 ;
	               l4 = new JLabel("Identificar-se?");
	            	l4.setBounds(40,120,100,30);
	                
	                r1.setBounds(130,125,100,30);    
	                r2.setBounds(130,145,100,30);    
	                ButtonGroup bg=new ButtonGroup();    
	                bg.add(r1);
	                bg.add(r2);    
	                
	           //     JTextArea F_idUser=new JTextArea();
	             //   F_idUser.setBounds(110, 110, 200, 30);
	                JButton create_but=new JButton("Submit");//creating instance of JButton to submit details  
	                create_but.setBounds(130,200,80,25);//x axis, y axis, width, height 
	                create_but.addActionListener(new ActionListener() {
	                	 String nomeUsuario = null;
	                    public void actionPerformed(ActionEvent e){
	                    // assign the book name, fSatisfacao, fSugestao
	                    String fReclamacao = F_fReclamacao.getText();
	                    String fSatisfacao = String.valueOf(cbSatisfacao.getSelectedItem());
	                    String fSugestao = F_fSugestao.getText();
	                //    String autor = txtAutor.getText();
	                    //convert fSugestao of integer to int
	                 //  int fSugestao_int = Integer.parseInt(fSugestao);
	                     
	                    Connection connection = Connect.connect();
	                    String sql=("INSERT INTO FEEDBACK (RECLAMACAO,SATISFACAO,SUGESTAO,USUARIO) VALUES (?,?,?,?)");
	                    try {
	                        PreparedStatement stmt = connection.prepareStatement(sql);	
	                 //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	                     stmt.executeUpdate("USE LIBRARY");
	         
	                     stmt.setString(1, fReclamacao);
	                     stmt.setString(2, fSatisfacao);
	                     stmt.setString(3, fSugestao);
	                    
	                     
	                     int UID_int = Integer.parseInt(UID); //Pass user ID
	                     try {
	 	                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	 	                     stmt1.executeUpdate("USE LIBRARY");
	 	                     
	 	                     Connection connection2 = Connect.connect(); 
	 	                     Statement stmt2 = connection2.createStatement();
	 	                     stmt2.executeUpdate("USE LIBRARY");                
	 	                    ResultSet rs2 = stmt2.executeQuery("SELECT USERNAME FROM USERS WHERE UID="+ UID_int); //seleciona o email do user com base no seu  id
	 	                    String nomeUser =null; 
	 	                    while (rs2.next()) {
	 	                    	nomeUser = rs2.getString(1);
	 	                          
	 	                    	nomeUsuario = nomeUser;
	 	                       }
	 	                    
	                     }
	 	                   catch (SQLException e1) {
		                        // TODO Auto-generated catch block
		                         JOptionPane.showMessageDialog(null, e1);
		                    }  
	                     if(r1.isSelected()){

	                    	

	                    	   }else if( r2.isSelected()){

	                    		   nomeUsuario = "Anonimo";

	                    	   }
	                     stmt.setString(4, nomeUsuario);
	                     JOptionPane.showMessageDialog(null, nomeUsuario);
	                     stmt.executeUpdate();
	                     JOptionPane.showMessageDialog(null,"Feedback enviado!");
	                     JOptionPane.showMessageDialog(null,UID_int);
	                     g.dispose();
	                      
	                    }
	                     
	                    catch (SQLException e1) {
	                        // TODO Auto-generated catch block
	                         JOptionPane.showMessageDialog(null, e1);
	                    }   
	                     
	                    }
	                     
	                });
	                                 
	                    g.add(l3);
	                    g.add(create_but);
	                    g.add(l1);
	                    g.add(l2);
	                    g.add(F_fReclamacao);
	                    g.add(cbSatisfacao);
	                    g.add(F_fSugestao);
	                    g.add(r1);
	                    g.add(r2); 
	                    g.add(l4);
	                   // g.add(F_idUser);
	                 //   g.add(txtAutor);
	                    //g.add(lbAutor);
	                    g.setSize(350,200);//400 width and 500 height  
	                    g.setLayout(null);//using no layout managers  
	                    g.setVisible(true);//making the frame visible 
	                    g.setLocationRelativeTo(null);
	                             
	    }
	    });
	     
	     
	    f.add(my_book); //add my books
	    f.add(view_but); // add view books
	    f.setSize(400,400);//400 width and 500 height  
	    f.setLayout(null);//using no layout managers  
	    f.setVisible(true);//making the frame visible 
	    f.setLocationRelativeTo(null);
	    f.add(add_feedback);
	    }
}

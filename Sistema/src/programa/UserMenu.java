package programa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//import com.itextpdf.text.Font;

import net.proteanit.sql.DbUtils;

public class UserMenu {
	public static void user_menu(String UID) {
	     
	     //teste 30/04
	    JFrame f=new JFrame("Funcoes de usuario"); //Da titulo ao formulario
	    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //
	    JButton btVerLivros=new JButton("Ver livros");//cria uma nova instancia do Jbutton
	    btVerLivros.setBounds(100, 200, 180, 25);//
	    btVerLivros.addActionListener((ActionListener) new ActionListener() { 
	        public void actionPerformed(ActionEvent e){
	             
	            JFrame f = new JFrame("Livros disponiveis"); //Ver livros armazenados na base de dados
	            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             
	             
	            Connection connection = Connect.connect();
	            String sql="select * from livros"; //seleciona os dados na base de dados 
	            try {
	                Statement stmt = (Statement) connection.createStatement(); //Conecta a base dados
	                 ((java.sql.Statement) stmt).executeUpdate("USE LIBRARY"); // usa a base de dados
	                stmt=(Statement) connection.createStatement();
	                ResultSet rs=((java.sql.Statement) stmt).executeQuery(sql);
	                JTable book_list= new JTable(); // mostra os dados em forma de tabela
	                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
	                  
	                JScrollPane scrollPane = new JScrollPane(book_list); //activa scroll bar
	 
	                f.add(scrollPane); //adiciona scroll bar
	                f.setSize(800, 500); //defie dimensoes do formulario ver livros
	                f.setVisible(true);
	                f.setLocationRelativeTo(null);
	            } catch (SQLException e1) {
	                // TODO Auto-generated catch block
	                 JOptionPane.showMessageDialog(null, e1);
	            }               
	             
	    }
	    }
	    );
	     
	    JButton btMeusLivros=new JButton("Meus livros");// 
	    btMeusLivros.setBounds(300, 200, 180, 25);// 
	    btMeusLivros.addActionListener(new ActionListener() { //acao do botao meus livross
	        public void actionPerformed(ActionEvent e){
	             
	               
	            JFrame f = new JFrame("Meus livros	"); //Ver livros alugados pelo usuario
	            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            int UID_int = Integer.parseInt(UID); //Passa o ID do usuario
	 
	     
	            Connection connection = Connect.connect(); //connecta a base de dados
	            //retrieve data
	            String sql="select distinct aluguer.*,livros.nomel,livros.genero,livros.preco from aluguer,livros " + "where ((aluguer.uid=" + UID_int + ") and (livros.bid in (select bid from aluguer where aluguer.uid="+UID_int+"))) group by iid";
	            String sql1 = "select bid from aluguer where uid="+UID_int;
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
	                f.setSize(800, 500); //set dimensions of my books frame
	                f.setVisible(true);
	                f.setLocationRelativeTo(null);
	            } catch (SQLException e1) {
	                // TODO Auto-generated catch block
	                 JOptionPane.showMessageDialog(null, e1);
	            }               
	                 
	    }
	    }
	    );
	    JButton btFeedback=new JButton("Adicionar feedback"); //creating instance of JButton for adding books
	    btFeedback.setBounds(500, 200, 180, 25); 
	     
	    btFeedback.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	                //set frame wot enter book details
	                JFrame g = new JFrame("Introduza detalhes do livro");
	                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                // set labels
	                JLabel l1,l2,l3, lbAutor; 
	               
	                l1=new JLabel("Reclaamacao");  //lebel 1 para reclamacao
	                l1.setBounds(230, 15, 100, 30); 
	                l1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	                l1.setForeground(Color.CYAN);
	                 
	                 
	                l2=new JLabel("Satisfacao");  //label 2 para fSatisfacao
	                l2.setBounds(230, 65, 100, 30);
	                l2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	                l2.setForeground(Color.CYAN);
	                 
	                l3=new JLabel("Sugestao");  //label 2 para fSugestao
	                l3.setBounds(230, 115, 100, 30);
	                l3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	                l3.setForeground(Color.CYAN);
	                
	               
	                JTextArea F_fReclamacao = new JTextArea();
	                F_fReclamacao.setBounds(350, 15, 200, 30);
	                
	                //set text field for fSatisfacao 
	                 JComboBox <String> cbSatisfacao = new JComboBox(); // combobox para generos
	                 cbSatisfacao.addItem("Muito insatisfeito ");
	                 cbSatisfacao.addItem("Insatisfeito");
	                 cbSatisfacao.addItem("Satisfeito");
	                 cbSatisfacao.addItem("Muito satisfeito");
	                //JTextField F_fSatisfacao=new JTextField();
	                 cbSatisfacao.setBounds(350, 65, 200, 30);
	                //set text field for fSugestao
	                JTextArea F_fSugestao=new JTextArea();
	                F_fSugestao.setBounds(350, 115, 200, 30);
	                         
	                JRadioButton r1=new JRadioButton(" SIM");    
	                JRadioButton r2=new JRadioButton(" NAO"); 
	                JLabel l4 ;
	               l4 = new JLabel("Identificar-se?");
	            	l4.setBounds(230, 165, 100, 30);
	            	l4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	                l4.setForeground(Color.CYAN);
	                
	            	r1.setBounds(360, 165, 80, 30);
	                r1.setBackground(Color.CYAN);   
	                r2.setBounds(460, 165, 80, 30);
	                r2.setBackground(Color.CYAN);    
	                
	                ButtonGroup bg=new ButtonGroup();    
	                bg.add(r1);
	                bg.add(r2);    
	                
	           //     JTextArea F_idUser=new JTextArea();
	             //   F_idUser.setBounds(110, 110, 200, 30);
	                JButton create_but=new JButton("retornar");//creating instance of JButton to submit details  
	                create_but.setBounds(330, 250, 80, 25);
	                create_but.setBackground(Color.CYAN);


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
	                    
	                     
	                     int UID_int = Integer.parseInt(UID); //Passa  ID do usuario
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
	                    
	                     stmt.executeUpdate();
	                     
	                     if(String.valueOf(cbSatisfacao.getSelectedItem()).equals("Muito insatisfeito ")) {
	                         
	                      // MULTA cons2
		                     String sql2=("update relatorio3 set M_INSA = ? where R3ID = 1");
		            
			                    int rl6 =0;
			           
			                    try {
			                    	try {
				 	                    Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				 	                     stmt7.executeUpdate("USE LIBRARY");
				 	                     int rid =1;
				 	                     Connection connection7 = Connect.connect(); 
				 	                     Statement stmt8 = connection7.createStatement();
				 	                     stmt8.executeUpdate("USE LIBRARY");                
				 	                    ResultSet rs7 = stmt8.executeQuery("SELECT M_INSA FROM relatorio3 WHERE R3ID="+rid); //seleciona o numero de usuarios muito insatisfeitos
				 	                    String m_ins =null; 
				 	                    while (rs7.next()) {
				 	                    	m_ins = rs7.getString(1);
				 	                          
				 	                    	rl6 = Integer.parseInt(m_ins);
				 	                    
				 	                       }
				 	                    
				 	                  
				 	                   //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
				 	                    
				                     }
				 	                   catch (SQLException e1) {
					                        // TODO Auto-generated catch block
					                         JOptionPane.showMessageDialog(null, e1);
					                    }  
			                  //  int rel1 = rl1+1;
			                        PreparedStatement stmt9 = connection.prepareStatement(sql2);	
			                 //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			                     stmt9.executeUpdate("USE LIBRARY");
			         
			                     stmt9.setInt(1, ++rl6);
			                     //stmt9.setInt(2, 1);
			                     stmt9.executeUpdate();
			
			                    
			                     g.dispose();
			                      
			                    }
			                     
			                    catch (SQLException e1) {
			                        // TODO Auto-generated catch block
			                         JOptionPane.showMessageDialog(null, e1);
			                    }
			                    // fim adicionar numero feed m ins
			                    
			                    // feed total
			                    // MULTA cons2
			                     String sql3=("update relatorio3 set FEED_TOTAL = ? where R3ID = 1");
			            
				                    int rl7 =0;
				           
				                    try {
				                    	try {
					 	                    Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					 	                     stmt7.executeUpdate("USE LIBRARY");
					 	                     int rid =1;
					 	                     Connection connection7 = Connect.connect(); 
					 	                     Statement stmt8 = connection7.createStatement();
					 	                     stmt8.executeUpdate("USE LIBRARY");                
					 	                    ResultSet rs7 = stmt8.executeQuery("SELECT FEED_TOTAL FROM relatorio3 WHERE R3ID="+rid); //seleciona o numero de usuarios que deram feedback
					 	                    String m_ins =null; 
					 	                    while (rs7.next()) {
					 	                    	m_ins = rs7.getString(1);
					 	                          
					 	                    	rl6 = Integer.parseInt(m_ins);
					 	                    	
					 	                       }
					 	                    
					 	                  
					 	                   //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
					 	                    
					                     }
					 	                   catch (SQLException e1) {
						                        // TODO Auto-generated catch block
						                         JOptionPane.showMessageDialog(null, e1);
						                    }  
				                  //  int rel1 = rl1+1;
				                        PreparedStatement stmt9 = connection.prepareStatement(sql3);	
				                 //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				                     stmt9.executeUpdate("USE LIBRARY");
				         
				                     stmt9.setInt(1, ++rl6);
				                     //stmt9.setInt(2, 1);
				                     stmt9.executeUpdate();
				                    
				                    
				                     g.dispose();
				                      
				                    }
				                     
				                    catch (SQLException e1) {
				                        // TODO Auto-generated catch block
				                         JOptionPane.showMessageDialog(null, e1);
				                    }
				                 
			                    //* fim feed total
			                    
	                     }else {
	                    	 if(String.valueOf(cbSatisfacao.getSelectedItem()).equals("Insatisfeito")) {
	                    		
	                    		// Insatisfeitos 
			                     String sql2=("update relatorio3 set INSA = ? where R3ID = 1");
			            
				                    int rl6 =0;
				           
				                    try {
				                    	try {
					 	                    Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					 	                     stmt7.executeUpdate("USE LIBRARY");
					 	                     int rid =1;
					 	                     Connection connection7 = Connect.connect(); 
					 	                     Statement stmt8 = connection7.createStatement();
					 	                     stmt8.executeUpdate("USE LIBRARY");                
					 	                    ResultSet rs7 = stmt8.executeQuery("SELECT INSA FROM relatorio3 WHERE R3ID="+rid); //seleciona o numero de usuarios  insatisfeitos
					 	                    String m_ins =null; 
					 	                    while (rs7.next()) {
					 	                    	m_ins = rs7.getString(1);
					 	                          
					 	                    	rl6 = Integer.parseInt(m_ins);
					 	                       }
					 	                    
					 	                  
					 	                   //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
					 	                    
					                     }
					 	                   catch (SQLException e1) {
						                        // TODO Auto-generated catch block
						                         JOptionPane.showMessageDialog(null, e1);
						                    }  
				                  //  int rel1 = rl1+1;
				                        PreparedStatement stmt9 = connection.prepareStatement(sql2);	
				                 //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				                     stmt9.executeUpdate("USE LIBRARY");
				         
				                     stmt9.setInt(1, ++rl6);
				                     //stmt9.setInt(2, 1);
				                     stmt9.executeUpdate();
				                    
				                     g.dispose();
				                      
				                    }
				                     
				                    catch (SQLException e1) {
				                        // TODO Auto-generated catch block
				                         JOptionPane.showMessageDialog(null, e1);
				                    }
				                    // fim adicionar numero feed m ins
				                    
				                    // feed total
				                    // MULTA cons2
				                     String sql3=("update relatorio3 set FEED_TOTAL = ? where R3ID = 1");
				            
					                    int rl7 =0;
					           
					                    try {
					                    	try {
						 	                    Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
						 	                     stmt7.executeUpdate("USE LIBRARY");
						 	                     int rid =1;
						 	                     Connection connection7 = Connect.connect(); 
						 	                     Statement stmt8 = connection7.createStatement();
						 	                     stmt8.executeUpdate("USE LIBRARY");                
						 	                    ResultSet rs7 = stmt8.executeQuery("SELECT FEED_TOTAL FROM relatorio3 WHERE R3ID="+rid); //seleciona o numero de usuarios que deram feedback
						 	                    String m_ins =null; 
						 	                    while (rs7.next()) {
						 	                    	m_ins = rs7.getString(1);
						 	                          
						 	                    	rl6 = Integer.parseInt(m_ins);
						 	                   
						 	                       }
						 	                    
						 	                  
						 	                   //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
						 	                    
						                     }
						 	                   catch (SQLException e1) {
							                        // TODO Auto-generated catch block
							                         JOptionPane.showMessageDialog(null, e1);
							                    }  
					                  //  int rel1 = rl1+1;
					                        PreparedStatement stmt9 = connection.prepareStatement(sql3);	
					                 //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					                     stmt9.executeUpdate("USE LIBRARY");
					         
					                     stmt9.setInt(1, ++rl6);
					                     //stmt9.setInt(2, 1);
					                     stmt9.executeUpdate();
					                   
					                    
					                     g.dispose();
					                      
					                    }
					                     
					                    catch (SQLException e1) {
					                        // TODO Auto-generated catch block
					                         JOptionPane.showMessageDialog(null, e1);
					                    }
					                 
				                    //* fim feed total
				                    
	                    	 }else {
	                    		 if(String.valueOf(cbSatisfacao.getSelectedItem()).equals("Satisfeito")) { 
	                    			// satisfeitos 
				                     String sql2=("update relatorio3 set SATISF = ? where R3ID = 1");
				            
					                    int rl6 =0;
					           
					                    try {
					                    	try {
						 	                    Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
						 	                     stmt7.executeUpdate("USE LIBRARY");
						 	                     int rid =1;
						 	                     Connection connection7 = Connect.connect(); 
						 	                     Statement stmt8 = connection7.createStatement();
						 	                     stmt8.executeUpdate("USE LIBRARY");                
						 	                    ResultSet rs7 = stmt8.executeQuery("SELECT SATISF FROM relatorio3 WHERE R3ID="+rid); //seleciona o numero de usuarios satisfeitos
						 	                    String satisf =null; 
						 	                    while (rs7.next()) {
						 	                    	satisf = rs7.getString(1);
						 	                          
						 	                    	rl6 = Integer.parseInt(satisf);
						 	                    	
						 	                       }
						 	                    
						 	                  
						 	                   //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
						 	                    
						                     }
						 	                   catch (SQLException e1) {
							                        // TODO Auto-generated catch block
							                         JOptionPane.showMessageDialog(null, e1);
							                    }  
					                  //  int rel1 = rl1+1;
					                        PreparedStatement stmt9 = connection.prepareStatement(sql2);	
					                 //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					                     stmt9.executeUpdate("USE LIBRARY");
					         
					                     stmt9.setInt(1, ++rl6);
					                     //stmt9.setInt(2, 1);
					                     stmt9.executeUpdate();
					                    
					                    
					                     g.dispose();
					                      
					                    }
					                     
					                    catch (SQLException e1) {
					                        // TODO Auto-generated catch block
					                         JOptionPane.showMessageDialog(null, e1);
					                    }
					                    // fim adicionar numero feed satisf
					                    
					                    // feed total
					                    // MULTA cons2
					                     String sql3=("update relatorio3 set FEED_TOTAL = ? where R3ID = 1");
					            
						                    int rl7 =0;
						           
						                    try {
						                    	try {
							 	                    Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
							 	                     stmt7.executeUpdate("USE LIBRARY");
							 	                     int rid =1;
							 	                     Connection connection7 = Connect.connect(); 
							 	                     Statement stmt8 = connection7.createStatement();
							 	                     stmt8.executeUpdate("USE LIBRARY");                
							 	                    ResultSet rs7 = stmt8.executeQuery("SELECT FEED_TOTAL FROM relatorio3 WHERE R3ID="+rid); //seleciona o numero de usuarios que deram feedback
							 	                    String m_ins =null; 
							 	                    while (rs7.next()) {
							 	                    	m_ins = rs7.getString(1);
							 	                          
							 	                    	rl6 = Integer.parseInt(m_ins);
							 	                    	
							 	                       }
							 	                    
							 	                  
							 	                   //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
							 	                    
							                     }
							 	                   catch (SQLException e1) {
								                        // TODO Auto-generated catch block
								                         JOptionPane.showMessageDialog(null, e1);
								                    }  
						                  //  int rel1 = rl1+1;
						                        PreparedStatement stmt9 = connection.prepareStatement(sql3);	
						                 //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
						                     stmt9.executeUpdate("USE LIBRARY");
						         
						                     stmt9.setInt(1, ++rl6);
						                     //stmt9.setInt(2, 1);
						                     stmt9.executeUpdate();
						                     
						                    
						                     g.dispose();
						                      
						                    }
						                     
						                    catch (SQLException e1) {
						                        // TODO Auto-generated catch block
						                         JOptionPane.showMessageDialog(null, e1);
						                    }
						                 
					                    //* fim feed total
		  

	                    		 
	                    	 }else {
	                    		 if(String.valueOf(cbSatisfacao.getSelectedItem()).equals("Muito satisfeito")) { 
	                    			// satisfeitos 
				                     String sql2=("update relatorio3 set M_SATISF = ? where R3ID = 1");
				            
					                    int rl6 =0;
					           
					                    try {
					                    	try {
						 	                    Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
						 	                     stmt7.executeUpdate("USE LIBRARY");
						 	                     int rid =1;
						 	                     Connection connection7 = Connect.connect(); 
						 	                     Statement stmt8 = connection7.createStatement();
						 	                     stmt8.executeUpdate("USE LIBRARY");                
						 	                    ResultSet rs7 = stmt8.executeQuery("SELECT M_SATISF FROM relatorio3 WHERE R3ID="+rid); //seleciona o numero de usuarios muito satisfeitos
						 	                    String mSatisf =null; 
						 	                    while (rs7.next()) {
						 	                    	mSatisf = rs7.getString(1);
						 	                          
						 	                    	rl6 = Integer.parseInt(mSatisf);
						 	                    
						 	                       }
						 	                    
						 	                  
						 	                   //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
						 	                    
						                     }
						 	                   catch (SQLException e1) {
							                        // TODO Auto-generated catch block
							                         JOptionPane.showMessageDialog(null, e1);
							                    }  
					                  //  int rel1 = rl1+1;
					                        PreparedStatement stmt9 = connection.prepareStatement(sql2);	
					                 //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					                     stmt9.executeUpdate("USE LIBRARY");
					         
					                     stmt9.setInt(1, ++rl6);
					                     //stmt9.setInt(2, 1);
					                     stmt9.executeUpdate();
					                     
					                    
					                     g.dispose();
					                      
					                    }
					                     
					                    catch (SQLException e1) {
					                        // TODO Auto-generated catch block
					                         JOptionPane.showMessageDialog(null, e1);
					                    }
					                    // fim adicionar numero feed satisf
					                    
					                    // feed total
					                    // MULTA cons2
					                     String sql3=("update relatorio3 set FEED_TOTAL = ? where R3ID = 1");
					            
						                    int rl7 =0;
						           
						                    try {
						                    	try {
							 	                    Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
							 	                     stmt7.executeUpdate("USE LIBRARY");
							 	                     int rid =1;
							 	                     Connection connection7 = Connect.connect(); 
							 	                     Statement stmt8 = connection7.createStatement();
							 	                     stmt8.executeUpdate("USE LIBRARY");                
							 	                    ResultSet rs7 = stmt8.executeQuery("SELECT FEED_TOTAL FROM relatorio3 WHERE R3ID="+rid); //seleciona o numero de usuarios que deram feedback
							 	                    String m_ins =null; 
							 	                    while (rs7.next()) {
							 	                    	m_ins = rs7.getString(1);
							 	                          
							 	                    	rl6 = Integer.parseInt(m_ins);
							 	                    	
							 	                       }
							 	                    
							 	                  
							 	                   //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
							 	                    
							                     }
							 	                   catch (SQLException e1) {
								                        // TODO Auto-generated catch block
								                         JOptionPane.showMessageDialog(null, e1);
								                    }  
						                  //  int rel1 = rl1+1;
						                        PreparedStatement stmt9 = connection.prepareStatement(sql3);	
						                 //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
						                     stmt9.executeUpdate("USE LIBRARY");
						         
						                     stmt9.setInt(1, ++rl6);
						                     //stmt9.setInt(2, 1);
						                     stmt9.executeUpdate();
						                    
						                    
						                     g.dispose();
						                      
						                    }
						                     
						                    catch (SQLException e1) {
						                        // TODO Auto-generated catch block
						                         JOptionPane.showMessageDialog(null, e1);
						                    }
						                 
					                    //* fim feed total

	                    			 
	                    			 
	                    		 }
	                    	 }
	                    		  
	                    	 }
	                     }
	                     JOptionPane.showMessageDialog(null,"Feedback enviado!");
	                    
	                     g.dispose();
	                      
	                    }
	                     
	                    catch (SQLException e1) {
	                        // TODO Auto-generated catch block
	                         JOptionPane.showMessageDialog(null, e1);
	                    }   
	                     
	                    }
	                     
	                });
	                         
	                
	                ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
	                ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");
	                
	               
	                
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
	                    
	                    g.add(new JLabel(fundo));
		                g.pack();
	                   // g.add(F_idUser);
	                 //   g.add(txtAutor);
	                    //g.add(lbAutor);
	                    g.setSize(800,500);//400 width and 500 height  
	                    g.setLayout(null);//using no layout managers  
	                    g.setVisible(true);//making the frame visible 
	                    g.setLocationRelativeTo(null);
	                    g.setIconImage(icone.getImage());
	                             
	    }
	    });
	     
	    
	    JLabel txt = new JLabel("BIBLIOTECA SKY"); //CIRAR A LABEL DO TITULO
        txt.setBounds(280, 15, 300, 30); //MARGEM A ESQUERDA, MARGEM ACIMA, LARGURA, ALTURA 
        txt.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        txt.setForeground(Color.CYAN);
        
        

        ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
        ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");
        btVerLivros.setIcon(new ImageIcon("img/ibooks.png"));
        btMeusLivros.setIcon(new ImageIcon("img/ivi.png"));
        btFeedback.setIcon(new ImageIcon("img/iissue.png"));
        

        btVerLivros.setBackground(Color.WHITE);
        btMeusLivros.setBackground(Color.WHITE);
        btFeedback.setBackground(Color.WHITE);
	     
	    f.add(btMeusLivros); //add my books
	    f.add(btVerLivros); // add view books
        f.add(txt);
        f.add(btFeedback);
	    f.setSize(800,500);//400 width and 500 height  
	    f.add(new JLabel(fundo));
        f.pack();
        f.setIconImage(icone.getImage());
	    f.setLayout(null);//using no layout managers  
	    f.setVisible(true);//making the frame visible 
	    f.setLocationRelativeTo(null);
	    }
}

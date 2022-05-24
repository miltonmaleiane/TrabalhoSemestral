package programa;

//import AdminMenu;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//import com.mysql.cj.xdevapi.Statement;

public class login {
	public static void login() {
	     
	    JFrame f=new JFrame("Login");//  
	    JLabel l1,l2, titulo;  
	    
	    titulo = new JLabel("BIBLIOTECA SKY");  //CIRAR A LABEL DO TITULO
        titulo.setBounds(280, 15, 300, 30); //MARGEM A ESQUERDA, MARGEM ACIMA, LARGURA, ALTURA 
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        titulo.setForeground(Color.CYAN);
	    
        
        
        ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
        ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

        l1 = new JLabel("Usuario");  //Cr
        l1.setBounds(300, 115, 100, 30); // 
        l1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        l1.setForeground(Color.CYAN);

        l2 = new JLabel("Senha");  //Criar label para password
        l2.setBounds(300, 215, 100, 30);
        l2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        l2.setForeground(Color.CYAN);

        JTextField txtUsuario = new JTextField(); //Criar text field para username
        txtUsuario.setBounds(300, 150, 200, 30);

        JPasswordField txtPassword = new JPasswordField(); //Criar text field para password
        txtPassword.setBounds(300, 250, 200, 30);

        JButton btLogin = new JButton("Login");//
        btLogin.setBounds(350, 350, 80, 25);//Dimensoes do botao
        btLogin.setBackground(Color.CYAN);
        btLogin.setForeground(Color.BLACK);
        btLogin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        
        
	    
        btLogin.addActionListener(new ActionListener() {  //acao
	         
	        public void actionPerformed(ActionEvent e){ 
	 
	        String username = txtUsuario.getText(); //"
	        String password = txtPassword.getText(); //
	         
	        if(username.equals("")) //
	        {
	            JOptionPane.showMessageDialog(null,"Please enter username"); //
	        } 
	        else if(password.equals("")) //If password is null
	        {
	            JOptionPane.showMessageDialog(null,"Please enter password"); 
	        }
	        else { //
	            //System.out.println("Login connect");
	            Connection connection= Connect.connect();  //Connecta a base de dados
	            try
	            {
	            Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	              ((java.sql.Statement) stmt).executeUpdate("USE LIBRARY"); //
	              String st = ("SELECT * FROM USERS WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"'"); //
	              ResultSet rs = ((java.sql.Statement) stmt).executeQuery(st); //Execute query
	              if(rs.next()==false) { //Move pointer below
	                  System.out.print("No user");  
	                  JOptionPane.showMessageDialog(null," Username/Password errado!"); //
	 
	              }
	              else {
	                  f.dispose();
	                rs.beforeFirst();  //
	                while(rs.next())
	                {
	                  String admin = rs.getString("ADMIN"); //
	                  //System.out.println(admin);
	                  String UID = rs.getString("UID"); //
	                  if(admin.equals("1")) { //
	                      AdminMenu.admin_menu(); //redirecionar ao   menu admin
	                  }
	                  else{
	                      UserMenu.user_menu(UID); //redirect ao  menu usuario para o ID em particular
	                  }
	              }
	              }
	            }
	            catch (Exception ex) {
	                 ex.printStackTrace();
	        }
	        }
	    }               
	    });
	 
	     
	    f.add(txtPassword); //adicionar password
        f.add(btLogin);// 
        f.add(txtUsuario);  
        f.add(l1);  // adicionar label para username
        f.add(l2);
        f.add(titulo); // adicionar label para password
        f.add(new JLabel(fundo));
        f.pack();

        f.setSize(800, 500);//  
        f.setLayout(null);//sem usar layout managers  
        f.setVisible(true);//
        f.setLocationRelativeTo(null);

        //MUDAR O ICONE
        f.setIconImage(icone.getImage());
//        login_but.setIcon(new ImageIcon("img/n.png"));

	     
	}

}

package programa;

import java.awt.event.ActionEvent;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import net.proteanit.sql.DbUtils;
import programa.Principal.ex;
import java.awt.Color;
import java.awt.Font;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
// teste do git

public class AdminMenu {

    public static void admin_menu() {

        ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
        ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

        //commit teste 4 git
        JFrame f = new JFrame("Janela do administrador"); //titulo do formulario admin
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //

        int cadUsers;
        int cadLivros;


        JButton btVerLivros = new JButton("Ver livros");//cria instancia do JButton para ler livros
        btVerLivros.setBounds(205, 100, 180, 25);
        btVerLivros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
                JFrame f = new JFrame("Livros disponíveis");
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Connection connection = Connect.connect(); //connecta a base de dados
                String sql = "select * from livros"; //selecio na todos livros
                try {
                    Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt.executeUpdate("USE LIBRARY"); //usar a base de dados
                    stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    JTable book_list = new JTable(); //ver dados em forma de tabela
                    book_list.setModel(DbUtils.resultSetToTableModel(rs));

                    JScrollPane scrollPane = new JScrollPane(book_list);

                    JButton novobotao = new JButton("Retornar");
                    novobotao.setIcon(new ImageIcon("img/iretorna.png"));
                    novobotao.addActionListener(new ActionListener(){
                   	 public void actionPerformed (ActionEvent e) {
                   		f.dispose();
                   		  AdminMenu.admin_menu();
                   		
                   	
                   	 }
                    });
                    novobotao.setBackground(Color.WHITE);
                    novobotao.setBounds(310, 400, 180, 25);

                    f.add(novobotao);
                    f.add(scrollPane); //adiciona o scrollpane
                    f.setSize(800, 500); //define tamanho do grame
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e1) {
                    
                    JOptionPane.showMessageDialog(null, e1);
                }

            }
        }
        );

        JButton btVerUsuarios = new JButton("Ver Usuários");//cria instancia do botao ver usuarios
        btVerUsuarios.setBounds(5, 100, 180, 25);
        btVerUsuarios.addActionListener(new ActionListener() { //acao do botao
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
                JFrame f = new JFrame("Users List");
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                Connection connection = Connect.connect();
                String sql = "select * from users"; //seleciona todos usuarios
                try {
                    Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt.executeUpdate("USE LIBRARY"); //usar a base de dados
                    stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    JTable book_list = new JTable();
                    book_list.setModel(DbUtils.resultSetToTableModel(rs));

                    JScrollPane scrollPane = new JScrollPane(book_list);

                    JButton novobotao = new JButton("Retornar");
                    novobotao.addActionListener(new ActionListener(){
                    	 public void actionPerformed (ActionEvent e) {
                    		  f.dispose();
                    		  AdminMenu.admin_menu();
                    	
                    	 }
                     });
                    novobotao.setIcon(new ImageIcon("img/iretorna.png"));
                    novobotao.setBackground(Color.WHITE);
                    novobotao.setBounds(310, 400, 180, 25);

                    f.add(novobotao);
                    f.add(scrollPane); //adiciona um scrollpane
                    f.setSize(800, 500); //define tamanho do frame
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

            }
        }
        );
// ver feedback

        JButton btFeedback = new JButton("Ver Feedbacks");//criar instancia do botao ver feedbacks
        btFeedback.setBounds(405, 180, 180, 25);
        btFeedback.addActionListener(new ActionListener() { //acao do botao
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
                JFrame f = new JFrame("Lista Feedbacks");
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Connection connection = Connect.connect();
                String sql = "select * from feedback"; //seleciona todos usuarios
                try {
                    Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt.executeUpdate("USE LIBRARY"); //usar a base de dados
                    stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    JTable book_list = new JTable();
                    book_list.setModel(DbUtils.resultSetToTableModel(rs));
                    //mention scroll bar
                    JScrollPane scrollPane = new JScrollPane(book_list);

                    JButton novobotao = new JButton("Retornar");
                    novobotao.addActionListener(new ActionListener(){
                   	 public void actionPerformed (ActionEvent e) {
                   		  f.dispose();
                   		  AdminMenu.admin_menu();
                   	
                   	 }
                    });
                    novobotao.setIcon(new ImageIcon("img/iretorna.png"));
                    novobotao.setBackground(Color.WHITE);
                    novobotao.setBounds(310, 300, 180, 25);

                    f.add(novobotao);
                    f.add(scrollPane); //adiciona o scrollpane
                    f.setSize(800, 400); //define tamanho do frame ver feedbacks
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

            }
        }
        );
// fim ver feedback

        JButton btAlugados = new JButton("Ver livros Alugados");//cria instancia do botao ver livros alugados
        btAlugados.setBounds(405, 100, 180, 25);
        btAlugados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
                JFrame f = new JFrame("Lista de usuarios");

                Connection connection = Connect.connect();
                String sql = "select * from ALUGUER";
                try {
                    Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt.executeUpdate("USE LIBRARY");
                    stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    JTable book_list = new JTable();
                    book_list.setModel(DbUtils.resultSetToTableModel(rs));

                    JScrollPane scrollPane = new JScrollPane(book_list);

                    JButton novobotao = new JButton("Retornar");
                    novobotao.addActionListener(new ActionListener(){
                   	 public void actionPerformed (ActionEvent e) {
                   		  f.dispose();
                   		  AdminMenu.admin_menu();
                   	
                   	 }
                    });
                    novobotao.setIcon(new ImageIcon("img/iretorna.png"));
                    novobotao.setBackground(Color.WHITE);
                    novobotao.setBounds(560, 400, 180, 25);

                    f.add(novobotao);
                    f.add(scrollPane);
                    f.setSize(1300, 600);
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

            }
        }
        );

        //by host
        /*    JButton excluir_but=new JButton("View Deleted Books");//creating instance of JButton to view the issued books
	    excluir_but.setBounds(40,110,120,25);//x axis, y axis, width, height 
	    excluir_but.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	                 
	                JFrame f = new JFrame("Users List");
	                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                 JButton bt = new JButton("New bt");
	                 bt.setBounds(500, 200, 100, 200);
	                Connection connection = Connect.connect();
	                String sql="delect   from books where BID = ? ";
	             
	                try {
	                    Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	                     stmt.executeUpdate("USE LIBRARY");
	                    stmt=connection.createStatement();
	                    ResultSet rs=stmt.executeQuery(sql);
	                JTable book_list= new JTable();
	                    book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
	                     
	                    JScrollPane scrollPane = new JScrollPane(book_list); 
	                    f.add(bt);
	                    f.add(scrollPane);
	                    f.setSize(800, 400);
	                    f.setVisible(true);
	                    f.setLocationRelativeTo(null);
	                } catch (SQLException e1) {
	                    // TODO Auto-generated catch block
	                     JOptionPane.showMessageDialog(null, e1);
	                }       
	                             
	    }
	        }
	    );
	     
	    
	     ++by host  */
        //adicionar usuario
        JButton btAdicionarUsuario = new JButton("Adicionar Usuários"); //cria instancia do botao adicionar usuarios
        btAdicionarUsuario.setBounds(5, 20, 180, 25); //

        btAdicionarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
                JFrame g = new JFrame("Introduza Dados do Usuário"); //Frame para detalhes do usuario
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //Create label 
                JLabel l1, l2, l3, lbBI, lbResidencia;
                l1 = new JLabel("Nome");  //label 1 para username
                l1.setBounds(230, 15, 100, 30);
                l1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                l1.setForeground(Color.CYAN);

                l2 = new JLabel("Senha");  //label 2 para password
                l2.setBounds(230, 65, 100, 30);
                l2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                l2.setForeground(Color.CYAN);

                l3 = new JLabel("E-mail");  //label 3 para email
                l3.setBounds(230, 115, 100, 30);
                l3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                l3.setForeground(Color.CYAN);

                //textField para usuario
                JTextField txtUsuario = new JTextField();
                txtUsuario.setBounds(310, 15, 200, 30);

                //textfield para password
                JPasswordField F_pass = new JPasswordField();
                F_pass.setBounds(310, 65, 200, 30);
                //set text field for email
                JTextField F_email = new JTextField();
                F_email.setBounds(310, 115, 200, 30);
                // radio button para admin
                JRadioButton a1 = new JRadioButton("Admin");
                a1.setBounds(275, 275, 80, 30);
                a1.setBackground(Color.WHITE);

                // radio button para user
                JRadioButton a2 = new JRadioButton("Usuário");
                a2.setBounds(400, 275, 80, 30);
                a2.setBackground(Color.WHITE);

                //adicionar radio buttons
                ButtonGroup bg = new ButtonGroup();
                bg.add(a1);
                bg.add(a2);

                lbBI = new JLabel("NR BI"); // Label para BI
                lbBI.setBounds(230, 165, 100, 30);
                lbBI.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                lbBI.setForeground(Color.CYAN);

                lbResidencia = new JLabel("Residencia"); // label para residencia
                lbResidencia.setBounds(230, 215, 100, 30);
                lbResidencia.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                lbResidencia.setForeground(Color.CYAN);

                JTextField txtBI = new JTextField(); // textField para BI
                txtBI.setBounds(310, 165, 200, 30);

                JTextField txtResidencia = new JTextField();
                txtResidencia.setBounds(310, 215, 200, 30);

                JButton btAdicionarUser = new JButton("Adicionar");//
                btAdicionarUser.setBounds(290, 350, 180, 25);
                btAdicionarUser.setBackground(Color.WHITE);
                btAdicionarUser.setIcon(new ImageIcon("img/icerto.png"));

                btAdicionarUser.addActionListener(new ActionListener() {
                    int cadUsers;

                    public void actionPerformed(ActionEvent e) {
                        boolean vEmail = false; // valida email
                        boolean vUser = false;
                        boolean vPass = false;
                        boolean vRes = false;
                        boolean vBI = false;
                        //validacao do username

                        String username = txtUsuario.getText();
                        if (username.equals("")) {
                            JOptionPane.showMessageDialog(null, "Por favor introduza um Nome");
                            vUser = false;

                        } else {
                            vUser = true;

                        }
                        //* fim validacao email

                        //validacao password
                        String password = F_pass.getText();
                        if (password.equals("")) {
                            JOptionPane.showMessageDialog(null, "Por favor introduza uma Senha");
                            vPass = false;

                        } else {
                            vPass = true;

                        }
                        //* fim validacao password
                        // validar email
                        String email = F_email.getText();
                        String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
                        Pattern pattern = Pattern.compile(regx);

                        //Cria instancia do matcher   
                        Matcher matcher = pattern.matcher(email);
                        if (matcher.matches() == true) {

                            vEmail = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "E-mail inválido");
                            vEmail = false;
                        }

                        //
                        //validacao do BI
                        String nrBI = txtBI.getText();
                        if (nrBI.equals("")) {
                            JOptionPane.showMessageDialog(null, "Por favor introduza um número de BI");
                            vBI = false;

                        } else {
                            vBI = true;

                        }
                        //*fim da validacao do BI
                        //validacao da residencia
                        String residencia = txtResidencia.getText();
                        if (residencia.equals("")) {
                            JOptionPane.showMessageDialog(null, "Por favor introduza uma residencia");
                            vRes = false;

                        } else {
                            vRes = true;

                        }
                        //* fim da validacao da residencia
                        Boolean admin = false;

                        if (a1.isSelected()) {
                            admin = true;
                        }
                        if (vEmail == true && vUser == true && vPass == true && vRes == true && vBI == true) { // validacao dos campos
                            Connection connection = Connect.connect();

                            try {

                                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY");
                                stmt.executeUpdate("INSERT INTO USERS(USERNAME,PASSWORD,NUMERO_BI,RESIDENCIA,EMAIL,ADMIN) VALUES ('" + username + "','" + password + "','" + nrBI + "','" + residencia + "','" + email + "'," + admin + ")");
                                JOptionPane.showMessageDialog(null, "Usuario adicionado!");
                                ;
                                // adicionar numero usuarios
                                String sql = ("update relatorio1 set CAD_USERS = ? where R1ID = ?");
                                int rl1 = 0;
                                try {
                                    try {
                                        Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt1.executeUpdate("USE LIBRARY");
                                        int rid = 1;
                                        Connection connection2 = Connect.connect();
                                        Statement stmt2 = connection2.createStatement();
                                        stmt2.executeUpdate("USE LIBRARY");
                                        ResultSet rs2 = stmt2.executeQuery("SELECT CAD_USERS FROM relatorio1 WHERE R1ID=" + rid); //seleciona o email do user com base no seu  id
                                        String nomeUser = null;
                                        while (rs2.next()) {
                                            nomeUser = rs2.getString(1);

                                            rl1 = Integer.parseInt(nomeUser);
                                            //      JOptionPane.showMessageDialog(null, "num user: " + rl1);
                                        }

                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }
                                    //  int rel1 = rl1+1;
                                    PreparedStatement stmt1 = connection.prepareStatement(sql);
                                    //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                    stmt1.executeUpdate("USE LIBRARY");

                                    stmt1.setInt(1, ++rl1);
                                    stmt1.setInt(2, 1);
                                    stmt1.executeUpdate();

                                    g.dispose();
                                    AdminMenu.admin_menu();
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, e1);
                                }
                                // fim adicionar numero usuarios
                                g.dispose();

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Não registrado, introduza valores válidos");
                        }

                    }

                });
               

                ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
                ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

                JButton novobotao = new JButton("Cancelar");
                novobotao.addActionListener(new ActionListener(){
               	 public void actionPerformed (ActionEvent e) {
               		  g.dispose();
               		  AdminMenu.admin_menu();
               	
               	 }
                });
                novobotao.setIcon(new ImageIcon("img/icanc.png"));
                novobotao.setBackground(Color.WHITE);
                novobotao.setBounds(290, 400, 180, 25);

                g.add(novobotao);
                g.add(btAdicionarUser);
                g.add(a2);
                g.add(a1);
                g.add(l1);
                g.add(l2);
                g.add(l3);
                g.add(txtUsuario);
                g.add(F_pass);
                g.add(F_email);
                g.add(txtBI);
                g.add(txtResidencia);
                g.add(lbResidencia);
                g.add(lbBI);
                g.setSize(800, 500);//  
                g.add(new JLabel(fundo));
                g.pack();
                g.setIconImage(icone.getImage());
                g.setLayout(null);//sem layout managers  
                g.setVisible(true);//
                g.setLocationRelativeTo(null);

            }
        });

        // fim adicionar usuario 
        // botao sair
        JButton sair = new JButton("Sair"); //criar instancia do botao sair 
        sair.setBounds(350, 400, 100, 25);
     sair.addActionListener(new ActionListener(){
    	 public void actionPerformed (ActionEvent e) {
    		 int result = JOptionPane.showConfirmDialog(null,
    			        "Certeza que deseja sair ?",
    			        "Confirmar saida.", JOptionPane.YES_NO_CANCEL_OPTION);
    			if (result == JOptionPane.YES_OPTION) System.exit(0);
    	 }
     });
       // fim botao sair
          //  }
        //actualizar usuario
        JButton btAtualizarUsuario = new JButton("Actualizar Usuário"); // botao para actualizar
        btAtualizarUsuario.setBounds(5, 260, 180, 25); //dimensoes do botao

        btAtualizarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
                JFrame g = new JFrame("Introduza Dados do Usuário");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //Create label 
                JLabel l1, l2, l3, lbBI, lbResidencia, lbID;
                l1 = new JLabel("Usuário");  //label 1 para username
                l1.setBounds(30, 15, 100, 30);
                l1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                l1.setForeground(Color.CYAN);

                lbID = new JLabel("ID usuário");  //label 1 para ID do usuario
                lbID.setBounds(480, 15, 100, 30);
                lbID.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                lbID.setForeground(Color.CYAN);

                JTextField tfID = new JTextField();
                tfID.setBounds(580, 15, 200, 30);

                l2 = new JLabel("Senha");  //label 2 para password
                l2.setBounds(30, 65, 100, 30);
                l2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                l2.setForeground(Color.CYAN);

                l3 = new JLabel("E-mail");  //label 3 para email
                l3.setBounds(30, 115, 100, 30);
                l3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                l3.setForeground(Color.CYAN);

                //textfield para user name
                JButton j1, j2, j3, j4, j5;
                j1 = new JButton("Actualizar"); // BOTAO PARA ACTUALIZAR user
                j1.setBounds(340, 15, 100, 30);
                j1.setBackground(Color.CYAN);

                j2 = new JButton("Actualizar");//botao  actualizar password
                j2.setBounds(340, 65, 100, 30);
                j2.setBackground(Color.CYAN);

                j3 = new JButton("Actualizar"); //botao actualizar email
                j3.setBounds(340, 115, 100, 30);
                j3.setBackground(Color.CYAN);

                j4 = new JButton("Actualizar"); // botao actualizar residencia
                j4.setBounds(340, 165, 100, 30);
                j4.setBackground(Color.CYAN);

                j5 = new JButton("Actualizar"); //add_user botao atualizar  BI
                j5.setBounds(340, 215, 100, 30);
                j5.setBackground(Color.CYAN);

                JTextField tfUser = new JTextField();
                tfUser.setBounds(110, 15, 200, 30);

                //textField para password
                JPasswordField tfPass = new JPasswordField();
                tfPass.setBounds(110, 65, 200, 30);

                //textField para email
                JTextField tfEmail = new JTextField();
                tfEmail.setBounds(110, 115, 200, 30);
                //set radio button for admin
//                JRadioButton a1 = new JRadioButton("Admin");
//                a1.setBounds(75, 275, 80, 30);
//                a1.setBackground(Color.CYAN);
//                //set radio button for user
//                JRadioButton a2 = new JRadioButton("User");
//                a2.setBounds(200, 275, 80, 30);
//                a2.setBackground(Color.CYAN);
                //add radio buttons
                ButtonGroup bg = new ButtonGroup();
//                bg.add(a1);
//                bg.add(a2);

                lbBI = new JLabel("NR BI"); // Label para BI
                lbBI.setBounds(30, 165, 100, 30);
                lbBI.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                lbBI.setForeground(Color.CYAN);

                lbResidencia = new JLabel("Residência"); // label para residencia
                lbResidencia.setBounds(30, 215, 100, 30);
                lbResidencia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                lbResidencia.setForeground(Color.CYAN);

                JTextField txtBI = new JTextField(); // textField para BI
                txtBI.setBounds(110, 165, 200, 30);

                JTextField txtResidencia = new JTextField();
                txtResidencia.setBounds(110, 215, 200, 30);

                // BOTAO ACTUALIZAR NOME
                j1.addActionListener(new ActionListener() {

                    String idUser = null;
                    int idUserInt;
                    boolean vUser;
                    boolean vID = false;

                    public void actionPerformed(ActionEvent j) {
                        // TODO Auto-generated method stub
                        String nomeUsuario;
                        idUser = tfID.getText();
                        try {
                            idUserInt = Integer.parseInt(idUser);
                            vID = true;

                        } catch (NumberFormatException l) {
                            JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");
                        }

                        nomeUsuario = tfUser.getText();
                        if (nomeUsuario.equals("")) {
                            JOptionPane.showMessageDialog(null, "Por favor introduza um Nome");
                            vUser = false;

                        } else {
                            vUser = true;

                        }

                        if (vUser == true && vID == true) {
                            Connection connection = Connect.connect();

                            String sql = ("update USERS set username = ? where UID = ?");
                            try {
                                PreparedStatement stmt = connection.prepareStatement(sql);
                                //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY");

                                stmt.setString(1, nomeUsuario);
                                stmt.setInt(2, idUserInt);
                                ;
                                //   JOptionPane.showMessageDialog(null, nomeUsuario);
                                stmt.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Actualizado!");

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Não actualizado ");
                        }
                    }

                });

                // * FIM botao actualizar NOME
                //BOTAO ATUALIZAR PASSWORD
                j2.addActionListener(new ActionListener() {

                    String idUser = null;
                    int idUserInt;
                    String password = null; // ira receber nome do livro
                    boolean vPass = false;
                    boolean vID = false;

                    public void actionPerformed(ActionEvent j) {
                        // TODO Auto-generated method stub
                        String password;

                        idUser = tfID.getText();
                        try {
                            idUserInt = Integer.parseInt(idUser);
                            vID = true;

                        } catch (NumberFormatException l) {
                        	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");
                        }

                        password = tfPass.getText();
                        // validacao da password
                        if (password.equals("")) {
                            JOptionPane.showMessageDialog(null, "Por favor introduza uma Senha");
                            vPass = false;

                        } else {
                            vPass = true;

                        }
                        //* fim da validacao da password

                        if (vPass == true && vID == true) {
                            Connection connection = Connect.connect();

                            String sql = ("update USERS set password = ? where UID = ?");
                            try {
                                PreparedStatement stmt = connection.prepareStatement(sql);
                                //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY");

                                stmt.setString(1, password);
                                stmt.setInt(2, idUserInt);
                                ;
                                //   JOptionPane.showMessageDialog(null, nomeUsuario);
                                stmt.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Senha actualizada!");

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Senha não actualizada");
                        }
                    }

                });

                //*FIM BOTAO ACTUALIZAR PASSWORD
                //BOTAO ATUALIZAR numero BI
                j5.addActionListener(new ActionListener() {
                    String idUser = null;
                    int idUserInt;
                    String residencia = null; // ira receber nome do livro
                    boolean vID = false;

                    public void actionPerformed(ActionEvent j) {
                        idUser = tfID.getText();
                        try {
                            idUserInt = Integer.parseInt(idUser);
                            vID = true;

                        } catch (NumberFormatException l) {
                        	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");
                        }

                        residencia = txtResidencia.getText();
                        boolean vRes = false;
                        // validacao da residencia
                        if (residencia.equals("")) {
                            JOptionPane.showMessageDialog(null, "Por favor introduza uma Residência");
                            vRes = false;

                        } else {
                            vRes = true;

                        }
                        //* fim da validacao da residencia
                        //    JOptionPane.showMessageDialog(null, "selected");
                        Connection connection = Connect.connect();
                        if (vRes == true && vID == true) {

                            String sql = ("update USERS set RESIDENCIA = ? where UID = ?");
                            try {
                                PreparedStatement stmt = connection.prepareStatement(sql);
                                //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY");

                                stmt.setString(1, residencia);
                                stmt.setInt(2, idUserInt);

                                //   JOptionPane.showMessageDialog(null, nomeUsuario);
                                stmt.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Residência actualizada!");

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }
                            // TODO Auto-generated method stub
                            // String password; 

                        } else {
                            JOptionPane.showMessageDialog(null, "Residnêcia não actualizada");
                        }
                    }
                });

                //*FIM BOTAO ACTUALIZAR numero BI
                //BOTAO ATUALIZAR numero BI
                j3.addActionListener(new ActionListener() {

                    String idUser = null;
                    int idUserInt;
                    String email = null; // ira receber nome do livro
                    boolean vEmail = false;
                    boolean vID = false;

                    public void actionPerformed(ActionEvent j) {
                        // TODO Auto-generated method stub

                        idUser = tfID.getText();
                        try {
                            idUserInt = Integer.parseInt(idUser);
                            vID = true;

                        } catch (NumberFormatException l) {
                        	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");
                        }

                        email = tfEmail.getText();
                        String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"; // padrao de email
                        Pattern pattern = Pattern.compile(regx);
                        //Cria instancia do matcher   
                        Matcher matcher = pattern.matcher(email);
                        if (matcher.matches() == true) {

                            vEmail = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "e-mail inválido");
                            vEmail = false;
                        }
                        Connection connection = Connect.connect();

                        String sql = ("update USERS set email = ? where UID = ?");
                        if (vEmail == true && vID == true) {

                            try {
                                PreparedStatement stmt = connection.prepareStatement(sql);
                                //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY");

                                stmt.setString(1, email);
                                stmt.setInt(2, idUserInt);
                                ;
                                //   JOptionPane.showMessageDialog(null, nomeUsuario);
                                stmt.executeUpdate();
                                JOptionPane.showMessageDialog(null, "E-mail actualizado!");

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Email não actualizado");
                        }
                    }
                });

                //*FIM BOTAO ACTUALIZAR numero BI
                //BOTAO ATUALIZAR Residencia
                j4.addActionListener(new ActionListener() {

                    String idUser = null;
                    int idUserInt;
                    String numeroBI = null; // ira receber nome do livro
                    boolean vBI = false;
                    boolean vID = false;

                    public void actionPerformed(ActionEvent j) {
                        // TODO Auto-generated method stub
                        idUser = tfID.getText();
                        try {
                            idUserInt = Integer.parseInt(idUser);
                            vID = true;

                        } catch (NumberFormatException l) {
                        	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");
                        }

                        //  email = tfEmail.getText();
                        //    JOptionPane.showMessageDialog(null, "selected");
                        Connection connection = Connect.connect();
                        idUser = tfID.getText();
                        idUserInt = Integer.parseInt(idUser);
                        numeroBI = txtBI.getText();
                        // validacao da residencia
                        if (numeroBI.equals("")) {
                            JOptionPane.showMessageDialog(null, "Por favor introduza uma Residência");
                            vBI = false;

                        } else {
                            vBI = true;

                        }
                        //* fim da validacao da residencia
                        String sql = ("update USERS set NUMERO_BI = ? where UID = ?");
                        if (vBI == true && vID == true) {
                            try {
                                PreparedStatement stmt = connection.prepareStatement(sql);
                                //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY");

                                stmt.setString(1, numeroBI);
                                stmt.setInt(2, idUserInt);

                                //    JOptionPane.showMessageDialog(null, numeroBI);
                                stmt.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Numero BI actualizado!");

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "BI não actualizado");
                        }
                    }

                });

                //*FIM BOTAO ACTUALIZAR residencia
                /*
                 
                 
                 */
 /*            
                
               


                 */
                ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
                ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

//                g.add(create_but);
//                g.add(a2);
//                g.add(a1);
                JButton novobotao = new JButton("Retornar");
                novobotao.addActionListener(new ActionListener(){
               	 public void actionPerformed (ActionEvent e) {
               		  g.dispose();
               		  AdminMenu.admin_menu();
               	
               	 }
                });
                novobotao.setIcon(new ImageIcon("img/iretorna.png"));
                novobotao.setBackground(Color.WHITE);
                novobotao.setBounds(310, 400, 180, 25);

                g.add(novobotao);
                g.add(l1);
                g.add(l2);
                g.add(l3);
                g.add(tfUser);
                g.add(tfPass);
                g.add(tfEmail);
                g.add(txtBI);
                g.add(txtResidencia);
                g.add(lbResidencia);
                g.add(lbBI);
                g.add(j1);
                g.add(j2);
                g.add(j3);
                g.add(j4);
                g.add(j5);
                g.add(tfID);
                g.add(lbID);
                g.setSize(800, 500);// 
                g.add(new JLabel(fundo));
                g.pack();
                g.setIconImage(icone.getImage());
                g.setLayout(null);// sem utilizar gestor de layouts  
                g.setVisible(true);//tornar o frame visivel
                g.setLocationRelativeTo(null);

            }
        });
        //*fim actualizar usuario 

        // pesquisar usuario
        JButton proc_user = new JButton("Pesquisar Usuário"); //mudado de - find_user-
        proc_user.setBounds(5, 180, 180, 25); //

        proc_user.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame g = new JFrame("Adicione Dados do Usuário"); //Frame para introduzir detalhes 
                f.dispose();
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //Create label 
                JLabel l1, l2, l3, lbBI, lbResidencia;
                l1 = new JLabel("ID USUARIO");  //label 1 para username
                l1.setBounds(230, 65, 100, 30);
                l1.setForeground(Color.CYAN);

                //set text field for username 
                JTextField F_user = new JTextField();
                F_user.setBounds(310, 65, 200, 30);

                JButton btProcUsuario = new JButton("Pesquisar");// 
                btProcUsuario.setBounds(320, 150, 100, 30);// 
                btProcUsuario.setBackground(Color.CYAN);

                btProcUsuario.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                    	f.dispose();
                        JFrame f = new JFrame("PROCURAR USUÁRIO");
                        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        String id = F_user.getText();
                        boolean vID = false;
                        int user_id = 0;
                        try {
                            user_id = Integer.parseInt(id);
                            vID = true;
                        } catch (NumberFormatException m) {
                         	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");
                        }
                        Connection connection = Connect.connect();
                        if (vID == true) {
                            String sql = ("SELECT * FROM USERS WHERE UID=" + user_id); //retrieve all users
                            try {
                            	g.dispose();
                                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY"); //use database
                                stmt = connection.createStatement();
                                ResultSet rs = stmt.executeQuery(sql);
                                JTable book_list = new JTable();
                                book_list.setModel(DbUtils.resultSetToTableModel(rs));
                                //mention scroll bar
                                JScrollPane scrollPane = new JScrollPane(book_list);

                                JButton novobotao = new JButton("Retornar");
                                novobotao.addActionListener(new ActionListener(){
                               	 public void actionPerformed (ActionEvent e) {
                               		  f.dispose();
                               		  AdminMenu.admin_menu();
                               		//  JOptionPane.showMessageDialog(null, "botao");
                               	
                               	 }
                                });
                                novobotao.setIcon(new ImageIcon("img/iretorna.png"));
                                novobotao.setBackground(Color.WHITE);
                                novobotao.setBounds(310, 400, 180, 25);

                                f.add(novobotao);
                                f.add(scrollPane); //adicionar scrollpane
                                f.setSize(800, 500); //tamanho do frame
                                f.setVisible(true);
                                f.setLocationRelativeTo(null);
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Digite um id válido");
                        }
                    }

                });

                ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
                ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

                JButton novobotao = new JButton("Retornar");
                novobotao.addActionListener(new ActionListener(){
                  	 public void actionPerformed (ActionEvent e) {
                  		  g.dispose();
                  		  AdminMenu.admin_menu();
                  		 
                  	
                  	 }
                   });
                novobotao.setIcon(new ImageIcon("img/iretorna.png"));
                novobotao.setBackground(Color.WHITE);
                novobotao.setBounds(310, 400, 180, 25);

                g.add(novobotao);
                g.add(btProcUsuario);
                g.add(l1);
                g.add(F_user);
                g.setSize(800, 500);//
                g.add(new JLabel(fundo));
                g.pack();
                g.setIconImage(icone.getImage());
                g.setLayout(null);//sem usar layout managers  
                g.setVisible(true);
                g.setLocationRelativeTo(null);

            }
        });
        // fim pesquisar usuario

        // pesquisar livro
        JButton proc_livro = new JButton("Pesquisar livro");  // mudado de find_book
        proc_livro.setBounds(205, 180, 180, 25);

        proc_livro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
                JFrame g = new JFrame("Adicione Dados do livro"); //
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //Create label 
                JLabel l1, l2, l3, lbBI, lbResidencia;
                l1 = new JLabel("ID LIVRO");  //label 1 para username
                l1.setBounds(230, 65, 100, 30);
                l1.setForeground(Color.CYAN);

                JTextField F_user = new JTextField();
                F_user.setBounds(310, 65, 200, 30);

                JButton create_but = new JButton("Pesquisar");
                create_but.setBounds(320, 150, 100, 30);;//
                create_but.setBackground(Color.CYAN);
                create_but.addActionListener(new ActionListener() {

                    String livroNome;  //recebe nome do livro
                    String livroAutor;  //recebe Autor do livro
                    String livroGenero;  //recebe genero do livro
                    String livroPreco;  //recebe genero do livro

                    public void actionPerformed(ActionEvent e) {
                    	g.dispose();
                        JFrame f = new JFrame("PESQUISAR LIVRO");
                        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        String id = F_user.getText();
                        boolean vID = false;
                        int book_id = 0;
                        try {
                            book_id = Integer.parseInt(id);
                            vID = true;
                        } catch (NumberFormatException m) {
                         	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");
                        }

                        Connection connection = Connect.connect();
                        // * buscar nome do livro 
                        if (vID == true) {
                            try {

                                Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

                                stmt1.executeUpdate("USE LIBRARY");

                                Connection connection2 = Connect.connect();
                                Statement stmt2 = connection2.createStatement();
                                stmt2.executeUpdate("USE LIBRARY");
                                ResultSet rs4 = stmt2.executeQuery("SELECT NOMEL FROM LIVROS WHERE BID=" + book_id); //seleciona o email do user com base no seu  id
                                String nomeLivro = null;
                                while (rs4.next()) {
                                    nomeLivro = rs4.getString(1);

                                    livroNome = nomeLivro;
                                }

                                stmt2.executeUpdate("USE LIBRARY");
                                ResultSet rs3 = stmt2.executeQuery("SELECT AUTOR FROM LIVROS WHERE BID=" + book_id); //seleciona o email do user com base no seu  id
                                String autorLivro = null;
                                while (rs3.next()) {
                                    autorLivro = rs3.getString(1);

                                    livroAutor = autorLivro;
                                }

                                stmt2.executeUpdate("USE LIBRARY");
                                ResultSet rs2 = stmt2.executeQuery("SELECT GENERO FROM LIVROS WHERE BID=" + book_id); //seleciona o email do user com base no seu  id
                                String generoLivro = null;
                                while (rs2.next()) {
                                    generoLivro = rs2.getString(1);

                                    livroGenero = generoLivro;
                                }

                                stmt2.executeUpdate("USE LIBRARY");
                                ResultSet rs1 = stmt2.executeQuery("SELECT preco FROM LIVROS WHERE BID=" + book_id); //seleciona o email do user com base no seu  id
                                String precoLivro = null;
                                while (rs1.next()) {
                                    precoLivro = rs1.getString(1);

                                    livroPreco = precoLivro;
                                }

                                JFrame fBook = new JFrame("Livros");

                                ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
                                ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

                                ImageIcon capa = new ImageIcon("img/book" + book_id + ".jpg");
                                JPanel img = new JPanel();
                                img.setBounds(75, 75, 300, 400);
                                img.setBackground(new Color(0f, 0f, 0f, 0f));
//                            img.setBackground(Color.red);
                                img.add(new JLabel(capa));

                                JLabel nomeE = new JLabel(livroNome); //CIRAR A LABEL DO TITULO
                                nomeE.setBounds(400, 90, 400, 30); //MARGEM A ESQUERDA, MARGEM ACIMA, LARGURA, ALTURA 
                                nomeE.setFont(new Font("Times New Roman", Font.PLAIN, 30));
                                nomeE.setForeground(Color.CYAN);

                                JLabel autorE = new JLabel("Autor: " + livroAutor); //CIRAR A LABEL DO TITULO
                                autorE.setBounds(400, 150, 400, 30); //MARGEM A ESQUERDA, MARGEM ACIMA, LARGURA, ALTURA 
                                autorE.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                                autorE.setForeground(Color.WHITE);

                                JLabel generoE = new JLabel("Género: " + livroGenero); //CIRAR A LABEL DO TITULO
                                generoE.setBounds(400, 200, 400, 30); //MARGEM A ESQUERDA, MARGEM ACIMA, LARGURA, ALTURA 
                                generoE.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                                generoE.setForeground(Color.WHITE);

                                JLabel precoE = new JLabel("Preco: " + livroPreco + "MZN"); //CIRAR A LABEL DO TITULO
                                precoE.setBounds(400, 250, 400, 30); //MARGEM A ESQUERDA, MARGEM ACIMA, LARGURA, ALTURA 
                                precoE.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                                precoE.setForeground(Color.WHITE);

                                JButton novobotao = new JButton("Retornar");
                                novobotao.addActionListener(new ActionListener(){
                                  	 public void actionPerformed (ActionEvent e) {
                                  		fBook.dispose();
                                  		  AdminMenu.admin_menu();
                                  		  
                                  	
                                  	 }
                                   });
                                novobotao.setIcon(new ImageIcon("img/iretorna.png"));
                                novobotao.setBackground(Color.WHITE);
                                novobotao.setBounds(310, 495, 180, 25);

                                fBook.setSize(800, 500); //
                                fBook.setVisible(true);
                                fBook.setLocationRelativeTo(null);

                                fBook.add(novobotao);
                                fBook.add(img);
                                fBook.add(nomeE);
                                fBook.add(autorE);
                                fBook.add(generoE);
                                fBook.add(precoE);
                                fBook.add(new JLabel(fundo));
                                fBook.pack();
                                fBook.setIconImage(icone.getImage());
                                fBook.setLayout(null);//sem layout managers  

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Digite um ID válido");
                        }
                    }

                });

                ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
                ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

                create_but.setBackground(Color.CYAN);

                JButton novobotao = new JButton("Retornar");
                novobotao.addActionListener(new ActionListener(){
                 	 public void actionPerformed (ActionEvent e) {
                 		g.dispose();
                 		  AdminMenu.admin_menu();
                 		
                 	
                 	 }
                  });
                novobotao.setIcon(new ImageIcon("img/iretorna.png"));
                novobotao.setBackground(Color.WHITE);
                novobotao.setBounds(310, 400, 180, 25);

                g.add(novobotao);
                g.add(create_but);
                g.add(l1);
                g.add(F_user);
                g.setSize(800, 500);// 
                g.add(new JLabel(fundo));
                g.pack();
                g.setIconImage(icone.getImage());
                g.setLayout(null);// 
                g.setVisible(true);//
                g.setLocationRelativeTo(null);

            }
        });

        // fim pesquisar livro
        // remover livro
        JButton btRemoveLivro = new JButton("Apagar livro"); //
        btRemoveLivro.setBounds(605, 100, 180, 25); //dimensoes dos botoes

        btRemoveLivro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
                JFrame g = new JFrame("Adicione Dados do livro"); //Frame para detalhes do livro
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //Create label 
                JLabel l1, l2, l3, lbBI, lbResidencia;
                l1 = new JLabel("ID LIVRO");  //
                l1.setBounds(230, 65, 100, 30);
                l1.setForeground(Color.CYAN);

                //set text field for username 
                JTextField F_user = new JTextField();
                F_user.setBounds(310, 65, 200, 30);

                JButton btRemover = new JButton("Remover");//instancia do botao remover
                btRemover.setBounds(320, 150, 100, 30);;// 
                btRemover.setBackground(Color.CYAN);

                btRemover.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                    	g.dispose();
                        JFrame f = new JFrame("REMOVER LIVRO");
                        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        boolean vID = false;
                        String id = F_user.getText();
                        int book_id = 0;
                        try {
                            book_id = Integer.parseInt(id);
                            vID = true;

                        } catch (NumberFormatException l) {
                         	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");
                        }

                        String idLivro = Integer.toString(book_id);
                        Connection connection = Connect.connect();
                        String sql = ("DELETE FROM LIVROS WHERE BID=?"); //
                        if (vID == true) {
                            try {

                                PreparedStatement statement = connection.prepareStatement(sql);
                                statement.executeUpdate("USE LIBRARY");
                                statement.setString(1, idLivro);

                                int rowsDeleted = statement.executeUpdate();
                                if (rowsDeleted > 0) {
                                    JOptionPane.showMessageDialog(null, "Livro removido com sucesso");
                                    AdminMenu.admin_menu();
                                    // subtrair numero livros

                                    String sqlx = ("update relatorio1 set CAD_LIVROS = ? where R1ID = ?");
                                    int rl2 = 0;
                                    try {
                                        try {
                                            Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                            stmt1.executeUpdate("USE LIBRARY");
                                            int rid = 1;
                                            Connection connection4 = Connect.connect();
                                            Statement stmt4 = connection4.createStatement();
                                            stmt4.executeUpdate("USE LIBRARY");
                                            ResultSet rs4 = stmt4.executeQuery("SELECT CAD_LIVROS FROM relatorio1 WHERE R1ID=" + rid); //seleciona o email do user com base no seu  id
                                            String nomeUser = null;
                                            while (rs4.next()) {
                                                nomeUser = rs4.getString(1);

                                                rl2 = Integer.parseInt(nomeUser);

                                            }

                                        } catch (SQLException e1) {
                                            // TODO Auto-generated catch block
                                            JOptionPane.showMessageDialog(null, e1);
                                        }

                                        int rl3 = rl2 - 1;

                                        PreparedStatement stmt5 = connection.prepareStatement(sqlx);
                                        //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt5.executeUpdate("USE LIBRARY");

                                        stmt5.setInt(1, rl3);
                                        stmt5.setInt(2, 1);
                                        stmt5.executeUpdate();
                                        //JOptionPane.showMessageDialog(null, "RELATORIO++!");

                                        g.dispose();

                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }
                                    // fim subtrair numero livros
                                }
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Livro não foi Removido ");
                        }
                    }

                });

                ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
                ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

                btRemover.setBackground(Color.CYAN);

                JButton novobotao = new JButton("Retornar");
                novobotao.addActionListener(new ActionListener(){
                	 public void actionPerformed (ActionEvent e) {
                		g.dispose();
                		  AdminMenu.admin_menu();
                		
                	
                	 }
                 });
                novobotao.setIcon(new ImageIcon("img/iretorna.png"));
                novobotao.setBackground(Color.WHITE);
                novobotao.setBounds(310, 400, 180, 25);

                g.add(novobotao);
                g.add(btRemover);
                g.add(l1);
                g.add(F_user);
                g.setSize(800, 500);//  
                g.add(new JLabel(fundo));
                g.pack();
                g.setIconImage(icone.getImage());
                g.setLayout(null);//sem o layout managers  
                g.setVisible(true);//
                g.setLocationRelativeTo(null);

            }
        });
        // fim remover livro

        // remover ALUGUER
        JButton remove_alugueres = new JButton("Apagar Alugueres"); //crir insTANCIA DO BOTAO REMOVER ALUGUER
        remove_alugueres.setBounds(605, 180, 180, 25); //

        remove_alugueres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
                JFrame g = new JFrame("Adicione Dados do aluguer");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //Create label 
                JLabel l1, l2, l3, lbBI, lbResidencia;
                l1 = new JLabel("ID da emissao");  //label 1 fPARA ID DA EMISSAO	
                l1.setBounds(230, 65, 100, 30);
                l1.setForeground(Color.CYAN);

                //set text field for username 
                JTextField F_user = new JTextField();
                F_user.setBounds(310, 65, 200, 30);

                JButton create_but = new JButton("Remover");//criar instancia do botao remover 
                create_but.setBounds(320, 150, 100, 30);;//
                create_but.setBackground(Color.CYAN);

                create_but.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                    	g.dispose();
                        JFrame f = new JFrame("Remover aluguer");
                        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        String id = F_user.getText();
                        int aluguer_id = 0;
                        boolean vID = false;
                        try {
                            aluguer_id = Integer.parseInt(id);
                            vID = true;

                        } catch (NumberFormatException l) {
                         	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");

                        }
                        String idAluguer = Integer.toString(aluguer_id);
                        Connection connection = Connect.connect();
                        String sql = ("DELETE FROM ALUGUER WHERE IID=?"); //querry para apagar um determinado aluguer
                        if (vID == true) {
                            try {

                                PreparedStatement statement = connection.prepareStatement(sql);
                                statement.executeUpdate("USE LIBRARY");
                                statement.setString(1, idAluguer);

                                int rowsDeleted = statement.executeUpdate();
                                if (rowsDeleted > 0) {
                                	  
                                     		  AdminMenu.admin_menu();
                                     		
                                     	
                                     	
                                    JOptionPane.showMessageDialog(null, "Aluguer removido com sucesso");
                                    
                                    // adicionar numero alugueres
                                    String sqlx = ("update relatorio1 set LIVROS_ALUGADOS = ? where R1ID = ?");
                                    int rl1 = 0;
                                    try {
                                        try {
                                            Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                            stmt7.executeUpdate("USE LIBRARY");
                                            int rid = 1;
                                            Connection connection7 = Connect.connect();
                                            Statement stmt8 = connection7.createStatement();
                                            stmt8.executeUpdate("USE LIBRARY");
                                            ResultSet rs7 = stmt8.executeQuery("SELECT LIVROS_ALUGADOS FROM relatorio1 WHERE R1ID=" + rid); //seleciona o email do user com base no seu  id
                                            String nomeUser = null;
                                            while (rs7.next()) {
                                                nomeUser = rs7.getString(1);

                                                rl1 = Integer.parseInt(nomeUser);
                                                // JOptionPane.showMessageDialog(null, "num XXX: " + rl1);
                                            }

                                        } catch (SQLException e1) {
                                            // TODO Auto-generated catch block
                                            JOptionPane.showMessageDialog(null, e1);
                                        }
                                        //  int rel1 = rl1+1;
                                        PreparedStatement stmt9 = connection.prepareStatement(sqlx);
                                        //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt9.executeUpdate("USE LIBRARY");

                                        stmt9.setInt(1, --rl1);
                                        stmt9.setInt(2, 1);
                                        stmt9.executeUpdate();
                                        // JOptionPane.showMessageDialog(null, "RELATORIO++!");

                                        g.dispose();

                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }
                                    // fim adicionar numero livros alugados
                                }
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "O aluguer não foi removido");
                        }
                    }
                });

                ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
                ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

                create_but.setBackground(Color.CYAN);

                JButton novobotao = new JButton("Retornar");
                novobotao.addActionListener(new ActionListener(){
               	 public void actionPerformed (ActionEvent e) {
               		g.dispose();
               		  AdminMenu.admin_menu();
               		
               	
               	 }
                });
                novobotao.setIcon(new ImageIcon("img/iretorna.png"));
                novobotao.setBackground(Color.WHITE);
                novobotao.setBounds(310, 400, 180, 25);

                g.add(novobotao);
                g.add(create_but);
                g.add(l1);
                g.add(F_user);
                g.setSize(800, 500);//  
                g.add(new JLabel(fundo));
                g.pack();
                g.setIconImage(icone.getImage());
                g.setLayout(null);//sem usar layout manager
                g.setVisible(true);//
                g.setLocationRelativeTo(null);

            }
        });
        // fim remover ALUGUER

        // remover USuarios
        JButton remove_usuarios = new JButton("Apagar Usuários"); //instancia do botao remover usuarios
        remove_usuarios.setBounds(605, 20, 180, 25); //

        remove_usuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
                JFrame g = new JFrame("Adicione Dados do Usuário"); //Frame para introduzir detalhes do usuario
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //Create label 
                JLabel l1, l2, l3, lbBI, lbResidencia;
                l1 = new JLabel("ID do usuario");  //label 1 para ID o usuario
                l1.setBounds(230, 65, 100, 30);
                l1.setForeground(Color.CYAN);

                //set text field for username 
                JTextField F_user = new JTextField();
                F_user.setBounds(310, 65, 200, 30);

                JButton btRemover = new JButton("Remover");// intancia do borao remover 
                btRemover.setBounds(320, 150, 100, 30);;// 
                btRemover.setBackground(Color.CYAN);

                btRemover.addActionListener(new ActionListener() {
                	
                    public void actionPerformed(ActionEvent e) {
                    	g.dispose();
                        JFrame f = new JFrame("Remover Usuário");
                        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        String id = F_user.getText();
                        int user_id = 0;
                        boolean vID = false;
                        try {
                            user_id = Integer.parseInt(id);
                            vID = true;

                        } catch (NumberFormatException l) {
                         	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");

                        }

                        String idUser = Integer.toString(user_id);
                        Connection connection = Connect.connect();
                        String sql = ("DELETE FROM USERS WHERE UID=?"); //querry para apagar usuarios
                        if (vID == true) {
                            try {

                                PreparedStatement statement = connection.prepareStatement(sql);
                                statement.executeUpdate("USE LIBRARY");
                                statement.setString(1, idUser);

                                int rowsDeleted = statement.executeUpdate();
                                if (rowsDeleted > 0) {
                                	
                                     		  AdminMenu.admin_menu();
                                     		
                                     	
                                    JOptionPane.showMessageDialog(null, "Usuário"
                                            + " removido com sucesso");
                                    // adicionar numero usuarios
                                    String sql4 = ("update relatorio1 set CAD_USERS = ? where R1ID = ?");
                                    int rl1 = 0;
                                    try {
                                        try {
                                            Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                            stmt1.executeUpdate("USE LIBRARY");
                                            int rid = 1;
                                            Connection connection2 = Connect.connect();
                                            Statement stmt2 = connection2.createStatement();
                                            stmt2.executeUpdate("USE LIBRARY");
                                            ResultSet rs2 = stmt2.executeQuery("SELECT CAD_USERS FROM relatorio1 WHERE R1ID=" + rid); //seleciona o email do user com base no seu  id
                                            String nomeUser = null;
                                            while (rs2.next()) {
                                                nomeUser = rs2.getString(1);

                                                rl1 = Integer.parseInt(nomeUser);
                                                //      JOptionPane.showMessageDialog(null, "num user: " + rl1);
                                            }

                                        } catch (SQLException e1) {
                                            // TODO Auto-generated catch block
                                            JOptionPane.showMessageDialog(null, e1);
                                        }
                                        //  int rel1 = rl1+1;
                                        PreparedStatement stmt1 = connection.prepareStatement(sql4);
                                        //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt1.executeUpdate("USE LIBRARY");

                                        stmt1.setInt(1, --rl1);
                                        stmt1.setInt(2, 1);
                                        stmt1.executeUpdate();

                                        g.dispose();

                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }
                                    // fim adicionar numero usuarios
                                }
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Usuário não removido");
                        }
                    }

                });

                ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
                ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

                btRemover.setBackground(Color.CYAN);

                JButton novobotao = new JButton("Retornar");
                novobotao.addActionListener(new ActionListener(){
               	 public void actionPerformed (ActionEvent e) {
               		g.dispose();
               		  AdminMenu.admin_menu();
               		
               	
               	 }
                });
                novobotao.setIcon(new ImageIcon("img/iretorna.png"));
                novobotao.setBackground(Color.WHITE);
                novobotao.setBounds(310, 400, 180, 25);

                g.add(novobotao);
                g.add(btRemover);
                g.add(l1);
                g.add(F_user);
                g.setSize(800, 500);// 
                g.add(new JLabel(fundo));
                g.pack();
                g.setIconImage(icone.getImage());
                g.setLayout(null);//sem usar layout managers  
                g.setVisible(true);//
                g.setLocationRelativeTo(null);

            }
        });

        //GERAR RELATORIO
        JButton btRelatorio = new JButton("Relatório");//criar instancia do botao relatorio
        btRelatorio.setBounds(605, 260, 180, 25);//
        btRelatorio.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String totalUsers = null;
                String totalLivros = null;
                String livrosDevolvidos = null;
                String livrosAlugados = null;
                String multasAtraso = null;
                String multasTotal = null;
                String multasCons = null;
                String feedTot = null;
                String feedMIns = null;
                String feedIns = null;
                String feedSatisf = null;
                String feedMSatisf = null;
                String ValorAluguer1 = null;
                String multaAtraso1 = null; // recebe o valor$
                String multasCons1 = null; // recebe o valor $
                double multasTotal1 = 0;  // recebe o valor $
                double lucroTotal1 = 0;  // recebe o valor $
                Connection connection = Connect.connect();
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection2 = Connect.connect();
                    Statement stmt2 = connection2.createStatement();
                    stmt2.executeUpdate("USE LIBRARY");
                    ResultSet rs2 = stmt2.executeQuery("SELECT CAD_USERS FROM relatorio1 where R1ID =1"); //seleciona o email do user com base no seu  id
                    String rel1 = null;
                    while (rs2.next()) {
                        rel1 = rs2.getString(1);

                        totalUsers = rel1;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

                //seleciona numero livros alugados
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection3 = Connect.connect();
                    Statement stmt3 = connection3.createStatement();
                    stmt3.executeUpdate("USE LIBRARY");
                    ResultSet rs3 = stmt3.executeQuery("SELECT CAD_LIVROS FROM relatorio1 where R1ID =1 "); //seleciona o email do user com base no seu  id
                    String rel3 = null;
                    while (rs3.next()) {
                        rel3 = rs3.getString(1);

                        totalLivros = rel3;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

                //fim selecaolivros
                //seleciona numero livros DEVOLVIDOS
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection4 = Connect.connect();
                    Statement stmt4 = connection4.createStatement();
                    stmt4.executeUpdate("USE LIBRARY");
                    ResultSet rs4 = stmt4.executeQuery("SELECT LIVROS_DEVOLVIDOS FROM relatorio1 where R1ID =1"); //seleciona o email do user com base no seu  id
                    String rel4 = null;
                    while (rs4.next()) {
                        rel4 = rs4.getString(1);

                        livrosDevolvidos = rel4;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

                //fim selecaolivros DEVOLVIOS
                //seleciona numero livros DEVOLVIDOS
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection5 = Connect.connect();
                    Statement stmt5 = connection5.createStatement();
                    stmt5.executeUpdate("USE LIBRARY");
                    ResultSet rs5 = stmt5.executeQuery("SELECT LIVROS_ALUGADOS FROM relatorio1 where R1ID =1"); //seleciona o email do user com base no seu  id
                    String rel5 = null;
                    while (rs5.next()) {
                        rel5 = rs5.getString(1);

                        livrosAlugados = rel5;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }
                // JOptionPane.showMessageDialog(null, livrosDevolvidos);
                //fim selecaolivros DEVOLVIOS

                //seleciona numero de multas atraso geradas
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection5 = Connect.connect();
                    Statement stmt5 = connection5.createStatement();
                    stmt5.executeUpdate("USE LIBRARY");
                    ResultSet rs5 = stmt5.executeQuery("SELECT MULTAS_ATRASO FROM relatorio2 where R2ID =1"); //seleciona o email do user com base no seu  id
                    String rel6 = null;
                    while (rs5.next()) {
                        rel6 = rs5.getString(1);

                        multasAtraso = rel6;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

                //fim selecao multa atraso
                //seleciona total de multas  geradas
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection5 = Connect.connect();
                    Statement stmt5 = connection5.createStatement();
                    stmt5.executeUpdate("USE LIBRARY");
                    ResultSet rs5 = stmt5.executeQuery("SELECT TOT_MULTAS FROM relatorio2 where R2ID =1"); //seleciona o email do user com base no seu  id
                    String rel6 = null;
                    while (rs5.next()) {
                        rel6 = rs5.getString(1);

                        multasTotal = rel6;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

                //fim selecao multa atraso
                //seleciona total de multas  conservacao
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection5 = Connect.connect();
                    Statement stmt5 = connection5.createStatement();
                    stmt5.executeUpdate("USE LIBRARY");
                    ResultSet rs5 = stmt5.executeQuery("SELECT MULTAS_CONS FROM relatorio2 where R2ID =1"); //seleciona o email do user com base no seu  id
                    String rel6 = null;
                    while (rs5.next()) {
                        rel6 = rs5.getString(1);

                        multasCons = rel6;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

                //fim selecao multa conservacao
                //seleciona total de feedbacks
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection5 = Connect.connect();
                    Statement stmt5 = connection5.createStatement();
                    stmt5.executeUpdate("USE LIBRARY");
                    ResultSet rs5 = stmt5.executeQuery("SELECT FEED_TOTAL FROM relatorio3 where R3ID =1"); //seleciona o email do user com base no seu  id
                    String rel6 = null;
                    while (rs5.next()) {
                        rel6 = rs5.getString(1);

                        feedTot = rel6;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

                //fim selecao total feedback
                //seleciona total de feedbacks muito insatisfeitos
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection5 = Connect.connect();
                    Statement stmt5 = connection5.createStatement();
                    stmt5.executeUpdate("USE LIBRARY");
                    ResultSet rs5 = stmt5.executeQuery("SELECT M_INSA FROM relatorio3 where R3ID =1"); //seleciona o email do user com base no seu  id
                    String rel6 = null;
                    while (rs5.next()) {
                        rel6 = rs5.getString(1);

                        feedMIns = rel6;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

                //fim selecao total feedback muito insatisfeitos
                //seleciona total de feedbacks  insatisfeitos
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection5 = Connect.connect();
                    Statement stmt5 = connection5.createStatement();
                    stmt5.executeUpdate("USE LIBRARY");
                    ResultSet rs5 = stmt5.executeQuery("SELECT INSA FROM relatorio3 where R3ID =1"); //seleciona o email do user com base no seu  id
                    String rel6 = null;
                    while (rs5.next()) {
                        rel6 = rs5.getString(1);

                        feedIns = rel6;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

                //fim selecao total feedback  insatisfeitos
                //seleciona total de feedbacks  satisfeitos
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection5 = Connect.connect();
                    Statement stmt5 = connection5.createStatement();
                    stmt5.executeUpdate("USE LIBRARY");
                    ResultSet rs5 = stmt5.executeQuery("SELECT SATISF FROM relatorio3 where R3ID =1"); //seleciona o email do user com base no seu  id
                    String rel6 = null;
                    while (rs5.next()) {
                        rel6 = rs5.getString(1);

                        feedSatisf = rel6;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

                //fim selecao total feedback  satisfeitos
                //seleciona total de feedbacks muito satisfeitos
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection5 = Connect.connect();
                    Statement stmt5 = connection5.createStatement();
                    stmt5.executeUpdate("USE LIBRARY");
                    ResultSet rs5 = stmt5.executeQuery("SELECT M_SATISF FROM relatorio3 where R3ID =1"); //seleciona o email do user com base no seu  id
                    String rel6 = null;
                    while (rs5.next()) {
                        rel6 = rs5.getString(1);

                        feedMSatisf = rel6;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }

                //fim selecao total feedback muito satisfeitos
                //seleciona valor aluguer
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection5 = Connect.connect();
                    Statement stmt5 = connection5.createStatement();
                    stmt5.executeUpdate("USE LIBRARY");
                    ResultSet rs5 = stmt5.executeQuery("SELECT VALOR_ALUGUER FROM relatorio4 where R4ID =1"); //seleciona o email do user com base no seu  id
                    String rel6 = null;
                    while (rs5.next()) {
                        rel6 = rs5.getString(1);

                        ValorAluguer1 = rel6;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }
                //fim selecao valor aluguer
                //seleciona valor multa atraso
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection5 = Connect.connect();
                    Statement stmt5 = connection5.createStatement();
                    stmt5.executeUpdate("USE LIBRARY");
                    ResultSet rs5 = stmt5.executeQuery("SELECT MULTA_ATRASO FROM relatorio4 where R4ID =1"); //seleciona o email do user com base no seu  id
                    String rel6 = null;
                    while (rs5.next()) {
                        rel6 = rs5.getString(1);

                        multaAtraso1 = rel6;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }
                //*fim selecao valor multa atraso

                //seleciona valor multa conservacao
                try {
                    Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt1.executeUpdate("USE LIBRARY");

                    Connection connection5 = Connect.connect();
                    Statement stmt5 = connection5.createStatement();
                    stmt5.executeUpdate("USE LIBRARY");
                    ResultSet rs5 = stmt5.executeQuery("SELECT MULTA_CONSERVACAO FROM relatorio4 where R4ID =1"); //seleciona o email do user com base no seu  id
                    String rel6 = null;
                    while (rs5.next()) {
                        rel6 = rs5.getString(1);

                        multasCons1 = rel6;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }
                //*fim selecao valor multa conservacao
                multasTotal1 = ((Double.parseDouble(multaAtraso1)) + (Double.parseDouble(multasCons1)));
                lucroTotal1 = ((Double.parseDouble(ValorAluguer1)) + multasTotal1);
                //gerar documento
                Document document = new Document();
                String a = "teste";
                int b = 2;
                JOptionPane.showMessageDialog(null, "Relatório gerado no caminho [D:\\\\RelatorioUEM.pdf]");
                try {

                    PdfWriter.getInstance(document, new FileOutputStream("D:\\RelatorioUEM.pdf"));
                    document.open();
                    String agora = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

                    // adicionando um parágrafo no documento
                    document.add(new Paragraph("                                                                                " + " Relatorio gerado no dia:" + agora));
                    document.add(new Paragraph("---------------**Relatório de cadastrados**------------------------------------------------------------------"));
                    // document.add(new Paragraph("Total usuarios:" + totalUsers));
                    document.add(new Paragraph("Total usuários:" + totalUsers));
                    document.add(new Paragraph("Total livros:" + totalLivros));
                    document.add(new Paragraph("-----------------************************------------------------------------------------------------------"));
                    document.add(new Paragraph("---------------*********Relatório de Alugueres*********------------------------------------------------------------------"));
                    document.add(new Paragraph("Total livros Alugados:" + livrosAlugados));
                    document.add(new Paragraph("Total livros devolvidos:" + livrosDevolvidos));
                    document.add(new Paragraph("Total Multas Conservacao:" + multasCons));
                    document.add(new Paragraph("Total Multas de atraso:" + multasAtraso));
                    document.add(new Paragraph("Total Multas geradas:" + multasTotal));
                    document.add(new Paragraph("-----------------************************------------------------------------------------------------------"));
                    document.add(new Paragraph("---------------*********Relatório de Feedbacks*********------------------------------------------------------------------"));
                    document.add(new Paragraph("Total de feedbacks gerados:" + feedTot));
                    document.add(new Paragraph("Total de feedbacks muito insatisfeitos:" + feedMIns));
                    document.add(new Paragraph("Total de feedbacks  insatisfeitos:" + feedIns));
                    document.add(new Paragraph("Total de feedbacks  satisfeitos:" + feedSatisf));
                    document.add(new Paragraph("Total de feedbacks Muito  satisfeitos:" + feedMSatisf));
                    document.add(new Paragraph("-----------------************************------------------------------------------------------------------"));
                    document.add(new Paragraph("---------------*********Relatório de Alugueres*********------------------------------------------------------------------"));
                    document.add(new Paragraph("Total de valor de aluguer arrecadado:" + ValorAluguer1));
                    document.add(new Paragraph("Valor de multas por atraso arrecadado:" + multaAtraso1));
                    document.add(new Paragraph("Valor de multas por atraso arrecadado:" + multasCons1));
                    document.add(new Paragraph("Total de valor de multas arrecadado:" + multasTotal1));
                    document.add(new Paragraph("Total de lucro arrecadado:" + lucroTotal1));
                    document.add(new Paragraph("-----------------******************************------------------------------------------------------------------"));
                    document.add(new Paragraph("-----------------------------------------------------------------------------------"));

                } catch (DocumentException de) {
                    System.err.println(de.getMessage());
                } catch (IOException ioe) {
                    System.err.println(ioe.getMessage());
                }
                document.close();

            }
        });
        //FIM GERAR RELATORIO

        // fim remover USuarios
        JButton btAdicionarLivro = new JButton("Adicionar livros"); //criar instancia do botao para adicionar livros
        btAdicionarLivro.setBounds(205, 20, 180, 25);

        btAdicionarLivro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //set frame wot enter book details
            	f.dispose();
                JFrame g = new JFrame("Introduza Dados do livro");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // set labels
                JLabel l1, l2, l3, lbAutor;
                l1 = new JLabel("Nome do livro");  //lebel 1 para nome do livro
                l1.setBounds(230, 15, 100, 30);

                l2 = new JLabel("Género");  //label 2 para genero
                l2.setBounds(230, 65, 100, 30);

                l3 = new JLabel("Preço");  //label 2 para preco
                l3.setBounds(230, 115, 100, 30); // label para autor
                lbAutor = new JLabel("Autor");
                lbAutor.setBounds(230, 165, 100, 30);
                //set text field for book name
                JTextField F_bname = new JTextField();
                F_bname.setBounds(310, 15, 200, 30);
                JTextField txtAutor = new JTextField();
                txtAutor.setBounds(310, 165, 200, 30);
                //set text field for genre 
                JComboBox<String> cbGenero = new JComboBox(); // combobox para generos
                cbGenero.addItem("Matemática");
                cbGenero.addItem("HQ");
                cbGenero.addItem("Romance");
                cbGenero.addItem("Literatura");
              //  cbGenero.addItem("Literatura Moçambicana");
                cbGenero.addItem("Religião");
                cbGenero.addItem("Ficcao Científica");
                cbGenero.addItem("Culinaria");
                cbGenero.addItem("Filosofia");
                cbGenero.addItem("Outro");

                //JTextField F_genre=new JTextField();
                cbGenero.setBounds(310, 65, 200, 30);
                //set text field for price
                JTextField F_price = new JTextField();
                F_price.setBounds(310, 115, 200, 30);

                JButton btAddLivro = new JButton("Adicionar");//cria instancia do botao adicionar detalhes
                btAddLivro.setBounds(290, 250, 180, 25);//
                btAddLivro.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        // assign the book name, genre, price
                    	g.dispose();
                        boolean vNome = false;
                        boolean vAutor = false;
                        boolean vPreco = false;
                        //validar nome dolivro
                        String nomeLivro = F_bname.getText();
                        if (nomeLivro.equals("")) {
                            JOptionPane.showMessageDialog(null, "Por favor introduza um nome do livro");
                            vNome = false;

                        } else {
                            vNome = true;

                        }
                        //* fim validar nome dolivro
                        String genero = String.valueOf(cbGenero.getSelectedItem());
                        String preco = F_price.getText();
                        int preco_int = 0;
                        String autor = txtAutor.getText();
                        //validar nome do autor

                        if (autor.equals("")) {
                            JOptionPane.showMessageDialog(null, "Por favor introduza um autor");
                            vAutor = false;

                        } else {
                            vAutor = true;

                        }
                        //* fim validar nome do autor
                        //converter preco do integer para int
                        try {
                            preco_int = Integer.parseInt(preco);
                            vPreco = true;
                        } catch (NumberFormatException n) {
                         	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");
                        }

                        Connection connection = Connect.connect();
                        int rl2 = 0;
                        if (vNome == true && vAutor == true && vPreco == true) {
                            try {
                                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY");
                                stmt.executeUpdate("INSERT INTO livros(NOMEL,AUTOR,GENERO,PRECO) VALUES ('" + nomeLivro + "','" + autor + "','" + genero + "'," + preco_int + ")");
                                JOptionPane.showMessageDialog(null, "Livro adicionado!");
                                // adicionar numero livros

                                String sqlx = ("update relatorio1 set CAD_LIVROS = ? where R1ID = ?");

                                try {
                                    try {
                                        Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt1.executeUpdate("USE LIBRARY");
                                        int rid = 1;
                                        Connection connection4 = Connect.connect();
                                        Statement stmt4 = connection4.createStatement();
                                        stmt4.executeUpdate("USE LIBRARY");
                                        ResultSet rs4 = stmt4.executeQuery("SELECT CAD_LIVROS FROM relatorio1 WHERE R1ID=" + rid); //seleciona o email do user com base no seu  id
                                        String nomeUser = null;
                                        while (rs4.next()) {
                                            nomeUser = rs4.getString(1);

                                            rl2 = Integer.parseInt(nomeUser);

                                        }

                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }

                                    int rl3 = rl2 + 1;

                                    PreparedStatement stmt5 = connection.prepareStatement(sqlx);
                                    //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                    stmt5.executeUpdate("USE LIBRARY");

                                    stmt5.setInt(1, rl3);
                                    stmt5.setInt(2, 1);
                                    stmt5.executeUpdate();
                                    //JOptionPane.showMessageDialog(null, "RELATORIO++!");

                                    g.dispose();
                                    AdminMenu.admin_menu();

                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, e1);
                                }
                                // fim adicionar numero livros
                                g.dispose();

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Livro Não adicionado");
                        }

                    }

                });

                ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
                ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

                l1.setForeground(Color.CYAN);
                l3.setForeground(Color.CYAN);
                l2.setForeground(Color.CYAN);
                lbAutor.setForeground(Color.CYAN);

                btAddLivro.setBackground(Color.WHITE);

                JButton novobotao = new JButton("Cancelar");
                novobotao.addActionListener(new ActionListener(){
               	 public void actionPerformed (ActionEvent e) {
               		g.dispose();
               		  AdminMenu.admin_menu();
               		
               	
               	 }
                });
                novobotao.setIcon(new ImageIcon("img/icanc.png"));
                btAddLivro.setIcon(new ImageIcon("img/icerto.png"));
                novobotao.setBackground(Color.WHITE);
                novobotao.setBounds(290, 300, 180, 25);

                g.add(novobotao);
                g.add(l3);
                g.add(btAddLivro);
                g.add(l1);
                g.add(l2);
                g.add(F_bname);
                g.add(cbGenero);
                g.add(F_price);
                g.add(txtAutor);
                g.add(lbAutor);
                g.setSize(800, 500);//  
                g.add(new JLabel(fundo));
                g.pack();
                g.setIconImage(icone.getImage());
                g.setLayout(null);//sem  layout managers  
                g.setVisible(true);//
                g.setLocationRelativeTo(null);

            }
        });

        //actualizar livro
        JButton btActualizarLivro = new JButton("Actualizar livro"); //criar instancia do botao actualizar livros  (mudado de update_book)
        btActualizarLivro.setBounds(205, 260, 180, 25);

        btActualizarLivro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //set frame wot enter book details
            	f.dispose();
                JFrame g = new JFrame("Introduza detalhes do livro");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // set labels
                JLabel l1, l2, l3, l4, lbAutor;
                l1 = new JLabel("Nome");  //lebel 1 para nome do livro
                l1.setBounds(30, 15, 100, 30);
                l1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                l1.setForeground(Color.CYAN);

                l2 = new JLabel("Autor");  //label 2 para genero do livro
                l2.setBounds(30, 65, 100, 30);

                l2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                l2.setForeground(Color.CYAN);

                l3 = new JLabel("Genero");  //label 2 para preco do livro
                l3.setBounds(30, 115, 100, 30); // label para autor
                l3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                l3.setForeground(Color.CYAN);

                lbAutor = new JLabel("Preco");
                lbAutor.setBounds(30, 165, 100, 30);
                //set text field para nome do livro
                l4 = new JLabel("ID LIVRO");  //lebel 1 para ID do livro
                l4.setBounds(480, 15, 100, 30);
                l4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                l4.setForeground(Color.CYAN);
                JButton j1, j2, j3, j4;

                JTextField txtUserID = new JTextField(); //PASSA ID USUAIO
                txtUserID.setBounds(580, 15, 200, 30);

                JTextField F_bname = new JTextField();
                F_bname.setBounds(110, 15, 200, 30);

                j1 = new JButton("Actualizar "); // BOTAO PARA ACTUALIZAR NOME
                j1.setBounds(340, 15, 100, 30);

                JTextField txtAutor = new JTextField();
                txtAutor.setBounds(110, 65, 200, 30);

                j2 = new JButton("Actualizar"); // botao para genero
                j2.setBounds(340, 115, 100, 30);  //
                //set text field for genre 
                JComboBox<String> cbGenero = new JComboBox(); // combobox para generos
                cbGenero.addItem("Matemática");
                cbGenero.addItem("HQ");
                cbGenero.addItem("Romance");
                cbGenero.addItem("Literatura");
                cbGenero.addItem("Literatura Moçambicana");
                cbGenero.addItem("Religiao");
                cbGenero.addItem("Ficcao Científica");
                cbGenero.addItem("Culinária");
                cbGenero.addItem("Filosofia");
                cbGenero.addItem("Outro");

                //JTextField F_genre=new JTextField();
                cbGenero.setBounds(110, 115, 200, 30);

                j3 = new JButton("Actualizar");   //botao para preco
                j3.setBounds(340, 165, 100, 30);  //

                //set text field for price
                JTextField F_price = new JTextField();
                F_price.setBounds(110, 165, 200, 30);

                j4 = new JButton(" Actualizar");  // botao para autor
                j4.setBounds(340, 65, 100, 30);  // (30, 65, 100, 30);(340, 165, 100, 30) (340, 165, 100, 30);

                j1.addActionListener(new ActionListener() {

                    String idLivro = null;
                    int idLivroInt;
                    String nomeLivro = null; // ira receber nome do livro
                    boolean vNome = false;
                    boolean vID = false;

                    public void actionPerformed(ActionEvent j) {
                        // TODO Auto-generated method stub
                        //validacao do id livro
                        idLivro = txtUserID.getText();
                        try {
                            idLivroInt = Integer.parseInt(idLivro);
                            vID = true;

                        } catch (NumberFormatException l) {
                         	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");
                        }

                        nomeLivro = F_bname.getText();
                        // validacao do nome livro
                        if (nomeLivro.equals("")) {
                            JOptionPane.showMessageDialog(null, "Por favor introduza um nome do livro");
                            vNome = false;

                        } else {
                            vNome = true;
                        }
                        //* fim da validacao  
                        //JOptionPane.showMessageDialog(null, "selected");
                        Connection connection = Connect.connect();
                        if (vNome == true && vID == true) {
                            String sql = ("update livros set nomel = ? where BID = ?");
                            try {
                                PreparedStatement stmt = connection.prepareStatement(sql);
                                //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY");

                                stmt.setString(1, nomeLivro);
                                stmt.setInt(2, idLivroInt);
                                ;
                                //   JOptionPane.showMessageDialog(null, nomeUsuario);
                                stmt.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Actualizado!");

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Não actualizado");
                        }
                    }
                });

                //botao actualizar genero
                j2.addActionListener(new ActionListener() {

                    String idLivro = null;
                    int idLivroInt;
                    boolean vID = false;
                    String generoLivro = null; // ira receber nome do livro

                    public void actionPerformed(ActionEvent j) {
                        // TODO Auto-generated method stub
                        idLivro = txtUserID.getText();
                        try {
                            idLivroInt = Integer.parseInt(idLivro);
                            vID = true;

                        } catch (NumberFormatException l) {
                         	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");
                        }

                        generoLivro = String.valueOf(cbGenero.getSelectedItem());

                        Connection connection = Connect.connect();

                        String sql = ("update livros set GENERO = ? where BID = ?");
                        if (vID == true) {
                            try {
                                PreparedStatement stmt = connection.prepareStatement(sql);
                                //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY");

                                stmt.setString(1, generoLivro);
                                stmt.setInt(2, idLivroInt);
                                ;
                                //   JOptionPane.showMessageDialog(null, nomeUsuario);
                                stmt.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Género actualizado!");

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Género não actualizado");
                        }
                    }

                });
                // fim botao actualizar preco

                j3.addActionListener(new ActionListener() {

                    String idLivro = null;
                    int idLivroInt;
                    String precoLivro = null;

                    double precoLivroReal = 0;
                    boolean vPreco = false;

                    public void actionPerformed(ActionEvent j) {
                        // TODO Auto-generated method stub
                        boolean vID = false;
                        idLivro = txtUserID.getText();
                        try {
                            idLivroInt = Integer.parseInt(idLivro);
                            vID = true;

                        } catch (NumberFormatException l) {
                         	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");
                        }

                        precoLivro = F_price.getText();

                        try {
                            precoLivroReal = Double.parseDouble(precoLivro);
                            vPreco = true;
                        } catch (NumberFormatException k) {
                         	 JOptionPane.showMessageDialog(null, "Digite um preço válido");
                        }
                        Connection connection = Connect.connect();

                        String sql = ("update livros set PRECO = ? where BID = ?");
                        if (vPreco == true && vID == true) {
                            try {
                                PreparedStatement stmt = connection.prepareStatement(sql);
                                //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY");

                                stmt.setDouble(1, precoLivroReal);
                                stmt.setInt(2, idLivroInt);

                                //   JOptionPane.showMessageDialog(null, nomeUsuario);
                                stmt.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Preço actualizado!");

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Preço não actualizado");
                        }
                    }

                });
                // fim botao actualizar preco

                // Actualizar autor
                j4.addActionListener(new ActionListener() {

                    String idLivro = null;
                    int idLivroInt;
                    String autorLivro = null; // ira receber nome do livro
                    boolean vAutor = false;
                    boolean vID = false;

                    public void actionPerformed(ActionEvent j) {
                        // TODO Auto-generated method stub
                        idLivro = txtUserID.getText();
                        try {
                            idLivroInt = Integer.parseInt(idLivro);
                            vID = true;

                        } catch (NumberFormatException l) {
                        	 JOptionPane.showMessageDialog(null, "Digite um ID válido(Nr inteiro)");
                        }

                        autorLivro = txtAutor.getText();
                        if (autorLivro.equals("")) {
                            JOptionPane.showMessageDialog(null, "Por favor introduza um autor");
                            vAutor = false;

                        } else {
                            vAutor = true;

                        }
                        //* fim validar nome do autor
                        //  JOptionPane.showMessageDialog(null, "selected");
                        Connection connection = Connect.connect();

                        String sql = ("update livros set autor = ? where BID = ?");
                        if (vAutor == true && vID == true) {
                            try {
                                PreparedStatement stmt = connection.prepareStatement(sql);
                                //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY");

                                stmt.setString(1, autorLivro);
                                stmt.setInt(2, idLivroInt);
                                ;
                                //   JOptionPane.showMessageDialog(null, nomeUsuario);
                                stmt.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Autor Actualizado!");

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Autor não actualizado");
                        }
                    }

                });
                // fim actualizar autor

                JButton btRetornar = new JButton("Retornar");//criar instancia do botao retornar
                btRetornar.setIcon(new ImageIcon("img/iretorna.png"));
                btRetornar.setBackground(Color.WHITE);
                btRetornar.setBounds(310, 400, 180, 25);//
                btRetornar.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        g.dispose();
                        AdminMenu.admin_menu();

                    }

                });

                ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
                ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

                l1.setForeground(Color.CYAN);
                l3.setForeground(Color.CYAN);
                l2.setForeground(Color.CYAN);
                lbAutor.setForeground(Color.CYAN);

                g.add(l3);
                g.add(l1);
                g.add(l2);
                g.add(F_bname);
                g.add(cbGenero);
                g.add(F_price);
                g.add(txtAutor);
                g.add(lbAutor);
                g.add(j1);
                g.add(j2);
                g.add(j3);
                g.add(j4);
                g.add(l4);
                g.add(txtUserID);
                g.add(btRetornar);
                g.setSize(800, 500);//
                g.add(new JLabel(fundo));
                g.pack();
                g.setIconImage(icone.getImage());
                g.setLayout(null);//sem layout managers  
                g.setVisible(true);// 
                g.setLocationRelativeTo(null);

            }
        });
        //fim actualizar livro

        JButton btAlugarLivro = new JButton("Alugar livro"); //criar instancia do botao
        btAlugarLivro.setBounds(405, 20, 180, 25);

        btAlugarLivro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
                //enter details
                JFrame g = new JFrame("INTRODUZA Dados");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //create labels
                JLabel l1, l2, l3, l4, l5;
                l1 = new JLabel(" ID do livro(BID)");  // Label 1 para id do livro
                l1.setBounds(10, 15, 100, 30);

                l2 = new JLabel("ID Usuario(UID)");  //Label 2 para ID do usuario
                l2.setBounds(10, 53, 100, 30);

                l3 = new JLabel("Periodo(dias)");  //Label 3 para periodo
                l3.setBounds(10, 90, 100, 30);

                LocalDate hoje = LocalDate.now();//pega a data atual;
                l4 = new JLabel("Data de Aluguer:");  //Label 4 para data dde aluguer
                l4.setBounds(600, 10, 100, 30);

                JTextField F_bid = new JTextField();
                F_bid.setBounds(110, 15, 200, 30);

                JTextField F_uid = new JTextField();
                F_uid.setBounds(110, 53, 200, 30);

                JTextField F_period = new JTextField();
                F_period.setBounds(110, 90, 200, 30);

                /*    JTextField F_issue=new JTextField();*/
                l5 = new JLabel(hoje + "");
                l5.setBounds(700, 10, 130, 30);

                JButton cancelar = new JButton("Cancelar");//criar intancia do JButton  
                cancelar.addActionListener(new ActionListener(){
               	 public void actionPerformed (ActionEvent e) {
               		g.dispose();
               		  AdminMenu.admin_menu();
               		
               	
               	 }
                });
                cancelar.setBounds(90, 250, 180, 25);//
                cancelar.setIcon(new ImageIcon("img/icanc.png"));

                JButton btSubmeter = new JButton("Submeter");//criar intancia do JButton  
                btSubmeter.setBounds(90, 200, 180, 25);//
                btSubmeter.setIcon(new ImageIcon("img/icerto.png"));

                btSubmeter.addActionListener(new ActionListener() {
                    String usuario_mail;
                    String usuario_nome;
                    String livro_nome;

                    public void actionPerformed(ActionEvent e) {

                        String uid = F_uid.getText();
                        String bid = F_bid.getText();
                        double livroPor = 0;
                        double valAluguer = 0; // calucula taxa a se pagar
                        String period = F_period.getText();
                        LocalDate dataAtual = LocalDate.now(); // pega a data actual
                        //    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        //  String dataActualFormatada = dateFormat.format(dataAtual); // converte a data actual para string
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String formattedDateTime = dataAtual.format(formatter);
                        String issued_date = formattedDateTime;
                        boolean vID = false;
                        int period_int = 0;
                        try {
                            period_int = Integer.parseInt(period);
                            vID = true;

                        } catch (NumberFormatException l) {
                        	 JOptionPane.showMessageDialog(null, "Digite um periodo válido(Nr inteiro)");
                        }

                        Connection connection = Connect.connect();
                        if (vID == true) {
                            try {   // retorna preco
                                Connection connection3 = Connect.connect();
                                Statement stmt3 = connection3.createStatement();
                                stmt3.executeUpdate("USE LIBRARY");
                                ResultSet rs3 = stmt3.executeQuery("SELECT PRECO FROM LIVROS WHERE BID=" + bid); //seleciona o preco do livro com base no seu  id
                                String price = null;
                                while (rs3.next()) {
                                    price = rs3.getString(1);

                                }
                                double periodoDouble = Double.parseDouble(period);
                                int preco = Integer.parseInt(price); // retorna preco do livro
                                livroPor = (0.05 * preco);
                                valAluguer = periodoDouble * livroPor;// retorna a porcentagem do valor normal
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }

                            try {
                                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY");
                                stmt.executeUpdate("INSERT INTO ALUGUER(UID,VALOR_ALUGUER,BID,DATA_ALUGUER,PERIOD) VALUES ('" + uid + "','" + valAluguer + "','" + bid + "','" + issued_date + "'," + period_int + ")");
                                JOptionPane.showMessageDialog(null, "LIVRO ALUGADO!");
                                // adicionar numero alugueres
                                String sql = ("update relatorio1 set LIVROS_ALUGADOS = ? where R1ID = ?");
                                int rl1 = 0;
                                try {
                                    try {
                                        Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt7.executeUpdate("USE LIBRARY");
                                        int rid = 1;
                                        Connection connection7 = Connect.connect();
                                        Statement stmt8 = connection7.createStatement();
                                        stmt8.executeUpdate("USE LIBRARY");
                                        ResultSet rs7 = stmt8.executeQuery("SELECT LIVROS_ALUGADOS FROM relatorio1 WHERE R1ID=" + rid); //seleciona o email do user com base no seu  id
                                        String nomeUser = null;
                                        while (rs7.next()) {
                                            nomeUser = rs7.getString(1);

                                            rl1 = Integer.parseInt(nomeUser);
                                            // JOptionPane.showMessageDialog(null, "num XXX: " + rl1);
                                        }

                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }
                                    //  int rel1 = rl1+1;
                                    PreparedStatement stmt9 = connection.prepareStatement(sql);
                                    //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                    stmt9.executeUpdate("USE LIBRARY");

                                    stmt9.setInt(1, ++rl1);
                                    stmt9.setInt(2, 1);
                                    stmt9.executeUpdate();
                                    // JOptionPane.showMessageDialog(null, "RELATORIO++!");

                                   // g.dispose();

                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, e1);
                                }
                                // fim adicionar numero livros alugados

                                //adicionar valor_aluguer
                                String sql2 = ("update relatorio4 set VALOR_ALUGUER = ? where R4ID = ?");
                                double rl4 = 0;
                                double valorAluguerTotal = valAluguer;
                                try {
                                    try {
                                        Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt7.executeUpdate("USE LIBRARY");
                                        int rid = 1;
                                        Connection connection7 = Connect.connect();
                                        Statement stmt8 = connection7.createStatement();
                                        stmt8.executeUpdate("USE LIBRARY");
                                        ResultSet rs7 = stmt8.executeQuery("SELECT VALOR_ALUGUER FROM relatorio4 WHERE R4ID=" + rid); //seleciona o email do user com base no seu  id
                                        String valorAluguer = null;
                                        while (rs7.next()) {
                                            valorAluguer = rs7.getString(1);

                                            rl4 = Double.parseDouble(valorAluguer);

                                        }
                                        valorAluguerTotal += rl4;
                                       // JOptionPane.showMessageDialog(null, valorAluguerTotal);
                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }

                                    //  int rel1 = rl1+1;
                                    PreparedStatement stmt9 = connection.prepareStatement(sql2);
                                    //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                    stmt9.executeUpdate("USE LIBRARY");

                                    stmt9.setDouble(1, valorAluguerTotal);
                                    stmt9.setInt(2, 1);
                                    stmt9.executeUpdate();
                                    ;

                                   // g.dispose();

                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, e1);
                                }

                                //fim adicionar valor aluguer
                                g.dispose();
                                AdminMenu.admin_menu();

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }

                            //busca email
                            try {
                                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt.executeUpdate("USE LIBRARY");

                                Connection connection8 = Connect.connect();
                                Statement stmt8 = connection8.createStatement();
                                stmt8.executeUpdate("USE LIBRARY");
                                ResultSet rs8 = stmt8.executeQuery("SELECT EMAIL FROM USERS WHERE UID=" + uid); //seleciona o email do user com base no seu  id
                                String emailUser = null;
                                while (rs8.next()) {
                                    emailUser = rs8.getString(1);

                                    usuario_mail = emailUser;
                                }

                                Connection connection9 = Connect.connect();
                                Statement stmt9 = connection9.createStatement();
                                stmt9.executeUpdate("USE LIBRARY");
                                ResultSet rs9 = stmt9.executeQuery("SELECT USERNAME FROM USERS WHERE UID=" + uid); //seleciona o email do user com base no seu  id
                                String nomeUser = null;
                                while (rs9.next()) {
                                    nomeUser = rs9.getString(1);

                                    usuario_nome = nomeUser;
                                }

                                Connection connection10 = Connect.connect();
                                Statement stmt10 = connection10.createStatement();
                                stmt10.executeUpdate("USE LIBRARY");
                                ResultSet rs10 = stmt10.executeQuery("SELECT NOMEL FROM LIVROS WHERE BID=" + bid); //seleciona o email do user com base no seu  id
                                String nomeLivro = null;
                                while (rs10.next()) {
                                    nomeLivro = rs10.getString(1);

                                    livro_nome = nomeLivro;
                                }

                            } // 
                            catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }

                            //JOptionPane.showMessageDialog(null, livro_nome);
                            //enviar email ///
                            Properties props = new Properties();
                            /**
                             * Parâmetros de conexão com servidor Gmail
                             */
                            props.put("mail.smtp.host", "smtp.gmail.com");
                            props.put("mail.smtp.socketFactory.port", "465");
                            props.put("mail.smtp.socketFactory.class",
                                    "javax.net.ssl.SSLSocketFactory");
                            props.put("mail.smtp.auth", "true");
                            props.put("mail.smtp.port", "465");

                            Session session = Session.getDefaultInstance(props,
                                    new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication("uembiblioteca2022@gmail.com",
                                            "@biblioteca2022");
                                }
                            });

                            /**
                             * Ativa Debug para sessão
                             */
                            session.setDebug(true);

                            try {

                                Message message = new MimeMessage(session);
                                message.setFrom(new InternetAddress("miltonmaleiane1@gmail.com"));
                                //Remetente

                                Address[] toUser = InternetAddress //Destinatário(s)
                                        .parse(usuario_mail);

                                message.setRecipients(Message.RecipientType.TO, toUser);
                                message.setSubject("Email de confirmacao do aluger do livro");//Assunto
                                message.setText("Caro(a) " + usuario_nome + ". Alugou o livro " + "'" + livro_nome + "'" + " com sucesso," + " tendo pago o valor de " + valAluguer + " MZN pelo aluguer. Por favor, devolva dentro de " + "" + period + " dias.  Obrigado!");
                                /**
                                 * Método para enviar a mensagem criada
                                 */
                                Transport.send(message);

                                System.out.println("Feito!!!");
                               // g.dispose();

                            } catch (MessagingException j) {
                                throw new RuntimeException(j);
                            }

                            //** enviar email **//
                        } else {
                            JOptionPane.showMessageDialog(null, "Preencha os campos correctamente");
                        }
                    }

                });

                ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
                ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

                btSubmeter.setBackground(Color.WHITE);
                cancelar.setBackground(Color.WHITE);
                l1.setForeground(Color.CYAN);
                l3.setForeground(Color.CYAN);
                l2.setForeground(Color.CYAN);
                l4.setForeground(Color.CYAN);
                l5.setForeground(Color.CYAN);

                g.add(l1);
                g.add(l2);
                g.add(l3);
                g.add(l4);
                g.add(btSubmeter);
                g.add(F_uid);
                g.add(F_bid);
                g.add(F_period);
                g.add(l5);
                g.add(cancelar);
                g.setSize(800, 500);// 
                g.add(new JLabel(fundo));
                g.pack();
                g.setIconImage(icone.getImage());
                g.setLayout(null);//sem usar layout managers  
                g.setVisible(true);// 
                g.setLocationRelativeTo(null);

            }
        });

        JButton btRetornarLivro = new JButton("Retornar livro"); //criar instancia do botao retornar livro
        btRetornarLivro.setBounds(405, 260, 180, 25);

        btRetornarLivro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	f.dispose();
                String email;
                JFrame g = new JFrame("INTRODUZA Dados");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //set labels 
                JLabel l1, l2, l3, l4, lbEstado;
                l1 = new JLabel("ID Aluguer(IID)");  //Label 1 for Issue ID
                l1.setBounds(280, 50, 100, 30);

                l4 = new JLabel("DATA DO RETORNO");
                l4.setBounds(100, 55, 150, 30);

                lbEstado = new JLabel("Estado");
                lbEstado.setBounds(280, 250, 150, 30);

                JTextField F_iid = new JTextField();
                F_iid.setBounds(400, 50, 130, 30);

                JTextField F_return = new JTextField();
                F_return.setBounds(400, 100, 130, 30);

                JLabel ld = new JLabel("Dia do Retorno");
                ld.setBounds(280, 100, 150, 30);

                JLabel lm = new JLabel("Mes do Retorno");
                lm.setBounds(280, 150, 150, 30);

                JLabel la = new JLabel("Ano do Retorno");
                la.setBounds(280, 200, 150, 30);

                JTextField mes_retorno = new JTextField();
                mes_retorno.setBounds(400, 150, 130, 30);

                JTextField ano_retorno = new JTextField();
                ano_retorno.setBounds(400, 200, 130, 30);

                JComboBox<String> cbEstado = new JComboBox();
                cbEstado.addItem("Bem conservado");
                cbEstado.addItem("Mal conservado");
                cbEstado.addItem("Critico");
                cbEstado.setBounds(400, 250, 130, 30);

                JButton cancelar = new JButton("Cancelar");//cria instancia do botao retornar
                cancelar.addActionListener(new ActionListener(){
               	 public void actionPerformed (ActionEvent e) {
               		g.dispose();
               		  AdminMenu.admin_menu();
               		
               	
               	 }
                });
                cancelar.setIcon(new ImageIcon("img/icanc.png"));
                cancelar.setBounds(300, 400, 180, 25);//

                JButton btRetornar = new JButton("Devolver Livro");//cria instancia do botao retornar
                btRetornar.setIcon(new ImageIcon("img/icerto.png"));
                btRetornar.setBounds(300, 350, 180, 25);//

                btRetornar.addActionListener(new ActionListener() {
                    double multaEstado;
                    String usuario_mail;
                    long diferencaEmDias;
                    String texto;
                    String textoPeriodo;
                    double multaDias;

                    public void actionPerformed(ActionEvent e) {

                        String iid = F_iid.getText();
                        String idUsuario;

                        String return_date = F_return.getText();
                        String mes_R = mes_retorno.getText();
                        String ano_R = ano_retorno.getText();
                        String estado = String.valueOf(cbEstado.getSelectedItem());
                        Connection connection = Connect.connect();

                        try {
                            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                            stmt.executeUpdate("USE LIBRARY");
                            //Intialize data 1 com valor nulo
                            String date1 = null;
                            String date2 = return_date; //Intialize date2 with return date
                            String mesDeRetorno = mes_R; // inicializa o mes de retorno
                            String anoDeRetorno = ano_R;
                            //edict              
                            //select issue date
                            ResultSet rs = stmt.executeQuery("SELECT DATA_ALUGUER FROM ALUGUER WHERE IID=" + iid);
                            while (rs.next()) {
                                date1 = rs.getString(1);

                            }
                            long diasDiferenca;
                            boolean vDiff = false;
                            try {
                                /*     */

                                Date date_1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1); //converte a string para date
                                LocalDate data1 = date_1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();// converte de date para LocalDate

                                //	 System.out.println( data1 );
                                //  JOptionPane.showMessageDialog(null, date_1 + "teste");
                                int anoR, mesR, diaR;

                                diaR = Integer.parseInt(return_date);
                                mesR = Integer.parseInt(mes_R);
                                anoR = Integer.parseInt(ano_R);
                                LocalDate date_2 = LocalDate.of(anoR, mesR, diaR); // atribui ano mes e dia para a data de retorno
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                String dataFormatada = date_2.format(formatter);
                                return_date = dataFormatada;

                                ///  JOptionPane.showMessageDialog(null, date_2);
                                //subtract the  dates and store in diff
                                // long diff = date_2.getTime() - date_1.getTime();
                                //Convert diff from milliseconds to days 
                                long diff = 0;
                                ex.days = (int) (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

                                //by host
                                // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");// padrao de formatacao da data
                                // LocalDateTime data1 = LocalDateTime.parse(date1, formatter); //formatacao de string para data1
                                // LocalDateTime data2 = LocalDateTime.parse(date2, formatter); // formatacao de string para data2
                                diferencaEmDias = ChronoUnit.DAYS.between(data1, date_2); // calcula diferenca de dias entre a requisicao e devolucao
                                diasDiferenca = diferencaEmDias;
                                System.out.println(diferencaEmDias + "dias de diferenca");
                                if (diferencaEmDias < 0) {
                                    vDiff = true;
                                }
                                //  long diferencaEmDias = ChronoUnit.DAYS.between(date_1, date_2);
                            } catch (ParseException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }

                            //update return date
                            if (vDiff == false) {
                                stmt.executeUpdate("UPDATE ALUGUER SET DATA_RETORNO='" + return_date + "' WHERE IID=" + iid);
                                stmt.executeUpdate("UPDATE ALUGUER SET ESTADO = '" + estado + "' WHERE IID=" + iid);
                                g.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Introduza uma data igual ou após a data de aluguer");
                            }
                            Connection connection1 = Connect.connect();
                            Statement stmt1 = connection1.createStatement();
                            stmt1.executeUpdate("USE LIBRARY");
                            ResultSet rs1 = stmt1.executeQuery("SELECT PERIOD FROM ALUGUER WHERE IID=" + iid); //seleciona periodo
                            //       ResultSet rs2 = stmt1.executeQuery("SELECT BID FROM ALUGUER WHERE BID="+iid);
                            //JOptionPane.showMessageDialog(null,"ID do livro"+rs2);
                            /*   
	                    while (rs2.next()) {
	                       String idLivro =null;
	                       idLivro = rs2.getString(1);
	                    }*/
                            String diff = null;
                            while (rs1.next()) {
                                diff = rs1.getString(1);

                            }
                            int diff_int = Integer.parseInt(diff);
                            //   JOptionPane.showMessageDialog(null, "periodo" + diff_int);

                            // connection 2
                            long periodoUltrapassado = diferencaEmDias - diff_int; // retorna a diferenca de dias que o usuario ultrapassou
                            Connection connection2 = Connect.connect();
                            Statement stmt2 = connection2.createStatement();
                            stmt2.executeUpdate("USE LIBRARY");
                            ResultSet rs2 = stmt2.executeQuery("SELECT BID FROM ALUGUER WHERE IID=" + iid); //seleciona o id do livro book id
                            String pre = null;
                            while (rs2.next()) {
                                pre = rs2.getString(1);

                            }
                            int idL = Integer.parseInt(pre); // retorna o id do livro
                            //    JOptionPane.showMessageDialog(null, "ID do livro" + idL);

                            // fim connection 2
                            // connection 3
                            Connection connection3 = Connect.connect();
                            Statement stmt3 = connection3.createStatement();
                            stmt3.executeUpdate("USE LIBRARY");
                            ResultSet rs3 = stmt3.executeQuery("SELECT PRECO FROM LIVROS WHERE BID=" + idL); //seleciona o preco do livro com base no seu  id
                            String price = null;
                            while (rs3.next()) {
                                price = rs3.getString(1);

                            }
                            int preco = Integer.parseInt(price); // retorna preco do livro
                            //       JOptionPane.showMessageDialog(null, "PRECO" + preco);

                            // fim da connecao 3
                            /*     
	                         
	                        
	                         
	                    }
	                    
                             */
                            double multaTotal = 0;
                            //    double livroPor = (0.5 * preco);// retorna a porcentagem do valor normal
                            double livroPor2 = (0.10 * preco); //retorna a porcentagem do valor multa

                            multaDias = 0; // declaracao e inicializacao de multaDIas
                            if (periodoUltrapassado > 0) {
                                multaDias = livroPor2 * periodoUltrapassado;

                                // MULTA ATRASO
                                String sql = ("update relatorio2 set MULTAS_ATRASO = ? where R2ID = ?");

                                int rl6 = 0;

                                try {
                                    try {
                                        Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt7.executeUpdate("USE LIBRARY");
                                        int rid = 1;
                                        Connection connection7 = Connect.connect();
                                        Statement stmt8 = connection7.createStatement();
                                        stmt8.executeUpdate("USE LIBRARY");
                                        ResultSet rs7 = stmt8.executeQuery("SELECT MULTAS_ATRASO FROM relatorio2 WHERE R2ID=" + rid); //seleciona o email do user com base no seu  id
                                        String multaA = null;
                                        while (rs7.next()) {
                                            multaA = rs7.getString(1);

                                            rl6 = Integer.parseInt(multaA);
                                            //        JOptionPane.showMessageDialog(null, "num XXX: " + rl6);
                                        }

                                        //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }
                                    //  int rel1 = rl1+1;
                                    PreparedStatement stmt9 = connection.prepareStatement(sql);
                                    //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                    stmt9.executeUpdate("USE LIBRARY");

                                    stmt9.setInt(1, ++rl6);
                                    stmt9.setInt(2, 1);
                                    stmt9.executeUpdate();

                                    g.dispose();

                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, e1);
                                }
                                // fim adicionar numero MULTAS ATRASO

                                // multa total
                                String sql2 = ("update relatorio2 set TOT_MULTAS = ? where R2ID = ?");

                                int rl7 = 0;
                                try {
                                    try {
                                        Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt7.executeUpdate("USE LIBRARY");
                                        int rid = 1;
                                        Connection connection8 = Connect.connect();
                                        Statement stmt9 = connection8.createStatement();
                                        stmt9.executeUpdate("USE LIBRARY");

                                        ResultSet rs9 = stmt9.executeQuery("SELECT TOT_MULTAS FROM relatorio2 WHERE R2ID=" + rid); //seleciona o email do user com base no seu  id
                                        String multaT = null;
                                        while (rs9.next()) {
                                            multaT = rs9.getString(1);

                                            rl7 = Integer.parseInt(multaT);
                                            //      JOptionPane.showMessageDialog(null, "M tot: " + rl7);
                                        }
                                        //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);

                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }
                                    //  int rel1 = rl1+1;
                                    PreparedStatement stmt10 = connection.prepareStatement(sql2);
                                    //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                    stmt10.executeUpdate("USE LIBRARY");

                                    stmt10.setInt(1, ++rl7);
                                    stmt10.setInt(2, 1);
                                    stmt10.executeUpdate();

                                    g.dispose();
                                    AdminMenu.admin_menu();
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, e1);
                                }
                                //* multa total

                                // MULTA ATRASO PARTE2
                                String sql3 = ("update relatorio4 set MULTA_ATRASO = ? where R4ID = ?");

                                double rl8 = 0;

                                try {
                                    try {
                                        Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt7.executeUpdate("USE LIBRARY");
                                        int rid = 1;
                                        Connection connection7 = Connect.connect();
                                        Statement stmt8 = connection7.createStatement();
                                        stmt8.executeUpdate("USE LIBRARY");
                                        ResultSet rs7 = stmt8.executeQuery("SELECT MULTA_ATRASO FROM relatorio4 WHERE R4ID=" + rid); //seleciona o email do user com base no seu  id
                                        String multaA = null;
                                        while (rs7.next()) {
                                            multaA = rs7.getString(1);

                                            rl8 = Double.parseDouble(multaA);

                                        }

                                        rl8 += multaDias;

                                        //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }
                                    //  int rel1 = rl1+1;
                                    PreparedStatement stmt9 = connection.prepareStatement(sql3);
                                    //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                    stmt9.executeUpdate("USE LIBRARY");

                                    stmt9.setDouble(1, rl8);
                                    stmt9.setInt(2, 1);
                                    stmt9.executeUpdate();

                                    g.dispose();

                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, e1);
                                }
                                // fim adicionar numero MULTAS ATRASO PARTE2
                            } else {
                                multaDias = 0;
                            }

                            stmt.executeUpdate("UPDATE ALUGUER SET MULTA_DIAS='" + multaDias + "' WHERE IID=" + iid);
                            //    JOptionPane.showMessageDialog(null, "multa dias!" + multaDias);

                            Connection connection4 = Connect.connect();
                            Statement stmt4 = connection4.createStatement();
                            stmt4.executeUpdate("USE LIBRARY");
                            ResultSet rs4 = stmt4.executeQuery("SELECT ESTADO FROM ALUGUER WHERE IID=" + iid); //seleciona o estado do livro com base no seu  id
                            String estadoCon = null;
                            while (rs4.next()) {
                                estadoCon = rs4.getString(1);

                            }

                            //  JOptionPane.showMessageDialog(null, "Estado" + estadoCon);
                            multaEstado = 0.0; // inicializa  a variavel multa conservacao

                            if (estadoCon.equals("Mal conservado")) { // calcula multa consevacao
                                multaEstado = (preco * 0.5);

                                //relatorio multas
                                // MULTA cons1
                                String sql = ("update relatorio2 set MULTAS_CONS = ? where R2ID = ?");

                                int rl6 = 0;

                                try {
                                    try {
                                        Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt7.executeUpdate("USE LIBRARY");
                                        int rid = 1;
                                        Connection connection7 = Connect.connect();
                                        Statement stmt8 = connection7.createStatement();
                                        stmt8.executeUpdate("USE LIBRARY");
                                        ResultSet rs7 = stmt8.executeQuery("SELECT MULTAS_CONS FROM relatorio2 WHERE R2ID=" + rid); //seleciona o email do user com base no seu  id
                                        String multaA = null;
                                        while (rs7.next()) {
                                            multaA = rs7.getString(1);

                                            rl6 = Integer.parseInt(multaA);

                                        }

                                        //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }
                                    //  int rel1 = rl1+1;
                                    PreparedStatement stmt9 = connection.prepareStatement(sql);
                                    //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                    stmt9.executeUpdate("USE LIBRARY");

                                    stmt9.setInt(1, ++rl6);
                                    stmt9.setInt(2, 1);
                                    stmt9.executeUpdate();
                                    //JOptionPane.showMessageDialog(null, "RELATORIO++!");

                                    g.dispose();

                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, e1);
                                }
                                // fim adicionar numero MULTAS cons

                                // multa total
                                String sql2 = ("update relatorio2 set TOT_MULTAS = ? where R2ID = ?");

                                int rl8 = 0;
                                try {
                                    try {
                                        Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt7.executeUpdate("USE LIBRARY");
                                        int rid = 1;
                                        Connection connection8 = Connect.connect();
                                        Statement stmt9 = connection8.createStatement();
                                        stmt9.executeUpdate("USE LIBRARY");

                                        ResultSet rs9 = stmt9.executeQuery("SELECT TOT_MULTAS FROM relatorio2 WHERE R2ID=" + rid); //seleciona o email do user com base no seu  id
                                        String multaT = null;
                                        while (rs9.next()) {
                                            multaT = rs9.getString(1);

                                            rl8 = Integer.parseInt(multaT);

                                        }
                                        //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);

                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }
                                    //  int rel1 = rl1+1;
                                    PreparedStatement stmt10 = connection.prepareStatement(sql2);
                                    //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                    stmt10.executeUpdate("USE LIBRARY");

                                    stmt10.setInt(1, ++rl8);
                                    stmt10.setInt(2, 1);
                                    stmt10.executeUpdate();

                                    g.dispose();

                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, e1);
                                }
                                //* multa total
                                //* relatorio multas
                                // MULTA conservacao PARTE2
                                String sql3 = ("update relatorio4 set MULTA_CONSERVACAO = ? where R4ID = ?");

                                double rl9 = 0;

                                try {
                                    try {
                                        Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt7.executeUpdate("USE LIBRARY");
                                        int rid = 1;
                                        Connection connection7 = Connect.connect();
                                        Statement stmt8 = connection7.createStatement();
                                        stmt8.executeUpdate("USE LIBRARY");
                                        ResultSet rs7 = stmt8.executeQuery("SELECT MULTA_CONSERVACAO FROM relatorio4 WHERE R4ID=" + rid); //seleciona o email do user com base no seu  id
                                        String multaC = null;
                                        while (rs7.next()) {
                                            multaC = rs7.getString(1);

                                            rl9 = Double.parseDouble(multaC);

                                        }

                                        rl9 += multaEstado;

                                        //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }
                                    //  int rel1 = rl1+1;
                                    PreparedStatement stmt9 = connection.prepareStatement(sql3);
                                    //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                    stmt9.executeUpdate("USE LIBRARY");

                                    stmt9.setDouble(1, rl9);
                                    stmt9.setInt(2, 1);
                                    stmt9.executeUpdate();

                                    g.dispose();

                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, e1);
                                }
                                // fim adicionar numero MULTAS conservacao PARTE2

                            } else {
                                if (estadoCon.equals("Critico")) {
                                    multaEstado = (preco);

                                    // valor multa conservacao parte 3
                                    String sql3 = ("update relatorio4 set MULTA_CONSERVACAO = ? where R4ID = ?");

                                    double rl9 = 0;

                                    try {
                                        try {
                                            Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                            stmt7.executeUpdate("USE LIBRARY");
                                            int rid = 1;
                                            Connection connection7 = Connect.connect();
                                            Statement stmt8 = connection7.createStatement();
                                            stmt8.executeUpdate("USE LIBRARY");
                                            ResultSet rs7 = stmt8.executeQuery("SELECT MULTA_CONSERVACAO FROM relatorio4 WHERE R4ID=" + rid); //seleciona o email do user com base no seu  id
                                            String multaC = null;
                                            while (rs7.next()) {
                                                multaC = rs7.getString(1);

                                                rl9 = Double.parseDouble(multaC);

                                            }

                                            rl9 += multaEstado;

                                            //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
                                        } catch (SQLException e1) {
                                            // TODO Auto-generated catch block
                                            JOptionPane.showMessageDialog(null, e1);
                                        }
                                        //  int rel1 = rl1+1;
                                        PreparedStatement stmt9 = connection.prepareStatement(sql3);
                                        //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt9.executeUpdate("USE LIBRARY");

                                        stmt9.setDouble(1, rl9);
                                        stmt9.setInt(2, 1);
                                        stmt9.executeUpdate();

                                        g.dispose();

                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }

                                    //*fim valor multa conservacao parte3 
                                    // MULTA cons2
                                    String sql = ("update relatorio2 set MULTAS_CONS = ? where R2ID = ?");

                                    int rl6 = 0;

                                    try {
                                        try {
                                            Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                            stmt7.executeUpdate("USE LIBRARY");
                                            int rid = 1;
                                            Connection connection7 = Connect.connect();
                                            Statement stmt8 = connection7.createStatement();
                                            stmt8.executeUpdate("USE LIBRARY");
                                            ResultSet rs7 = stmt8.executeQuery("SELECT MULTAS_CONS FROM relatorio2 WHERE R2ID=" + rid); //seleciona o email do user com base no seu  id
                                            String multaA = null;
                                            while (rs7.next()) {
                                                multaA = rs7.getString(1);

                                                rl6 = Integer.parseInt(multaA);

                                            }

                                            //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);
                                        } catch (SQLException e1) {
                                            // TODO Auto-generated catch block
                                            JOptionPane.showMessageDialog(null, e1);
                                        }
                                        //  int rel1 = rl1+1;
                                        PreparedStatement stmt9 = connection.prepareStatement(sql);
                                        //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt9.executeUpdate("USE LIBRARY");

                                        stmt9.setInt(1, ++rl6);
                                        stmt9.setInt(2, 1);
                                        stmt9.executeUpdate();

                                        g.dispose();

                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }
                                    // fim adicionar numero MULTAS cons2
                                    // multa total

                                    String sql2 = ("update relatorio2 set TOT_MULTAS = ? where R2ID = ?");

                                    int rl8 = 0;
                                    try {
                                        try {
                                            Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                            stmt7.executeUpdate("USE LIBRARY");
                                            int rid = 1;
                                            Connection connection8 = Connect.connect();
                                            Statement stmt9 = connection8.createStatement();
                                            stmt9.executeUpdate("USE LIBRARY");

                                            ResultSet rs9 = stmt9.executeQuery("SELECT TOT_MULTAS FROM relatorio2 WHERE R2ID=" + rid); //seleciona o email do user com base no seu  id
                                            String multaT = null;
                                            while (rs9.next()) {
                                                multaT = rs9.getString(1);

                                                rl8 = Integer.parseInt(multaT);

                                            }
                                            //JOptionPane.showMessageDialog(null, "num XXX: "+rl7);

                                        } catch (SQLException e1) {
                                            // TODO Auto-generated catch block
                                            JOptionPane.showMessageDialog(null, e1);
                                        }
                                        //  int rel1 = rl1+1;
                                        PreparedStatement stmt10 = connection.prepareStatement(sql2);
                                        //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                        stmt10.executeUpdate("USE LIBRARY");

                                        stmt10.setInt(1, ++rl8);
                                        stmt10.setInt(2, 1);
                                        stmt10.executeUpdate();

                                        g.dispose();

                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(null, e1);
                                    }
                                    //* multa total
                                }

                            }
                            multaTotal = multaDias + multaEstado;
                            Connection connection5 = Connect.connect();
                            Statement stmt5 = connection5.createStatement();
                            stmt5.executeUpdate("USE LIBRARY");
                            ResultSet rs5 = stmt5.executeQuery("SELECT UID FROM ALUGUER WHERE IID=" + iid); //seleciona o estado do livro com base no seu  id
                            String usuario_id = null;
                            while (rs5.next()) {
                                idUsuario = rs5.getString(1);
                                usuario_id = idUsuario;

                            }
                            int iduser = Integer.parseInt(usuario_id);
                            Connection connection6 = Connect.connect();
                            Statement stmt6 = connection6.createStatement();
                            stmt6.executeUpdate("USE LIBRARY");
                            ResultSet rs6 = stmt6.executeQuery("SELECT EMAIL FROM USERS WHERE UID=" + iduser); //seleciona o email do user com base no seu  id
                            String emailUser = null;
                            while (rs6.next()) {
                                emailUser = rs6.getString(1);

                                usuario_mail = emailUser;

                            }

                            // JOptionPane.showMessageDialog(null, "id usuario" + usuario_id);
                            //     System.out.println("multa conservacao" + multaEstado);
                            stmt.executeUpdate("UPDATE ALUGUER SET MULTA_CONSERVACAO='" + multaEstado + "' WHERE IID=" + iid);
                            stmt.executeUpdate("UPDATE ALUGUER SET MULTA_TOTAL='" + multaTotal + "' WHERE IID=" + iid);

                            JOptionPane.showMessageDialog(null, "Livro retornado!");
                            // adicionar numero RETORNOS DE LIVROS
                            String sql = ("update relatorio1 set LIVROS_DEVOLVIDOS = ? where R1ID = ?");
                            int rl1 = 0;
                            try {
                                try {
                                    Statement stmt7 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                    stmt7.executeUpdate("USE LIBRARY");
                                    int rid = 1;
                                    Connection connection7 = Connect.connect();
                                    Statement stmt8 = connection7.createStatement();
                                    stmt8.executeUpdate("USE LIBRARY");
                                    ResultSet rs7 = stmt8.executeQuery("SELECT LIVROS_DEVOLVIDOS FROM relatorio1 WHERE R1ID=" + rid); //seleciona o email do user com base no seu  id
                                    String nomeUser = null;
                                    while (rs7.next()) {
                                        nomeUser = rs7.getString(1);

                                        rl1 = Integer.parseInt(nomeUser);

                                    }

                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, e1);
                                }
                                //  int rel1 = rl1+1;
                                PreparedStatement stmt9 = connection.prepareStatement(sql);
                                //   Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                stmt9.executeUpdate("USE LIBRARY");

                                stmt9.setInt(1, ++rl1);
                                stmt9.setInt(2, 1);
                                stmt9.executeUpdate();
                                ;

                         

                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                JOptionPane.showMessageDialog(null, e1);
                            }
                            // fim adicionar numero RETORNOS

                            //
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            JOptionPane.showMessageDialog(null, e1);
                        }

                        //enviar email ///
                        Properties props = new Properties();
                        /**
                         * Parâmetros de conexão com servidor Gmail
                         */
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.socketFactory.port", "465");
                        props.put("mail.smtp.socketFactory.class",
                                "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.port", "465");

                        Session session = Session.getDefaultInstance(props,
                                new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication("uembiblioteca2022@gmail.com",
                                        "@biblioteca2022");
                            }
                        });

                        /**
                         * Ativa Debug para sessão
                         */
                        session.setDebug(true);

                        try {

                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("uembiblioteca2022@gmail.com"));
                            //Remetente

                            Address[] toUser = InternetAddress //Destinatário(s)
                                    .parse(usuario_mail);

                            if (multaEstado > 0) {
                                texto = ",poderia ter evitado a multa de " + multaEstado + "MZN conservando bem o livro";
                            } else {
                                texto = "";
                            }

                            if (multaDias > 0) {
                                textoPeriodo = ",pagou  " + multaDias + "MZN de multa por nao ter devolvido o livro dentro do periodo estabelecido";
                            } else {
                                textoPeriodo = "";
                            }

                            message.setRecipients(Message.RecipientType.TO, toUser);
                            message.setSubject("Email de confirmacao do retorno do livro");//Assunto
                            message.setText(" Devolveu o livro com sucesso  " + "" + texto + "" + textoPeriodo);
                            //message.setText(" Mensagem 2 exemp");
                            /**
                             * Método para enviar a mensagem criada
                             */
                            Transport.send(message);

                            System.out.println("Feito!!!");

                        } catch (MessagingException j) {
                            throw new RuntimeException(j);
                        }

                        //** enviar email **// 
                    }

                });

                ImageIcon icone = new ImageIcon("img/iconeGeral.jpg");
                ImageIcon fundo = new ImageIcon("img/fundoGeral.jpg");

                l1.setForeground(Color.CYAN);
                l4.setForeground(Color.CYAN);
                ld.setForeground(Color.CYAN);
                lm.setForeground(Color.CYAN);
                la.setForeground(Color.CYAN);
                lbEstado.setForeground(Color.CYAN);
                cancelar.setBackground(Color.WHITE);
                btRetornar.setBackground(Color.WHITE);

//                g.add(l4);
                g.add(ld);
                g.add(lm);
                g.add(la);
                g.add(btRetornar);
                g.add(cancelar);
                g.add(l1);
                g.add(F_iid);
                g.add(F_return);
                g.add(ano_retorno);
                g.add(mes_retorno);
                g.add(lbEstado);
                g.add(cbEstado);
                g.add(new JLabel(fundo));
                g.pack();
                g.setIconImage(icone.getImage());
                g.setSize(800, 500);//  
                g.setLayout(null);//sem layout managers  
                g.setVisible(true);//
                g.setLocationRelativeTo(null);
            }
        });

        //====================================================================================
//        ImageIcon modelo2 = new ImageIcon("img/modelo1.jpg");
//        JPanel img = new JPanel();
//        img.setBounds(350, 175, 325, 215);
//        img.setBackground(new Color(0f, 0f, 0f, 0f));
//        img.add(new JLabel(modelo2));
//        f.add(img);
//        create_but.setIcon(new ImageIcon("img/ireset.png"));
       
        sair.setIcon(new ImageIcon("img/icanc.png"));
        sair.setBackground(Color.WHITE);
        sair.setBounds(350, 400, 100, 25);

        btRetornarLivro.setIcon(new ImageIcon("img/iretorna.png"));
        btVerUsuarios.setIcon(new ImageIcon("img/iusers.png"));
        btVerLivros.setIcon(new ImageIcon("img/ibooks.png"));
        btAlugarLivro.setIcon(new ImageIcon("img/iissue.png"));
        btAlugados.setIcon(new ImageIcon("img/ivi.png"));
        remove_alugueres.setIcon(new ImageIcon("img/iapag.png"));
        btRemoveLivro.setIcon(new ImageIcon("img/iapag.png"));
        remove_usuarios.setIcon(new ImageIcon("img/iapag.png"));
        btAdicionarLivro.setIcon(new ImageIcon("img/iad.png"));
        btAdicionarUsuario.setIcon(new ImageIcon("img/iadus.png"));
        proc_livro.setIcon(new ImageIcon("img/ipesquisar.png"));
        proc_user.setIcon(new ImageIcon("img/ipesquisar.png"));
        btFeedback.setIcon(new ImageIcon("img/iissue.png"));
        btAtualizarUsuario.setIcon(new ImageIcon("img/ireset.png"));
        btRelatorio.setIcon(new ImageIcon("img/ivi.png"));
        btActualizarLivro.setIcon(new ImageIcon("img/ireset.png"));

//        create_but.setBackground(Color.WHITE);
        btFeedback.setBackground(Color.WHITE);
        btRetornarLivro.setBackground(Color.WHITE);
        btAlugarLivro.setBackground(Color.WHITE);
        btAdicionarLivro.setBackground(Color.WHITE);
        btAlugados.setBackground(Color.WHITE);
        btVerUsuarios.setBackground(Color.WHITE);
        btVerLivros.setBackground(Color.WHITE);
        btAdicionarUsuario.setBackground(Color.WHITE);
        proc_user.setBackground(Color.WHITE);
        proc_livro.setBackground(Color.WHITE);
        btRemoveLivro.setBackground(Color.WHITE);
        remove_alugueres.setBackground(Color.WHITE);
        remove_usuarios.setBackground(Color.WHITE);
        btAtualizarUsuario.setBackground(Color.WHITE);
        btRelatorio.setBackground(Color.WHITE);
        btActualizarLivro.setBackground(Color.WHITE);

//        f.add(create_but);
        f.add(sair);
        f.add(btRetornarLivro);
        f.add(btAlugarLivro);
        f.add(btAdicionarLivro);
        f.add(btAlugados);
        f.add(btVerUsuarios);
        f.add(btVerLivros);
        f.add(btAdicionarUsuario);
        f.add(proc_user);
        f.add(proc_livro);
        f.add(btRemoveLivro);
        f.add(remove_alugueres);
        f.add(remove_usuarios);
        f.add(btFeedback);
        f.add(btActualizarLivro);
        f.add(btAtualizarUsuario);
        f.add(btRelatorio);
        f.add(new JLabel(fundo));
        f.pack();
        f.setIconImage(icone.getImage());
        f.setSize(805, 500);
        f.setLayout(null);//sem layoit manager 
        f.setVisible(true);//
        f.setLocationRelativeTo(null);

    }
}

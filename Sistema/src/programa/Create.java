package programa;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;


//import com.mysql.cj.xdevapi.Statement;

public class Create {
	public static void create() {
	    try {
	    Connection connection= Connect.connect();
	    ResultSet resultSet = connection.getMetaData().getCatalogs();
	    //
	        while (resultSet.next()) {
	          
	          String databaseName = resultSet.getString(1);
	          if(databaseName.equals("library")) {
	              //System.out.print("sim");
	              Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	              //
	              String sql = "DROP DATABASE library";
	              ((java.sql.Statement) stmt).executeUpdate(sql);
	          }
	        }
	          Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	           
	          String sql = "CREATE DATABASE LIBRARY"; //criar base de dados;
	          ((java.sql.Statement) stmt).executeUpdate(sql); 
	          ((java.sql.Statement) stmt).executeUpdate("USE LIBRARY"); //Use Database
	          //
	          String sql1 = "CREATE TABLE USERS(UID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, USERNAME VARCHAR(30), PASSWORD VARCHAR(30), NUMERO_BI VARCHAR(30), RESIDENCIA VARCHAR(30), EMAIL VARCHAR(30),  ADMIN BOOLEAN)";
	          ((java.sql.Statement) stmt).executeUpdate(sql1);
	          //Insert into  tabela uarios
	          ((java.sql.Statement) stmt).executeUpdate("INSERT INTO USERS(USERNAME, PASSWORD,NUMERO_BI,RESIDENCIA,EMAIL, ADMIN) VALUES('admin','admin','12345','fomento','miltonplay2k@gmail.com',TRUE)");
	          //
	          ((java.sql.Statement) stmt).executeUpdate("CREATE TABLE LIVROS(BID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, NOMEL VARCHAR(50), AUTOR VARCHAR(50),  GENERO VARCHAR(20), PRECO INT)");
	          //Criar  Tabela livros alugados
	          ((java.sql.Statement) stmt).executeUpdate("CREATE TABLE ALUGUER(IID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, UID INT, BID INT, DATA_ALUGUER VARCHAR(20), DATA_RETORNO VARCHAR(20), PERIOD INT, VALOR_ALUGUER DECIMAL(10,0), ESTADO VARCHAR(20), MULTA_DIAS DECIMAL(10,0), MULTA_CONSERVACAO DECIMAL(10,0), MULTA_TOTAL DECIMAL(10,0))");
	          //Insert into  tabela livros
	          ((java.sql.Statement) stmt).executeUpdate("INSERT INTO BOOKS(BNAME, GENRE, PRICE) VALUES ('War and Peace', 'Mystery', 200),  ('The Guest Book', 'Fiction', 300), ('The Perfect Murder','Mystery', 150), ('Accidental Presidents', 'Biography', 250), ('The Wicked King','Fiction', 350)");
	           
	          //
	          ((java.sql.Statement) stmt).executeUpdate("CREATE TABLE FEEDBACK(FID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, SUGESTAO TEXT, RECLAMACAO TEXT, SATISFACAO VARCHAR(20), USUARIO VARCHAR(20))");
	          //Criar relatorio1 tabela
	          ((java.sql.Statement) stmt).executeUpdate("CREATE TABLE relatorio1(R1ID INT ,CAD_USERS INT, CAD_LIVROS INT , LIVROS_ALUGADOS INT, LIVROS_DEVOLVIDOS INT)");
	          ((java.sql.Statement) stmt).executeUpdate("CREATE TABLE relatorio2(R2ID INT ,MULTAS_ATRASO INT, MULTAS_CONS INT , TOT_MULTAS INT)");
	          ((java.sql.Statement) stmt).executeUpdate("CREATE TABLE relatorio3(R3ID INT ,FEED_TOTAL INT, M_INSA INT,INSA INT, SATISF INT, M_SATISF INT)");
	          ((java.sql.Statement) stmt).executeUpdate("CREATE TABLE relatorio4(R4ID INT ,MULTA_ATRASO DECIMAL(10,0), MULTA_CONSERVACAO DECIMAL(10,0),MULTA_TOTAL DECIMAL(10,0))");
	   // ESTADO, MULTA_DIAS, MULTA_CONSERVACAO
	          
	          // QUERRY RELATORIO 2
	          // INSERT INTO RELATORIO2 (R2ID,MULTAS_ATRASO, MULTAS_CONS, TOT_MULTAS) VALUES(1,0,0,0);

	       // QUERRY RELATORIO 3
	          // INSERT INTO RELATORIO3 (R3ID, FEED_TOTAL, M_INSA, INSA,SATISF,M_SATISF ) VALUES(1,0,0,0,0);
	          
	       // QUERRY RELATORIO 4
	          // INSERT INTO RELATORIO4 (R4ID, MULTA_ATRASO, MULTA_CONSERVACAO,MULTA_TOTAL ) VALUES(1,0,0,0);
	          resultSet.close();
	    }
	     catch (Exception ex) {
	         ex.printStackTrace();
	         
	         
	}
	}
}

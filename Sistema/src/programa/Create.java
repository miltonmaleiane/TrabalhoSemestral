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
	    //iterate each catalog in the ResultSet
	        while (resultSet.next()) {
	          // Get the database name, which is at position 1
	          String databaseName = resultSet.getString(1);
	          if(databaseName.equals("library")) {
	              //System.out.print("yes");
	              Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	              //Drop database if it pre-exists to reset the complete database
	              String sql = "DROP DATABASE library";
	              ((java.sql.Statement) stmt).executeUpdate(sql);
	          }
	        }
	          Statement stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	           
	          String sql = "CREATE DATABASE LIBRARY"; //Create Database
	          ((java.sql.Statement) stmt).executeUpdate(sql); 
	          ((java.sql.Statement) stmt).executeUpdate("USE LIBRARY"); //Use Database
	          //Create Users Table
	          String sql1 = "CREATE TABLE USERS(UID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, USERNAME VARCHAR(30), PASSWORD VARCHAR(30), NUMERO_BI VARCHAR(30), RESIDENCIA VARCHAR(30), EMAIL VARCHAR(30),  ADMIN BOOLEAN)";
	          ((java.sql.Statement) stmt).executeUpdate(sql1);
	          //Insert into users table
	          ((java.sql.Statement) stmt).executeUpdate("INSERT INTO USERS(USERNAME, PASSWORD,NUMERO_BI,RESIDENCIA,EMAIL, ADMIN) VALUES('admin','admin','12345','fomento','miltonplay2k@gmail.com',TRUE)");
	          //Create Books table
	          ((java.sql.Statement) stmt).executeUpdate("CREATE TABLE LIVROS(BID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, NOMEL VARCHAR(50), AUTOR VARCHAR(50),  GENERO VARCHAR(20), PRECO INT)");
	          //Create Issued Table
	          ((java.sql.Statement) stmt).executeUpdate("CREATE TABLE ALUGUER(IID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, UID INT, BID INT, DATA_ALUGUER VARCHAR(20), DATA_RETORNO VARCHAR(20), PERIOD INT, FINE INT, ESTADO VARCHAR(20), MULTA_DIAS DECIMAL(10,0), MULTA_CONSERVACAO DECIMAL(10,0), MULTA_TOTAL DECIMAL(10,0))");
	          //Insert into books table
	          ((java.sql.Statement) stmt).executeUpdate("INSERT INTO BOOKS(BNAME, GENRE, PRICE) VALUES ('War and Peace', 'Mystery', 200),  ('The Guest Book', 'Fiction', 300), ('The Perfect Murder','Mystery', 150), ('Accidental Presidents', 'Biography', 250), ('The Wicked King','Fiction', 350)");
	           
	   // ESTADO, MULTA_DIAS, MULTA_CONSERVACAO
	          resultSet.close();
	    }
	     catch (Exception ex) {
	         ex.printStackTrace();
	         
	         
	}
	}
}

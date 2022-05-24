package programa;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	public static Connection connect() //workd 
	{
	try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        //System.out.println("Logado ao driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root&password=123456");
	        //System.out.println("Connectedado ao MySQL");
	        return con;
	 } 
	 catch (Exception ex) {
	        ex.printStackTrace();
	 }
	return null;
	}
}

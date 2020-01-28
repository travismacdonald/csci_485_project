
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main485 {
	static Connection con;
	static Statement stmt;
	static ResultSet rs;
	
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		String host = "23.229.237.194:3306/", user = "p485", pass = "project485", name = "project485";
		try {
			System.out.println("Attempting to connect to the database . . .");
			con = DriverManager.getConnection("jdbc:mysql://" + host + name, user, pass);
			System.out.println("Connection to database successful!");
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM events");
			while (rs.next()) {
				System.out.println("Query Result: " + rs.getString(1));
			}
			//stmt.executeUpdate("INSERT INTO events VALUES('java', 0000-00-00, 'javaTest', 'MULH', 'CSCI', 20)");
			con.close();
		} catch (Exception e) {
			System.out.println("Error connecting to the database.");
			e.printStackTrace();
		}
		System.out.println("\nSession Complete.");
	}
}
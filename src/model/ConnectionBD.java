package model;
import java.sql.*;
public class ConnectionBD {
	private static ConnectionBD BD;
	private java.sql.Connection connect;
	Statement St;
	private ConnectionBD() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_projetjee", "root", "");
		System.out.println("Connection BD ok");
		St = connect.createStatement();
	}
	
	public static ConnectionBD getConnectionBD() throws ClassNotFoundException, SQLException{
		if(null==BD){
			BD = new ConnectionBD();
		}
		
		return BD;
	}
	public ResultSet Query(String request) throws SQLException{
		
		return St.executeQuery(request);
		
	}
	
	public java.sql.Connection getConnect() {
		return connect;
	}

	public void setConnect(java.sql.Connection connect) {
		this.connect = connect;
	}

	public int QueryUpdate(String update) throws SQLException{
		return St.executeUpdate(update);
	}
	
	
	public void close() throws SQLException{
		St.close();
		connect.close();
	}
	
}

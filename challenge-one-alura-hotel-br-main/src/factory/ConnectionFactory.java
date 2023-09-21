package factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	 private DataSource dataSource;
	 
	 public ConnectionFactory() {
		 ComboPooledDataSource cpds = new ComboPooledDataSource();
		 cpds.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimezone=true&serverTimezone=UTC");
		 cpds.setUser("root");
		 cpds.setPassword("12345");
		 this.dataSource = cpds;
	}
	
	public Connection RecuperaConexao() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	 
//	public Connection conect () throws SQLException {
//	 return DriverManager.getConnection("jdbc:mysql://localhost/hotel_alura?useTimezone=true&serverTimezone=UTC", "root", "12345");
//	}
	
}

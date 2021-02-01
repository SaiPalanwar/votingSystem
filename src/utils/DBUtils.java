package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DBUtils {
		static Connection fetchDBConnection() throws ClassNotFoundException,SQLException
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3307/acts?useSSL=false&allowPublicKeyRetrieval=true";
			return DriverManager.getConnection(url, "root", "sai9860");
		}
}

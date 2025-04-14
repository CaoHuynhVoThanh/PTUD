package connectDB;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectDB {
	private Connection con;
	private static ConnectDB instance = new ConnectDB();
	
	public static ConnectDB getInstance() {
		return instance;
	}
	public void connect() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QLNH;trustServerCertificate=true;encrypt=true";
		String user = "sa";
		String password = "sapassword";
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("OK");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                connect(); // Mở lại kết nối nếu cần
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    private boolean isClosed() {
        try {
            return con == null || con.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
}
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.KhuVuc;

public class KhuVuc_DAO {
	public static ArrayList<KhuVuc> getAllKhuVuc() {
		ConnectDB.getInstance().connect();;
		Connection conN = ConnectDB.getInstance().getConnection();
		ArrayList<KhuVuc> dskv = new ArrayList<>();
		String sql = "Select * from KhuVuc";
		try {
			Statement st = conN.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	        	String ma = rs.getString("maKV");
	        	String ten = rs.getString("tenKV");
	        	Double phuThu = rs.getDouble("phuThu");
	        	KhuVuc kv = new KhuVuc(ma, ten, phuThu);
	        	dskv.add(kv);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return dskv;
	}
}
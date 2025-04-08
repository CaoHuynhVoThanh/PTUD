package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.Mon;

public class Mon_DAO {
	public static ArrayList<Mon> getAllMon() {
		ArrayList<Mon> dsMon = new ArrayList<Mon>();
		Connection con = ConnectDB.getInstance().getConnection();
		String sql = "{call getAllMonAn}";
		CallableStatement stmt = null;
		try {
			stmt = con.prepareCall(sql);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	        	String maMon= rs.getString("maMon");
	        	String tenMon = rs.getString("tenMon");
	        	String loaiMon = rs.getString("loaiMon");
	        	double donGia = rs.getDouble("donGia");
	        	String hinhAnh = rs.getString("hinhAnh");
	        	Mon mon = new Mon(maMon, tenMon, loaiMon, donGia, hinhAnh);
	        	dsMon.add(mon);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return dsMon;
	}
	public static Mon getMonTheoTen(String tenMon) {
        for (Mon mon : getAllMon()) {
            if (mon.getTenMon().equalsIgnoreCase(tenMon)) {
                return mon;
            }
        }
        return null;
    }
}

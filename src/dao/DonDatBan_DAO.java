package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import connectDB.ConnectDB;

public class DonDatBan_DAO {
	public static int getSLDDBHomNay() {
		ConnectDB.getInstance().connect();;
		Connection conN = ConnectDB.getInstance().getConnection();
		String sql = "SELECT COUNT(*) AS soLuong FROM DonDatBan WHERE CAST(thoiGianDat AS DATE) = CAST(GETDATE() AS DATE);";
		int sl=0;
		try {
			Statement st = conN.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	        	sl = rs.getInt("soLuong");
	        	}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return sl;
	}
	 public static boolean insertDonDatBan(String maDDB, String maHD, String maNV, String maKH,
            LocalDateTime thoiGianDat, LocalDateTime thoiGianNhan,
            int soKhach, double tienCoc, boolean trangThai) {
			String sql = "INSERT INTO DonDatBan (maDDB, maHD, maNV, maKH, thoiGianDat, thoiGianNhan, soKhach, tienCoc, trangThai) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				try (Connection con = ConnectDB.getInstance().getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
				
				pst.setString(1, maDDB);
				pst.setString(2, maHD);
				pst.setString(3, maNV);
				pst.setString(4, maKH);
				pst.setTimestamp(5, Timestamp.valueOf(thoiGianDat));
				pst.setTimestamp(6, Timestamp.valueOf(thoiGianNhan));
				pst.setInt(7, soKhach);
				pst.setDouble(8, tienCoc);
				pst.setBoolean(9, trangThai);
				
				int rowsInserted = pst.executeUpdate();
				return rowsInserted > 0;
				
				} catch (SQLException e) {
				e.printStackTrace();
				return false;
		}
	}
}

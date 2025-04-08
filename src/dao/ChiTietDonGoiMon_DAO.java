package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entities.ChiTietDonGoiMon;
import entities.DonGoiMon;

public class ChiTietDonGoiMon_DAO {
	public static boolean themChiTietDonGoiMon(ChiTietDonGoiMon chiTietDGM) {
		int n = 0;
		ConnectDB.getInstance();
		Connection conN = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		String sql = "INSERT INTO ChiTietDonGoiMon ( maMon, maDGM, soLuong, soLuongDaThanhToan) VALUES (?,?,?,?)";
		try {
			pstm = conN.prepareStatement(sql);
			pstm.setString(1, chiTietDGM.getMaMon());
			pstm.setString(2, chiTietDGM.getMaDGM());
			pstm.setInt(3, chiTietDGM.getSoLuong());
			pstm.setInt(4, chiTietDGM.getSoLuongDaThanhToan());
			n = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
    }
}

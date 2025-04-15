package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.DonDatBan;
import entities.HoaDon;

public class DonDatBan_DAO {
	public static int getSLDDBHomNay() {
		ConnectDB.getInstance().connect();
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
            int soKhach, double tienCoc, int trangThai) {
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
				pst.setInt(9, trangThai);
				
				int rowsInserted = pst.executeUpdate();
				return rowsInserted > 0;
				
				} catch (SQLException e) {
				e.printStackTrace();
				return false;
		}
	}
	 public static boolean insertChiTietDonDatBan(String maDDB, String maBan, String maDGM) {
        String sql = "INSERT INTO ChiTietDonDatBan (maDDB, maBan, maDGM) VALUES (?, ?, ?)";
        ConnectDB.getInstance().connect();;
		Connection conN = ConnectDB.getInstance().getConnection();
        try{
             PreparedStatement pst = conN.prepareStatement(sql);
            pst.setString(1, maDDB);
            pst.setString(2, maBan);
            pst.setString(3, maDGM);

            int rowsInserted = pst.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	 public static int demSoKhachTrongNgayHienTai() {
		    int soKhach = 0;
		    String query = """
		            SELECT COUNT(*)
		            FROM DonDatBan
		            WHERE CAST(thoiGianNhan AS DATE) = CAST(GETDATE() AS DATE)
		            """;

		    ConnectDB.getInstance().connect();
		    Connection conN = ConnectDB.getInstance().getConnection();

		    try {
		        PreparedStatement stmt = conN.prepareStatement(query);
		        ResultSet rs = stmt.executeQuery();

		        if (rs.next()) {
		            soKhach = rs.getInt(1);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return soKhach;
		}
	 public static ArrayList<DonDatBan> getDonDatBanTheoNgay(LocalDate ngay) {
		    ArrayList<DonDatBan> list = new ArrayList<>();
		    String sql = "SELECT * FROM DonDatBan WHERE CAST(thoiGianNhan AS DATE) = ? AND trangThai = 0";
		    
		    ConnectDB.getInstance().connect();
		    Connection con = ConnectDB.getInstance().getConnection();
		    
		    try {
		        PreparedStatement pst = con.prepareStatement(sql);
		        pst.setDate(1, java.sql.Date.valueOf(ngay)); // Convert từ LocalDate sang java.sql.Date

		        ResultSet rs = pst.executeQuery();
		        while (rs.next()) {
		            String maDDB = rs.getString("maDDB");
		            String maHD = rs.getString("maHD");
		            String maNV = rs.getString("maNV");
		            String maKH = rs.getString("maKH");
		            Timestamp thoiGianDat = rs.getTimestamp("thoiGianDat");
		            Timestamp thoiGianNhan = rs.getTimestamp("thoiGianNhan");
		            int soKhach = rs.getInt("soKhach");
		            double tienCoc = rs.getDouble("tienCoc");
		            int trangThai = rs.getInt("trangThai");

		            DonDatBan ddb = new DonDatBan(
		                maDDB, maHD, maNV, maKH, 
		                thoiGianDat.toLocalDateTime(), 
		                thoiGianNhan.toLocalDateTime(), 
		                soKhach, tienCoc, trangThai
		            );
		            list.add(ddb);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return list;
		}
	 public static ArrayList<DonDatBan> getDonDatBanTheoNgayChuaTT(LocalDate ngay) {
		    ArrayList<DonDatBan> list = new ArrayList<>();
		    String sql = """
		        SELECT * 
		        FROM DonDatBan 
		        WHERE CAST(thoiGianNhan AS DATE) = ? 
		        AND trangThai = 0 
		        AND maHD IS NULL
		    """;

		    ConnectDB.getInstance().connect();
		    Connection con = ConnectDB.getInstance().getConnection();

		    try {
		        PreparedStatement pst = con.prepareStatement(sql);
		        pst.setDate(1, java.sql.Date.valueOf(ngay)); // Convert LocalDate → java.sql.Date

		        ResultSet rs = pst.executeQuery();
		        while (rs.next()) {
		            String maDDB = rs.getString("maDDB");
		            String maHD = rs.getString("maHD");
		            String maNV = rs.getString("maNV");
		            String maKH = rs.getString("maKH");
		            Timestamp thoiGianDat = rs.getTimestamp("thoiGianDat");
		            Timestamp thoiGianNhan = rs.getTimestamp("thoiGianNhan");
		            int soKhach = rs.getInt("soKhach");
		            double tienCoc = rs.getDouble("tienCoc");
		            int trangThai = rs.getInt("trangThai");

		            DonDatBan ddb = new DonDatBan(
		                maDDB, maHD, maNV, maKH,
		                thoiGianDat.toLocalDateTime(),
		                thoiGianNhan.toLocalDateTime(),
		                soKhach, tienCoc, trangThai
		            );
		            list.add(ddb);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return list;
		}
	 public static boolean capNhatTrangThaiDonDatBan(String maDDB, int trangThaiMoi) {
		 	System.out.println(trangThaiMoi);
		 	System.out.println(maDDB);
		    String sql = "UPDATE DonDatBan SET trangThai = ? WHERE maDDB = ?";
		    ConnectDB.getInstance().connect();
		    Connection con = ConnectDB.getInstance().getConnection(); // Giả sử bạn đã có class ConnectDB
		    try (PreparedStatement stmt = con.prepareStatement(sql)) {
		        stmt.setInt(1, trangThaiMoi);
		        stmt.setString(2, maDDB);

		        int rows = stmt.executeUpdate(); // Số dòng bị ảnh hưởng
		        return rows > 0; // Nếu có ít nhất 1 dòng bị ảnh hưởng thì trả về true
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return false;
		}


	 public static boolean capNhatMaHDChoDonDatBan(String maDDB, String maHD) {
		    String sql = "UPDATE DonDatBan SET maHD = ? WHERE maDDB = ?";

		    ConnectDB.getInstance().connect();
		    Connection con = ConnectDB.getInstance().getConnection();

		    try (PreparedStatement stmt = con.prepareStatement(sql)) {
		        stmt.setString(1, maHD);
		        stmt.setString(2, maDDB);

		        int rows = stmt.executeUpdate();
		        return rows > 0; // Trả về true nếu có ít nhất 1 dòng được cập nhật
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return false; // Trường hợp thất bại
		}

}

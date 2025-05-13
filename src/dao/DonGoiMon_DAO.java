package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.DonGoiMon;
public class DonGoiMon_DAO {
	public static boolean themDonGoiMon(DonGoiMon don) {
		int n = 0;
		ConnectDB.getInstance();
		Connection conN = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		String sql = "INSERT INTO DonGoiMon ( maDGM, thoiGianGM, ghiChu) VALUES (?,?, ?)";
		try {
			pstm = conN.prepareStatement(sql);
			pstm.setString(1, don.getMaDGM());
			pstm.setTimestamp(2, Timestamp.valueOf(don.getThoiGianGM()));
			pstm.setString(3, don.getGhiChu());
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
	public static int demSoDonTrongNgay(LocalDate date) {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM DonGoiMon WHERE CAST(thoiGianGM AS DATE) = ?";
	    ConnectDB.getInstance();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    PreparedStatement stmt = null;
	    try {
	        stmt = conN.prepareStatement(sql);
	        stmt.setDate(1, java.sql.Date.valueOf(date)); // Gán giá trị cho tham số 1
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return count;
	}
	public static ArrayList<DonGoiMon> getDonGoiMonTheoBan(String maBan, LocalDate date) {
	    ArrayList<DonGoiMon> dsDon = new ArrayList<>();
	    ConnectDB.getInstance();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    try {
	        String sql = """
	            SELECT d.* 
	            FROM DonGoiMon d 
	            JOIN ChiTietDonDatBan c ON d.maDGM = c.maDGM 
	            WHERE c.maBan = ? AND CAST(d.thoiGianGM AS DATE) = ?
	        """;
	        PreparedStatement stmt = conN.prepareStatement(sql);
	        stmt.setString(1, maBan);
	        stmt.setDate(2, java.sql.Date.valueOf(date)); // Filter by the provided date
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            DonGoiMon don = new DonGoiMon(
	                rs.getString("maDGM"),
	                rs.getTimestamp("thoiGianGM").toLocalDateTime(),
	                rs.getString("ghiChu")
	            );
	            dsDon.add(don);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsDon;
	}

	
	public static DonGoiMon getDonGoiMonTheoMaDDBVaMaBan(String maDDB, String maBan) {
	    DonGoiMon donGoiMon = null;
	    ConnectDB.getInstance();
	    Connection conN = ConnectDB.getInstance().getConnection();

	    try {
	        String sql = "SELECT dgm.maDGM, dgm.thoiGianGM, dgm.ghiChu " +
	                     "FROM DonGoiMon dgm " +
	                     "JOIN ChiTietDonDatBan ct ON dgm.maDGM = ct.maDGM " +
	                     "WHERE ct.maDDB = ? AND ct.maBan = ?";
	        PreparedStatement stmt = conN.prepareStatement(sql);
	        stmt.setString(1, maDDB);
	        stmt.setString(2, maBan);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            String maDGM = rs.getString("maDGM");
	            Timestamp timestamp = rs.getTimestamp("thoiGianGM");
	            LocalDateTime thoiGianGM = timestamp != null ? timestamp.toLocalDateTime() : null;
	            String ghiChu = rs.getString("ghiChu");

	            donGoiMon = new DonGoiMon(maDGM, thoiGianGM, ghiChu);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return donGoiMon;
	}
    public static DonGoiMon getDonGoiMonMoiNhatTrongNgay(String maBan) {
    	String sql = """
    		    SELECT dgm.*
    		    FROM DonGoiMon dgm
    		    JOIN ChiTietDonDatBan ctd ON dgm.maDGM = ctd.maDGM
    		    JOIN DonDatBan ddb ON ddb.maDDB = ctd.maDDB
    		    WHERE ctd.maBan = ?
    		      AND CAST(ddb.thoiGianNhan AS DATE) = CAST(GETDATE() AS DATE)
    		      AND ddb.thoiGianNhan = (
    		          SELECT MAX(ddb2.thoiGianNhan)
    		          FROM DonDatBan ddb2
    		          JOIN ChiTietDonDatBan ctd2 ON ddb2.maDDB = ctd2.maDDB
    		          WHERE ctd2.maBan = ? AND CAST(ddb2.thoiGianNhan AS DATE) = CAST(GETDATE() AS DATE)
    		      )
    		""";

        ConnectDB.getInstance();
	    Connection conN = ConnectDB.getInstance().getConnection();
        try (
       
            PreparedStatement stmt = conN.prepareStatement(sql)
        ) {
            stmt.setString(1, maBan);
            stmt.setString(2, maBan);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	String maDGM = rs.getString("maDGM");
                LocalDateTime thoiGianGM = rs.getTimestamp("thoiGianGM").toLocalDateTime();
                String ghiChu = rs.getString("ghiChu");
                return new DonGoiMon(maDGM, thoiGianGM, ghiChu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

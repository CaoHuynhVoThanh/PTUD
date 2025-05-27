package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entities.KhuyenMai;
import entities.Mon;

public class KhuyenMai_DAO {
	public static ArrayList<KhuyenMai> getAllKhuyenMai() {
	    ConnectDB.getInstance().connect();
	    Connection conN = ConnectDB.getInstance().getConnection();
	    ArrayList<KhuyenMai> dsKM = new ArrayList<>();

	    String sql = "SELECT * FROM ChuongTrinhKhuyenMai";
	    try {
	        PreparedStatement pst = conN.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            String ma = rs.getString("maCTKM");
	            String ten = rs.getString("tenCTKM");
	            double phanTram = rs.getDouble("phanTramKM");
	            Timestamp tgBatDau = rs.getTimestamp("thoiGianBatDau");
	            Timestamp tgKetThuc = rs.getTimestamp("thoiGianKetThuc");

	            LocalDateTime batDau = tgBatDau != null ? tgBatDau.toLocalDateTime() : null;
	            LocalDateTime ketThuc = tgKetThuc != null ? tgKetThuc.toLocalDateTime() : null;
	            String thanhvien = rs.getString("doiTuongApDung");
	            Boolean loai = rs.getBoolean("loai");
	            Boolean gioiHanMoiTaiKhoan = rs.getBoolean("gioiHanMoiTaiKhoan");

	            KhuyenMai km = new KhuyenMai(ma, ten, phanTram, batDau, ketThuc, thanhvien, loai, gioiHanMoiTaiKhoan);
	            dsKM.add(km);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsKM;
	}
	
	public static boolean deleteKhuyenMai(String maKM) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();
	    
	    String sql = "DELETE FROM ChuongTrinhKhuyenMai WHERE maCTKM = ?";
	    
	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, maKM);

	        int rowsAffected = pst.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
//	public static ArrayList<KhuyenMai> searchKhuyenMaiByLoaiTV(String loaiTV) {
//	    ConnectDB.getInstance().connect();
//	    Connection con = ConnectDB.getInstance().getConnection();
//	    ArrayList<KhuyenMai> dsKM = new ArrayList<>();
//
//	    String sql = "SELECT * FROM ChuongTrinhKhuyenMai WHERE loaiThanhVien = ?";
//
//	    try {
//	        PreparedStatement pst = con.prepareStatement(sql);
//	        pst.setString(1, loaiTV);
//
//	        ResultSet rs = pst.executeQuery();
//	        while (rs.next()) {
//	            String ma = rs.getString("maMon");
//	            String ten = rs.getString("tenMon");
//	            String loai = rs.getString("loaiMon");
//	            Double donGia = rs.getDouble("donGia");
//	            String hinhAnh = rs.getString("hinhAnh");
//
//	            Mon mon = new Mon(ma, ten, loai, donGia, hinhAnh);
//	            dsMon.add(mon);
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//
//	    return dsKM;
//	}
	public static ArrayList<KhuyenMai> timKhuyenMaiKetThucTruoc(LocalDateTime ngayChon) {
	    ArrayList<KhuyenMai> dsKM = new ArrayList<>();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();

	    String sql = "SELECT * FROM ChuongTrinhKhuyenMai WHERE thoiGianKetThuc < ?";
	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setTimestamp(1, Timestamp.valueOf(ngayChon));

	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	        	String ma = rs.getString("maCTKM");
	            String ten = rs.getString("tenCTKM");
	            double phanTram = rs.getDouble("phanTramKM");
	            LocalDateTime batDau = rs.getTimestamp("thoiGianBatDau").toLocalDateTime();
	            LocalDateTime ketThuc = rs.getTimestamp("thoiGianKetThuc").toLocalDateTime();

	            String thanhvien = rs.getString("doiTuongApDung");
	            Boolean loai = rs.getBoolean("loai");
	            Boolean gioiHanMoiTaiKhoan = rs.getBoolean("gioiHanMoiTaiKhoan");

	            KhuyenMai km = new KhuyenMai(ma, ten, phanTram, batDau, ketThuc, thanhvien, loai, gioiHanMoiTaiKhoan);
	            dsKM.add(km);          
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsKM;
	}
	
	public static boolean themKhuyenMai(KhuyenMai km) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();

	    String sql = "INSERT INTO ChuongTrinhKhuyenMai (maCTKM, tenCTKM, phanTramKM, thoiGianBatDau, thoiGianKetThuc, doiTuongApDung, loai, gioiHanMoiTaiKhoan) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, km.getMa());
	        pst.setString(2, km.getTen());
	        pst.setDouble(3, km.getPhanTram());
	        pst.setTimestamp(4, Timestamp.valueOf(km.getThoiGianBatDau()));
	        pst.setTimestamp(5, Timestamp.valueOf(km.getThoiGianKetThuc()));
	        pst.setString(6, km.getThanhVien());
	        pst.setBoolean(7, km.getLoai());
	        pst.setBoolean(8, km.isGioiHanMoiTaiKhoan());
            

	        int rowsAffected = pst.executeUpdate();
	        return rowsAffected > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	public static boolean updateKhuyenMai(KhuyenMai khuyenMai) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getInstance().getConnection();
	    String sql = "UPDATE ChuongTrinhKhuyenMai SET tenCTKM = ?, phanTramKM = ?, thoiGianBatDau = ?, thoiGianKetThuc = ?, doiTuongApDung = ?, loai = ?, gioiHanMoiTaiKhoan = ? WHERE maCTKM = ?";

	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, khuyenMai.getTen());
	        pst.setDouble(2, khuyenMai.getPhanTram());
	        // Chuyển đổi từ java.util.Date sang java.sql.Date
	        LocalDateTime localDateTimeBatDau = khuyenMai.getThoiGianBatDau();
	        java.sql.Date ngayBatDauSQL = java.sql.Date.valueOf(localDateTimeBatDau.toLocalDate());
	        
	        LocalDateTime localDateTimeKetThuc = khuyenMai.getThoiGianKetThuc();
	        java.sql.Date ngayKetThucSQL = java.sql.Date.valueOf(localDateTimeKetThuc.toLocalDate());
	        pst.setDate(3, ngayBatDauSQL);
	        pst.setDate(4, ngayKetThucSQL);
	        pst.setString(5, khuyenMai.getThanhVien());
	        pst.setBoolean(6, khuyenMai.getLoai());
	        pst.setBoolean(7, khuyenMai.isGioiHanMoiTaiKhoan());
	        pst.setString(8, khuyenMai.getMa());

	        // Thực thi câu lệnh SQL
	        int rowsAffected = pst.executeUpdate();

	        // Kiểm tra số dòng bị ảnh hưởng, nếu > 0 có nghĩa là cập nhật thành công
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static String sinhMaKhuyenMai() {
	    try {
	        ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getInstance().getConnection();

	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
	        String datePart = now.format(formatter);

	        String prefix = "KM" + datePart;

	        String sql = "SELECT maCTKM FROM ChuongTrinhKhuyenMai WHERE maCTKM LIKE ?";

	        try (PreparedStatement pst = con.prepareStatement(sql)) {
	            pst.setString(1, prefix + "%");

	            ResultSet rs = pst.executeQuery();
	            int maxSoThuTu = 0;

	            while (rs.next()) {
	                String ma = rs.getString("maCTKM");
	                if (ma.length() >= 10) {
	                    try {
	                        int soThuTu = Integer.parseInt(ma.substring(8));
	                        if (soThuTu > maxSoThuTu) {
	                            maxSoThuTu = soThuTu;
	                        }
	                    } catch (NumberFormatException ignored) {
	                        // Bỏ qua nếu không đúng định dạng
	                    }
	                }
	            }

	            int soThuTuMoi = maxSoThuTu + 1;
	            String maMoi = String.format("KM%s%02d", datePart, soThuTuMoi);
	            return maMoi;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
    private static int getHangValue(String hang) {
        switch (hang) {
            case "Bạc": return 1;
            case "Vàng": return 2;
            case "Kim Cương": return 3;
            default: return 0;
        }
    }

    public static ArrayList<KhuyenMai> getDanhSachKhuyenMai(String maThanhVien, String hangThanhVien) {
    	ArrayList<KhuyenMai> danhSachKM = new ArrayList<>();
        int hangValue = getHangValue(hangThanhVien);

        String query = """
        	    SELECT *
			    FROM ChuongTrinhKhuyenMai km
			    WHERE km.loai = 1
			      AND km.thoiGianKetThuc >= CAST(GETDATE() AS DATE)
			
			      AND (
			          km.doiTuongApDung = 'Tất cả'
			          OR CASE 
			              km.doiTuongApDung
			              WHEN 'Bạc' THEN 1
			              WHEN 'Vàng' THEN 2
			              WHEN 'Kim Cương' THEN 3
			              ELSE 0
			          END <= ?
			      )
			      AND (
			        km.gioiHanMoiTaiKhoan = 1
			        OR NOT EXISTS (
			            SELECT 1
			            FROM HoaDon hd
			            WHERE hd.maCTKM = km.maCTKM
			              AND hd.maTV = ?
			        )
			      )
			    ORDER BY km.phanTramKM DESC
        	""";

        
        try {
        	ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getInstance().getConnection();
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setInt(1, hangValue);
            stmt.setString(2, maThanhVien);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String makm = rs.getString("maCTKM");
                    String tenkm = rs.getString("tenCTKM");
                    double phanTram = rs.getDouble("phanTramKM");

                    // Chuyển java.sql.Date thành LocalDateTime
                    Timestamp startTs = rs.getTimestamp("thoiGianBatDau");
                    Timestamp endTs = rs.getTimestamp("thoiGianKetThuc");
                    LocalDateTime time1 = null, time2 = null;
                    if (startTs != null) {
                        time1 = startTs.toLocalDateTime();
                    }
                    if (endTs != null) {
                        time2 = endTs.toLocalDateTime();
                    }

                    String doiTuong = rs.getString("doiTuongApDung");
                    boolean loai = rs.getBoolean("loai");
                    boolean gioiHan = rs.getBoolean("gioiHanMoiTaiKhoan");
                    KhuyenMai km = new KhuyenMai(makm, tenkm, phanTram, time1, time2, doiTuong, loai, gioiHan);
                    danhSachKM.add(km);
                }
            }
		} catch (Exception e) {
			// TODO: handle exception
		}

        return danhSachKM;
    }
    
    public static KhuyenMai getKhuyenMaiTotNhat(String maThanhVien, String hangThanhVien) {
    	KhuyenMai km = null;
        int hangValue = getHangValue(hangThanhVien);

        String query = """
        	    SELECT TOP(1) *
			    FROM ChuongTrinhKhuyenMai km
			    WHERE km.loai = 1
			      AND km.thoiGianKetThuc >= CAST(GETDATE() AS DATE)
			
			      AND (
			          km.doiTuongApDung = 'Tất cả'
			          OR CASE 
			              km.doiTuongApDung
			              WHEN 'Bạc' THEN 1
			              WHEN 'Vàng' THEN 2
			              WHEN 'Kim Cương' THEN 3
			              ELSE 0
			          END <= ?
			      )
			      AND (
			        km.gioiHanMoiTaiKhoan = 1
			        OR NOT EXISTS (
			            SELECT 1
			            FROM HoaDon hd
			            WHERE hd.maCTKM = km.maCTKM
			              AND hd.maTV = ?
			        )
			      )
			    ORDER BY km.phanTramKM DESC
        	""";

        
        try {
        	ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getInstance().getConnection();
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setInt(1, hangValue);
            stmt.setString(2, maThanhVien);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String makm = rs.getString("maCTKM");
                    String tenkm = rs.getString("tenCTKM");
                    double phanTram = rs.getDouble("phanTramKM");

                    // Chuyển java.sql.Date thành LocalDateTime
                    Timestamp startTs = rs.getTimestamp("thoiGianBatDau");
                    Timestamp endTs = rs.getTimestamp("thoiGianKetThuc");
                    LocalDateTime time1 = null, time2 = null;
                    if (startTs != null) {
                        time1 = startTs.toLocalDateTime();
                    }
                    if (endTs != null) {
                        time2 = endTs.toLocalDateTime();
                    }

                    String doiTuong = rs.getString("doiTuongApDung");
                    boolean loai = rs.getBoolean("loai");
                    boolean gioiHan = rs.getBoolean("gioiHanMoiTaiKhoan");
                    km = new KhuyenMai(makm, tenkm, phanTram, time1, time2, doiTuong, loai, gioiHan);
                }
            }
		} catch (Exception e) {
			// TODO: handle exception
		}

        return km;
    }


}

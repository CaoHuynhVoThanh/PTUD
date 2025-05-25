package dao;

import connectDB.ConnectDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class LichSu_DAO {

    public Object[][] getAllDonDatBanWithViTri() throws SQLException {
        List<Object[]> dataList = new ArrayList<>();
        String query = "SELECT ddb.maDDB, ddb.thoiGianNhan, " +
                      "ISNULL(kh.tenKH, N'Khách lẻ') + CASE WHEN kh.soDienThoai IS NOT NULL THEN N' (' + kh.soDienThoai + N')' ELSE N'' END AS ThongTinKH, " +
                      "ddb.tienCoc, ddb.soKhach, ddb.trangThai, " +
                      "STRING_AGG(CONCAT(kv.tenKV, ' - Bàn ', b.soBan), ', ') AS khuVucBan, " +
                      "STRING_AGG(CAST(b.viTri AS NVARCHAR), ', ') AS viTri " +
                      "FROM DonDatBan ddb " +
                      "LEFT JOIN KhachHang kh ON ddb.maKH = kh.maKH " +
                      "LEFT JOIN ChiTietDonDatBan ctdb ON ddb.maDDB = ctdb.maDDB " +
                      "LEFT JOIN Ban b ON ctdb.maBan = b.maBan " +
                      "LEFT JOIN KhuVuc kv ON b.maKV = kv.maKV " +
                      "GROUP BY ddb.maDDB, ddb.thoiGianNhan, kh.tenKH, kh.soDienThoai, ddb.tienCoc, ddb.soKhach, ddb.trangThai " +
                      "ORDER BY ddb.thoiGianNhan DESC";
        try (Connection conn = ConnectDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String maDDB = rs.getString("maDDB");
                Timestamp thoiGianNhanBan = rs.getTimestamp("thoiGianNhan");
                String thongTinKH = rs.getString("ThongTinKH");
                double tienCoc = rs.getDouble("tienCoc");
                int soKhach = rs.getInt("soKhach");
                int trangThaiInt = rs.getInt("trangThai");
                String khuVucBan = rs.getString("khuVucBan");
                String viTriRaw = rs.getString("viTri");

                // Chuyển đổi trạng thái từ INT sang String
                String trangThai;
                switch (trangThaiInt) {
                    case 0: trangThai = "Đã hủy"; break;
                    case 1: trangThai = "Chờ nhận bàn"; break;
                    case 2: trangThai = "Đã nhận bàn"; break;
                    case 3: trangThai = "Đã thanh toán"; break;
                    default: trangThai = "Không xác định";
                }

                // Xử lý vị trí (tầng)
                String viTriDisplay = "Chưa xác định";
                if (viTriRaw != null && !viTriRaw.isEmpty()) {
                    String[] viTriList = viTriRaw.split(", ");
                    Set<String> uniqueViTri = new TreeSet<>();
                    for (String viTri : viTriList) {
                        try {
                            int viTriInt = Integer.parseInt(viTri.trim());
                            uniqueViTri.add("Tầng " + viTriInt);
                        } catch (NumberFormatException ignored) {}
                    }
                    viTriDisplay = uniqueViTri.isEmpty() ? "Chưa xác định" : String.join(", ", uniqueViTri);
                }

                Object[] row = new Object[]{
                    maDDB,
                    thoiGianNhanBan,
                    thongTinKH,
                    tienCoc,
                    soKhach,
                    khuVucBan != null ? khuVucBan : "Chưa xác định",
                    viTriDisplay,
                    trangThai
                };
                dataList.add(row);
            }
        }
        return dataList.toArray(new Object[0][]);
    }

    public Object[][] getAllHoaDon() throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        String query = "SELECT hd.maHD AS MaHD, hd.thoiGianThanhToan AS ThoiGian, nv.tenNV AS NguoiTao, " +
                      "ISNULL(kh.tenKH, N'Khách vãng lai') + CASE WHEN kh.soDienThoai IS NOT NULL THEN N' (' + kh.soDienThoai + N')' ELSE N'' END AS ThongTinKH, " +
                      "hd.phuongThucThanhToan AS PhuongThuc " +
                      "FROM HoaDon hd " +
                      "INNER JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                      "LEFT JOIN DonDatBan ddb ON hd.maHD = ddb.maHD " +
                      "LEFT JOIN KhachHang kh ON ddb.maKH = kh.maKH " +
                      "ORDER BY hd.thoiGianThanhToan DESC";
        try (Connection conn = ConnectDB.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("MaHD"));
                row.add(rs.getTimestamp("ThoiGian"));
                row.add(rs.getString("NguoiTao"));
                row.add(rs.getString("ThongTinKH"));
                row.add(rs.getString("PhuongThuc"));
                row.add(""); // Thêm cột "Tổng tiền" rỗng
                data.add(row);
            }
        }
        return data.stream().map(Vector::toArray).toArray(Object[][]::new);
    }

    public List<Object[]> getChiTietBanByMaDDB(String maDDB) throws SQLException {
        List<Object[]> result = new ArrayList<>();
        String query = "SELECT kv.tenKV, b.soBan, b.viTri, kv.phuThu " +
                      "FROM ChiTietDonDatBan ctdb " +
                      "INNER JOIN Ban b ON ctdb.maBan = b.maBan " +
                      "LEFT JOIN KhuVuc kv ON b.maKV = kv.maKV " +
                      "WHERE ctdb.maDDB = ?";
        try (Connection conn = ConnectDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, maDDB);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("tenKV") != null ? rs.getString("tenKV") : "N/A",
                    rs.getString("soBan") != null ? rs.getString("soBan") : "N/A",
                    rs.getInt("viTri"),
                    rs.getDouble("phuThu")
                };
                result.add(row);
            }
        }
        return result;
    }

    public List<Object[]> getChiTietMonAnByMaHD(String maHD) throws SQLException {
        List<Object[]> result = new ArrayList<>();
        String query = "SELECT m.tenMon, ctdgm.soLuong, m.donGia, (ctdgm.soLuong * m.donGia) AS ThanhTien " +
                       "FROM HoaDon hd " +
                       "INNER JOIN DonDatBan ddb ON hd.maHD = ddb.maHD " +
                       "INNER JOIN ChiTietDonDatBan ctddb ON ddb.maDDB = ctddb.maDDB " +
                       "INNER JOIN DonGoiMon dgm ON ctddb.maDGM = dgm.maDGM " +
                       "INNER JOIN ChiTietDonGoiMon ctdgm ON dgm.maDGM = ctdgm.maDGM " +
                       "INNER JOIN Mon m ON ctdgm.maMon = m.maMon " +
                       "WHERE hd.maHD = ?";
        try (Connection conn = ConnectDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, maHD);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("tenMon"),
                    rs.getInt("soLuong"),
                    rs.getDouble("donGia"),
                    rs.getDouble("ThanhTien")
                };
                result.add(row);
            }
        }
        return result;
    }

    public List<Integer> getDistinctViTri() throws SQLException {
        List<Integer> danhSachViTri = new ArrayList<>();
        String query = "SELECT DISTINCT viTri FROM Ban ORDER BY viTri";
        try (Connection conn = ConnectDB.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                danhSachViTri.add(rs.getInt("viTri"));
            }
        }
        return danhSachViTri;
    }

    public List<String> getAllKhuVuc() throws SQLException {
        List<String> khuVucList = new ArrayList<>();
        String query = "SELECT tenKV FROM KhuVuc ORDER BY tenKV";
        try (Connection conn = ConnectDB.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                khuVucList.add(rs.getString("tenKV"));
            }
        }
        return khuVucList;
    }

    public List<String> getKhuVucByViTri(int viTri) throws SQLException {
        List<String> khuVucList = new ArrayList<>();
        String query = "SELECT DISTINCT kv.tenKV " +
                      "FROM KhuVuc kv " +
                      "INNER JOIN Ban b ON kv.maKV = b.maKV " +
                      "WHERE b.viTri = ? " +
                      "ORDER BY kv.tenKV";
        try (Connection conn = ConnectDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, viTri);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String tenKV = rs.getString("tenKV");
                if (tenKV != null) {
                    khuVucList.add(tenKV);
                }
            }
        }
        return khuVucList;
    }

    public List<String> getAllNguoiTao() throws SQLException {
        List<String> nguoiTaoList = new ArrayList<>();
        String query = "SELECT tenNV FROM NhanVien ORDER BY tenNV";
        try (Connection conn = ConnectDB.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                nguoiTaoList.add(rs.getString("tenNV"));
            }
        }
        return nguoiTaoList;
    }

    public LocalDateTime getThoiGianDatByMaDDB(String maDDB) throws SQLException {
        String query = "SELECT thoiGianDat FROM DonDatBan WHERE maDDB = ?";
        try (Connection conn = ConnectDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, maDDB);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("thoiGianDat");
                return timestamp != null ? timestamp.toLocalDateTime() : null;
            }
        }
        return null;
    }

    public String getNguoiDatByMaDDB(String maDDB) throws SQLException {
        String query = "SELECT nv.tenNV " +
                      "FROM DonDatBan ddb " +
                      "INNER JOIN NhanVien nv ON ddb.maNV = nv.maNV " +
                      "WHERE ddb.maDDB = ?";
        try (Connection conn = ConnectDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, maDDB);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("tenNV");
            }
        }
        return null;
    }
}
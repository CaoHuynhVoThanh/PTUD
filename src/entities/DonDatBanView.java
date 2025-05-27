package entities;

public class DonDatBanView {
    private DonDatBan donDatBan;
    private int soBan;
    private int soMon;
    private String soDienThoai;
    private String tenKhachHang;
    private double tienCoc;

    public DonDatBanView(DonDatBan donDatBan, int soBan, int soMon, String soDienThoai, String tenKhachHang, double tienCoc) {
        this.donDatBan = donDatBan;
        this.soBan = soBan;
        this.soMon = soMon;
        this.soDienThoai = soDienThoai;
        this.tenKhachHang = tenKhachHang;
        this.tienCoc = tienCoc;
    }

    // Getters
    public DonDatBan getDonDatBan() {
        return donDatBan;
    }

    public int getSoBan() {
        return soBan;
    }

    public int getSoMon() {
        return soMon;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public double getTienCoc() {
        return tienCoc;
    }

    // Setters
    public void setDonDatBan(DonDatBan donDatBan) {
        this.donDatBan = donDatBan;
    }

    public void setSoBan(int soBan) {
        this.soBan = soBan;
    }

    public void setSoMon(int soMon) {
        this.soMon = soMon;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setTienCoc(double tienCoc) {
        this.tienCoc = tienCoc;
    }
}




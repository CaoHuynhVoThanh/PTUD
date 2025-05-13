package entities;

public class DonDatBanView {
    private DonDatBan donDatBan;
    private int soBan;
    private String soDienThoai;

    public DonDatBanView(DonDatBan donDatBan, int soBan, String soDienThoai) {
        this.donDatBan = donDatBan;
        this.soBan = soBan;
        this.soDienThoai = soDienThoai;
    }

    // Getter/setter nếu cần
    public DonDatBan getDonDatBan() {
        return donDatBan;
    }

    public int getSoBan() {
        return soBan;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }
}


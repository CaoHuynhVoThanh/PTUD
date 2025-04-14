package entities;

import java.time.LocalDateTime;

public class DonDatBan {
	private String maDDB;
	private String maHD;
	private String maNV;
	private String maKH;
	private LocalDateTime thoiGianDat;
	private LocalDateTime thoiGianNhan;
	private int soKhach;
	private double tienCoc;
	private int trangThai;
	
	public DonDatBan(String maDDB, String maHD, String maNV, String maKH, LocalDateTime thoiGianDat,
			LocalDateTime thoiGianNhan, int soKhach, double tienCoc, int trangThai) {
		this.maDDB = maDDB;
		this.maHD = maHD;
		this.maNV = maNV;
		this.maKH = maKH;
		this.thoiGianDat = thoiGianDat;
		this.thoiGianNhan = thoiGianNhan;
		this.soKhach = soKhach;
		this.tienCoc = tienCoc;
		this.trangThai = trangThai;
	}
	public String getMaDDB() {
		return maDDB;
	}
	public String getMaHD() {
		return maHD;
	}
	public String getMaNV() {
		return maNV;
	}
	public String getMaKH() {
		return maKH;
	}
	public LocalDateTime getThoiGianDat() {
		return thoiGianDat;
	}
	public void setThoiGianDat(LocalDateTime thoiGianDat) {
		this.thoiGianDat = thoiGianDat;
	}
	public LocalDateTime getThoiGianNhan() {
		return thoiGianNhan;
	}
	public void setThoiGianNhan(LocalDateTime thoiGianNhan) {
		this.thoiGianNhan = thoiGianNhan;
	}
	public int getSoKhach() {
		return soKhach;
	}
	public void setSoKhach(int soKhach) {
		this.soKhach = soKhach;
	}
	public double getTienCoc() {
		return tienCoc;
	}
	public void setTienCoc(double tienCoc) {
		this.tienCoc = tienCoc;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public String toString() {
		return "DonDatBan [maDDB=" + maDDB + ", maHD=" + maHD + ", maNV=" + maNV + ", maKH=" + maKH + ", thoiGianDat="
				+ thoiGianDat + ", thoiGianNhan=" + thoiGianNhan + ", soKhach=" + soKhach + ", tienCoc=" + tienCoc
				+ ", trangThai=" + trangThai + "]";
	}
	
}

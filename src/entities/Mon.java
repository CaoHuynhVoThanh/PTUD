package entities;

public class Mon {
	private String maMon;
	private String tenMon;
	private String loaiMon;
	private double donGia;
	private String hinhAnh;
	public Mon(String maMon, String tenMon, String loaiMon, double donGia, String hinhAnh) {
		this.maMon = maMon;
		this.tenMon = tenMon;
		this.loaiMon = loaiMon;
		this.donGia = donGia;
		this.hinhAnh = hinhAnh;
	}
	public String getMaMon() {
		return maMon;
	}
	public String getTenMon() {
		return tenMon;
	}
	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}
	public String getLoaiMon() {
		return loaiMon;
	}
	public void setLoaiMon(String loaiMon) {
		this.loaiMon = loaiMon;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	@Override
	public String toString() {
		return "Mon [maMon=" + maMon + ", tenMon=" + tenMon + ", loaiMon=" + loaiMon + ", donGia=" + donGia
				+ ", hinhAnh=" + hinhAnh + "]";
	}
	
}

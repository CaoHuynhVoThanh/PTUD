package entities;

public class ChiTietDonGoiMon {
	private String maDGM;
	private String maMon;
	private int soLuong;
	private int soLuongDaThanhToan;
	public ChiTietDonGoiMon(String maMon, String maDGM, int soLuong, int soLuongDaThanhToan) {
		this.maDGM = maDGM;
		this.maMon = maMon;
		this.soLuong = soLuong;
		this.soLuongDaThanhToan = soLuongDaThanhToan;
	}
	public String getMaDGM() {
		return maDGM;
	}
	public String getMaMon() {
		return maMon;
	}
	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public int getSoLuongDaThanhToan() {
		return soLuongDaThanhToan;
	}
	public void setSoLuongDaThanhToan(int soLuongDaThanhToan) {
		this.soLuongDaThanhToan = soLuongDaThanhToan;
	}
	@Override
	public String toString() {
		return "ChiTietDonGoiMon [maDGM=" + maDGM + ", maMon=" + maMon + ", soLuong=" + soLuong
				+ ", soLuongDaThanhToan=" + soLuongDaThanhToan + "]";
	}
	
}

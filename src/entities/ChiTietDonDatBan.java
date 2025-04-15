package entities;

public class ChiTietDonDatBan {
	private String maDDB;
	private String maBan;
	private String maDGM;
	public ChiTietDonDatBan(String maDDB, String maBan, String maDGM) {
		this.maDDB = maDDB;
		this.maBan = maBan;
		this.maDGM = maDGM;
	}
	public String getMaDDB() {
		return maDDB;
	}
	public void setMaDDB(String maDDB) {
		this.maDDB = maDDB;
	}
	public String getMaBan() {
		return maBan;
	}
	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}
	public String getMaDGM() {
		return maDGM;
	}
	public void setMaDGM(String maDGM) {
		this.maDGM = maDGM;
	}
	@Override
	public String toString() {
		return "ChiTietDonDatBan [maDDB=" + maDDB + ", maBan=" + maBan + ", maDGM=" + maDGM + "]";
	}
	
}

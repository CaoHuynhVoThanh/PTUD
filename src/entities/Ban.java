package entities;

public class Ban {
	private String maBan;
	private int loaiBan;
	private int viTri;
	private String soBan;
	private int tinhTrang;
	private String tenKV;
	private double phuPhi;
	private double phiCoc;
	public Ban(String maBan, int loaiBan, int viTri, String soBan, int tinhTrang, String tenKV, double phuPhi,
			double phiCoc) {
		super();
		this.maBan = maBan;
		this.loaiBan = loaiBan;
		this.viTri = viTri;
		this.soBan = soBan;
		this.tinhTrang = tinhTrang;
		this.tenKV = tenKV;
		this.phuPhi = phuPhi;
		this.phiCoc = phiCoc;
	}

	public Ban(String maBan, int loaiBan, int viTri, String soBan, int tinhTrang, String tenKV, double phuPhi) {
        super();
        this.maBan = maBan;
        this.loaiBan = loaiBan;
        this.viTri = viTri;
        this.soBan = soBan;
        this.tinhTrang = tinhTrang;
        this.tenKV = tenKV;
        this.phuPhi = phuPhi;
        this.phiCoc = 0.0; 
    }

	public String getMaBan() {
		return maBan;
	}
	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}
	public int getLoaiBan() {
		return loaiBan;
	}
	public void setLoaiBan(int loaiBan) {
		this.loaiBan = loaiBan;
	}
	public int getViTri() {
		return viTri;
	}
	public void setViTri(int viTri) {
		this.viTri = viTri;
	}
	public String getSoBan() {
		return soBan;
	}
	public void setSoBan(String soBan) {
		this.soBan = soBan;
	}
	public int getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public String getTenKV() {
		return tenKV;
	}
	public void setTenKV(String tenKV) {
		this.tenKV = tenKV;
	}
	public double getPhuPhi() {
		return phuPhi;
	}
	public void setPhuPhi(double phuPhi) {
		this.phuPhi = phuPhi;
	}
	public double getPhiCoc() {
		return phiCoc;
	}
	public void setPhiCoc(double phiCoc) {
		this.phiCoc = phiCoc;
	}

	public String getHinh() {
		return ""+this.loaiBan+this.tinhTrang;
	}

	@Override
	public String toString() {
		return "Ban [maBan=" + maBan + ", loaiBan=" + loaiBan + ", viTri=" + viTri + ", soBan=" + soBan + ", tinhTrang="
				+ tinhTrang + ", tenKV=" + tenKV + ", phuPhi=" + phuPhi + ", phiCoc=" + phiCoc + "]";
	}
	
	
	
}

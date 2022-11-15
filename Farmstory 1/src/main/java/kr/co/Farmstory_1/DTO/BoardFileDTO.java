package kr.co.Farmstory_1.DTO;

public class BoardFileDTO {
	private int fno;
	private int parent;
	private String newName;
	private String oriName;
	private int download;
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public void setFno(String fno) {
		this.fno = Integer.parseInt(fno);
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public void setParent(String parent) {
		this.parent = Integer.parseInt(parent);
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public int getDownload() {
		return download;
	}
	public void setDownload(int download) {
		this.download = download;
	}
	public void setDownload(String download) {
		this.download = Integer.parseInt(download);
	}
}

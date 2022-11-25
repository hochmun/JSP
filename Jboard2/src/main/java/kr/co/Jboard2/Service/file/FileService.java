package kr.co.Jboard2.Service.file;

import kr.co.Jboard2.dao.FileDAO;
import kr.co.Jboard2.vo.FileVO;

public enum FileService {
	INSTANCE;
	private FileDAO dao;
	
	private FileService() {
		dao = new FileDAO();
	}
	
	//
	
	public void insertFile(int parent, String newName, String fname) {
		dao.insertFile(parent, newName, fname);
	}
	
	public FileVO selectFileData(String no) {
		return dao.selectFileData(no);
	}
	
	public void updateDownloadCount(int fno) {
		dao.updateDownloadCount(fno);
	}
}

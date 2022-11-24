package kr.co.Jboard2.Service.file;

import kr.co.Jboard2.dao.FileDAO;

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
}

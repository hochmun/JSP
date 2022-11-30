package kr.co.Farmstory2.service;

import kr.co.Farmstory2.dao.FileDAO;

public enum FileService {
	
	INSTANCE;
	private FileDAO dao;
	private FileService() {
		dao = new FileDAO();
	}
	
	// create
	public void insertFile(int parent, String newName, String fileName) {
		dao.insertFile(parent, newName, fileName);
	}
	
	// read
	
	// upload
	
	// delete
	
	// service
}

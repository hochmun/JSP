package kr.co.Farmstory_1.DAO;

import kr.co.Farmstory_1.DTO.BoardFileDTO;
import kr.co.Farmstory_1.config.DBCP;
import kr.co.Farmstory_1.config.Sql;

public class BoardFileDAO extends DBCP {
	private static BoardFileDAO instance = new BoardFileDAO();
	public static BoardFileDAO getInstance() {
		return instance;
	}
	private BoardFileDAO() {}
	
	// create
	/**
	 * 2022/11/16<br/>파일첨부
	 * @author 심규영
	 * @param bfdto
	 */
	public void insertFile(BoardFileDTO bfdto) {
		try {
			logger.info("insertFile...");
			
			conn = getConnection();
			
			psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, bfdto.getParent());
			psmt.setString(2, bfdto.getNewName());
			psmt.setString(3, bfdto.getOriName());
			
			psmt.executeUpdate();
			
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	// read
	/**
	 * 파일 정보 불러오기, 파일 다운로드 횟수 증가
	 * @param parent
	 * @return {@link BoardFileDTO}
	 */
	public BoardFileDTO selectFile(String parent) {
		BoardFileDTO bfdto = new BoardFileDTO();
		try {
			logger.info("selectFile");
			
			conn = getConnection();
			
			// 게시물 정보 받기
			psmt = conn.prepareStatement(Sql.SELECT_FILE);
			psmt.setString(1, parent);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				bfdto.setFno(rs.getInt("fno"));
				bfdto.setParent(rs.getInt("parent"));
				bfdto.setNewName(rs.getString("newName"));
				bfdto.setOriName(rs.getString("oriName"));
				bfdto.setDownload(rs.getInt("download"));
			}
			
			psmt = conn.prepareStatement(Sql.UPDATE_FILE_DOWNLOAD);
			psmt.setInt(1, bfdto.getFno());
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bfdto;
	}
	
	// upload
	
	// delete
}

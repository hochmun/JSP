package fileupload;

import java.util.List;
import java.util.Vector;

import common.DBConnPool;

public class MyfileDAO extends DBConnPool{
	
	// create
	/**
	 * 새로운 게시물을 입력
	 * @author 심규영
	 * @date 2022/11/09
	 * @param dto
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statementsor (2) 0 for SQL statements that return nothing
	 */
	public int insertFile(MyfileDTO dto) {
		int applyResult = 0;
		try {
			String query = "INSERT INTO myfile ( "
					+ " idx, name, title, cate, ofile, sfile) "
					+ " VALUES ( "
					+ " seq_board_num.nextval, ?, ?, ?, ?, ?)";
			
			psmt = conn.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getCate());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			
			applyResult = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("INSERT 중 예외 발생");
			e.printStackTrace();
		}
		return applyResult;
	}
	
	// read
	/**
	 * DAO에 목록 반환 메서드 추가
	 * @author 심규영
	 * @date 2022/11/09
	 * @return List<MyfileDTO>
	 */
	public List<MyfileDTO> myFileList() {
		List<MyfileDTO> fileList = new Vector<>();
		
		// 쿼리문
		String query = "SELECT * FROM myfile ORDER BY idx DESC";
		try {
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MyfileDTO dto = new MyfileDTO();
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setCate(rs.getString(4));
				dto.setOfile(rs.getString(5));
				dto.setSfile(rs.getString(6));
				dto.setPostdate(rs.getString(7));
				fileList.add(dto);
			}
		} catch(Exception e) {
			System.out.println("SELECT 시 예외 발생");
			e.printStackTrace();
		}
		return fileList;
	}
	
	// upload
	
	// delete
}

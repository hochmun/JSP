package dao;

import java.util.ArrayList;
import java.util.List;

import config.DBCP;
import config.Sql;
import vo.BookVO;

public class BookDAO extends DBCP {
	private static BookDAO instance = new BookDAO();
	public static BookDAO getInstance() {
		return instance;
	}
	private BookDAO() {}
	
	// create
	/**
	 * 도서 등록
	 * @param bv
	 */
	public void insertBook(BookVO bv) {
		try {
			logger.info("insertBook...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_BOOK);
			psmt.setInt(1, bv.getBookId());
			psmt.setString(2, bv.getBookName());
			psmt.setString(3, bv.getPublisher());
			psmt.setInt(4, bv.getPrice());
			
			psmt.executeUpdate();
			
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	// read
	/**
	 * 도서목록 불러오기
	 * @return List<{@link BookVO}> 
	 */
	public List<BookVO> selectBooks() {
		List<BookVO> bvs = new ArrayList<>();
		try {
			logger.info("selectBooks...");
			conn = getConnection();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_BOOKS);
			
			while(rs.next()) {
				BookVO bv = new BookVO();
				bv.setBookId(rs.getInt("bookId"));
				bv.setBookName(rs.getString("bookName"));
				bv.setPublisher(rs.getString("publisher"));
				bv.setPrice(rs.getInt("price"));
				bvs.add(bv);
			}
			
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("bvs : "+bvs);
		return bvs;
	}
	
	/**
	 * 도서 정보 읽기
	 * @param bookId
	 * @return
	 */
	public BookVO selectBook(String bookId) {
		BookVO bv = new BookVO();
		try {
			logger.info("selectBook...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_BOOK);
			psmt.setString(1, bookId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				bv.setBookId(rs.getInt("bookId"));
				bv.setBookName(rs.getString("bookName"));
				bv.setPublisher(rs.getString("publisher"));
				bv.setPrice(rs.getInt("price"));
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("bv : "+bv);
		return bv;
	}
	
	// upload
	/**
	 * 도서 수정
	 * @param bv
	 */
	public void updateBook(BookVO bv) {
		try {
			logger.info("updateBook...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_BOOK);
			psmt.setInt(4, bv.getBookId());
			psmt.setString(1, bv.getBookName());
			psmt.setString(2, bv.getPublisher());
			psmt.setInt(3, bv.getPrice());
			psmt.executeUpdate();
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	// delete
	public void deleteBook(String bookId) {
		try {
			logger.info("deleteBook...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.DELETE_BOOK);
			psmt.setString(1, bookId);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}

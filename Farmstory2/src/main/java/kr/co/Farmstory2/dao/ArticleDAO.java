package kr.co.Farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import kr.co.Farmstory2.db.DBCP;
import kr.co.Farmstory2.db.Sql;
import kr.co.Farmstory2.vo.articleVO;

public class ArticleDAO extends DBCP {

	// create
	
	// read
	public List<articleVO> selectArticles(String cateName, int limitStart, String search) {
		List<articleVO> avos = new ArrayList<>();
		String word = "";
		if (search != null) word = "%"+search+"%";
		else word = "%%";
		try {
			logger.info("selectArticle...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setString(1, cateName);
			psmt.setString(2, word);
			psmt.setString(3, word);
			psmt.setInt(4, limitStart);
			rs = psmt.executeQuery();
			while(rs.next()) {
				articleVO vo = new articleVO();
				vo.setNo(rs.getInt(1));
				vo.setParent(rs.getInt(2));
				vo.setComment(rs.getInt(3));
				vo.setCate(rs.getString(4));
				vo.setTitle(rs.getString(5));
				vo.setContent(rs.getString(6));
				vo.setFile(rs.getInt(7));
				vo.setHit(rs.getInt(8));
				vo.setUid(rs.getString(9));
				vo.setRegip(rs.getString(10));
				vo.setRdate(rs.getString(11).substring(2, 10));
				vo.setNick(rs.getString(12));
				avos.add(vo);
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return avos;
	}
	
	/**
	 * 카테고리별 전체 게시물 갯수 + 검색기능
	 * @param search
	 * @param cateName
	 * @return
	 */
	public int selectCountArticles(String search, String cateName) {
		int total = 0;
		try {
			logger.info("selectCountArticles...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_ARTICLES);
			psmt.setString(1, cateName);
			psmt.setString(2, search);
			psmt.setString(3, search);
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	
	// upload
	
	// delete
	
}

package kr.co.Jboard2.dao;

import java.util.ArrayList;
import java.util.List;

import kr.co.Jboard2.db.DBCP;
import kr.co.Jboard2.db.Sql;
import kr.co.Jboard2.vo.articleVO;

public class ArticleDAO extends DBCP {
	// create
	
	// read
	public List<articleVO> selectarticles(int limitStart) {
		List<articleVO> lists = new ArrayList<>();
		try {
			logger.info("selectarticles...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLE_LIST);
			psmt.setInt(1, limitStart);
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
				lists.add(vo);
			}
			
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return lists;
	}
	
	public int selectCountArticles() {
		int total = 0;
		try {
			logger.info("selectCountArticles...");
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_COUNT_TOTAL_ARTICLE);
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("total : "+total);
		return total;
	}
	
	// upload
	
	// delete
}

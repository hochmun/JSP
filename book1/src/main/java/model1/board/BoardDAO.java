package model1.board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.JDBConnect;

/**
 * 게시글 목록 CRUD용 DAO
 * 
 * @author 심규영
 * @since 2022/10/24
 */
public class BoardDAO extends JDBConnect {
	public BoardDAO(ServletContext application) {
		super(application);
	}

	/**
	 * 검색 조건에 맞는 게시물의 개수를 반환
	 * 
	 * @since 2022/10/24
	 * @author 심규영
	 * @param map
	 * @return int
	 */
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0; // 결과(게시물 수)를 담을 변수

		// 게시물 수를 얻어오는 쿼리문 작성
		String query = "select count(*) from `board`";
		if (map.get("searchWord") != null) {
			query += " where `" + map.get("searchField") + "` " 
					+ "like '%" + map.get("searchWord") + "%'";
		}

		try {
			stmt = conn.createStatement(); // 쿼리문 생성
			rs = stmt.executeQuery(query); // 쿼리 실행
			rs.next(); // 커서를 첫 번째 행으로 이동
			totalCount = rs.getInt(1); // 첫 번째 컬럼 값을 가져옴
		} catch (Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}

		return totalCount;
	}

	/**
	 * 검색 조건에 맞는 게시물 목록을 반환(페이징 기능 지원)
	 * @author 심규영
	 * @since 2022/10/26
	 * @param map
	 * @return List<BoardDTO>
	 */
	public List<BoardDTO> selectListPage(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>();
		
		// 쿼리문 템플릿
		String query = "SELECT * FROM ("
				+ "SELECT Tb.*, ROWNUM rNum FROM ("
				+ "SELECT * FROM `board`";
		
		// 검색 조건 추가
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")
				   + " LIKE '%'" + map.get("searchWord") + "%'";
		}
		
		query += " ORDER BY num DESC"
				+ ") Tb"
				+ ")"
				+ " WHERE rNum BETWEEN ? AND ?";
		
		try {
			// 쿼리문 완성
			psmt = conn.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			
			// 쿼리문 실행
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				// 한 행(게시물 하나)의 데이터를 DTO에 저장
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				// 반환할 결과 목록에 게시물 추가
				bbs.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		// 목록 반환
		return bbs;
	}
	
	/**
	 * 검색 조건에 맞는 게시물 목록을 반환
	 * 
	 * @since 2022/10/24
	 * @author 심규영
	 * @param map
	 * @return List<BoardDTO>
	 */
	public List<BoardDTO> selectList(Map<String, Object> map) {
		// 결과(게시물 목록)를 담을 변수
		List<BoardDTO> bbs = new Vector<BoardDTO>();

		String query = "select * from `board`";
		if (map.get("searchWord") != null) {
			query += " where `" + map.get("searchField") + "` " 
					+ "like '%" + map.get("searchWord") + "%' ";
		}
		query += " ORDER BY `num` DESC ";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();

				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));

				bbs.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}

		return bbs;
	}

	/**
	 * 게시글 데이터를 받아 DB에 추가
	 * 
	 * @author 심규영
	 * @since 2022/10/24
	 * @param dto
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statementsor (2) 0 for SQL statements that return nothing
	 */
	public int insertWrite(BoardDTO dto) {
		int result = 0;

		try {
			// insert 쿼리문 작성
			String query = "INSERT INTO `board` (`title`,`content`" 
					+ ",`id`,`postdate`,`visitcount`) VALUES ("
					+ "?,?,?,now(),0)";
			psmt = conn.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());

			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * DAO에 게시물 조회 메서드 추가
	 * @author 심규영
	 * @since 2022/10/24
	 * @param num
	 * @return BoardDTO
	 */
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		
		// 쿼리문 준비
		String query = "SELECT B.*, M.name "
				+ " FROM `member` as M INNER JOIN `board` as B "
				+ " ON M.id = B.id WhERE `num`=?";
		
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			
			// 결과 처리
			if (rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString(6));
				dto.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		
		return dto;
	}

	/**
	 * 지정한 게시물의 조회수를 1 증가시킵니다.
	 * @author 심규영
	 * @since 2022/10/24
	 * @param num
	 */
	public void updateVisitCount(String num) {
		// 쿼리문 준비
		String query = "UPDATE `board` SET visitcount = visitcount+1"
				+ " WHERE `num`=?";
		
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}

	/**
	 * DAO에 수정하기 메서드 추가
	 * @author 심규영
	 * @since 2022/10/25
	 * @param dto
	 * @return either (1) the row count for SQL Data Manipulation 
	 * Language (DML) statementsor (2) 0 for SQL statements that 
	 * return nothing
	 */
	public int updateEdit(BoardDTO dto) {
		int result = 0;
		
		try {
			// 쿼리문 템플릿
			String query = "UPDATE `board` SET"
						 + " `title`=?, `content`=? "
						 + "WHERE `num`=?";
			// 쿼리문 완성
			psmt = conn.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());
			
			// 쿼리문 실행
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		
		return result; // 결과 반환
	}

	/**
	 * DAO에 삭제하기 메서드 추가
	 * @author 심규영
	 * @since 2022/10/25
	 * @param dto
	 * @return either (1) the row count for SQL Data Manipulation 
	 * Language (DML) statementsor (2) 0 for SQL statements that 
	 * return nothing
	 */
	public int deletePost(BoardDTO dto) {
		int result = 0;
		
		try {
			// 쿼리문 템플릿
			String query = "DELETE FROM `board` WHERE `num`=?";
			
			// 쿼리문 완성
			psmt = conn.prepareStatement(query);
			psmt.setString(1, dto.getNum());
			
			// 쿼리문 실행
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
}
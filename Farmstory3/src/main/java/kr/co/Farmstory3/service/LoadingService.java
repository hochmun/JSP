package kr.co.Farmstory3.service;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public enum LoadingService {
	INSTANCE;
	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	private LoadingService () {}
	
	// service
	/**
	 * 로그인 실패
	 * @param out
	 * @throws IOException
	 */
	public void loginFail(Writer out) throws IOException {
		out.write("<script>alert('로그인에 실패하였습니다.\\n아이디와 비밀번호를 재확인 후 다시 로그인 하십시오.');location.href='/Farmstory3/user/login.do';</script>");
	}
	
	/**
	 * 로그아웃
	 * @param out
	 * @throws IOException
	 */
	public void logout(Writer out) throws IOException {
		out.write("<script>alert('로그아웃 하였습니다.');location.href = document.referrer;</script>");
	}
	
	/**
	 * 유저 회원 가입
	 * @param out
	 * @throws IOException
	 */
	public void insertUser(Writer out) throws IOException {
		out.write("<script>alert('회원 가입이 완료되었습니다.');location.href='/Farmstory3/user/login.do';</script>");
	}
	
	/**
	 * 게시물 삭제
	 * @param out
	 * @throws IOException
	 */
	public void deleteArticle(Writer out, HttpServletRequest req) throws IOException {
		String search = (String) req.getSession().getAttribute("search");
		out.write("<script>alert('게시물이 삭제 되었습니다.');"
				+ "location.href='/Farmstory3/board/list.do?"
				+ "cate="+req.getParameter("cate")
				+ "&tit="+req.getParameter("tit")
				+ "&pg="+req.getParameter("pg")
				+ "&search="+search+"';</script>");
	}

	/**
	 * 게시물 수정
	 * @param out
	 * @param req
	 * @throws IOException
	 */
	public void modifyArticle(Writer out, HttpServletRequest req) throws IOException {
		String search = (String) req.getSession().getAttribute("search");
		out.write("<script>alert('게시물이 수정 되었습니다.');"
				+ "location.href='/Farmstory3/board/view.do?"
				+ "cate="+req.getParameter("cate")
				+ "&tit="+req.getParameter("tit")
				+ "&pg="+req.getParameter("pg")
				+ "&no="+req.getParameter("no")
				+ "&search="+search
				+ "';</script>");
	}
}

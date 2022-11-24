package kr.co.Jboard2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.Service.user.UserService;
import kr.co.Jboard2.vo.userVO;

@WebFilter("/*")
public class AutoLoginFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.INSTANCE;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("AutoLoginFilter...");
		
		// 자동 로그인 여부에 따라 로그인 처리
		HttpServletRequest req = (HttpServletRequest)request;
		
		HttpSession sess = req.getSession();
		Cookie[] cookies = req.getCookies();
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("SESSID")) {
					String sessId = cookie.getValue();
					userVO vo = service.selectUserBySessId(sessId);
					logger.debug("autologin vo : "+vo);
					if (vo.getUid() != null) {
						logger.debug("AutoLoginFilter cookiecheck");
						sess.setAttribute("sessUser", vo); // 로그인 처리
						cookie.setMaxAge(60*60*24*3); // 쿠키 만료일 연장
						((HttpServletResponse)response).addCookie(cookie);
						// 데이터베이스 sessId 만료일 연장
						service.updateUserForSessLimitDate(sessId);
					}
				}
			}
		}

		// 다음 필터 실행
		chain.doFilter(request, response);
	}

}

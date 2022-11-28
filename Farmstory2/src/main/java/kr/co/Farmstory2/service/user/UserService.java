package kr.co.Farmstory2.service.user;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Farmstory2.dao.UserDAO;
import kr.co.Farmstory2.vo.termsVO;
import kr.co.Farmstory2.vo.userVO;

public enum UserService {
	INSTANCE;
	private UserDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String password = "kxuxvucyzszwfipr";
	
	private UserService() {
		dao = new UserDAO();
	}
	
	// create
	public void insertUser(userVO vo){
		dao.insertUser(vo);
	}
	
	// read
	public termsVO selectTerms() {
		return dao.selectTerms();
	}
	
	/**
	 * 아이디 체크
	 * @param uid
	 */
	public int checkUid(String uid) {
		logger.info("checkUid...");
		return dao.selectCountUid(uid);
	}
	/**
	 * 닉네임 중복 체크
	 * @param nick
	 * @return
	 */
	public int checkNick(String nick) {
		return dao.selectCountNick(nick);
	}
	/**
	 * 이름 이메일 검사
	 * @param name
	 * @param email
	 * @return
	 */
	public int selectCountUserName(String name, String email) {
		return dao.selectCountUserName(name, email);
	}
	public int selectCountUserUid(String uid, String email) {
		return dao.selectCountUserUid(uid, email);
	}
	
	// upload
	
	// delete
	
	// service
	/**
	 * 유저 정보 입력
	 * @param req
	 * @return
	 */
	public userVO inputUserVO(HttpServletRequest req){
		logger.info("inputUserVO... User Info Insert");
		userVO uvo = new userVO();
		uvo.setUid(req.getParameter("uid"));
		uvo.setPass(req.getParameter("pass2"));
		uvo.setName(req.getParameter("name"));
		uvo.setNick(req.getParameter("nick"));
		uvo.setEmail(req.getParameter("email"));
		uvo.setHp(req.getParameter("hp"));
		uvo.setZip(req.getParameter("zip"));
		uvo.setAddr1(req.getParameter("addr1"));
		uvo.setAddr2(req.getParameter("addr2"));
		uvo.setRegip(req.getRemoteAddr());
		return uvo;
	}
	/**
	 * 메일 보내기
	 * @param receiver
	 * @return
	 */
	public int[] sendEmailCode(String receiver) {
		// 인증코드 생성
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
		
		// 기본 정보
		String sender = "me03454@gmail.com";
		
		String title = "Farmstory2 인증코드 입니다.";
		String content = "<h1>인증코드는 "+code+" 입니다.</h1>";
		
		// Gmail SMTP 서버 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});
		
		// 메일발송
		Message message = new MimeMessage(session);
		int status = 0;
		try {
			logger.debug("메일 시작...");
			message.setFrom(new InternetAddress(sender, "관리자", "UTF-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=utf-8");
			Transport.send(message);
			status = 1;
		} catch (Exception e) {
			logger.error("메일전송 실패..."+e.getMessage());
		}
		logger.debug("메일 전송 성공...");
		
		int result [] = {status, code};
		return result;
	}
}

package kr.co.Jboard2.Service.user;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.dao.UserDAO;
import kr.co.Jboard2.vo.termsVO;
import kr.co.Jboard2.vo.userVO;

public enum UserService {
	
	INSTANCE;
	private UserDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String password = "kxuxvucyzszwfipr";
	
	private UserService() {
		dao = new UserDAO();
	}
	
	public int[] sendEmailCode(String receiver) {
		
		// 인증코드 생성
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
		
		// 기본정보
		String sender = "me03454@gmail.com";
		
		String title = "jboard2 인증코드 입니다.";
		String content = "<h1>인증코드는 "+code+" 입니다.</h1>";
		
		// Gmail SMTP 서버 설정
		Properties props = new Properties(); // K-V(String - Stirng) 자료 유형
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
			e.printStackTrace();
			logger.error("메일전송 실패...");
		}
		
		logger.debug("메일 전송 성공...");
		
		int result[] = {status, code};
		
		return result;
	}
	
	// create
	public void insertUser(userVO vo) {
		dao.insertUser(vo);
	}
	
	// read
	public termsVO selectTerms() {
		return dao.selectTerms();
	}
	public void selectUser() {}
	public void selectUsers() {}
	public int selectCountUserUid(String uid) {
		return dao.selectCountUserUid(uid);
	}
	public int selectCountUserNick(String nick) {
		return dao.selectCountUserNick(nick);
	}
	// upload
	public void updateUser() {}
	
	// delete
	public void deleteUser() {}
	
}

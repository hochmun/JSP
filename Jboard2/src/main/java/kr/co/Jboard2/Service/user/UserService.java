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
	
	// 이메일 코드전송
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
	// 사이트 이용약관 불러오기
	public termsVO selectTerms() {
		return dao.selectTerms();
	}
	
	// 이메일로 아이디 정보 찾기(아이디 찾기, 비밀번호 찾기 공통 사용)
	public userVO selectUserEmail(String Email) {
		return dao.selectUserEmail(Email);
	}
	
	public userVO selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	}
	
	public void selectUsers() {}
	
	/**
	 * 회원정보 접근 - 아이디 비밀번호 체크
	 * @param uid
	 * @param pass
	 * @return
	 */
	public int selectUserCheck(String uid, String pass) {
		return dao.selectUserCheck(uid, pass);
	}
	
	// 아이디 중복검색
	public int selectCountUserUid(String uid) {
		return dao.selectCountUserUid(uid);
	}
	
	// 닉네임 중복검색
	public int selectCountUserNick(String nick) {
		return dao.selectCountUserNick(nick);
	}
	
	// 이름 이메일 검사
	public int selectCountUserName(String name, String email) {
		return dao.selectCountUserName(name, email);
	}
	
	// 아이디 이메일 검사
	public int selectCountUserUid(String uid, String email) {
		return dao.selectCountUserUid(uid, email);
	}
	
	// 유저 자동 로그인 여부 검사
	public userVO selectUserBySessId(String sessId) {
		return dao.selectUserBySessId(sessId);
	}
	
	// upload
	/**
	 * 유저 정보 갱신
	 * @param vo
	 */
	public int updateUser(userVO vo) {
		return dao.updateUser(vo);
	}
	
	/**
	 * 비밀 번호 변경 안함
	 * @param vo
	 * @return
	 */
	public int updateUserNotPass(userVO vo) {
		return dao.updateUserNotPass(vo);
	}
	
	public int updateUserPass(String uid, String pass) {
		return dao.updateUserPass(uid, pass);
	}
	
	public void updateUserForSession(String sessId, String uid) {
		dao.updateUserForSession(sessId, uid);
	}
	
	public void updateUserForSessLimitDate(String sessId) {
		dao.updateUserForSessLimitDate(sessId);
	}
	
	public void updateUserForSessionOut(String uid) {
		dao.updateUserForSessionOut(uid);
	}
	
	// delete
	/**
	 * 유저 정보 삭제
	 * @param uid
	 * @return
	 */
	public int deleteUser(String uid) {
		return dao.deleteUser(uid);
	}
	
}

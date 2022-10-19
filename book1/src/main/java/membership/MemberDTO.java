package membership;
/*
 * 날짜 : 2022/10/19
 * 이름 : 심규영
 * 내용 : 회원 정보를 담을 DTO 클래스, p218
 * 
 * 	DTO (Data Transfer Object)
 *   - 계층 사이에서 데이터를 교환하기 위해 생성하는 객체
 *   - 값 객체(Value Object, VO)라고도 함
 *   - Bean
 */
public class MemberDTO {
	// 멤버 변수 선언
	private String id;
	private String pass;
	private String name;
	private String regidate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegidate() {
		return regidate;
	}
	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}
	
}

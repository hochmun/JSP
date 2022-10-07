package common;
/*
 * 날짜 : 2022/10/07
 * 이름 : 심규영
 * 내용 : Person 클래스 (자바빈즈 규약), p118
 */
public class Person {
	private String name;
	private int age;
	
	public Person() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

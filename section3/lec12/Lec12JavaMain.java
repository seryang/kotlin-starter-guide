package section3.lec12;

public class Lec12JavaMain {
	public static void main(String[] args) {
		// Person.Companion.newBaby("이름 없음"); // companion object 의 이름이 없을 접근
		Person.newBaby("@JvmStatic 사용"); // @JvmStatic 어노테이션을 사용한 접근
		Person.Factory.newBaby("이름 있음"); // companion object 의 이름이 있다면 이름으로 접근 가능
	}
}

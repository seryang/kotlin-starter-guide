package section3.lec11


val a = 3 // 앞에 protected를 붙일 수 없음

fun main() {

    // 11강. 코틀린에서 접근 제어를 다루는 방법
    // 1. 자바와 코틀린의 가시성 제어
    // 자바는 public(모든 곳), protected(같은 패키지 or 하위클래스), default(같은 패키지), private(선언된 클래스내)
    // 코틀린은 public (모든 곳에), private (선언된 클래스내)
    // 코틀린의 protected (선언된 클래스 또는 하위 클래스) - 자바의 protected는 같은 패키지 내에서 접근 가능
    // ㄴ 코틀린에서는 패키지를 namespace를 관리하기 위한 용도로만 사용! 가시성 제어에는 사용되지 않음
    // 코틀린의 default(같은 모듈)
    // ㄴ internal 이라는 새로운 가시성 제어가 생김 (모듈은 한번에 컴파일 되는 kotlin 코드)
    // 자바의 기본 접근 지시어는 default, !코틀린의 기본 접근 지시어는 public!


    // 2. 코틀린 파일의 접근 제어
    // 코틀린은 .kt 파일에 변수, 함수, 클래스 여러개를 바로 만들 수 있음
    // * 코틀린 파일내에서
    // ㄴ pulbic (기본값. 어디서든 접글할 수 있음)
    // ㄴ protected (파일 최상단에는 사용 불가능)
    // ㄴㄴ 이유는 protected 가 코틀린에서는 선언된 클래스나 하위 클래스에 작동하는 지시어인데 애당초 클래스가 아닌 파일이기 때문
    // ㄴ internal (같은 모듈에서는 접근 가능)
    // ㄴ private (같은 파일 내에서만 접근 가능)

    // 3. 다양한 구성요소의 접근 제어
    // ㄴ 클래스 안의 멤버
    // public : 모든 곳에서 접근 가능
    // protected : 선언된 클래스 또는 하위 클래스에서 접근 가능
    // internal(default) : 같은 모듈에서만 접근 가능
    // private : 선언된 클래스내에서만 접근 가능
    // ㄴ 생성자
    // 생성자도 가시성 범위는 동일. 단!
    // 생성자에 접근 지시어를 붙이려면, constructor 을 붙여줘야함
    // ㄴ 프로퍼티
    // 프로퍼티도 가시성 범위는 동일. 단!
    // 프로퍼티의 가시성을 제어하는 방법으로는 2가지가 있음
    // 첫번째는 getter, setter 한번에 접근 지시어를 정하거나, getter는 public/ setter는 private 으로 만들고 싶으면 custom getter/setter를 만들었던 것처럼 앞에 추가로 가시성을 부여할 수 있음
    // Car

    // 4. Java와 Kotlin을 함께 사용할 때 주의할 점
    // ㄴ Internal은 바이트 코드 상 public이 된다.
    // 때문에 Java 코드에서는 Kotlin 모듈의 internal 코드를 가져올 수 있다.
    // ㄴ Kotlin의 protected와 Java의 protected는 다르다.
    // Java는 같은 패키지의 Kotlin protected 멤버에 접근할 수 있다.
    // Java가 생각하는 protected와 코틀린이 생각하는 protected가 다름

    // * 정리 *
    // - Kotlin에서 패키지는 namespace 관리용이기 때문에 protected는 의미가 달라짐
    // - Kotlin에서 default가 사라지고, 모듈간의 접근을 통젷나느 internal이 새로 생김
    // - 생성자에 접근 지시어를 붙일 떄는 constructor를 명시적으로 써주어야 함
    // - 유틸성 함수를 만들 때는 파일 최상단을 이용하면 편리
    // - 프로퍼티의 custom setter에 접근 지시어를 붙일 수 있음
    // - java에서 kotlin 코드를 사용할때는 internal과 protected는 주의해야함
}

class A private constructor()
class B internal constructor()
class C protected constructor() // protected에 줄이 뜨는 이유는 open을 써주라는 거임
                                // protected는 하위 클래스와 본인 자신만 쓸수 있는데 애초에 코틀린은 하위 클래스를 애당초 못 만들게 final 자동으로 붙어 있기 떄문
class D public constructor() // public constructor 는 생략되어 있는 거임
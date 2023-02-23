package section3.lec10

fun main() {

    /* Let10. 코틀린에서 상속을 다루는 방법 */
    // 1. 추상 클래스
    // Animal 추상클래스, cat, penguin 하위 클래스
    // java, kotlin 모두 추상 클래스는 인스턴스화 할 수 없다.
    val cat = Cat("페르시안", 4)
    println(cat)
    val penguin = Penguin("북극")
    println(penguin)


    // 2. 인터페이스
    // java, kotlin 모두 인터페이스를 인스턴스화 할 수 없다.
    // kotlin 에서는 backing field 가 없는 프로퍼티를 Interface 에 만들 수 있다


    // 3. 클래스를 상속할 떄 주의할 점
    val derived = Derived(10)
    // 출력 순서는 Base Class (부모) Derived Class (자식)
    // number 가 0이 나옴......
    // *** Derived 를 인스턴스화 하면서 number 에 값을 집어 넣어준다는 건데, 이때 상위 클래스(Base)에서 number 를 호출하면 하위 클래스(Derived)의 number 를 가져오는데
    // *** 아직 상위 클래스에 constructor 가 먼저 실행된 단계라서 하위 클래스에 number 는 초기화가 이루어지지 않은 상태임
    // 따라서 상위 클래스를 설계할 때 생성자 또는 초기화 블록에 사용되는 프로퍼티에는 open 을 꼭 !!!! 피해야 함


    // 4. 상속 관련 키워드 4가지 정리
    // (1) final : override 를 할 수 없게 한다. defualt 로 보이지 않게 존재한다.
    // (2) open : override 를 열어 준다.
    // (3) abstract : 반드시 override 해야 한다
    // (4) override : 상위 타입을 오버라이드 하고 있다.

    /* 정리 */
    // 상속 또는 구현을 할때에 : 을 사용해야 한다
    // 상위 클래스 상속을 구현할 때 생성자를 반드시 호출해야 함
    // override 를 필수로 붙여야 함
    // 추상 멤버가 아니면 기본적으로 오버라이드가 불가능하다.
    // ㄴ open 을 사용해주어야 한다
    // 상위 클래스의 생성자 또는 초기화 블록에서 open 프로퍼티를 사용하면 얘기치 못한 버그가 생길 수 있다.
}

open class Base(
    open val number: Int = 100
) {
    init {
        println("Base Class")
        println(number) // 하위 클래스의 field 에 접근하면 안된다고 경고가 뜸
    }
}

class Derived(
    override val number: Int
) : Base(number) {
    init {
        println("Derived Class")
        println(number)
    }
}
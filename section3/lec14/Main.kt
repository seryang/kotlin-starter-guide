import section3.lec14.NaverCar
import section3.lec14.PersonDto

fun main() {
    // Lec14. 코틀린에서 다양한 클래스를 다루는 방법
    // 1. Data Class (DTO - 계층간의 데이터를 전달하기 위함 Data Transfer Object)
    // ㄴ IDE를 활용할 수도 있고, lombok을 활용할 수도 있지만 클래스가 장황해지거나 클래스 생성 이후 추가적인 처리를 해줘야 하는 단점이 있음
    // ㄴ data 키워드를 붙여주면 equals, hashCode, toString을 자동으로 만들어준다!
    // ㄴ 여기에 named argument 까지 활용하면 builder pattern을 쓰는 것 같은 효과도 있음


    val dto1 = PersonDto("최세량", 38)
    val dto2 = PersonDto("최세량", 37)

    println(dto1 == dto2)
    println(dto1.hashCode())
    println(dto2.toString())

    val dto3 = PersonDto(name = "최세량", age = 12)
    println(dto3)

    // 2. Enum Class
    // ㄴ (Java) 추가적인 클래스를 상속받을 수 없음. 인터페이스는 구현할 수 있으며, 각 코드가 싱글톤
    // ㄴ 코틀린에서 Enum에 대한 분기 처리를 할때 when을 사용해서 조금 더 읽기 쉬운 코드로 바꿀 수 있음
    // ㄴ 컴파일러가 country 의 모든 타입을 알고 있어 다른 타입에 대한 로직(else)을 작성하지 않아도 됨 (return 하는 경우에도 동일)
    // ㄴ 사라지건 빠지건 이 경우에 IDE단에서 warning을 주거나 하는 식으로 알 수 있음

    // 3. Sealed Class, Sealed Interface
    // 상속이 가능하도록 추상클래스를 만들까 하는데, 외부에서는 이 클래스를 상속받지 않으면 좋겠다?
    // ㄴ 하위 클래스를 봉인(sealed)하자!
    // 컴파일 타임때 하위 클래스의 타입을 모두 기억함. 즉, 런타임때 클래스 타입이 추가될 수 없음
    // 하위 클래스는 같은 패키지에 있어야 함
    // Enum 과 다른점은 클래스를 상속받을 수 있으며, 하위 클래슨느 멀티 인스턴스가 가능함
    handleCar(NaverCar.Avante())
    // 추상화가 필요한 Entity or DTO에 sealed class를 활용 (jdk17에서도 sealed class 추가됨)

    // *정리*
    // 코틀린의 Data class를 사용하면 equals, hashCode, toString을 자동으로 만들어 줌
    // 코틀린의 Eum class는 자바의 Enum Class와 동일하지만, when과 함꼐 사용함으로써 큰 장점을 갖음
    // Enum class 보다 유연하지만, 하위 클래스를 제한하는 Sealed Class 역시 when과 함께 주로 사용됨
}

private fun handleCar(car: NaverCar) {
    when(car) {
        is NaverCar.Avante -> TODO()
        is NaverCar.Sonata -> TODO()
        is NaverCar.Grandeur -> TODO()
    }
}
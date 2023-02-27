package section3.lec13


fun main() {

    // Lec 13. 코틀린에서 중첩 클래스를 다루는 방법
    // 1. 중첩 클래스의 종류
    // 2. 코틀린의 중첩 클래스와 내부 클래스
    // ㄴ 기본적으로 바깥 클래스 참조하지 않음
    // ㄴ 바깥 클래스를 참조하고 싶다면 inner 키워드를 추가
    val h = House("잠실")

    println(h.livingRoom.address)

    // * 정리 *
    // 클래스 안에 클래스가 있는 경우 종류는 두 가지
    // ㄴ (Java기준) static을 사용하는 클래스
    // ㄴ (Java기준) static을 사용하지 않는 클래스
    // 권장되는 클래스는 static을 사용하는 클래스
    // 코틀린에서 이러한 가이드를 따르기 위해
    // ㄴ 클래스 안에 기본 클래스를 사용하면 바깥 클래스에 대한 참조가 없고
    // ㄴ 바깥 클래스를 참조하고 싶다면, inner 키워드르르 붙여야 함
    // 코틀린 inner class에서 바깥 클래스를 참조하려면 this@바깥클래스를 사용해야 함
    // (Java) 클래스 안의 static 클래스가 (Kotlin)에서는 클래스 안의 클래스로 바뀜  --> 권장되는 유형
    // (Java) 클래스 안의 클래스가 (Kotlin)에서는 클래스 안의 inner 클래스로 바뀜
}

class House(
    private val address: String = "잠실"
) {
    val livingRoom = this.LivingRoom(10.0)

    inner class LivingRoom( // (권장되지 않는) 내부 클래스를 코틀린에서 만들때는 inner 라는 키워드를 직접적으로 붙여 주어야 함
        private val area: Double
    ) {
        val address: String
            get() = this@House.address + " 서울시" + this.area // 바깥클래스 참조를 위해 this@바깥클래스를 사용
    }
}
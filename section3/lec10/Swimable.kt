package section3.lec10

interface Swimable {
    val swimAbility: Int
        get() = 3

    fun act() { // default 키워드 없이 (jdk8에서 나옴) 메소드 구현이 가능하다
        println("어푸 어푸")
    }
}
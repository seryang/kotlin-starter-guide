package section3.lec10

interface Flyable {
    fun act() { // default 키워드 없이 (jdk8에서 나옴) 메소드 구현이 가능하다
        println("파닥 파닥")
    }

    fun fly() // kotlin 에서도 추상 메소드를 만들 수 있다
}
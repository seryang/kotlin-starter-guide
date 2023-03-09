package section3.lec12

fun main() {
    // Lec12. 코틀린에서 Object 키워드를 다루는 방법
    // 1. static 함수와 변수
    // ㄴ 코틀린에는 static 이 없음
    // ㄴ companion object 를 사용
    // ㄴ companion object 도 결국 객체. 따라서 이름을 붙일 수도 있고, interface 를 구현할 수 도 있음
    // ㄴ companion object 에 유틸성 함수들을 넣어도 되지만, 최상단 파일을 활용하는 것을 추천함!

    // 2. 싱글톤
    // object 클래스명
    println(Singleton.a)
    Singleton.a += 10
    println(Singleton.a)
    // 애당초 인스턴스가 하나이기 때문에 싱글톤 클래스에 대해서는 인스턴스화를 하는게 아니라 코드에서 바로 필드 혹은 함수 접근 가능
    // 코틀린에서 직접적으로 싱글톤을 만들 필요는 전혀 없음

    // 3. 익명 클래스
    // 특정 인터페이스나 클래스를 상속받은 구현체를 일회성으로 사용할 때 쓰는 클래스
    moveSomething(object : Movable { // 자바는 new Movable)(), 코틀린에서는 object : 타입이름
        override fun fly() {
            TODO("Not yet implemented")
        }

        override fun move() {
            TODO("Not yet implemented")
        }
    })

    // * 정리 *
    // - Java 의 static 변수와 함수를 만드려면, Kotlin 에서는 companion object 를 사용해야 한다.
    // - companion object 도 하나의 객체로 간주되기 때문에 이름을 붙일 수 있고, 다른 타입을 상속받을 수도 있다.
    // - Kotlin 에서 싱글톤 클래스를 만들 때 object 키워드를 사용한다.
    // - Kotlin 에서 익명 클래스를 만들 때 Object : 타입을 사용한다.
}

private fun moveSomething(movable: Movable) {
    movable.move()
    movable.fly()
}

class Person private constructor(
    val name: String, val age: Int
) {
    companion object Factory : Log { // static 대신 companion object (클래스와 동행하는 유일한 오브젝트)를 사용
        private const val MIN_AGE: Int =
            1 // val 는 런타임시 1로 할당됨. const 를 붙이면 컴파일시에 변수가 할당됨. 따라서 진짜 상수에 붙이기 위한 용도. 기본 타입과 String에 붙임

        @JvmStatic // java 클래스에서 함수를 바로 접근할때 사용
        fun newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }

        override fun log() {
            println("나는 Person 클래스의 동객객체 Factory예요")
        }
    }
}

object Singleton {
    var a: Int = 0
}
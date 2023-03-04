package section4.lec17

fun main() {
    // Lec17. 코틀린에서 람다를 다루는 방법
    // 1. java 에서 람다를 다루기 위한 노력
    // ㄴ 변수 -> 변수를 이용한 함수
    // ㄴ (변수1, 변수2) -> 변수1과 변수 2를 이용한 함수


    // 2. 코틀린에서의 람다
    // ㄴ Java와는 근본적으로 다른 한 가지
    // ㄴ 코틀린에서는 함수가 그 자체로 값이 될 수 있다. 변수에 할당할 수도, 파라미터로 넘길 수도 있다.
    val fruits = listOf(
        Fruit("사과", 1_000),
        Fruit("사과", 1_200),
        Fruit("사과", 1_200),
        Fruit("사과", 1_500),
        Fruit("바나나", 3_000),
        Fruit("바나나", 3_200),
        Fruit("바나나", 2_500),
        Fruit("수박", 10_000)
    )

    // 람다를 만드는 방법 1
    // 함수의 타입 : (파라미터 타입..) -> 반환 타입
    val isApple: (Fruit) -> Boolean = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }

    // 람다를 만드는 방법 2 (이 방법을 많이 사용)
    // 함수의 타입 : (파라미터 타입..) -> 반환 타입
    val isApple2: (Fruit) -> Boolean = { fruit: Fruit -> fruit.name == "사과" }

    // 람다를 호출하는 방법 1
    isApple(fruits[0])

    // 람다를 호출하는 방법 2
    isApple.invoke(fruits[0]) // invoke를 불러주면서 함수를 쓰는 법

    // Kotlin 에서는 함수가 1급 시민
    filterFruits(fruits, isApple)
    // 함수 자체를 파라미터로 받게 함
    filterFruits(fruits, { fruit: Fruit -> fruit.name == "사과" })
    // 마지막 파라미터가 함수인 경우, 소괄호 밖에 람다 사용 가능
    filterFruits(fruits) { fruit: Fruit -> fruit.name == "사과" }

    // 람다를 여렂루 작성할 수 있고, 마지막 줄의 결과가 람다의 반환값!!!
    filterFruits(fruits) { fruit: Fruit ->
        println("사과만 받는다..!!")
        fruit.name == "사과"
    }
    filterFruits(fruits) { fruit -> fruit.name == "사과" }
    filterFruits(fruits) { a -> a.name == "사과" }

    // 람다를 작성할때, 람다의 파라미터를 it 으로 직접 참조할 수 있다.
    // 다만, 부르는쪽에서는 it 데이터가 어떤 데이터인지 잘 모르기 때문에 fruit -> fruit.name 이 더 선호된다
    filterFruits(fruits) { it.name == "사과" }

    // 3. Closure
    // Java 에서는 람다를 쓸 때 사요할 수 있는 변수에 제약이 있음
    // 단, 코틀린에서는 아무런 문제 없이 동작한다 !
    // 코틀린에서는 람다가 시작하는 지점에 참조하고 있는 변수들을 모두 포획하여 그 정보를 가지고 있다.
    // 이렇게 해야만, 람다를 진정한 일급 시민으로 간주할 수 있다.
    // 이 데이터 구조를 Closure 라고 부른다.
    var targetFruitName: String = "바나나"
    targetFruitName = "수박"
    filterFruits(fruits) { it.name == targetFruitName }

    // 4. 다시 try with resources
    // (7강 코드 참고)
    // ex) public inline fun <T : Closeable?, R> T.use(block: (T) -> R): R {
    // Closeable 구현체에 대한 확장 함수이다. (확장함수는 "타입.함수이름"으로 확장)
    // inline 함수
    // 받고 있는 파라미터가 block이라는 이름을 가진 함수. (T) -> R 함수 (T타입의 파라미터를 받고 R 타입의 반환 타입을 갖는 람다를 받도록 만들어진 함수)

    /* 정리 */
    // 함수는 Java 에서 2급시민이지만, 코틀린에서는 1급시민
    // ㄴ 때문에, 함수 자체를 변수에 넣을 수도 있고 파라미터로 전달할 수도 있다.
    // 코틀린에서 함수 타입은 (파라미터 타입, ...) -> 반환타입
    // 코틀린에서 람다는 두 가지 방법으로 만들 수 있고, {} 방법이 더 많이 사용되어짐
    // 함수를 호출하며, 마지막 파라미터인 람다를 쓸 때는 소괄호 밖으로 람다를 뺼 수 있다.
    // 람다의 마지막 expression 결과는 람다의 반환 값
    // 코틀린에서는 Closure를 사용하여 non-final(값이 바뀌는 가변) 변수도 람다에서 사용할 수 있다.

}

private fun filterFruits(
    fruits: List<Fruit>, filter: (Fruit) -> Boolean
): List<Fruit> {
    val results = mutableListOf<Fruit>()
    for (fruit in fruits) {
        if (filter(fruit)) {
            results.add(fruit)
        }
    }
    return results
}

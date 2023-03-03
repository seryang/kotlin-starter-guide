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

    val isApple = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }


    // 3. Closure

    // 4. 다시 try with resources

}


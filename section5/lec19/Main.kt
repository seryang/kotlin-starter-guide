package section5.lec19

import section4.lec17.Fruit
import section5.lec19.a.printHelloWorld as printHelloWorldA
import section5.lec19.b.printHelloWorld as printHelloWorldB


/* Lec 19. 코틀린의 이모저모 */
// 긴 이름의 클래스 혹은 함수 타입이 있을때 축약하거나 더 좋은 이름을 쓰고 싶다!
// 1. Type Alias 와 as import
typealias FruitFilter = (Fruit) -> Boolean

fun filterFruits(furits: List<Fruit>, filter: FruitFilter) {

}

data class UltraSuperGuardTribe(
    val name: String
)

typealias UGTMap = Map<String, UltraSuperGuardTribe>

// 다른 패키지의 같은 이름 함수를 동시에 가져오고 싶다면?!
// 이름이 같은 서로 다른 두 함수를 가져오기 위해서는 as import 를 사용

data class Person(
    val name: String,
    val age: Int
)

class People(
    val n: String,
    val a: Int
) {
    operator fun component1(): String {
        return this.n
    }

    operator fun component2(): Int {
        return this.a
    }
}

fun main() {
    printHelloWorldA()
    printHelloWorldB()

    // 2. 구조분해와 componentN 함수
    // 구조분해 : 복합적인 값을 분해하여 여러 변수를 한 번에 초기화하는 것
    val person = Person("최세량", 38)
    // val (name, age) = person
    val name = person.component1()
    val age = person.component2()
    // componentN은 무슨 의미냐? 데이터 클래스는 기본적으로 자기가 가지고 있는 field 에 대해서 componentN 이라는 함수를 만들어주는데
    // 예를 들어 component1은 첫번째 프로퍼티를, component2는 두번째 프로퍼티를 가져옴
    println("이름 : ${name}, 나이 : ${age}")
    // Data Class 가 아닌데 구조분해를 사용하고 싶다면, componentN 함수를 직접 구현해줄 수도 있다.
    val people = People("최세량", 38)
    val (n, a) = people
    println("이름 : ${n}, 나이 : ${a}")

    // 이 역시 구조분해 문법
    val map = mapOf(1 to "A", 2 to "B")
    for ((key, value) in map.entries) {
        println("key : ${key}, value : ${value}")
    }

    // 3. Jump 와 Label
    // return : 기본적으로 가장 가까운 enclosing function 또는 익명함수로 값이 반환
    // break : 가장 가까운 루프가 제거됨
    // continue : 가장 가까운 루프를 다음 step 으로 보냄
    // for 문 및 while 문에서 break, continue 기능은 JAVA 와 동일. 단!

    val numbers = listOf(1, 2, 3)
    for (number in numbers) {
        println(number)
    }
    // numbers.map { number -> number + 1 }.forEach { number -> println(number) }
    numbers.map { it + 1 }
        .forEach {
            if (it == 3)
//            continue (사용못함)
//            break (사용못함)
//                return (사용가능)
                println(it)
        }

    // foreach 문에서 break 를 사용하려면 다음과 같이
    run {
        numbers.map { it + 1 }
            .forEach {
                if (it == 3) return@run // break 와 동일한 기능
                println(it)
            }
    }

    // foreach 문에서 continue 를 사용하려면 다음과 같이
    numbers.map { it + 1 }
        .forEach {
            if (it == 3)
                return@forEach
            println(it)
        }
    // break, continue 를 사용할 때엔 가급적 익숙한 for 문 사용을 추천!!

    // 코틀린에는 라벨이라는 기능이 있다.
    // 특정 expression 에 라벨이름@ 을 붙여 하나의 라벨로 간주하고 break, continue, return 등을 사용하는 기능
    // 라벨을 사용한 jump 는 사용하지 않는 것을 강력 추천함
    println("-- 코틀린 라벨 --")
    abc@ for (i in 1..100) {
        for (j in 1..100) {
            if (j == 2) {
                break@abc
            }
            println("${i} ${j}")
        }
    }
}

// 4. TakeIf와 TakeUnless
// 코틀린에서는 method chaining 을 위한 특이한 함수를 제공
fun getNumberOrNull(num: Int): Int? {
    return if (num <= 0) {
        null
    } else {
        num
    }
}

// 위의 함수를 아래와 같이 동일하게 변경 가능
// 주어진 조건을 만족하면 그 값이, 그렇지 않으면 null 이 반환
fun getNumberOrNullV2(num: Int): Int? {
    return num.takeIf { it > 0 }
}

// 위의 함수와 반대 기능
// 주어진 조건을 만족하지 않으면 그 값이, 그렇지 않으면 null 이 반환
fun getNumberOrNullV3(num: Int): Int? {
    return num.takeUnless { it <= 0 }
}


// *정리*
// 타입에 대한 별칭을 줄 수 있는 typealias 라는 키워드 존재
// import 당시 이름을 바꿀 수 있는 as import 기능이 존재
// 변수를 한 번에 선언할 수 있는 구조분해 기능이 있으며 componentN 함수를 사용함
// for 문, while 문과 달리 forEach 에는 break 와 continue 를 사용할 수 없다.
// takeIf 와 takeUnless 를 활용해 코드양을 줄이고 method chaining 를 활용할 수 있다
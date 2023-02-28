package lec15


fun main() {

    // Lec15. 코틀린에서 배열과 컬렉션을 다루는 방법

    // 1. 배열
    // 사실 배열은 잘 사용하지 않음! effective java 에서도 배열보다는 리스트를 사용하라고 함
    val array = arrayOf(100, 200)
    val array1 = arrayOf(100, "200")

    // 방법 1
    for (i in array) {
        println("$i")
    }

    // 방법 2
    // array.indices 는 0부터 마지막 index 까지의 Range
    for (i in array.indices) {
        println("$i ${array[i]}")
    }

    // 방법 3
    // withIndex() 를 사용하면, 인덱스와 값을 한 번에 가져올 수 있음
    for ((index, value) in array1.withIndex()) {
        println("$index $value")
    }

    array.plus(300) // 배열에 element 를 추가할 수도 있음
    for (i in array.indices) {
        println("$i ${array[i]}")
    }

    // 2. 코틀린에서의 Collection - List, Set, Map
    // 컬렉션을 만들어 줄 때 불변인지, 가변인지를 설정해야 함
    // 가변 (Mutable) 컬렉션 : 컬렉션에 element 를 추가, 삭제할 수 있음
    // 불변 컬렉션 : 컬렉션에 element 를 추가, 삭제할 수 없음
    // Collection 을 만들자마자 Collections.unmodifiableList() 등을 붙여준다 !- 불변
    // 불변 컬렉션이라 하더라도 Reference Type 인 Element 의 필드는 바꿀 수 있음

    val numbers: List<Int> = listOf(100, 200) // listOf 는 '불변 리스트'
    val emptyList = emptyList<String>() // 비어있는 리스트는 타입 선언을 해줘야함
    printNumbers(emptyList()) // 타입 추론이 가능하면 생략 가능

    numbers[0] // 대괄호로 접근 가능. (= numbers.get(0))


    val mutableNumbersList = mutableListOf(100, 200) // mutableListOf 는 '가변 리스트'
    mutableNumbersList.add(300)
    mutableNumbersList.plus(400) // list 에 plus 는 어떻게 동작하는지? 왜 add 가 안되는 건지?

    println("- mutableNumbersList--")
    for (i in mutableNumbersList) {
        println(i)
    }
    // List Tip! 불변 리스트를 만들고, 꼭 필요한 경우 가변 리스트로 바꾸자 !

    // 집합은 list 와 다르게 순서가 없고, 같은 element 는 하나만 존재할 수 있다.
    // 자료구조적 의미만 제외하면 모든 기능이 List 와 비슷하다!
    val setNumbers = setOf(100, 200)
    for (i in setNumbers) {
        println(i)
    }

    // 가변 집합은
    val setMutableNumbers = mutableSetOf(100, 200)
    for (i in setMutableNumbers) {
        println(i)
    }

    // map
    // kotlin 도 동일하게 MutableMap 을 만들어 넣을 수도 있고, 정적 팩토리 메소드를 바로 활용할 수도 있음
    // 타입을 추론할 수 없어, 타입을 지정해줘야함
    // 가변 Map 이기 때문에 (K, V)를 넣을 수 있음
    // java 철머 put 을 쓸 수도 있고, map[K] = V 을 쓸 수도 있음
    val oldMap = mutableMapOf<Int, String>()
    oldMap.put(1, "monday")
    oldMap[2] = "tuesday"

    // mapOf(K to V) 를 사용해 불변 map 을 만들 수 있음
    mapOf(1 to "monday", 2 to "tuesday")

    for (key in oldMap.keys) {
        println(key)
        println(oldMap[key])
    }

    for ((k, v) in oldMap.entries) {
        println("$k - $v")
    }

    // 3. 컬렉션의 null 가능성, Java 와 함꼐 사용하기
    // ? 위치에 따라 null 간으성 의미가 달라지므로 차이를 잘 이해해야 함!!
    // List<Int?> : 리스트에 null 이 들어갈 수 있지만, 리스트는 절대 null 이 아님
    // List<Int>? : 리스트에 null 이 들어갈 수 없지만, 리스트는 null 일 수 있음
    // List<Int?>? : 리스트에 null 이 들어갈 수 있고, 리스트가 null 일 수 있음
    // Java 는 읽기 전용 컬렉션과 변경 가능 컬렉션을 구분하지 않음


    // * 정리
    // 배열의 사용법은 약간 다르다.
    // 코틀린에서는 컬렉션을 만들 때도 불변/가변을 지정해야 함
    // List, Set, Map 에 대한 사용법이 변경, 확장되었다.
    // Java 와 Kotlin 코드를 섞어 컬렉션을 사용할 때는 주의해야 함
    // ㄴ Java 에서 Kotlin 컬렉션을 가져갈 때는 불변 컬렉션을 수정할 수도 있고, non-nullable 컬렉션에 null 을 넣을 수도 있음
    // ㄴ Kotlin 에서 Java 컬렉션을 가져갈 때는 플랫폼타입을 주의해야 함
}

private fun printNumbers(numbers: List<Int>) {
    for (i in numbers.indices) {
        println("${numbers[i]}")
    }

    for (i in numbers) {
        println(i)
    }
}
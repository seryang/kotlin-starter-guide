package section4.let18

import section4.lec17.Fruit

val List<Fruit>.samePriceFilter: List<Fruit>
    get() = this.filter(Fruit::isSamePrice)

fun main() {
    // Lec18. 코틀린에서 컬렉션을 함수형으로 다루는 방법
    // 1. 필터와 맵
    val fruits = listOf(
        Fruit(1L, "사과", 1_000, 1_200),
        Fruit(2L, "사과", 1_200, 1_200),
        Fruit(3L, "사과", 1_200, 1_200),
        Fruit(4L, "사과", 1_500, 1_200),
        Fruit(5L, "바나나", 3_000, 3_200),
        Fruit(6L, "바나나", 3_200, 3_200),
        Fruit(7L, "바나나", 2_500, 3_200),
        Fruit(8L, "수박", 10_000, 10_000)
    )

    // filter 로 사과만
    val apple = fruits.filter { fruit -> fruit.name == "사과" }
    println(apple)

    // filter 로 수박만
    val waterMelon = fruits.filter { it.name == "수박" }
    println(waterMelon)

    // filter 에서 인덱스가 필요하다면?!
    val filterApple = fruits.filterIndexed { idx, fruit ->
        println(idx)
        fruit.name == "사과"
    }

    // map : 사과의 가격들을 알려주세요!
    val applePrices = fruits.filter { fruit -> fruit.name == "사과" }.map { fruit -> fruit.currentPrice }
    println(applePrices)

    // map에서 인덱스가 필요하다면?
    val applePricesIdx = fruits.filter { fruit -> fruit.name == "사과" }.mapIndexed { idx, fruit ->
        println(idx)
        fruit.currentPrice
    }
    println(applePricesIdx)

    // Mapping의 결과가 null이 아닌 것만 가져오고 싶다면!?
    val values = fruits.mapNotNull { fruit -> fruit.nullOrValue() }

    println(values)

    val filterFruits = filterFruits(fruits, { fruit: Fruit -> fruit.name == "사과" })

    // 2. 다양한 컬렉션 처리 기능
    // 모든 과일이 사과인가? 출고가 10,000원 이상의 과일이 하나라도 있나?
    // all : 조건을 모두 만족하면 true 그렇지 않으면 false
    val isAllApple = fruits.all { fruit -> fruit.name == "사과" }
    println(isAllApple) // false
    // none : 조건을 모두 불만족하면 true 그렇지 않으면 false
    val isNoneApple = fruits.none { fruit -> fruit.name == "사과" }
    println(isNoneApple) // false
    // any : 조건을 하나라도 만족하면 true 그렇지 않으면 false
    val isAnyApple = fruits.any { fruit -> fruit.factoryPrice >= 10_000 }
    println(isAnyApple) // true
    // 총 과일 갯구가 몇개인지? 낮은 가격 순으로 보여줘? 과일이 몇 종류 있지?
    // count : 개수를 세다
    val fruitCount = fruits.count()
    println(fruitCount)
    // sortedBy : (오름차순) 정렬을 한다
    val fruitSortAsc = fruits.sortedBy { fruit: Fruit -> fruit.currentPrice }
    println(fruitSortAsc)
    // sortedByDescending : (내림차순) 정렬을 한다
    val fruitSortDesc = fruits.sortedByDescending { fruit: Fruit -> fruit.factoryPrice }
    println(fruitSortDesc)
    // distinctBy : 변형된 값을 기준으로 중복을 제거한다
    val distinctFruitNames = fruits.distinctBy { fruit: Fruit -> fruit.name }.map { fruit: Fruit -> fruit.name }
    println(distinctFruitNames)

    // 첫번째 과일만 주세요, 마지막 과일만 주세요
    // first : 첫번째 값을 가져온다 (무조건 null이 아니어야함)
    // firstOrNull : 첫번째 값 또는 null을 가져온다
    // last : 마지막 값을 가져온다 (무조건 null이 아니어야함)
    // lastOrNull : 첫번째 값 또는 null을 가져온다

    // 3. List를 Map으로
    // 과일이름 -> List<과일>인 Map이 필요해요!
    val fruitMap: Map<String, List<Fruit>> = fruits.groupBy { fruit -> fruit.name }
    println(fruitMap)

    // id -> 과일인 Map이 필요해요!
    // associateBy 는 중복되지 않은 키를 가지고 map을 만들때 사용
    val priceMap: Map<Long, Fruit> = fruits.associateBy { fruit -> fruit.id }
    println(priceMap)

    // 과일이름 -> List<판매가격> Map이 필요해요!
    // groupBy({key}, {value})
    val nameListPriceMap: Map<String, List<Long>> = fruits.groupBy({ fruit -> fruit.name }, { fruit -> fruit.currentPrice })
    println(nameListPriceMap)

    // Id -> 판매가격 Map 이 필요해요!
    // associateBy({key}, {value})
    val namePriceMap: Map<Long, Long> = fruits.associateBy({ fruit -> fruit.id }, { fruit -> fruit.currentPrice })
    println(namePriceMap)

    // Map 에 대해서도 앞선 기능들을 대부분 사용할 수 있음
    // List를 Map (과일이름, 리스트<과일>) 로 그룹팅 후, 과일이름 (Key)가 사과인 경우만 filter 처리
    val map: Map<String, List<Fruit>> = fruits.groupBy { fruit -> fruit.name }
        .filter { (key, value) -> key == "사과" }
    println(map)

    // 4. 중첩된 컬렉션 처리
    val fruitsInList: List<List<Fruit>> = listOf(
        listOf(
            Fruit(1L, "사과", 1_000, 1_500),
            Fruit(2L, "사과", 1_200, 1_500),
            Fruit(3L, "사과", 1_200, 1_500),
            Fruit(4L, "사과", 1_500, 1_500)
        ),
        listOf(
            Fruit(5L, "바나나", 3_000, 3_200),
            Fruit(6L, "바나나", 3_200, 3_200),
            Fruit(7L, "바나나", 2_500, 3_200),
        ),
        listOf(
            Fruit(8L, "수박", 10_000, 10_000)
        )
    )
    // 출고가와 현재가가 동일한 과일을 골라주세요!
    // flatMap 을 사용하면 List<List<>>가 단일 List 로 바뀌게 됨
    val samePriceFruits = fruitsInList.flatMap { list ->
        list.filter { fruit -> fruit.currentPrice == fruit.factoryPrice }
    }
    println(samePriceFruits)

    // 위에걸 리팩토링 한다면 이런식으로
    val samePriceFruitRefactoring = fruitsInList.flatMap { list -> list.samePriceFilter }
    println(samePriceFruitRefactoring)

    // List<List<Fruit>>를 List<Fruit>로 바꿔줘!
    val flatten = fruitsInList.flatten()
    println(flatten)
}

// lec17 의 filterFruits 를 filter로 변경
private fun filterFruits(
    fruits: List<Fruit>, filter: (Fruit) -> Boolean
): List<Fruit> {
    return fruits.filter(filter)
}
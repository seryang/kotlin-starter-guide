package section5.lec20

import section3.lec09.Person
import section3.lec14.PersonDto

fun main() {

    // 1. scope function 이란 무엇인가?!
    // scope : 영역
    // function : 함수
    // scope + function = 일시적인 영역을 형성하는 함수
    // 람다를 사용해 일시적인 영역을 만들고 코드를 더 간결하게 만들거나, method chaining 에 활용하는 함수를 scope function 이라고 함

    val person = Person("최세량", 38)
    printPerson(person)


    // 2. scope function 의 분류
    // 5개의 종류가 있음
    // let  / run / also / apply / with (with 만 확장함수가 아님)
    // let 과 run 은 람다의 결과를 반환 (value1 과 value2 는 age 를 반환)
    // let 과 also 를 타고 들어가보면 일반 함수를 받는다.
    // ㄴ (T) -> R
    // ㄴ 일반 함수는 파라미터를 받아서 함수 내부에서 호출
    // ㄴ 그렇기 때문에 파라미터에 대한 이름을 직접 넣어줄 수 있는 것임
    // run 과 apply 를 타고 들어가보면 확장 함수를 받는다.
    // ㄴ T.() -> R
    // ㄴ T에 대한 확장 함수를 받음
    // ㄴ 확장함수에서는 본인 자신을 this 로 호출하고, 생략할 수 있었다.
    var value1 = person.let {// let 는 람다 안에서 it 사용
        it.age
    }
    value1 = person.let { seryang ->
        seryang.age
    }

    println(value1)
    val value2 = person.run {// run 은 람다 안에서 this 사용
        this.age
    }
    println(value2)
    // also 와 apply 는 객체 그 자체를 반환 (value3 와 value4 는 person 을 반환)
    val value3 = person.also {//  also 는 it 사용
        it.age
    }
    println(value3)
    val value4 = person.apply {// apply 는 this 사용
        this.age
    }
    println(value4)

    // 그럼 여기서 it 과 this 의 차이!?
    // this :  생략이 가능한 대신, 다른 이름을 붙일 수 없다.
    // it : 생략이 불가능한 대신, 다른 이름을 붙일 수 있다.

    // with (파라미터){람다} : this 를 사용해 접근하고, this 는 생략 가능하다.
    // ㄴ with 는 확장 함수가 아님!!
    with(person) {
        println(name)
        println(this.age)
    }

    // 3. 언제 어떤 scope function 을 사용해야 할까?!
    // (1) let
    // ㄴ하나 이상의 함수를 call chaining 결과로 호출 할때 사용하자
    val strings = listOf("APPLE", "CAR")
    strings.map { it.length }
        .filter { it >= 3 }
        .let(::println)

    // ㄴ위와 동일한 코드임
    strings.map { it.length }
        .filter { it > 3 }
        .let { lengths -> println(lengths) }

    // ㄴnon-null 값에 대해서만 code block 을 실행시킬 때
    val str = "abc"
    val length = str?.let {
        println(it.uppercase())
        it.length
    }

    // ㄴ일회성으로 제한된 영역에 지역 변수를 만들 때
    val numbers = listOf("one", "two", "three")
    val modifiedFirstItem = numbers.first()
        .let { firstItem ->
            if (firstItem.length >= 5) firstItem else "!$firstItem!"
        }.uppercase()
    println(modifiedFirstItem)

    // (2) run
    // ㄴ 객체 초기화와 반환 값의 계산을 동시에 해야 할 때
    // ㄴ 예로, 객체를 만들어 DB에 바로 저장하고, 그 인스턴스를 활용할 때
    val personRepository = mutableListOf<Person>()

    val p = Person("최세량", 38).run(personRepository::add)
    val p2 = Person("최세량", 38).run{
        age = 37
        personRepository.add(this)
    }

    // 개인적으로 잘 사용하지는 않음!
    // 아래 코드(p3)가 익숙하기도 하고, 반복되는 생성 후 처리는 생성자, 프로퍼티, init block 으로 넣는 것이 좋음
    val p3 = personRepository.add(Person("최세량", 38))

    // (3) apply
    // ㄴ apply 의 특징은 객체 그 자체가 반환
    // ㄴ 객체 설정을 할때에 객체를 수정하는 로직이 call chain 중간에 필요할 때
    createPerson("최세량", 38)
    val pp = Person("최세량", 58)
    pp.apply { this.age = 38 }
        .let{println(it)}

    // (4) also
    // ㄴ 객체 그자체가 반환
    // ㄴ 객체를 수정하는 로직이 call chain 중간에 필요할 때
    mutableListOf("one", "two", "three")
        .also { println("four 추가 이전 지금 값 : $it") }
        .add("four")

    val alsoTest = mutableListOf("one", "two", "three")
    println("four 추가 이전 지금 값 : $alsoTest")
    alsoTest.add("four")

    // (5) with
    // ㄴ 특정 객체를 다른 객체로 변환해야 하는데, 모듈 간의 의존성에 의해 정적 팩토리 혹은 toClass 함수를 만들기 어려울 때
//    return with(person) {
//        PersonDto(
//            name = name, // PersonDto.name = Person.name
//            age = age, // PersonDto.age = Person.age
//        )
//    }
    // this 를 생략할 수 있어 필드가 많아도 코드가 간결해진다.
    // 객체를 converting 할때 한쪽에 로직을 넣기 어려울 때 사용하는 게 좋음!!


    //4. scope function 과 가독성
    // ㄴ scope function 을 사용한 코드가 그렇지 않은 코드보다 가독성 좋은 코드일까!?
    // 1번 코드
    if (person != null && person.isAdult) {
        println("어른")
    } else {
        println("에러")
    }

    // 2번 코드
    person?.takeIf { it.isAdult }
        ?.let { _ ->
            println("어른")
        }
        ?: println("에러")

    // 개인적으로는 1번 코드가 더 좋다고 생각
    // 1. 구현 2는 숙련된 코틀린 개발자만 더 알아보기 쉽다.
    // ㄴ 어쩌면 숙련된 코틀린 개발자도 잘 이해하지 못할 수 있다.
    // 2. 구현 1의 디버깅이 쉽다.
    // 3. 구현이 1이 수정도 더 쉽다.
    // 사용 빈도가 적은 관용구는 코드를 더 복잡하게 만들고 이런 관용구들을 한 문장 내에서 조합해 사용하면 복잡성이 훨씬 증가한다.
    // 하지만 scope function 을 사용하면 안된다는 건 아님
    // 적절한 convention 을 적용하면 유용하게 적용할 수 있음

    // * 정리
    // - 코틀린의 scope function 은 일시적인 영역을 만들어 코드를 더 간결하게 하거나, method chain 에 활용된다.
    // - scope function 의 종류에는 let / run / also / apply / with 가 있었다.
    // - scope function 을 사용한 코드는 사람에 따라 가독성을 다르게 느낄 수 있기 때문에, 함께 프로덕트를 만들어 가는 팀끼리 convention 을 잘 정하자!

}

fun printPerson(person: Person?) {
    if (person != null) {
        println(person.name)
        println(person.age)
    }

    // 위의 코드를 리팩토링
    // safe call (?.) 을 사용 : person 이 null 이 아닐때에 let 을 호출
    // let (scope function 의 한 종류)을 통해 일시적인 영역이 생김
    person?.let {
        println(it.name)
        println(it.age)
        // 람다를 사용하고 있다. 람다 안에서는 it 을 통해 들어온 파라미터로 접근할 수 있음
    }
}

fun createPerson(
    name: String,
    age: Int,
): Person {
    return Person(
        name = name,
        age = age
    ).apply{
        this.age = age
    }
}
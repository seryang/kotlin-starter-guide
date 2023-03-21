package section3.lec09

fun main() {

    /* Lec 09. 코틀린에서 클래스를 다루는 방법 */
    /**********************************/

    // 1. 클래스와 프로퍼티
//    val person = section3.lec09.section1.Person("최세량", 38)
//    println(person.name + " " + person.age)
////    person.name = "김유경" // val 이라 대입 불가
//    // getter 대신 . 으로 필드 접근
//    person.age = 32
//    println(person.age)
//
//    // 2. 생성자와 init
//    // init (초기화) 블록은 생성자가 호출되는 시점에 호출된다.
////    val ageCheck = section3.lec09.section1.Person("최세량", 0)
//
//    // 주생성자 (primary constructor)
//    // ㄴ 반드시 존재해야 한다.
//    val overloadConstructor = section3.lec09.section1.Person("최세량")
//    println(overloadConstructor.name)
//    // ㄴ 단, 주생성자에 파라미터가 하나도 없다면 생략 가능!
//    val student = section3.lec09.Student()

    // 부생성자(secondary constructor)
    // ㄴ있을수도 있고, 없을수도 있고
    // ㄴ최종적으로 주생성자를 this로 호출해야 한다.
    // ㄴbody를 가질 수 있다.
    // ㄴ 부생성자를 추가로 만들 수 있으며, 부생성자는 최종적으로 주생성자를 호출하게 됨
    val secondConstructor = Person()
    println(secondConstructor) // 초기화 블록부터 차례로 호출된다 (본문은 역순으로 호출됨)
    // 다만, kotlin에서는 부생성자보다는 default parameter를 권장함!!!
    // converting과 같은 경구 부생성자를 사용할 수 있지만, 그보다는 정적 팩토리 메소들를 추천함


    // 3. 커스텀 getter, setter

    // custom getter
//    val customGetter = section3.lec09.section1.Person()
//    println(customGetter.isAdult)

    // custom setter
    val customSetter = School("애플", "과학")
    println(customSetter.computer)
    println(customSetter.science)

    // 4. backing field
    // name이 아닌 field를 사용 (name안에 getter 안의 name의 getter ... 무한루프) field는 무한루프를 막기 위한 예약어, 자기 자신을 가리킴
    // setter는 지양. custom setter도 잘 안씀

    // * 정리
    // 1. 코틀린에서는 필드를 만들면 getter 와 (필요에 따라) setter 가 자동으로 생긴다.
    // ㄴ 때문에 이를 프로퍼티라고 부른다.
    // 2. 코틀린에서는 주생성자가 필수이다
    // 3. 코틀린에서는 constructor 키워드를 사용해 부생성자를 추가로 만들 수 있다.
    // ㄴ 단, default parameter 나 정적 팩토리 메소드를 추천
    // 4. 실제 메모리에 존재한느 것과 무관하게 custom getter 와 custom setter 를 만들 수 있다.
    // 5. custom getter, custom setter 에서 무한루프를 막기 위해 field 라는 키웓르르 사용한다.
    // ㄴ 이를 backing field 라고 부른다.
}

class Student
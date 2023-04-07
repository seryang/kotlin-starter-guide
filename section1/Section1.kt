package section1

import Person

fun main() {

    // null 허용
    val intNumber: Int? = null

    // 타입 선언 & safe call (.?) & elvis 연산자 (?:)
    val longNumber: Long = intNumber?.toLong() ?: 0L

    // null이 절대 아님 단언 (!!)
    // 만약 null 이 넘어온다면? run time exception 발생
    val longNotNull: Long = intNumber!!.toLong()
    println(startWith(null))
    println(startWith("ABC"))
    printAgeIfPerson(Person(38))
    printAgeIfPerson(null)
//
//    val name = "최세량"
//    val str = """
//        ABC
//        EFG
//        HIF
//    """.trimIndent()
//    println(str)
//
//
//    val chartName = name[0]
//    println(chartName)

//        // 단항 연산자 ++ , --
//        val age1 = Plus(35)
//        val age2 = age1
//        val age3 = Plus(35)
//
//        println(age1 == age3)
//        println(age1 === age3)
//        println(age1.plus(age3))
//        println(age1 + age3)

    val a : String = "하이"
    val b = a
    val c : String = "하이"

    println(a == c)
    println(a === c)

}

fun printAgeIfPerson(obj: Any?): Unit {
    val per = obj as? Person
    println(per?.age) // 스마트캐스트

    if (obj is Person) { // java의 instanceof
        println(obj.age)
        val per = obj as Person
        println(per.age)
    }

    if (obj !is Person) {

        println(per?.age)
    }

    println("이름 : ${per?.age}")

    /* Any */
    // Java의 Object 역할 (모든 객체의 최상위 타입)
    // 모든 Primitive Type의 최상의 타입도 Any이다.
    // Any 자체로는 null을 포함할 수 없어 null을 포함하고 싶다면, Any?로 표현
    // Any에 equlas / hashCode / toString 존재

}

fun startWith(str: String?): Boolean? {
    return str?.startsWith("A")
}

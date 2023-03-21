package section3.lec09

// 프로퍼티 = 필드 + getter + setter
// 코틀린에서는 필드만 만들면 getter, setter를 자동으로 만들어줌
class Person(
    name: String, var age: Int
) {
    val name = name
        get() = field.uppercase() // name이 아닌 field를 사용 (name안에 getter 안의 name의 getter ... 무한루프) field는 무한루프를 막기 위한 예약어, 자기 자신을 가리킴

    // init (초기화) 블록은 생성자가 호출되는 시점에 호출된다.
    init {
        if (this.age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다")
        }
        println("초기화 블록")
    }

    // constructor(파라미터)로 생성자를 추가!
    constructor(name: String) : this(name, 1) {
        println("첫번째 부생성자")
    }

    constructor() : this("최세량") {
        println("두번째 부생성자")
    }

    override fun toString(): String {
        return "section3.lec09.section1.Person(name='$name', age=$age)"
    }

    // 함수로 만드는 법
//    fun isAdult(): Boolean {
//        return this.age >= 20
//    }

    // 프로퍼티로 만드는 법 (custom getter)
    val isAdult: Boolean
        get() {
            return this.age >= 20
        }

    // get() = this.age >= 20 // 위에 구문이랑 같은 문법

    // 모두 동일한 방법인데, 객체의 속성이라면 custom getter 그렇지 않으면 함수로 선언
}


class School(computer : String, science : String) {
    var computer: String = computer
        set(value) {
            field = value.uppercase()
        }
    var science: String = science
}
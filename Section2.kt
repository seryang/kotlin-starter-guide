fun main() {

    /* Lec 05. 코틀린에서 조건문을 다루는 방법 */
    // 1. if문
    validateScoreIsNotNegative(-2)

    // 2. Expression과 Statement
//     Expression : 하나의 값으로 도출되는 문장
//     Statement : 프로그램의 문장, 하나의 값으로 도출되지 않는다
//     Expression은 Statement에 포함되어 있음
//     kotlin에서의 if문은 Expression이다. Java의 if문은 Statement다. 단, Java의 3항 연산자는 Expression이다.
//     Kotlin은 if-else를 expression으로 사용할 수 있기 때문에 3항 연산자가 없다.
    println(getPassOrFail(89))

    // 3. switch와 when
    // kotlin에서는 switch대신 when을 사용
    // case 대신에 바로 그 경우를 작성해준 다음에 화살표로 분기
    // default 대신 else를 사용
    // when(값) {
    //     조건부 -> 어떠한 구문
    //     조건부 -> 어떠한 구문
    //     else -> 어떠한 구문
    // }
    println(getGradeWithSwitch(24))

    // 조건부에는 어떠한 expression을 사용해도 됨 (is 도 가능 = is는 자바의 instanceof)
    println(getStartsWithA("ABC"))

    // 조건부에 or 조건은 다음과 같이 사용
    judgeNumber(2)
    judgeNumber2(2)

    // when은 Enum Class 혹은 Sealed Class와 함께 사용할 경우, 더욱더 진가를 발휘한다


    // *정리
    // if / if-else / if -else if - else 모두 Java와 문법이 동일하다
    // 단 kotlin에서는 Expression으로 취급된다 (삼항 연산자가 없다)
    // java의 switch는 kotlin에서 when으로 대체되었고, when은 더 강력한 기능을 갖는다
}

fun judgeNumber2(number: Int) {
    // when에 값 자체가 없는 케이스
    when {
        number == 0 -> println("주어진 숫자는 0입니다")
        number % 2 == 0 -> println("주어진 숫자는 짝수입니다.")
        else -> println("주어진 숫자는 홀수입니다.")
    }

}

fun judgeNumber(number: Int) {
    when (number) {
        1, 0, -1 -> println("어디서 많이 본 숫자입니다") // java 코드로 보자면 (number == 1 || number == 0 || number == -1)
        else -> println("1, 0, -1이 아닙니다")
    }
}

fun getStartsWithA(obj: Any): Boolean {
    return when (obj) {
        is String -> obj.startsWith("A")
        else -> false
    }
}

fun getGradeWithSwitch(score: Int): String {
//    return when (score/10) {
//        9 -> "A"
//        8 -> "B"
//        7 -> "C"
//        6 -> "D"
//        else -> "E"
//    }

    return when (score) {
        in 90..100 -> "A" // 90~100
        in 80..89 -> "B" // 80~89
        in 70..79 -> "C" // 70~79
        else -> "D"
    }
}


fun validateScoreIsNotNegative(score: Int) {
    if (score < 0) {
        // kotlin에서는 new를 사용하지 않고 예외를 throw
        throw IllegalArgumentException("${score}는 0보다 작을 수 없습니다.")
    }
}

fun getPassOrFail(score: Int): String {
    if (score >= 50) {
        return "P"
    } else {
        return "F"
    }

    return if (score >= 50) {
        "P"
    } else {
        "F"
    }

    if (score !in 0..100) {
        throw java.lang.IllegalArgumentException("score의 범위는 0부터 100입니다")
    }

    if (score in 0..100) {
        return "당신의 스코어는 ${score}입니다."
    }

    return "Empty"
}

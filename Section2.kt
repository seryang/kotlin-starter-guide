import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main() {

    /* Lec 05. 코틀린에서 조건문을 다루는 방법 */
    /**********************************/
    // 1. if문
    validateScoreIsNotNegative(-2)

    // 2. Expression과 Statement
    // Expression : 하나의 값으로 도출되는 문장
    // Statement : 프로그램의 문장, 하나의 값으로 도출되지 않는다
    // Expression은 Statement에 포함되어 있음
    // kotlin에서의 if문은 Expression이다. Java의 if문은 Statement다. 단, Java의 3항 연산자는 Expression이다.
    // Kotlin은 if-else를 expression으로 사용할 수 있기 때문에 3항 연산자가 없다.
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


    /* Lec 06. 코틀린에서 반복문을 다루는 방법 */
    /**********************************/
    // 1. for-each문 (자바와 동일)
    getForEach()

    // 2. 전통적인 for문
    getFor()
    // 3. Progression과 Range
    // .. 연산자 : 범위를 만들어 내는 연산자
    // 1..3 : 1부터 3의 범위
    // downTo, step도 함수이다! (중위 호출 함수)
    // 변수.함수이름(argument)대신 -> 변수 함수이름 argument
    // 코틀린에서 전통적인 for문은 등차수열을 이용한다!

    // 4. while문
    getWhile() // java와 동일

    /* 정리 */
    // for each문에서 java는 : 을 사용, kotlin은 in을 사용
    // 전통적인 for문에서 kotlin은 등차수열과 in을 사용
    // 그 외 for문 문법은 모두 동일
    // while문과 do while문은 더욱더 놀랍도록 동일


    /* Lec 07. 코틀린에서 예외를 다루는 방법   */
    /**********************************/
    // 1. try catch finally 구문
    println(parseIntOrThrow("34"))
    println(parseIntOrThrow("A"))
    println(parseIntOrThrow2("A"))// null을 반환

    // 2. Checked Exception과 Unchecked Exception
    getFilePrinter() // java에서는 checked exception이 발생하지만 kotlin은 없음..?
    // 이유는 kotlin에서는 Checked Exception과 Unchecked Exception을 구분하지 않음. 모두 Unchecked Exception임

    // 3. try with resources (jdk7에 추가)
    tryWithResources("~/a.txt")

    /* 정리 */
    // try catch finally 구문은 문법적으로 java와 완전히 동일
    // ㄴ kotlin에서는 try catch가 expression이다. return 시작 가능
    // kotlin에서 모든 예외는 uncehcked exception
    // kotlin에서 try with resoucres 구문이 없음. 대신 코틀린의 언어적 특징을 활용해 close를 호출해줌

}

fun tryWithResources(path: String) {
    // kotlin 은 try with resources 구문이 없음 -> try () {}
    // kotlin은 use라는 inline 함수를 사용
    BufferedReader(FileReader(path)).use { reader ->
        println(reader.readLine())
    }
}

fun getFilePrinter() {
    val currentFile = File(".")
    val file = File(currentFile.absolutePath + "/a.txt")
    val reader = BufferedReader(FileReader(file))
    println(reader.readLine())
    reader.close()
}

fun parseIntOrThrow(str: String): Int {
    try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        throw java.lang.IllegalArgumentException("주어진 ${str}는 숫자가 아닙니다.", e)
    }
}

fun parseIntOrThrow2(str: String): Int? {
    return try { // 하나의 expression으로 처리되기 때문에 return try {} 구문이 됨
        str.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}

fun getWhile() {
    var i = 1
    while (i <= 3) {
        println(i)
        i++
    }
    // do-while도 동일
}

fun getFor() {
    for (i in 1..3) { // 시작값 1, 끝값 3, 공차가 +1인 등차수열
        println(i)
    }
    for (i in 3 downTo 1) { // 시작값 3, 끝값 1, 공차가 -1인 등차수열
        println(i)
    }

    for (i in 1..5 step 2) { // 시작값 1, 끝값 5, 공차가 +2인 등차수열
        println(i)
    }
}

fun getForEach() {
    val numbers = listOf(1L, 2L, 3L) // listOf 컬렉션
    for (number in numbers) { // 콜론 대신 in을 사용
        println(number)
    }
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

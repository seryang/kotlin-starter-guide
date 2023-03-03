package section4.lec16

fun main() {
    // Lec16. 코틀린에서 다양한 함수를 다루는 방법
    // 1. 확장 함수
    // 배경 : 어떤 클래스안에 있는 메소드처럼 호출할 수 잇지만, 함수는 밖에 만들 수 있게 하자!
    // fun 확장하려는클래스.함수이름(팔마ㅣ터): 리턴타입 { // 확장하려는 클래스 -> 수신객체 타입
    //     // this 를 이용해 실제 클래스 안의 값에 접근 // this -> 수신 객체
    // }
    val str = "ABC"
    println(str.lastChar())
    // 원래 String 에 있는 멤버 함수처럼 사용할 수 있음
    // 확장함수는 원본 클래스에 있는 private 또는 protected 멤버를 가져올 수 없음!
    // 확장함수와 멤버함수의 시그니처가 동일한다면 멤버함수가 우선적으로 호출된다 (멤버함수에 우선권이 있음)
    // 확장함수는 현재 타입을 기준으로 호출된다
    // 확장함수 라는 개념은 확장프로퍼티와도 연결된다.

    // 2. infix 함수
    // 중위함수, 함수를 호출하는 새로운 방법!
    // downTo, step 도 함수이다! (중위 호출 함수)
    // 변수.함수이름(arg)대신 -> 변수 함수이름 arg
    println(3.add(4))
    println(3.add2(4))
    println(3 add2 4)
    // infix 는 멤버함수에도 붙일 수 있음

    // 3. inline 함수
    // 함수가 호출되는 대신, 함수를 호출한 지점에 함수 본문을 그대로 복붙하고 싶은 경우!
    // 함수를 파라미터로 전달할 떄에 오버헤드를 줄일 수 있음
    // 하지만 inline 함수의 사용은 성능 측정과 함께 신중하게 사용되어야 함
    println(3.add3(4))

    // 4. 지역 함수
    // 함수 안에 함수를 선언할 수 있음
    // 함수로 추출하면 좋을 것 같은데, 이 함수를 지금 함수 내에서만 사용하고 싶을때 사용
    // 다만, depth가 깊어지기도 하고, 코드가 깔끔하지 않음

    // * 정리
    // 1. java 코드가 있는 상황에서, kotlin 코드로 추가 기능 개발을 하기 위해 확장함수와 확장프로퍼티가 등장함
    // fun 확장하려는클래스.함수이름(파라미터): 리턴타입 {
    //    // this 를 이용해 실제 크래스 안의 값에 접근
    // }
    // 2. 확장함수는 원본클래스의 protected 나 private 멤버 접근이 안된다
    // 3. 멤버함수, 확장함수 중 멤버함수에 우선권이 있음
    // 4. 확장함수는 현재 타입을 기준으로 호출됨
    // 5. java 에서는 static 함수를 쓰는 것처럼 kotlin 의 확장함수를 쓸 수 있음 (코틀린파일명kt.함수명())
    // 6. 함수 호출 방식을 바꿔주는 infix 함수가 존재
    // 7. 함수를 복사-붙여넣기 하는 inline 함수가 존재
    // 8. kotlin 에서는 함수 안에 함수를 선언할 수 있고, 이를 지역함수라 부름
}

fun String.lastChar(): Char { // String. -> String 클래스를 확장
    return this[this.length - 1] // 함수 안에서는 this 를 통해 인스턴스에 접근 가능하다!
}

fun Int.add(other: Int): Int {
    return this + other
}

infix fun Int.add2(other: Int): Int {
    return this + other
}

inline fun Int.add3(other: Int): Int {
    return this + other
}
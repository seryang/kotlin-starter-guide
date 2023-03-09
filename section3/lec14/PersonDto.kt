package section3.lec14

data class PersonDto( // data 키워드는 equals + hashCode+ toString을 기본적으로 만들어 줌
    val name: String,
    val age: Int
)
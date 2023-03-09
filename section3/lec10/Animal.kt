package section3.lec10

abstract class Animal(
    val species: String,
    open val legCount: Int // (추상 프로퍼티가 아니라면) 상속받을때 (=프로퍼티를 override 할때) 무조건 open 을 붙여 줘야만 함
) {
    abstract fun move()

    override fun toString(): String {
        return "Animal(species='$species', legCount=$legCount)"
    }

}
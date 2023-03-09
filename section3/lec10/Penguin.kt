package section3.lec10

class Penguin(
    species: String
) : Animal(species, 2), Swimable, Flyable { // 인터페이스도 :을 사용. 추가는 , 로

    private val wingCount: Int = 2

    override fun move() {
        println("펭귄이 움직입니다~ 꿱꿱")
    }

    override val legCount: Int // 프로퍼티를 override 할때 무조건 open 을 붙여 줘야만 함
        get() = super.legCount + this.wingCount

    override fun act() {
        super<Swimable>.act() // 중복되는 인터페이스를 특정할때 super<타입>.함수 사용
        super<Flyable>.act()
    }

    override fun fly() {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "Penguin(wingCount=$wingCount, legCount=$legCount, species=$species)"
    }
}
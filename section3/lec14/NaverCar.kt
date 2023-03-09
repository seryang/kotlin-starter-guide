package section3.lec14

sealed class NaverCar(
    val name:String,
    val price:Long
) {
    class Avante : NaverCar("아반떼", 1_000L)
    class Sonata : NaverCar("소나타", 2_000L)
    class Grandeur : NaverCar("그랜져", 3_000L)
}
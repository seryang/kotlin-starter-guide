package section3.lec10

class Cat(
    species: String, legCount: Int,
) : Animal(species, 4) { // 상속은 extends 가 아닌 : 을 붙여준다. :을 붙여쓰면 타입 선언, 한칸 띄고 : 를 쓰면 상속
    // 코틀린에서는 어떤 클래스를 상속 받을때 무조건 상위 클래스의 생성자를 바로 호출해야함
    override fun move() { // override 를 필수적으로 붙여줘야 함
        println("고양이가 사뿐 사뿐 걸어가~")
    }
}

data class Plus(
    val age: Int
) {
    operator fun plus(o: Plus): Plus {
        return Plus(this.age + o.age)
    }
}
package section4.lec17

public data class Fruit(
    val id: Long,
    val name: String,
    val factoryPrice: Long,
    val currentPrice: Long
) {
    fun nullOrValue(): String? {
        if (this.name == "바나나") {
            return this.name
        }
        return null
    }

    override fun toString(): String {
        return "Fruit(id=$id, name='$name', factoryPrice=$factoryPrice, currentPrice=$currentPrice)"
    }

    val isSamePrice: Boolean
        get() = factoryPrice == currentPrice
}

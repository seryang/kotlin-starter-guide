package section3.lec11

class Car(
   internal val name: String,
   private var owner: String,
   _price: Int
) {
    public var price = _price
        private set
}
package section3.lec14

fun handleCountry(country: Country): String {
    return when (country) {
        Country.AMERICA -> "미국"
        Country.KOREA -> "한국"
    }
}

enum class Country(
    private val code: String
) {
    KOREA("KO"),
    AMERICA("US")
    ;
}
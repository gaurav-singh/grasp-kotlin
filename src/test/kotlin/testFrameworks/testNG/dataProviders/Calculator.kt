package testFrameworks.testNG.dataProviders

class Calculator(
    private val first: Int,
    private val second: Int
) {
    fun add(): Int {
        return first + second
    }

    fun sub(): Int {
        return first - second
    }
}


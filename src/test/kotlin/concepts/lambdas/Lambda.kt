package concepts.lambdas

import org.testng.annotations.Test

/**
 * Lambda expressions and anonymous functions
 * https://kotlinlang.org/docs/reference/lambdas.html#lambda-expressions-and-anonymous-functions
 */


class LambdaTest {
    @Test
    fun showHowToCreateLambdaExpression() {
        fun isGreaterThan(first: Int, second: Int): Boolean {
            return first > second
        }

        /**
         * Lambda syntax
         * 1. Start with enclosed braces
         * 2. Params can be specified separated by commas
         * 3. Actual statements can be specified to the RHS of ->
         * 4. Last value of the lambda would be the return value
         */
        val isGreaterThanLambda: (Int, Int) -> Boolean = { first, second -> first > second }

        // Optionally we can remove the type annotations
        val isGreaterThanLambda2 = { first: Int, second: Int -> first > second }

        println(isGreaterThan(2, 5))
        println(isGreaterThanLambda(2, 5))
        println(isGreaterThanLambda2(2, 5))
    }

    @Test(description = "show how if last param is a lambda then it could be moved outside the params")
    fun showUseOfTrailingLambda() {
        val people = listOf("X", "Y", "Z", "ABC", "DBE")

        /**
         * Here filter accepts a lambda as the last argument and hence it could be written outside
         * Also we can omit the round brackets for filter altogether
         * Also if a lambda has a single param, then we can omit -> and refer to it via **it** inside the lambda
         */

        val filtered = people.filter { it.length > 2 }
        println(filtered)
    }

    @Test(description = "Using qualified return (return@filter) we can return early from a lambda if needed")
    fun showHowToReturnUsingQualifiedReturnInLambdas() {
        val numbers = (1..100)

        // Using return@filter we can return from the lambda early
        println(numbers.filter {
            val shouldReturn = it > 50
            return@filter shouldReturn
        })

        // If no return is specified, last expressions value is assumed as the default return value
        println(numbers.filter {
            val shouldReturn = it < 40
            shouldReturn
        })
    }

    @Test
    fun unusedLambdaParamsCanBeReplacedWithUnderscores() {
        val map = mapOf("Germany" to "Berlin", "India" to "Delhi")
        map.forEach { (_, value) -> println(value) }
    }

    @Test
    fun destructuringInLambdas() {
        // If lambda has a param of Pair or Map.Entry type then we can use destructuring declaration syntax
        // https://kotlinlang.org/docs/reference/multi-declarations.html#destructuring-in-lambdas-since-11
        val map = mapOf("Germany" to "Berlin", "India" to "Delhi")
        map.forEach { (key, value) -> println("$key: $value}") }
    }

    @Test
    fun anonymousFunctionSyntax() {
        // return statement inside anonymous function would return from this
        // whereas return statement inside lambda expression would return from the enclosing function
        val numbers = (1..1000)
        println(numbers.filter(fun(num) = num > 990))
    }

    @Test
    fun functionLiteralsWithReceiver() {
        // https://kotlinlang.org/docs/reference/lambdas.html#function-literals-with-receiver
        val sum: Int.(Int) -> Int = { other -> plus(other) }
        val anotherSum = fun Int.(other: Int): Int = this + other
    }


}
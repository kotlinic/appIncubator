package test

fun printSum(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

fun main() {
    printSum(-1, 8)

    println("sum of 19 and 23 is ${sum(19, 23)}")
}

fun sum(a: Int, b: Int) = a + b

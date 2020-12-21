package test
//在 Kotlin 中，if 也可以用作表达式：
fun maxOf(a: Int, b: Int) = if (a > b) a else b

fun main() {
    println("max of 0 and 42 is ${maxOf(0, 42)}")
    System.out.println(System.getProperty("sun.boot.class.path"));
}
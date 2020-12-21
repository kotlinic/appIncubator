package test;

class Turtle {
    fun penDown() {
        print("haha")
    }

    fun penUp() {}
    fun turn(degrees: Double) {}
    fun forward(pixels: Double) {}
    fun main() {

    }

    fun Builder() {
        println("OK JUnit")
    }
}

open class DeclarationProcessor { /*……*/ }

object EmptyDeclarationProcessor : DeclarationProcessor() { /*……*/ }

fun main() {
    val myTurtle = Turtle()
    with(myTurtle) { // 画一个 100 像素的正方形
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
            Builder()
        }
        penUp()

    }
}
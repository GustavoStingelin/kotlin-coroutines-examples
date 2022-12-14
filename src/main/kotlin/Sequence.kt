import kotlinx.coroutines.runBlocking
import java.math.BigInteger

fun fibonacciSeq() = sequence {
    var x = BigInteger.ZERO
    var y = BigInteger.ONE
    while (true) {
        yield(x)
        Thread.sleep(200)
        //delay(100)
        x = y.also {
            y += x
        }
    }
}

fun main() {
    runBlocking {
        fibonacciSeq().take(10).forEach { println(it) }
    }
}

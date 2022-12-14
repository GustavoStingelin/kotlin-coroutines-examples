import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import java.math.BigInteger

fun fibonacci(): Flow<BigInteger> = flow {
    var x = BigInteger.ZERO
    var y = BigInteger.ONE
    while (true) {
        emit(x)
        delay(100)
        x = y.also {
            y += x
        }
    }
}

fun main() {
    runBlocking {
        fibonacci().take(10).collect { println(it) }
    }
}

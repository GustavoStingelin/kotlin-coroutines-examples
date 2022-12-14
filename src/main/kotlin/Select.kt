import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun data1() = CoroutineScope(Dispatchers.Default).async {
    delay(1000)
    "Hello"
}

fun data2() = CoroutineScope(Dispatchers.Default).async {
    delay(2000)
    "World"
}

fun main() {
    runBlocking {
        val winner = select {
            data1().onAwait { it }
            data2().onAwait { it }
        }
        println("The winner = $winner")
    }
}

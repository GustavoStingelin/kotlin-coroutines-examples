import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

fun asyncClient(a: Int) = CoroutineScope(Dispatchers.IO).async {
    syncClient(a)
}

fun syncClient(a: Int): Int {
    Thread.sleep(1000)
    return a
}

@OptIn(ExperimentalTime::class)
fun main() {
    val time = measureTime {
        val a = asyncClient(10)
        val b = asyncClient(15)
        val c = asyncClient(20)

        runBlocking {
            println("a: ${a.await()}, b: ${b.await()}, c: ${c.await()}")
        }
    }

    println(time)
}


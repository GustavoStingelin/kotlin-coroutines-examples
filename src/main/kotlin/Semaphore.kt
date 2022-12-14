import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Semaphore

suspend fun <T, R> Iterable<T>.map(
    concurrency: Int,
    transform: suspend (T) -> R
): List<R> = coroutineScope {
    val semaphore = Semaphore(concurrency)
    map { item ->
        semaphore.acquire()
        async {
            try {
                transform(item)
            } finally {
                semaphore.release()
            }
        }
    }.awaitAll()
}

fun main() = runBlocking {
    val items = (1..100).toList()
    val result = items.map(10) { item ->
        println("Processing $item")
        delay(1000)
        item * 2
    }
    println(result)
}

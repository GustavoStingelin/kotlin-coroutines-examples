import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() {
    runBlocking {
        val pool = newSingleThreadContext("Single Thread")

        launch(pool) {
            while (true) {
                Thread.sleep(1000)
                delay(10)
                println("Hello")
            }
        }

        launch(pool) {
            while (true) {
                Thread.sleep(1000)
                println("World")
                yield()
            }
        }
    }
}

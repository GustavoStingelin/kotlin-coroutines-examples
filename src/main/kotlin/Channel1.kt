import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun say(s: String) {
    for (i in 0..4) {
        delay(100)
        logger.info(s)
    }
}

fun sayBlocking(s: String) = runBlocking {
    say(s)
}

fun main() {
    go { say("hello") }
    Thread.sleep(50)
    sayBlocking("world")
}

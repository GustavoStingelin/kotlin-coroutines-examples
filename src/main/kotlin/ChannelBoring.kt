import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.Random

suspend fun boring(msg: String): ReceiveChannel<String> {
    val c = Channel<String>()
    val rnd = Random()
    go {
        var i = 0
        while (true) {
            c.send("$msg $i")
            delay(rnd.nextInt(1000).toLong())
            i++
        }
    }
    return c
}

fun main() = runBlocking {
    val joe = boring("Joe")
    val ann = boring("Ann")
    for (i in 0..4) {
        println(joe.receive())
        println(ann.receive())
    }
    println("Your're both boring; I'm leaving.")
}

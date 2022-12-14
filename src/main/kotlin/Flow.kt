import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

fun myFlow(): Flow<Int> = flow {
    var counter = 1

    while (counter < 6) {
        emit(counter)
        counter++
        delay(300)
    }
}

fun main() {
    runBlocking {
        //myFlow().collect { println(it) }
        myFlow().take(3).map {
            delay(100)
            println("map: $it")
            it * 2
        }.collect() { println(it) }
    }
}

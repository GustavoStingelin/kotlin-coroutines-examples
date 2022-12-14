import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

val io = Dispatchers.IO //offload blocking I/O to a shared pool of threads
val default = Dispatchers.Default //CPU heavy
val unconfined = Dispatchers.Unconfined //to avoid stack overflows

val mySqlPool = Dispatchers.IO.limitedParallelism(10)
val hashMaker = Dispatchers.Default.limitedParallelism(100)

val backgroundDispatcher = newFixedThreadPoolContext(4, "App Background")
val imageProcessingDispatcher = backgroundDispatcher.limitedParallelism(2)
val jsonProcessingDispatcher = backgroundDispatcher.limitedParallelism(3)
val fileWriterDispatcher = backgroundDispatcher.limitedParallelism(1)


fun main() = runBlocking {

    val contexts = listOf(
        io,
        default,
        unconfined,
        mySqlPool,
        hashMaker,
        backgroundDispatcher,
        imageProcessingDispatcher,
        jsonProcessingDispatcher,
        fileWriterDispatcher,
        backgroundDispatcher
    )

    contexts.forEach {
        withContext(it) {
            logger.info("Running on $it")
        }
    }
}

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousFileChannel
import java.nio.channels.CompletionHandler
import java.nio.file.Paths
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


fun main() {

    runBlocking {
        readAsync()
    }

    val job = CoroutineScope(Dispatchers.IO).launch {
        readAsync()
    }

    val deferred = CoroutineScope(Dispatchers.IO).async {
        readAsync()
    }

    runBlocking {
        println(job.join())
        println(job)
        println(deferred.await())
    }
}

suspend fun readAsync(): Int {
    val fileName = "src/main/kotlin/Suspendable.kt"
    logger.info("Asynchronously loading file \"$fileName\" ...")

    val channel = AsynchronousFileChannel.open(Paths.get(fileName))
    val buf = ByteBuffer.allocate(4096)

    channel.use {
        val bytesRead = it.aRead(buf)
        logger.info("Read $bytesRead bytes starting with \"${String(buf.array().copyOf(10))}\"")
    }
    return 10
}

suspend fun AsynchronousFileChannel.aRead(buf: ByteBuffer): Int = suspendCoroutine { cont ->
    read(buf, 0L, Unit, object : CompletionHandler<Int, Unit> {
        override fun completed(bytesRead: Int, attachment: Unit) {
            cont.resume(bytesRead)
        }

        override fun failed(exception: Throwable, attachment: Unit) {
            cont.resumeWithException(exception)
        }
    })
}

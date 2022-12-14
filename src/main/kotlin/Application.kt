import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import plugins.configureRouting

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::myApplicationModule).start(wait = true)
}

fun Application.myApplicationModule() {
    configureRouting()
}

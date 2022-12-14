package plugins

import asyncClient
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    // Starting point for a Ktor app:
    routing {
        get("/") {
            val a = asyncClient(10)
            val b = asyncClient(15)
            val c = asyncClient(20)
            call.respond("a: ${a.await()}, b: ${b.await()}, c: ${c.await()}")
        }
    }
}

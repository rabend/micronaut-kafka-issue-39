package mn.test

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("/greet")
interface GreetingClient {

    @Get("/{name}")
    fun getGreeting(name: String): String
}

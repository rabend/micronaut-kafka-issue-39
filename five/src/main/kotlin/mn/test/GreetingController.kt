package mn.test

import io.micrometer.prometheus.PrometheusMeterRegistry
import io.micronaut.http.MediaType.TEXT_PLAIN
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import javax.inject.Inject

@Secured
@Controller("/greet")
class GreetingController @Inject constructor(private val meterRegistry: PrometheusMeterRegistry){

    @Get("/{name}", produces = [TEXT_PLAIN])
    fun greetName(name: String): String {
        meterRegistry.counter("greetings", "index", "greet", "get", "name").increment()
        return String.format("Hello %s", name)
    }
}

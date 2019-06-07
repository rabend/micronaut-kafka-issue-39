package mn.test

import io.kotlintest.Spec
import io.kotlintest.TestCase
import io.kotlintest.TestResult
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken


class GreetingControllerSpec : StringSpec() {
    private val server = ApplicationContext.run(EmbeddedServer::class.java)
    private val client = server.applicationContext.createBean(RxHttpClient::class.java, server.url)

    override fun afterSpec(spec: Spec) {
        server.stop()
    }

    init {
//        "it should work" {
//            val result: HttpResponse<String>?
//            try {
//                result = client.toBlocking().exchange(HttpRequest.GET<String>("/greet/Ilya"))
//            } catch (e: Exception) {
//                e.message shouldBe "Unauthorized"
//            }
//        }

        "an unauthorized client should not be able to access" {
            val credentials = UsernamePasswordCredentials("Foo", "Bar")
            val req = HttpRequest.POST("/login", credentials)
            val authResp = client.toBlocking().exchange(req, BearerAccessRefreshToken::class.java)

            val request = HttpRequest.GET<String>("/greet/Test").header(HttpHeaders.AUTHORIZATION, "Bearer ${authResp.body()!!.accessToken}")
            request shouldBe "Hello Test"
        }
    }
}

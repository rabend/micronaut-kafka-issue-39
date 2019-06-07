package sync.commit

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("sync.commit")
                .mainClass(Application.javaClass)
                .start()
    }
}
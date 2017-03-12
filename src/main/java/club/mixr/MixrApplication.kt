package club.mixr

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MixrApplication

fun main(args: Array<String>) {
    SpringApplication.run(MixrApplication::class.java, *args)
}

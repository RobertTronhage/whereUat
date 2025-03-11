package se.tronhage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class WhereUatApplication

fun main(args: Array<String>) {
    runApplication<WhereUatApplication>(*args)
}

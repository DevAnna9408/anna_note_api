package kr.co.anna.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling // Spring Scheduler 사용 어노테이션
@ConfigurationPropertiesScan
@SpringBootApplication(
    scanBasePackages = ["kr.co.anna.domain", "kr.co.anna.lib", "kr.co.anna.api"]
)
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}

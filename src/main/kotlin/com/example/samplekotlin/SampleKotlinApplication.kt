package com.example.samplekotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping

@SpringBootApplication
class SampleKotlinApplication

fun main(args: Array<String>) {
    runApplication<SampleKotlinApplication>(*args)
}

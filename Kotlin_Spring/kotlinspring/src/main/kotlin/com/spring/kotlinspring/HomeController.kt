package com.spring.kotlinspring

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
class HomeController {

    @GetMapping("/api/hello")
    fun helloWorld(): String {
        return "Hello spring!"
    }
}
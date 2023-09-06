package com.spring.kotlinspring.config

import org.springframework.http.HttpMethod
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class MvcConfig: WebMvcConfigurer {
    override
    fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods(HttpMethod.GET.toString(),
                        HttpMethod.POST.toString(),
                        HttpMethod.PUT.toString(),
                        HttpMethod.PATCH.toString(),
                        HttpMethod.DELETE.toString()
                )
    }
}
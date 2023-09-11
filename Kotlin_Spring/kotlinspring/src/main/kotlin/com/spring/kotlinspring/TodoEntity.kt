package com.spring.kotlinspring

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class TodoEntity (
    var createdBy: String,
    var todo: String,
    @Id @GeneratedValue var id: Int? = null,
)
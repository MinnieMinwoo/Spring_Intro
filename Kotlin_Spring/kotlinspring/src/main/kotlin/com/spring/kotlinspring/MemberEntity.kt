package com.spring.kotlinspring

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class MemberEntity(
    var email: String,
    var password: String,
    @Id @GeneratedValue var id: Int? = null
)


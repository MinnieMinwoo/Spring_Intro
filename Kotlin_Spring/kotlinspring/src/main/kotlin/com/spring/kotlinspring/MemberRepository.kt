package com.spring.kotlinspring

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Repository

@Repository
class MemberRepository {
    private var userMap = HashMap<String, MemberEntity>();
    fun SignUp(email:String, password: String) {
        if(userMap.get(email) !== null) {
            throw Exception("유저 중복")
        }
        userMap.set(email, MemberEntity(email, password))
    }

    fun SignIn(email: String, password: String): MemberEntity {
        var user = userMap.get(email)
        if(user === null) {
            throw Exception("유저 없음")
        } else if (user.password != password) {
            throw Exception("비밀번호 오류")
        } else {
            return user
        }
    }
}
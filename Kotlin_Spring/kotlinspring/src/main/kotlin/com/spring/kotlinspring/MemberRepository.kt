package com.spring.kotlinspring

class MemberRepository(private var userMap: HashMap<String, MemberEntity>) {
    private var sequence = 0;
    fun SignIn(email:String, password: String) {
        if(userMap.get(email) !== null) {
            throw Exception("유저 중복")
        }
        userMap.set(email, MemberEntity(email, password))
        println(userMap.get(email))
    }
}
package com.spring.kotlinspring

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.web.bind.annotation.*

@RestController
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
class HomeController {

    private var MemberRepository = MemberRepository();
    private var TodoRepository = TodoRepository();

    @PostMapping("/api/auth/signup")
    fun SignUp(@RequestBody Member: MemberEntity): MemberEntity {
        MemberRepository.SignUp(Member.email, Member.password)
        return Member
    }

    @PostMapping("/api/auth/signin")
    @ResponseBody
    fun SignIn(@RequestBody Member: MemberEntity): MemberEntity {
        return MemberRepository.SignIn(Member.email, Member.password)
    }

    @PostMapping("/api/todo")
    @ResponseBody
    fun UploadTodo(@RequestBody Todo: TodoEntity): TodoEntity {
        val (email, todo) = Todo
        return TodoRepository.addTodo(email, todo)
    }
}
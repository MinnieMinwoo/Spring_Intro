package com.spring.kotlinspring

class TodoRepository {
    private var todoMap = HashMap<Number, TodoEntity>()
    private var idNum = 1

    fun addTodo(email: String, todo: String): TodoEntity {
        var newId = idNum++
        var newTodo = TodoEntity(email, todo, newId)
        todoMap[newId] = newTodo
        return newTodo
    }

    fun getTodoList(email: String): Array<TodoEntity> {
        return todoMap.filter { (_, value) -> value.createdBy === email }
                      .values
                      .toTypedArray()
    }
}
package com.kongsub.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.kongsub.todolist.data.Todo
import com.kongsub.todolist.data.TodoDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

// AndroidViewModel 은 액티비티와 수명이 같다.
class MainViewModel(application: Application) : AndroidViewModel(application) {
    // Room 데이터베이스
    private val db = Room.databaseBuilder(
        application,
        TodoDatabase::class.java, "todo"
    ).build()

    var selectedTodo: Todo? = null

    // DB의 결과를 관찰할 수 있도록 하는 방법
    // StateFlow: 현재 상태와 새로운 상태 업데이트를 관찰하는 곳에 보내는 데이터 흐름을 표현함. (상태 = 데이타)
    // -> 주로 UI에 상태(데이터)를 표현할때 사용함.
    // value 프로퍼티를 통해 현재 상태 값을 읽어들임.
    // MutableStateFlow : 상태를 업데이트하고 관찰하는 곳으로 상태를 전달하기 위해 MutableStateFlow 클래스의 value 프로퍼티에 새 값을 할당
    private val _items = MutableStateFlow<List<Todo>>(emptyList())
    val items: StateFlow<List<Todo>> = _items

    // 초기화시 모든 데이터를 읽어 들임.
    init {
        // viewModel 과 AndroidViewModel 클래스는 viewModelScope 코루틴 스코프를 제공
        // launch 함수 내에서 suspend 메서드를 실행할 수 있고 이는 비동기로 동작함.
        viewModelScope.launch {
            // Flow 객체는 collect 로 현재 값을 가져올 수 있음.
            db.todoDao().getAll().collect { todos ->
                // StateFlow 객체는 value 프로퍼티로 현재 상태값을 읽거나 쓸 수 있음.
                // 현재 상태를 _items.value에 할당하여 최신 정보로 교체
                _items.value = todos
            }
        }
    }

    // 할 일 추가
    fun addTodo(text: String, date: Long) {
        viewModelScope.launch {
            db.todoDao().insert(Todo(text, date))
        }
    }

    // 할 일 수정
    fun updateTodo(text: String, date: Long) {
        selectedTodo?.let { todo ->
            todo.apply {
                this.title = text
                this.date = date
            }
            viewModelScope.launch {
                db.todoDao().update(todo)
            }
            selectedTodo = null
        }
    }

    // 할 일 삭제 메서드
    fun deleteTodo(id: Long) {
        _items.value
            .find { todo -> todo.id == id }
            ?.let { todo ->
                viewModelScope.launch {
                    db.todoDao().delete(todo)
                }
                selectedTodo = null
            }
    }

}
package com.kongsub.todolist.adapter

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kongsub.todolist.data.Todo
import com.kongsub.todolist.databinding.ItemTodoBinding

// 생성자로 아이템이 클릭된 경우, 처리할 함수를 인자로 받음.
class TodoListAdapter (
    private val onClick: (Todo) -> Unit,
    ) : ListAdapter<Todo, TodoListAdapter.TodoViewHolder>(TodoDiffUtilCallback()) { // 아이템 클래스 및 뷰홀더 클래스 타입을 제네릭으로 지정

    // 바인딩 객체를 저장할 변수
    private lateinit var binding: ItemTodoBinding

    // 뷰 홀더를 생성하는 로직 작성, 바인딩 객체를 얻고 뷰 홀더를 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        // 뷰 홀더 생성, (바인딩 객체, 클릭 시 수행할 함수)
        return TodoViewHolder(binding, onClick)
    }

    // 화면에 각 아이템이 보여질 때마다 호출됨.
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.setOnClickListener(getItem(position))
    }

    class TodoViewHolder(
        private val binding: ItemTodoBinding,
        private val onClick: (Todo) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(todo: Todo){
            binding.text1.text = todo.title
            binding.text2.text = DateFormat.format("yyyy/MM/dd", todo.date)
        }

        fun setOnClickListener(todo: Todo) {
            binding.root.setOnClickListener {
                onClick(todo)
            }
        }
    }
}

package com.kongsub.todolist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.kongsub.todolist.data.Todo

class TodoDiffUtilCallback : DiffUtil.ItemCallback<Todo>() {

    // 아이템이 같음을 판단하는 규칙, id가 다르면 변경된것으로 판단하여 해당 아이템이 교체됨
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }

    // 내용을 비교하는 규칙
    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }
}
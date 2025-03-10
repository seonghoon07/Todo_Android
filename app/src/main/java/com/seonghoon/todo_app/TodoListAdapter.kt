package com.seonghoon.todo_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter(private val itemList: List<Todo>): RecyclerView.Adapter<TodoListAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoListAdapter.ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.holder_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoListAdapter.ItemViewHolder, position: Int) {
        val todo = itemList[position]
        holder.textView.text = todo.text
        holder.checkBox.isChecked = todo.isCheck

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            todo.isCheck = isChecked
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.TodolistTitle)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
    }
}
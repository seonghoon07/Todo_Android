package com.seonghoon.todo_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val todoList = mutableListOf(
        Todo(false,"빨래하기"),
        Todo(false,"산책가기"),
        Todo(false,"공부하기"),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.Rv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 어댑터 연결
        val adapter = TodoListAdapter(todoList)
        recyclerView.adapter = adapter

        // 할일 추가 버튼 연결
        val addTodoBtn = findViewById<Button>(R.id.addTodoBtn)
        addTodoBtn.setOnClickListener {
            addTodoDialog(adapter)
        }
    }

    private fun addTodoDialog(adapter: TodoListAdapter) {
        val dialog = AlertDialog.Builder(this)
        val editText = EditText(this)
        editText.hint = "할 일을 입력하세요"

        dialog.setTitle("새 할일 추가")
            .setView(editText)
            .setPositiveButton("추가") { _, _ ->
                val newTask = editText.text.toString()
                if (newTask.isNotEmpty()) {
                    todoList.add(Todo(false, newTask)) // 리스트에 추가
                    adapter.notifyItemInserted(todoList.size - 1)
                }
            }
            .setNegativeButton("취소", null)
        val alertDialog = dialog.create()
        alertDialog.window?.setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE) // 키보드 자동 표시
        alertDialog.show()
    }
}
package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : ComponentActivity() {
    // we can initialise lately
    private lateinit var todoAdapter:TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvTodoItems = findViewById<RecyclerView>(R.id.rvTodoItems)
        todoAdapter= TodoAdapter(mutableListOf())
        rvTodoItems.adapter = todoAdapter
        //layout (grid,pane ....)
        rvTodoItems.layoutManager=LinearLayoutManager(this)
        val btnAddTodos = findViewById<Button>(R.id.btnAddTodos)
        val btnDeleteTodos = findViewById<Button>(R.id.btnDeleteTodos)
        val etTodoTitle = findViewById<EditText>(R.id.etTodoTitle)
        btnAddTodos.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
        btnDeleteTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }


    }
}







package com.example.myapplication

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TodoAdapter (
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    class TodoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
       return TodoViewHolder(
           //LayoutInflater code xml to kotlin
           LayoutInflater.from(parent.context).inflate(
               R.layout.item_todo,
               parent,
               false
           )
       )
    }
    fun deleteDoneTodos(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }
    fun addTodo(todo:Todo){
        todos.add(todo)
        //visible new itm on screen
        notifyItemInserted(todos.size-1)
    }
    private fun toggleStrikeThrough(tvItem:TextView,isChecked: Boolean){
        if(isChecked){
            tvItem.paintFlags = tvItem.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvItem.paintFlags = tvItem.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var currTodo = todos[position]
        //avoid writing every time same code
        val tvItem = holder.itemView.findViewById<TextView>(R.id.tvItem)
        val cbDone = holder.itemView.findViewById<CheckBox>(R.id.cbDone)
        tvItem.text = currTodo.title
        cbDone.isChecked = currTodo.isChecked
        toggleStrikeThrough(tvItem,currTodo.isChecked)
        cbDone.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(tvItem,isChecked)
            currTodo.isChecked=!currTodo.isChecked
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
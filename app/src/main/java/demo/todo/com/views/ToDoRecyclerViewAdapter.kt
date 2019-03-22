package demo.todo.com.views

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import demo.todo.com.R
import demo.todo.com.model.ToDoItem

class ToDoRecyclerViewAdapter : RecyclerView.Adapter<ToDoRecyclerViewHolder>() {
    lateinit var todoItemsList : List<ToDoItem>
    private fun setItems(todoItemsList : List<ToDoItem>) {
        this.todoItemsList = todoItemsList
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ToDoRecyclerViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.todo_item,viewGroup,false)
        return ToDoRecyclerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  todoItemsList.size
    }

    override fun onBindViewHolder(p0: ToDoRecyclerViewHolder, p1: Int) {
    }
}

class ToDoRecyclerViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

}

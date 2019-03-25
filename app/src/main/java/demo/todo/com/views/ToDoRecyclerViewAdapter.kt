package demo.todo.com.views

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import demo.todo.com.R
import demo.todo.com.db.model.ToDoItemEntity
import kotlinx.android.synthetic.main.todo_item.view.*

class ToDoRecyclerViewAdapter : RecyclerView.Adapter<ToDoRecyclerViewHolder>() {
    lateinit var todoItemsList: List<ToDoItemEntity>
    private fun setItems(todoItemsList: List<ToDoItemEntity>) {
        this.todoItemsList = todoItemsList
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ToDoRecyclerViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.todo_item, viewGroup, false)
        return ToDoRecyclerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return todoItemsList.size
    }

    override fun onBindViewHolder(toDoRecyclerViewHolder: ToDoRecyclerViewHolder, position: Int) {
        toDoRecyclerViewHolder.title.text = todoItemsList[position].title
        toDoRecyclerViewHolder.content.text = todoItemsList[position].content
    }
}

class ToDoRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.title_text
    var content: TextView = itemView.content_text

}

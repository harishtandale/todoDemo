package demo.todo.com.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "todo_table")
data class ToDoItemEntity(@PrimaryKey (autoGenerate = true) val id :Int,
                          val title:String, val content : String)
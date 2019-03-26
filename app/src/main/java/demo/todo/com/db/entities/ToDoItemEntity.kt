package demo.todo.com.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "todo_table")
data class ToDoItemEntity(val title:String, val content : String,@PrimaryKey (autoGenerate = true) val id : Long?){
}

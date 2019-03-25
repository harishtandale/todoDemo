package demo.todo.com.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import demo.todo.com.db.model.ToDoItemEntity

@Dao
interface ToDoDao {
    @Query("SELECT * FROM TODO_TABLE")
    fun getAllToDoItems() : LiveData<List<ToDoItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateToDoItem(toDoItemEntity: ToDoItemEntity)

    @Delete
    fun deleteToDoItem(toDoItemEntity: ToDoItemEntity)

    @Query("DELETE FROM TODO_TABLE")
    fun deleteAllToDoItems()
}
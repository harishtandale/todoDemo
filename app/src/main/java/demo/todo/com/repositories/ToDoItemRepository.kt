package demo.todo.com.repositories

import android.arch.lifecycle.LiveData
import demo.todo.com.db.dao.ToDoDao
import demo.todo.com.db.entities.ToDoItemEntity

/**
 * Central place where all business logic present where to get data from.
 */
class ToDoItemRepository (private  val toDoDao: ToDoDao) {
    val allToDoItemsEntity: LiveData<List<ToDoItemEntity>> = toDoDao.getAllToDoItems()

    fun insertOrUpdate(toDoItemEntity: ToDoItemEntity) {
        toDoDao.insertOrUpdateToDoItem(toDoItemEntity)
    }

    fun deleteToDoItem(toDoItemEntity: ToDoItemEntity) {
        toDoDao.deleteToDoItem(toDoItemEntity)
    }

    fun deleteAllToDoItems() {
        toDoDao.deleteAllToDoItems()
    }
}
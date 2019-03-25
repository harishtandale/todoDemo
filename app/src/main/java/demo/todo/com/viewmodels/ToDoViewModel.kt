package demo.todo.com.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import demo.todo.com.db.database.ToDoDatabase
import demo.todo.com.db.model.ToDoItemEntity
import demo.todo.com.repositories.ToDoItemRepository

class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ToDoItemRepository
    val allTodoToDoItemEntity: LiveData<List<ToDoItemEntity>>

    init {
        val toDoDao = ToDoDatabase.getDatabase(application).toDoItemDao()
        repository = ToDoItemRepository(toDoDao)
        allTodoToDoItemEntity = repository.allToDoItemsEntity
    }

    fun insertOrUpdate(toDoItemEntity: ToDoItemEntity) {
        repository.insertOrUpdate(toDoItemEntity)
    }

    fun delete(toDoItemEntity: ToDoItemEntity) {
        repository.deleteToDoItem(toDoItemEntity)
    }

    fun deleteAllTodoItems() {
        repository.deleteAllToDoItems()
    }


}
package demo.todo.com.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import demo.todo.com.db.database.ToDoDatabase
import demo.todo.com.db.entities.ToDoItemEntity
import demo.todo.com.repositories.ToDoItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * This class is lifecycle aware and provides data as per lifecycle of the attached view component
 */
class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ToDoItemRepository
    val allTodoToDoItemEntity: LiveData<List<ToDoItemEntity>>
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val toDoDao = ToDoDatabase.getDatabase(application).toDoItemDao()
        repository = ToDoItemRepository(toDoDao)
        allTodoToDoItemEntity = repository.allToDoItemsEntity
    }

    @WorkerThread
     fun insertOrUpdate(toDoItemEntity: ToDoItemEntity) {
        scope.launch(Dispatchers.IO) { repository.insertOrUpdate(toDoItemEntity) }
    }

    @WorkerThread
    fun delete(toDoItemEntity: ToDoItemEntity) {
        scope.launch(Dispatchers.IO) { repository.deleteToDoItem(toDoItemEntity) }

    }

    @WorkerThread
    fun deleteAllTodoItems() {
        scope.launch(Dispatchers.IO) { repository.deleteAllToDoItems() }

    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}
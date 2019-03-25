package demo.todo.com.db.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import demo.todo.com.db.dao.ToDoDao
import demo.todo.com.db.model.ToDoItemEntity

@Database(entities = [ToDoItemEntity::class],version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoItemDao() : ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getDatabase(context: Context): ToDoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
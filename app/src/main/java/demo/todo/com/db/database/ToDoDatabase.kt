package demo.todo.com.db.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import demo.todo.com.db.dao.ToDoDao
import demo.todo.com.db.entities.ToDoItemEntity
/**
 * Class creates and shares the instance of DB to perform operations on it
 */
@Database(entities = [ToDoItemEntity::class],version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoItemDao() : ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        /**
         * Function create singleton instance of DB
         */
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
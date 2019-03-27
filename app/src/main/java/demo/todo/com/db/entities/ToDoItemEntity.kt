package demo.todo.com.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity(tableName = "todo_table")
data class ToDoItemEntity(var title:String, var content : String): Parcelable{
    @PrimaryKey (autoGenerate = true)
    var id : Long = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ToDoItemEntity> {
        override fun createFromParcel(parcel: Parcel): ToDoItemEntity {
            return ToDoItemEntity(parcel)
        }

        override fun newArray(size: Int): Array<ToDoItemEntity?> {
            return arrayOfNulls(size)
        }
    }
}

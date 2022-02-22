package com.example.search_filteramproom_paging.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.search_filteramproom_paging.utility.Keys

@Entity(tableName = Keys.DATABASE_NAME, indices = [Index(value = ["id"], unique = true)])
class User {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var firstName: String
    var lastName: String
    var age: Int

    @Ignore
    constructor(firstName: String, lastName: String, age: Int) {
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
    }

    constructor(id: Int?, firstName: String, lastName: String, age: Int) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
    }

}

object UserDiffUtilCallBack: DiffUtil.ItemCallback<User>(){
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
}

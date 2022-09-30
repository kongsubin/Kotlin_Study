package com.kongsub.todolist.data

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo ORDER BY date DESC")
    fun getAll(): Flow<List<Todo>> // Flow : 코틀린의 고급 기능 중 하나 - 데이터를 관찰할 수 있도록 함.

    // suspend : 비동기 처리를 위한 선언
    @Insert(onConflict = REPLACE)
    suspend fun insert(entity: Todo)

    @Update
    suspend fun update(entity: Todo)

    @Delete
    suspend fun delete(entity: Todo)
}
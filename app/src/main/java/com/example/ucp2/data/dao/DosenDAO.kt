package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

@Dao
interface DosenDAO {
    // Get All Data
    @Query("select * from Dosen")
    fun getALlDosen() : Flow<List<Dosen>>

    @Insert
    suspend fun insertDosen(
        dosen: Dosen
    )
}
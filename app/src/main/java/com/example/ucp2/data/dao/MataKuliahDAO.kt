package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface MataKuliahDAO {
    // Get All MataKuliah
    @Query("Select * from MataKuliah order by kode asc")
    fun getMatKul() : Flow<List<MataKuliah>>

    // Get Detail Matkul
    @Query("select * from MataKuliah where kode = kode")

    @Insert
    suspend fun insertMatkul(
        mataKuliah: MataKuliah
    )

    @Delete
    suspend fun deleteMatkul(
        mataKuliah: MataKuliah
    )

    @Update
    suspend fun updateMatkul(
        mataKuliah: MataKuliah
    )
}
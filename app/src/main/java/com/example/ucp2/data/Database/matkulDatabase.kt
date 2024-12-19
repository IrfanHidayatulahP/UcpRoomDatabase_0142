package com.example.ucp2.data.Database

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.DosenDAO
import com.example.ucp2.data.dao.MataKuliahDAO
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Dosen::class, MataKuliah::class], version = 1, exportSchema = false)
abstract class matkulDatabase : RoomDatabase() {
    // Mendefinisikan fungsi untuk mengatur data Matkul
    abstract fun MataKuliahDAO() : MataKuliahDAO
    abstract fun DosenDAO() : DosenDAO

    companion object {
        @Volatile //Memastikan bahwa nilai variable Instance selalu sama
        private var Instances : matkulDatabase ?= null

        @OptIn (InternalCoroutinesApi::class)
        fun getDatabase(context : Context) : matkulDatabase {
            return (Instances ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    matkulDatabase::class.java,
                    "MatkulDatabase"
                )
                    .build().also { Instances = it }
            })
        }
    }
}
package com.example.ucp2.repository

import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryKrs {
    // Operasi untuk Mata Kuliah
    fun getMatkul(kode : String) : Flow<List<MataKuliah>>
    fun getDetailMk(kode: String) : Flow<List<MataKuliah>>
    suspend fun insertMatkul(mataKuliah: MataKuliah)
    suspend fun deleteMatkul(mataKuliah: MataKuliah)
    suspend fun updateMatkul(mataKuliah: MataKuliah)

    // Operasi untuk Dosen
    fun getAllDosen(nidn : String) : Flow<List<Dosen>>
    fun getDosen(nama : String) : Flow<List<Dosen>>
    suspend fun insertDosen(dosen: Dosen)

}
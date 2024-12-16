package com.example.ucp2.repository

import com.example.ucp2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepoDosen {
    // Get Dosen
    fun getAllDosen(nim: String) : Flow<Dosen>

    // Insert Dosen
    suspend fun insertDosen(dosen: Dosen)
}
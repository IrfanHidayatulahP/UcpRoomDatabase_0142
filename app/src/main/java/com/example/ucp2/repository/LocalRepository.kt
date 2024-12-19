package com.example.ucp2.repository

import com.example.ucp2.data.dao.DosenDAO
import com.example.ucp2.data.dao.MataKuliahDAO
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

class LocalRepository (
    private val dosenDAO: DosenDAO,
    private val mataKuliahDAO: MataKuliahDAO
) : RepositoryKrs {
    // Operasi Untuk MataKuliah
    override fun getMatkul(): Flow<List<MataKuliah>> {
        return mataKuliahDAO.getMatKul()
    }

    override fun getDetailMk(kode: String): Flow<List<MataKuliah>> {
        return mataKuliahDAO.getDetailMk(kode)
    }

    override suspend fun insertMatkul(mataKuliah: MataKuliah) {
        mataKuliahDAO.insertMatkul(mataKuliah = mataKuliah)
    }

    override suspend fun deleteMatkul(mataKuliah: MataKuliah) {
        mataKuliahDAO.deleteMatkul(mataKuliah = mataKuliah)
    }

    override suspend fun updateMatkul(mataKuliah: MataKuliah) {
        mataKuliahDAO.updateMatkul(mataKuliah = mataKuliah)
    }

    // Operasi untuk Dosen
    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDAO.getAllDosen()
    }

    override fun getDosen(nama: String): Flow<List<Dosen>> {
        return dosenDAO.getDosen(nama = nama)
    }

    override suspend fun insertDosen(dosen: Dosen) {
        return dosenDAO.insertDosen(dosen = dosen)
    }

}
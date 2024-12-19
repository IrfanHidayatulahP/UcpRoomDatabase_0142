package com.example.ucp2.ui.viewmodel.MataKuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryKrs
import kotlinx.coroutines.launch

class InsertMKViewModel (private val repositoryKrs: RepositoryKrs) : ViewModel() {
    var uiState by mutableStateOf(MKUIState())

    // Memperbarui state
    fun updateState(MatkulEvent: MKEvent) {
        uiState = uiState.copy(
            MatkulEvent = MatkulEvent
        )
    }
    // Validasi data Input
    private fun validateField() : Boolean {
        val event = uiState.MatkulEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "Kode Tidak Boleh Kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama Tidak Boleh Kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS Tidak Boleh Kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester Tidak Boleh Kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis Tidak Boleh Kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen Pengampu Tidak Boleh Kosong",

        )
        uiState = uiState.copy(
            isEntryValid = errorState
        )
        return errorState.isValid()
    }
    // Menyimpan Data ke Repository
    fun saveData() {
        val currentEvent = uiState.MatkulEvent
        if (validateField()) {
            viewModelScope.launch {
                try {
                    repositoryKrs.insertMatkul(currentEvent.toMKEntity())
                    uiState = uiState.copy(
                        snackBarMessagge = "Data Berhasil Disimpan",
                        MatkulEvent = MKEvent(), // Reset input Form
                        isEntryValid = FormErrorState() // Reset Error State
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessagge = "Data Gagal Disimpan"
                    )
                }
            }
        }
        else {
            uiState = uiState.copy(
                snackBarMessagge = "Input Tidak Valid. Periksa Kembali Data yang Anda Masukkan"
            )
        }
    }
    // Reset pesan SnackBar
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessagge = null)
    }
}

data class MKUIState(
    val MatkulEvent: MKEvent = MKEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessagge: String? = null,
)

data class FormErrorState(
    val kode: String? = null,
    val nama: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosenPengampu: String? = null,
) {
    fun isValid() : Boolean {
        return kode == null && nama == null && sks == null && semester == null
                && jenis == null && dosenPengampu == null
    }
}

// Menyimpan input form ke dalam Entity
fun MKEvent.toMKEntity() : MataKuliah = MataKuliah (
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenPengampu = dosenPengampu
)

data class MKEvent(
    val kode: String = "",
    val nama: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenis: String = "",
    val dosenPengampu: String = ""
)
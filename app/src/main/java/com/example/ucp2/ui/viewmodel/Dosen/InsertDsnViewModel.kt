package com.example.ucp2.ui.viewmodel.Dosen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositoryKrs
import kotlinx.coroutines.launch

class InsertDsnViewModel (private val repositoryKrs: RepositoryKrs) : ViewModel() {
    var uiState by mutableStateOf(DsnUIState())

    // Memperbarui state berdasarkan input pengguna
    fun updateState (DosenEvent: DosenEvent) {
        uiState = uiState.copy(
            dosenEvent = DosenEvent
        )
    }
    // Validasi data input Pengguna
    private fun validateFields() : Boolean {
        val event = uiState.dosenEvent
        val errorState = FormErrorState(
            nidn = if (event.nidn.isNotEmpty()) null else "NIDN Tidak Boleh Kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama Tidak Boleh Kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin Tidak Boleh Kosong"
        )
        uiState = uiState.copy(
            isEntryValid = errorState
        )
        return errorState.isValid()
    }
    // Menyimpan Data ke Repository
    fun saveData() {
        val currentEvent = uiState.dosenEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryKrs.insertDosen(currentEvent.toDosenEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil Disimpan",
                        dosenEvent = DosenEvent(),
                        isEntryValid = FormErrorState()
                    )
                }
                catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        }
        else {
            uiState = uiState.copy(
                snackBarMessage = "Input Tidak Valid. Periksa Kembali Data yang Anda Masukkan"
            )
        }
    }
    // Reset pesan SnackBar setelah ditampilkan
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class DsnUIState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)

data class FormErrorState(
    val nidn: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null
) {
    fun isValid() : Boolean {
        return nidn == null && nama == null && jenisKelamin == null
    }
}

fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jenisKelamin = jenisKelamin
)

data class DosenEvent(
    val nidn: String = "",
    val nama: String = "",
    val jenisKelamin: String = ""
)
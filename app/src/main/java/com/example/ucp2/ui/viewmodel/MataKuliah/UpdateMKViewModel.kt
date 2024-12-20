package com.example.ucp2.ui.viewmodel.MataKuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryKrs
import com.example.ucp2.ui.navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMKViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryKrs: RepositoryKrs
) : ViewModel() {
    var updateUiState by mutableStateOf(MKUIState(MatkulEvent = MKEvent()))
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdate.KODE])

    init {
        viewModelScope.launch {
            updateUiState = repositoryKrs.getDetailMk(_kode)
                .filterNotNull()
                .first()
                .toUiStateMK()
        }
    }

    fun updateState (mkEvent: MKEvent) {
        updateUiState = updateUiState.copy(
            MatkulEvent = mkEvent,
        )
    }

    fun validateFields() : Boolean {
        val event = updateUiState.MatkulEvent
        val errorState = FormErrorState (
            kode = if (event.kode.isNotEmpty()) null else "Kode Tidak Boleh Kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama Tidak Boleh Kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS Tidak Boleh Kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester Tidak Boleh Kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis Tidak Boleh Kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen Pengampu Tidak Boleh Kosong",
        )

        updateUiState = updateUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateUiState.MatkulEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryKrs.updateMatkul(currentEvent.toMKEntity())
                    updateUiState = updateUiState.copy(
                        snackBarMessagge = "Data Berhasil di Update",
                        MatkulEvent = MKEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackBarMessage diatur : ${updateUiState.snackBarMessagge}")
                } catch (e: Exception) {
                    updateUiState = updateUiState.copy(
                        snackBarMessagge = "Data Gagal Diupdate"
                    )
                }
            }
        } else {
            updateUiState = updateUiState.copy(
                snackBarMessagge = "Data Gagal di Update"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateUiState = updateUiState.copy(snackBarMessagge = null)
    }
}

fun MataKuliah.toUiStateMK() : MKUIState = MKUIState(
    MatkulEvent = this.toDetailUiEvent()
)
package com.example.ucp2.ui.viewmodel.MataKuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryKrs

class DetailMKViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryKrs: RepositoryKrs,
) : ViewModel() {
    private val _kode: String = checkNotNull(savedStateHandle[])
}

data class DetailUiState(
    val detailUiEvent: MKEvent = MKEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MKEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MKEvent()
}

// Memindahkan data dari Entity ke UI
fun MataKuliah.toDetailUiEvent() : MKEvent {
    return MKEvent(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosenPengampu =  dosenPengampu
    )
}
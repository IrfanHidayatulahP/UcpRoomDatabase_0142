package com.example.ucp2.ui.viewmodel.MataKuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryKrs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMKViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryKrs: RepositoryKrs,
) : ViewModel() {
    private val kode: String = checkNotNull(savedStateHandle[DestinasiDetail.kode])

    val detailUiState: StateFlow<DetailUiState> = repositoryKrs.getDetailMk(kode)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(), // Mengonversi MataKuliah ke MKEvent
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true,
            ),
        )

    fun deleteMK() {
        detailUiState.value.detailUiEvent.toMKEntity().let {
            viewModelScope.launch {
                repositoryKrs.deleteMatkul(it)
            }
        }
    }
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
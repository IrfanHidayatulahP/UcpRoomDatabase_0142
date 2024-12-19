package com.example.ucp2.ui.viewmodel.MataKuliah

import com.example.ucp2.data.entity.MataKuliah

class InsertMKViewModel {
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
)

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
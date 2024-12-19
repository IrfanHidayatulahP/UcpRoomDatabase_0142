package com.example.ucp2.ui.viewmodel.MataKuliah

import com.example.ucp2.data.entity.MataKuliah

class InsertMKViewModel {
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
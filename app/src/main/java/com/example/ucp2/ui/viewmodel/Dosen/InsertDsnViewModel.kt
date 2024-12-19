package com.example.ucp2.ui.viewmodel.Dosen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositoryKrs

class InsertDsnViewModel (private val repositoryKrs: RepositoryKrs) : ViewModel() {

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
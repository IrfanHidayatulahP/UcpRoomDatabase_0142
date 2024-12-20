package com.example.ucp2.ui.view.MatKul

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.ui.viewmodel.MataKuliah.DetailUiState
import com.example.ucp2.ui.viewmodel.MataKuliah.toMKEntity



@Composable
fun BodyDetailMK(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState = DetailUiState(),
    onDeleteClick: () -> Unit = { }
) {
    var deleteConfimationRequired by rememberSaveable { mutableStateOf(false) }
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        detailUiState.isUiEventNotEmpty -> {
            Column (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailMK(
                    mataKuliah = detailUiState.detailUiEvent.toMKEntity(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        deleteConfimationRequired = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Delete")
                }

                if (deleteConfimationRequired) {
                    DeleteConfirmDialog(
                        onDeleteConfirm = {
                            deleteConfimationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = {
                            deleteConfimationRequired = false
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        detailUiState.isUiEventEmpty -> {
            Box (
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Data Tidak Ditemukan",
                    modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun ItemDetailMK(
    modifier: Modifier = Modifier,
    mataKuliah: MataKuliah
) {
    Card(
        modifier = Modifier.padding(16.dp)
    ) {
        ComponentDetailMK(judul = "Kode MK", isinya = mataKuliah.kode)
        Spacer(modifier = Modifier.padding(4.dp))

        ComponentDetailMK(judul = "Nama MK", isinya = mataKuliah.nama)
        Spacer(modifier = Modifier.padding(4.dp))

        ComponentDetailMK(judul = "SKS MK", isinya = mataKuliah.sks)
        Spacer(modifier = Modifier.padding(4.dp))

        ComponentDetailMK(judul = "Semester MK", isinya = mataKuliah.semester)
        Spacer(modifier = Modifier.padding(4.dp))

        ComponentDetailMK(judul = "Jenis MK", isinya = mataKuliah.jenis)
        Spacer(modifier = Modifier.padding(4.dp))

        ComponentDetailMK(judul = "Dosen Pengampu", isinya = mataKuliah.dosenPengampu)
        Spacer(modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun ComponentDetailMK(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Column (
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DeleteConfirmDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { },
        title = { Text("Delete Data") },
        text = { Text("Apakah Anda Ingin Menghapus Data?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        })
}
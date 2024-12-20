package com.example.ucp2.ui.view.MatKul

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.MataKuliah.FormErrorState
import com.example.ucp2.ui.viewmodel.MataKuliah.InsertMKViewModel
import com.example.ucp2.ui.viewmodel.MataKuliah.MKEvent
import com.example.ucp2.ui.viewmodel.MataKuliah.MKUIState


@Composable
fun InsertBodyMK(
    modifier: Modifier = Modifier,
    onValueChange: (MKEvent) -> Unit,
    uiState: MKUIState,
    onClick: () -> Unit
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormMataKuliah(
            mkEvent = uiState.MatkulEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            onValueChangedEvent = { selectedDosen ->
                onValueChange(uiState.MatkulEvent.copy(dosenPengampu = selectedDosen))
            },
            label = "Pilih Dosen",
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = onClick) {
            Text("Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormMataKuliah(
    mkEvent: MKEvent = MKEvent(),
    onValueChange: (MKEvent) -> Unit = { },
    errorState: FormErrorState = FormErrorState(),
    viewModel: InsertMKViewModel = viewModel(),
    label: String,
    onValueChangedEvent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedValue by remember { mutableStateOf("") }
    var options by remember { mutableStateOf(listOf<String>()) }
    var expanded by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Nama MK")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mkEvent.nama,
            onValueChange = {
                onValueChange(mkEvent.copy(nama = it))
            },
            label = { Text("Nama MK") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama MK") },
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        Text(text = "Kode MK")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mkEvent.kode,
            onValueChange = {
                onValueChange(mkEvent.copy(kode = it))
            },
            label = { Text("Kode MK") },
            isError = errorState.kode != null,
            placeholder = { Text("Masukkan Kode MK") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Text(text = errorState.kode ?: "", color = Color.Red)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "SKS")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mkEvent.sks,
            onValueChange = {
                onValueChange(mkEvent.copy(kode = it))
            },
            label = { Text("SKS") },
            isError = errorState.sks != null,
            placeholder = { Text("Masukkan Jumlah SKS MK") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = errorState.sks ?: "", color = Color.Red)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Semester")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mkEvent.semester,
            onValueChange = {
                onValueChange(mkEvent.copy(kode = it))
            },
            label = { Text("Semester") },
            isError = errorState.semester != null,
            placeholder = { Text("Masukkan Semester MK") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = errorState.semester ?: "", color = Color.Red)
        Text(text = "Jenis MK")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mkEvent.jenis,
            onValueChange = {
                onValueChange(mkEvent.copy(kode = it))
            },
            label = { Text("Jenis") },
            isError = errorState.jenis != null,
            placeholder = { Text("Masukkan Jenis MK") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Text(text = errorState.jenis ?: "", color = Color.Red)

        // Mengambil Data dari ViewModel
        LaunchedEffect(Unit) {
            viewModel.dosenList.collect { dosenList ->
                options = dosenList.map { it.nama }
            }
        }

        Text(text = "Dosen Pengampu")
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded != expanded},
            modifier = modifier
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedValue,
                onValueChange = { },
                label = { Text(text = label) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = OutlinedTextFieldDefaults.colors(),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            expanded = false
                            selectedValue = option
                            onValueChangedEvent(option)
                        }
                    )
                }
            }
        }
    }
}
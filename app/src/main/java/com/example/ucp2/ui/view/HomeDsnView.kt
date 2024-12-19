package com.example.ucp2.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.Dosen.HomeDsnViewModel
import com.example.ucp2.ui.viewmodel.Dosen.HomeUiState
import com.example.ucp2.ui.viewmodel.PenyediaViewModel

@Composable
fun HomeDsnView(
    viewModel: HomeDsnViewModel = viewModel(
        factory = PenyediaViewModel.Factory
    ),
    onAddDsn: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold (
        topBar = {
            TopAppBar(
                judul = "Daftar Dosen",
                showBackButton = false,
                onBack = { },
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddDsn,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Dosen",
                )
            }
        }
    ) {
        innerPadding ->
        val HomeUiState by viewModel.homeUiState.collectAsState()
        )
    }
}

@OptIn
@Composable
fun CardDsn(
    dsn: Dosen,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column (
            modifier = Modifier.padding(8.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = dsn.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = dsn.nidn,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Face, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = dsn.jenisKelamin,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
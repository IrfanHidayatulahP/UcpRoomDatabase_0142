package com.example.ucp2.ui.view.MatKul

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ucp2.ui.viewmodel.MataKuliah.FormErrorState
import com.example.ucp2.ui.viewmodel.MataKuliah.MKEvent

@Composable
fun FormMataKuliah(
    mataKuliah: MKEvent = MKEvent(),
    onValueChange: (MKEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
) {

}
package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.krsApp
import com.example.ucp2.ui.viewmodel.Dosen.HomeDsnViewModel
import com.example.ucp2.ui.viewmodel.Dosen.InsertDsnViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeDsnViewModel(
                krsApp().containerApp.repositoryKrs
            )
        }

        initializer {
            InsertDsnViewModel(
                krsApp().containerApp.repositoryKrs
            )
        }
    }
}

fun CreationExtras.krsApp(): krsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as krsApp)
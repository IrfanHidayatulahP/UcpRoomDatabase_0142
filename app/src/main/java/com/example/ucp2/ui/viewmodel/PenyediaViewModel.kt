package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.krsApp
import com.example.ucp2.ui.viewmodel.Dosen.HomeDsnViewModel
import com.example.ucp2.ui.viewmodel.Dosen.InsertDsnViewModel
import com.example.ucp2.ui.viewmodel.MataKuliah.DetailMKViewModel
import com.example.ucp2.ui.viewmodel.MataKuliah.HomeMKViewModel
import com.example.ucp2.ui.viewmodel.MataKuliah.InsertMKViewModel
import com.example.ucp2.ui.viewmodel.MataKuliah.UpdateMKViewModel

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

        initializer {
            HomeMKViewModel(
                krsApp().containerApp.repositoryKrs
            )
        }

        initializer {
            InsertMKViewModel(
                krsApp().containerApp.repositoryKrs
            )
        }

        initializer {
            DetailMKViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repositoryKrs
            )
        }

        initializer {
            UpdateMKViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repositoryKrs
            )
        }
    }
}

fun CreationExtras.krsApp(): krsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as krsApp)
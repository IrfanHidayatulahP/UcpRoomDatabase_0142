package com.example.ucp2.dependenciesinjection

import android.content.Context
import com.example.ucp2.data.Database.matkulDatabase
import com.example.ucp2.data.dao.DosenDAO
import com.example.ucp2.data.dao.MataKuliahDAO
import com.example.ucp2.repository.LocalRepository
import com.example.ucp2.repository.RepositoryKrs

interface InterfaceContainerApp {
    val repositoryKrs : RepositoryKrs
}

abstract class ContainerApp (private val context: Context) : InterfaceContainerApp {
    override val repositoryKrs: RepositoryKrs by lazy {
        LocalRepository(
            matkulDatabase.getDatabase(context).DosenDAO(),
            matkulDatabase.getDatabase(context).MataKuliahDAO())
    }
}
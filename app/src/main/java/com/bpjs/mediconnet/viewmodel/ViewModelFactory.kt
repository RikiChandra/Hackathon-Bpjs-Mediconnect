package com.bpjs.mediconnet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bpjs.mediconnet.repository.PharmacyRepository
import com.bpjs.mediconnet.repository.Repository

class ViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PharmacyViewModel::class.java) -> {
                PharmacyViewModel(repository as PharmacyRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE : ViewModelFactory? = null

        fun getInstance(repository: Repository): ViewModelFactory {
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: ViewModelFactory(repository).also {
                    INSTANCE = it
                }
            }
        }
    }

}
package com.bpjs.mediconnet.di

import com.bpjs.mediconnet.repository.PharmacyRepository

object Injection {
    fun provideRepository(): PharmacyRepository {
        return PharmacyRepository()
    }
}
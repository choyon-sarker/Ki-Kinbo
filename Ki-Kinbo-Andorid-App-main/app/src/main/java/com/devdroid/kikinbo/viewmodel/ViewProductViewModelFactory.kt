package com.devdroid.kikinbo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devdroid.kikinbo.model.repository.IProductRepository


class ViewProductViewModelFactory(private val repository: IProductRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

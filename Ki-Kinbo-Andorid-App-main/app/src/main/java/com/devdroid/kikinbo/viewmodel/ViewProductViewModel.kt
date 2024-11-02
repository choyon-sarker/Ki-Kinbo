package com.devdroid.kikinbo.viewmodel

import androidx.lifecycle.ViewModel
import com.devdroid.kikinbo.model.repository.IProductRepository
import com.devdroid.kikinbo.model.ProductDataModel

class ViewProductViewModel(private val repository: IProductRepository) : ViewModel() {
    fun getProductData(productId: String, callback: (ProductDataModel?) -> Unit) {
        repository.fetchProductById(productId, callback)
    }
}

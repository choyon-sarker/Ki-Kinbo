package com.devdroid.kikinbo.model.repository


import com.devdroid.kikinbo.model.ProductDataModel

interface IProductRepository {
    fun fetchProductById(productId: String, callback: (ProductDataModel?) -> Unit)
}

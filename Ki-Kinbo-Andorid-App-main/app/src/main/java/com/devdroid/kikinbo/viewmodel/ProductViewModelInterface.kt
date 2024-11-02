package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.ProductDataModel

interface ProductViewModelInterface {
    fun fetchProductData(productId: String, callback: (ProductDataModel?) -> Unit)

}
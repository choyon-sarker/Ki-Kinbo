package com.devdroid.kikinbo

import com.devdroid.kikinbo.model.ProductDataModel

interface ProductViewModelInterface {
    fun fetchProductData(productId: String, callback: (ProductDataModel?) -> Unit)

}
package com.devdroid.kikinbo

import android.widget.TextView
import com.devdroid.kikinbo.model.ProductDataModel

class ProductDisplayHelper {

    fun displayProductDetails(
        product: ProductDataModel,
        productNameId: TextView,
        productPriceId: TextView,
        productRatingId: TextView,
        productCatagoryId: TextView,
        productDetailsId: TextView
    ) {
        productNameId.text = product.productName
        productPriceId.text = "${product.productPrice} BDT"
        productRatingId.text = "${product.productRating}"
        productCatagoryId.text = "${product.productCategory}"
        productDetailsId.text = "${product.productDetail}"
    }
}

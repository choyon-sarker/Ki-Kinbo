package com.devdroid.kikinbo

import android.widget.TextView
import com.devdroid.kikinbo.model.ProductDataModel

/**
 * A helper class to manage the display of product details in the UI.
 * This class updates the content of multiple TextView elements
 * using data from a ProductDataModel instance.
 */
class ProductDisplayHelper {

    /**
     * Populates the provided TextViews with the details of a given product.
     *
     * @param product The product data to display, encapsulated in a [ProductDataModel] object.
     * @param productNameId The [TextView] where the product name will be displayed.
     * @param productPriceId The [TextView] where the product price will be displayed, formatted with "BDT".
     * @param productRatingId The [TextView] where the product rating will be displayed.
     * @param productCatagoryId The [TextView] where the product category will be displayed.
     * @param productDetailsId The [TextView] where the product details or description will be displayed.
     */
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

package com.devdroid.kikinbo.model

/**
 * Represents the shipping address for an order.
 *
 * @property city The city where the shipping address is located.
 * @property country The country where the shipping address is located.
 */
data class ShippingAddressDataModel(
    val city: String,
    val division:String,
    val country: String
)

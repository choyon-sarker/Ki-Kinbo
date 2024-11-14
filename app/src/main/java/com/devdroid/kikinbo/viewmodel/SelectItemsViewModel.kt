package com.devdroid.kikinbo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.devdroid.kikinbo.model.OrderItemDataModel
import com.devdroid.kikinbo.model.ProductStockInto
import com.devdroid.kikinbo.model.repository.DummyDataStore

/**
 * ViewModel responsible for processing the order based on selected items and their quantities.
 * It communicates with the data store to retrieve product stock information and creates order items.
 *
 * @param application The application context passed to the ViewModel.
 */
class SelectItemsViewModel(application: Application) : AndroidViewModel(application) {

    // Instance of the data store for retrieving product stock information
    private val dataStore = DummyDataStore()

    /**
     * Processes the order based on selected quantities for headphones, mouse, and pens.
     * It checks the available stock and generates order items with appropriate details.
     *
     * @param selectedHeadphoneQty The quantity of headphones selected by the user.
     * @param selectedMouseQty The quantity of mice selected by the user.
     * @param selectedPenQty The quantity of pens selected by the user.
     * @return A pair containing either a list of `OrderItemDataModel` representing the order items,
     *         or `null` along with a message indicating if no items were selected.
     */
    fun processOrder(
        selectedHeadphoneQty: Int,
        selectedMouseQty: Int,
        selectedPenQty: Int
    ): Pair<List<OrderItemDataModel>?, String> {
        val itemsToSend = mutableListOf<OrderItemDataModel>()
        val productStockList: List<ProductStockInto> = dataStore.productStockList

        // Process Headphone selection
        if (selectedHeadphoneQty > 0) {
            val headphoneStock = productStockList.find { it.productId == "Headphoneid" }
            headphoneStock?.let {
                itemsToSend.add(
                    OrderItemDataModel(
                        "Headphoneid",
                        "Headphone",
                        headphoneStock.availableStock,
                        selectedHeadphoneQty,
                        300
                    )
                )
            }
        }

        // Process Mouse selection
        if (selectedMouseQty > 0) {
            val mouseStock = productStockList.find { it.productId == "Mouseid" }
            mouseStock?.let {
                itemsToSend.add(
                    OrderItemDataModel(
                        "Mouseid",
                        "Mouse",
                        mouseStock.availableStock,
                        selectedMouseQty,
                        230
                    )
                )
            }
        }

        // Process Pen selection
        if (selectedPenQty > 0) {
            val penStock = productStockList.find { it.productId == "Penid" }
            penStock?.let {
                itemsToSend.add(
                    OrderItemDataModel(
                        "Penid",
                        "Pen",
                        penStock.availableStock,
                        selectedPenQty,
                        35
                    )
                )
            }
        }

        // Return the result: either a list of order items or an error message
        return if (itemsToSend.isNotEmpty()) {
            Pair(itemsToSend, "Saved the data")
        } else {
            Pair(null, "Please select at least one item")
        }
    }
}

package com.devdroid.kikinbo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devdroid.kikinbo.R
import com.devdroid.kikinbo.model.ProductDataModel

/**
 * Adapter for displaying a list of products in a RecyclerView.
 *
 * @property productList List of [ProductDataModel] representing the products to display.
 */
class SearchProductAdapter(private val productList: List<ProductDataModel>) : RecyclerView.Adapter<SearchProductAdapter.ViewHolder>() {

    /**
     * Creates a new ViewHolder instance to hold the view for each item in the RecyclerView.
     *
     * @param parent The parent ViewGroup.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds the view for each item.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ViewHolder(itemView)
    }

    /**
     * Binds the data from the product at the given position to the ViewHolder.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProduct = productList[position]
        holder.tvProName1.text = currentProduct.productName
        holder.tvProName2.text = currentProduct.productName
        holder.tvProCatagory.text = currentProduct.productCategory
        holder.tvProPrice.text = currentProduct.productPrice.toString()
        holder.tvProReview.text = currentProduct.productReview
        holder.tvProRating.text = currentProduct.productRating.toString()
    }

    /**
     * Returns the total number of items in the product list.
     *
     * @return The size of the product list.
     */
    override fun getItemCount(): Int {
        return productList.size
    }

    /**
     * ViewHolder class that holds references to the views for each item in the RecyclerView.
     *
     * @param itemView The view for each item.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /** TextView to display the first product name. */
        val tvProName1: TextView = itemView.findViewById(R.id.tvProductNameid1)

        /** TextView to display the second product name. */
        val tvProName2: TextView = itemView.findViewById(R.id.tvProductNameid2)

        /** TextView to display the product category. */
        val tvProCatagory: TextView = itemView.findViewById(R.id.tvProductCatagoryid)

        /** TextView to display the product price. */
        val tvProPrice: TextView = itemView.findViewById(R.id.tvProductPriceid)

        /** TextView to display the product review. */
        val tvProReview: TextView = itemView.findViewById(R.id.tvProductReviewid)

        /** TextView to display the product rating. */
        val tvProRating: TextView = itemView.findViewById(R.id.tvProductRatingid)
    }
}

package com.devdroid.kikinbo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchProductAdapter(private val productList: List<ProductDataModel>):RecyclerView.Adapter<SearchProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchProductAdapter.ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.product_list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchProductAdapter.ViewHolder, position: Int) {
        val currentProduct=productList[position]
        holder.tvProName1.text=currentProduct.productName
        holder.tvProName2.text=currentProduct.productName
        holder.tvProCatagory.text=currentProduct.productCategory
        holder.tvProPrice.text=currentProduct.productPrice.toString()
        holder.tvProReview.text=currentProduct.productReview
        holder.tvProRating.text=currentProduct.productRating.toString()
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolder(itemView :View ):RecyclerView.ViewHolder(itemView) {

        val tvProName1:TextView=itemView.findViewById(R.id.tvProductNameid1)
        val tvProName2:TextView=itemView.findViewById(R.id.tvProductNameid2)
        val tvProCatagory:TextView=itemView.findViewById(R.id.tvProductCatagoryid)
        val tvProPrice:TextView=itemView.findViewById(R.id.tvProductPriceid)
        val tvProReview:TextView=itemView.findViewById(R.id.tvProductReviewid)
        val tvProRating:TextView=itemView.findViewById(R.id.tvProductRatingid)
    }
}
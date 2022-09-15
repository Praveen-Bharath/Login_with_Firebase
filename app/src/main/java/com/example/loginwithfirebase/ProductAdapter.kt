package com.example.loginwithfirebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private var productList: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val layoutView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_card_view, parent, false)
        return ProductViewHolder(layoutView)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productImage.setImageResource(productList[position].img)
        holder.productTitle.text = productList[position].title
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var productImage: ImageView = view.findViewById(R.id.product_image)
        var productTitle: TextView = view.findViewById(R.id.product_title)
    }

}
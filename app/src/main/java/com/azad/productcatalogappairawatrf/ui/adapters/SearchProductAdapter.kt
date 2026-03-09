package com.azad.productcatalogappairawatrf.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.azad.productcatalogappairawatrf.R
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.Product
import com.azad.productcatalogappairawatrf.ui.ProductClickListener
import com.bumptech.glide.Glide


class SearchProductAdapter(
    private var productList: List<Product>?, private val listener: ProductClickListener
) : RecyclerView.Adapter<SearchProductAdapter.ProductViewHolder>() {

    private var filteredList: MutableList<Product>? = productList?.toMutableList()

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.txtTitle)
        val price: TextView = itemView.findViewById(R.id.txtPrice)
        val image: ImageView = itemView.findViewById(R.id.imgProduct)
        val searchItem: LinearLayout = itemView.findViewById(R.id.search_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_product_item_layout, parent, false)

        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int = filteredList?.size?:0

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = filteredList?.get(position)

        holder.title.text = product?.title
        holder.price.text = "$${product?.price}"

        Glide.with(holder.itemView.context)
            .load(product?.thumbnail)
            .into(holder.image)
        holder.searchItem.setOnClickListener {
            if(product !=null){
                listener.onProductClick(product)
            }

        }
    }

    fun filter(query: String) {

        filteredList = if (query.isEmpty()) {
            productList?.toMutableList()
        } else {
            productList?.filter {
                it.title.contains(query, true)
            }?.toMutableList()
        }

        notifyDataSetChanged()
    }
}
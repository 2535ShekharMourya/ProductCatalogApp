package com.azad.productcatalogappairawatrf.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.azad.productcatalogappairawatrf.R
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.Product
import com.azad.productcatalogappairawatrf.ui.ProductClickListener
import com.bumptech.glide.Glide

class ProductsAdapter(val context: Context, val templateClickListener: ProductClickListener): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    var templatesDataList: MutableList<Product> = mutableListOf()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage = itemView.findViewById<ImageView>(R.id.img_product_Image)
      val productRating = itemView.findViewById<TextView>(R.id.txt_product_rating)
        val productBrand = itemView.findViewById<TextView>(R.id.txt_product_brand)
        val productCategory = itemView.findViewById<TextView>(R.id.txt_product_category)
        val productPrice = itemView.findViewById<TextView>(R.id.txt_product_Price)
        val favoriteIcon = itemView.findViewById<ImageView>(R.id.favorite_Icon)
        val productItem = itemView.findViewById<View>(R.id.product_item)




    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        val item = templatesDataList[position]
        Glide.with(context)
            .load(item.thumbnail)
            .placeholder(R.drawable.favorite_selected)
            .into(holder.productImage)
       holder.productRating.text = item.rating.toString()
        holder.productPrice.text = item.price.toString()
        holder.productBrand.text = item.brand
        holder.productCategory.text = item.category
        holder.favoriteIcon.setOnClickListener {

        }
        holder.productItem.setOnClickListener {
            templateClickListener.onProductClick(item)
        }
    }

    override fun getItemCount(): Int {
        return templatesDataList.size
    }
    fun addProducts(newTemplates: List<Product>) {
        val start = templatesDataList.size
        templatesDataList.addAll(newTemplates)
        notifyItemRangeInserted(start, newTemplates.size)
    }

}
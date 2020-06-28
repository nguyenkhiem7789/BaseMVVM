package com.nguyen.basemvvm.ui.listProduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nguyen.basemvvm.R
import com.nguyen.basemvvm.data.remote.response.Product
import com.nguyen.basemvvm.ui.base.adapter.LoadMoreAdapter
import com.nguyen.basemvvm.utils.extensions.formatCurrency
import com.nguyen.basemvvm.utils.extensions.loadImageFromUrl
import com.nguyen.basemvvm.utils.extensions.loadPlaceHolder
import com.nguyen.basemvvm.utils.extensions.strikeThrough
import kotlinx.android.synthetic.main.item_product.view.*

class ListProductAdapter(var arrayProduct: ArrayList<Product?>) : LoadMoreAdapter<Product>(arrayProduct) {

    companion object {
        private const val TYPE_ITEM = 1
    }

    override fun onCreateViewHolderItem(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ListProductViewHolder(itemView)
    }

    override fun onBindViewHolderItem(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ListProductViewHolder) {
            if(arrayProduct[position] != null) {
                holder.bindTo(arrayProduct[position]!!)
            }
        }
    }

    inner class ListProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindTo(product: Product) {
            val arrayImage = product.arrayImage
            if(arrayImage != null && arrayImage.size > 0 && arrayImage[0].url != null) {
                itemView.productImageView.loadImageFromUrl(arrayImage[0].url!!)
            } else {
                itemView.productImageView.loadPlaceHolder()
            }
            itemView.nameTextView.text = product.name
            itemView.finalPriceTextView.text = product.price?.sellPrice?.formatCurrency()
            itemView.totalPriceTextView.text = product.price?.supplierSalePrice?.formatCurrency()?.strikeThrough()
        }
    }

    override fun getViewTypeItem(position: Int): Int {
        return TYPE_ITEM
    }
}



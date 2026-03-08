package com.azad.productcatalogappairawatrf.ui

import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.Product

interface ProductClickListener {
    fun onProductClick(product: Product)
}
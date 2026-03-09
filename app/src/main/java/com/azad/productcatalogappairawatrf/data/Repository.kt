package com.azad.productcatalogappairawatrf.data

import com.azad.productcatalogappairawatrf.core.Resource
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.AllProductsResponse
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.Product

interface Repository {
    suspend fun getAllProducts(): Resource<AllProductsResponse>
    suspend fun getProductsCategory(category: String?,limit: Int, skip: Int): Resource<AllProductsResponse>
    suspend fun getProductById(id: Int): Resource<Product>
}



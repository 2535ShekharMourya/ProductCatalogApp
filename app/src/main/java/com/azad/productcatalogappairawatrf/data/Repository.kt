package com.azad.productcatalogappairawatrf.data

import com.azad.productcatalogappairawatrf.core.Resource
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.AllProductsResponse

interface Repository {
    suspend fun getAllProducts(): Resource<AllProductsResponse>
    suspend fun getProductsCategory(category: String?): Resource<AllProductsResponse>
}



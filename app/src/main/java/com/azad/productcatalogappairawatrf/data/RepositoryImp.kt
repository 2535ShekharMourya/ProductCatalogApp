package com.azad.productcatalogappairawatrf.data

import com.azad.productcatalogappairawatrf.data.remotedata.ApiService
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.AllProductsResponse

class RepositoryImp( private val apiService: ApiService): Repository {
    override suspend fun getAllProducts(): AllProductsResponse {
        TODO("Not yet implemented")
    }
}
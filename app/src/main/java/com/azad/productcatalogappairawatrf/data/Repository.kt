package com.azad.productcatalogappairawatrf.data

import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.AllProductsResponse

interface Repository {
    suspend fun getAllProducts(): AllProductsResponse
}
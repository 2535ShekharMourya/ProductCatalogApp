package com.azad.productcatalogappairawatrf.data.remotedata

import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.AllProductsResponse
import retrofit2.http.GET

interface ApiService {

        @GET("products")
        suspend fun getAllProducts(): AllProductsResponse

}
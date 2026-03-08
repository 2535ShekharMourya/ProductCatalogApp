package com.azad.productcatalogappairawatrf.data.remotedata

import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.AllProductsResponse
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

        @GET("products")
        suspend fun getAllProducts(): Response<AllProductsResponse>
    @GET("products/category/{category}")
    suspend fun getProductsCategory( @Path("category") category: String?): Response<AllProductsResponse>


}
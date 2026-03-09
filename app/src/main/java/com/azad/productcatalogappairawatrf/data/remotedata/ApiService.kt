package com.azad.productcatalogappairawatrf.data.remotedata

import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.AllProductsResponse
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

        @GET("products")
        suspend fun getAllProducts(): Response<AllProductsResponse>
    @GET("products/category/{category}")
    suspend fun getProductsCategory(@Path("category") category: String?, @Query("limit") limit: Int,
                                    @Query("skip") skip: Int): Response<AllProductsResponse>
    @GET("products/{id}")
    suspend fun getProductById( @Path("id") id: Int): Response<Product>

    @GET("products")
    suspend fun getAllProductsTab(@Query("limit") limit: Int,
                                  @Query("skip") skip: Int): Response<AllProductsResponse>



}
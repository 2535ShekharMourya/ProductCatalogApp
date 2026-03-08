package com.azad.productcatalogappairawatrf.data

import android.util.Log
import com.azad.productcatalogappairawatrf.core.Resource
import com.azad.productcatalogappairawatrf.data.remotedata.ApiService
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.AllProductsResponse
import retrofit2.HttpException
import java.io.IOException

class RepositoryImp( private val apiService: ApiService?): Repository {
    override suspend fun getAllProducts(): Resource<AllProductsResponse> {
        return try {
            val response = apiService?.getAllProducts()
            if (response?.isSuccessful == true) {
                response.body()?.let { products ->

                    Log.d("Response", "getProducts: ${products}")
                    Resource.Success(products)
                } ?: Resource.Error("Empty response body")
            } else {
                Log.d("Response", "getProducts: ${response?.code()}")
                Resource.Error("HTTP ${response?.code()}: ${response?.message()}")
            }
        } catch (e: IOException) {
            Log.d("Response", "Network error: Check your internet connection")
            Resource.Error("Network error: Check your internet connection")
        } catch (e: HttpException) {
            Log.d("Response", "Server error: ${e.message()}")
            Resource.Error("Server error: ${e.message()}")
        } catch (e: Exception) {
            Log.d("Response", "Unexpected error: ${e.message}")
            Resource.Error("Unexpected error: ${e.message}")
        }

    }

    override suspend fun getProductsCategory(category: String?): Resource<AllProductsResponse> {
        return try {
            val response = apiService?.getProductsCategory(category)
            if (response?.isSuccessful == true) {
                response.body()?.let { products ->

                    Log.d("Response", "getProducts: ${products}")
                    Resource.Success(products)
                } ?: Resource.Error("Empty response body")
            } else {
                Log.d("Response", "getProductsCategory: getProducts: ${response?.code()}")
                Resource.Error("HTTP ${response?.code()}: ${response?.message()}")
            }
        } catch (e: IOException) {
            Log.d("Response", "getProductsCategory: Network error: Check your internet connection")
            Resource.Error("Network error: Check your internet connection")
        } catch (e: HttpException) {
            Log.d("Response", "getProductsCategory: Server error: ${e.message()}")
            Resource.Error("Server error: ${e.message()}")
        } catch (e: Exception) {
            Log.d("Response", "getProductsCategory: Unexpected error: ${e.message}")
            Resource.Error("Unexpected error: ${e.message}")
        }
    }
}
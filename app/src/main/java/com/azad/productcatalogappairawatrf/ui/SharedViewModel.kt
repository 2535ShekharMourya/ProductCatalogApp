package com.azad.productcatalogappairawatrf.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azad.productcatalogappairawatrf.core.Resource
import com.azad.productcatalogappairawatrf.data.Repository
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.AllProductsResponse
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.Product
import kotlinx.coroutines.launch

class SharedViewModel(private val repo: Repository) : ViewModel() {
    private val _products = MutableLiveData<Resource<AllProductsResponse>>()
    val products: LiveData<Resource<AllProductsResponse>> = _products

    private val _specificProducts = MutableLiveData<Resource<AllProductsResponse>>()
    val specificProducts: LiveData<Resource<AllProductsResponse>> = _specificProducts

    private val _productWithId = MutableLiveData<Resource<Product>>()
    val productWithId: LiveData<Resource<Product>> = _productWithId


fun getProductById(id: Int){
    viewModelScope.launch {
        _productWithId.value = Resource.Loading()
        _productWithId.value = repo.getProductById(id)
    }
}


    fun getAllProducts() {
        viewModelScope.launch {
            _products.value = Resource.Loading()
            _products.value = repo.getAllProducts()
        }
    }
    fun getAllProductsTab(limit: Int, skip: Int) {
        viewModelScope.launch {
            _products.value = Resource.Loading()
            _products.value = repo.getAllProductsTab(limit, skip)
        }
    }
    fun getProductsCategory(category: String?,limit: Int, skip: Int){
        viewModelScope.launch {
            _specificProducts.value = Resource.Loading()
            _specificProducts.value = repo.getProductsCategory(category,limit, skip)
        }
    }
}
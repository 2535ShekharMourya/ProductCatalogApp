package com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel

data class AllProductsResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)
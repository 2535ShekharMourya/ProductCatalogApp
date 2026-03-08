package com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel

data class Review(
    val comment: String,
    val date: String,
    val rating: Int,
    val reviewerEmail: String,
    val reviewerName: String
)
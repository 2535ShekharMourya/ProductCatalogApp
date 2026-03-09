package com.azad.productcatalogappairawatrf.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import com.azad.productcatalogappairawatrf.R
import com.azad.productcatalogappairawatrf.core.Resource
import com.azad.productcatalogappairawatrf.data.Repository
import com.azad.productcatalogappairawatrf.data.RepositoryImp
import com.azad.productcatalogappairawatrf.data.remotedata.RetrofitAPIClient
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.Product
import com.azad.productcatalogappairawatrf.databinding.ActivityProductDetailsBinding
import com.bumptech.glide.Glide
import kotlin.getValue

class ProductDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailsBinding
    // 1. Create a Repository instance
    private val repo: Repository by lazy {
        val remoteDataSource = RetrofitAPIClient.getInstance()
        RepositoryImp(remoteDataSource)
    }

    // 2. Create a Factory instance
    private val viewModelFactory by lazy {
        SharedViewmodelFactory(repo)
    }

    // 3. Use the by viewModels delegate with the Factory
    private val viewModel: SharedViewModel by viewModels { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val productId = intent.getIntExtra("product_id", -1)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
       // }

        viewModel.getProductById(productId)
        viewModel.productWithId.observe(this) { products ->
            handleCollectionTemplatesResource(products)
        }

    }
    fun handleCollectionTemplatesResource(resource: Resource<Product>) {
        when (resource) {
            is Resource.Loading -> {}
            is Resource.Success -> {
                binding.txtBrand.text = resource.data?.brand
                binding.txtCategory.text = resource.data?.category
                binding.txtDescription.text = resource.data?.description
                binding.txtPrice.text = resource.data?.price.toString()
                binding.txtTitle.text = resource.data?.title
                binding.txtRating.text = resource.data?.rating.toString()
                binding.txtStock.text = resource.data?.stock.toString()
                Glide.with(this)
                    .load(resource.data?.thumbnail)
                    .placeholder(R.drawable.favorite_selected)
                    .into(binding.productImage)
            }

            is Resource.Error<*> -> {}

        }
    }
}
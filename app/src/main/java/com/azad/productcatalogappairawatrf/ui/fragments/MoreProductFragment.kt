package com.azad.productcatalogappairawatrf.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.azad.productcatalogappairawatrf.R
import com.azad.productcatalogappairawatrf.core.Resource
import com.azad.productcatalogappairawatrf.data.Repository
import com.azad.productcatalogappairawatrf.data.RepositoryImp
import com.azad.productcatalogappairawatrf.data.remotedata.RetrofitAPIClient
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.AllProductsResponse
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.Product
import com.azad.productcatalogappairawatrf.databinding.FragmentMoreProductBinding
import com.azad.productcatalogappairawatrf.ui.ProductClickListener
import com.azad.productcatalogappairawatrf.ui.ProductDetailsActivity
import com.azad.productcatalogappairawatrf.ui.SharedViewModel
import com.azad.productcatalogappairawatrf.ui.SharedViewmodelFactory
import com.azad.productcatalogappairawatrf.ui.adapters.ProductsAdapter
import kotlin.getValue


class MoreProductFragment : Fragment(), ProductClickListener {
    lateinit var binding: FragmentMoreProductBinding
    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: ProductsAdapter
    private var category: String? = null

    private var isLoading = false
    private var limit = 4
    private var skip = 0
    private var total = 0
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
        arguments?.let {
            category = it.getString(TAB_DATA)
        }
        Log.d("azad", "MoreProductFragment: onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoreProductBinding.inflate(inflater, container, false)
        return binding.root
       // return inflater.inflate(R.layout.fragment_more_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("azad", "MoreProductFragment: onViewCreated")
        setUpeRecyclerView(view)
       loadProducts(category)
        viewModel.specificProducts.observe(viewLifecycleOwner) { products ->
            handleCollectionTemplatesResource(products)
        }
        viewModel.products.observe(viewLifecycleOwner) { products ->
            handleCollectionTemplatesResource(products)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && skip < total) {

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                    ) {

                        loadProducts(category)
                    }
                }
            }
        })

    }

    fun loadProducts(category: String?){
        if (category == "All") {
            if (isLoading) return

            isLoading = true
            viewModel.getAllProductsTab(limit, skip)

        } else {
            if (isLoading) return

            isLoading = true
            viewModel.getProductsCategory(category,limit, skip)
        }



    }

    private fun handleCollectionTemplatesResource(resource: Resource<AllProductsResponse>) {
        when (resource) {
            is Resource.Loading -> {
                // Handle loading state
//                binding.lottieAnimationViewTabs.visibility = View.VISIBLE
//                binding.lottieAnimationViewTabs.playAnimation()
            }

            is Resource.Success -> {
                // Handle success state
//                binding.lottieAnimationViewTabs.visibility = View.GONE
//                binding.lottieAnimationViewTabs.pauseAnimation()
                val data = resource.data

                if (data != null) {

                    total = data.total

                    adapter.addProducts(data.products)

                    skip += limit

                    isLoading = false
                }
//                resource.data.let { templates ->
//                    if (templates!=null){
//                        templates.products.let { adapter.addProducts(it) }
//                    }
//                }
            }

            is Resource.Error<*> -> {
                isLoading = false
                // Handle error state
//                binding.lottieAnimationViewTabs.visibility = View.VISIBLE
//                binding.lottieAnimationViewTabs.playAnimation()
                val errorMessage = resource.message ?: "Unknown error occurred"
                // Show error message to the user
            }
        }

    }

    fun setUpeRecyclerView(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.category_recycler_view)
        adapter = ProductsAdapter(requireContext(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

    }

    override fun onProductClick(product: Product) {
        val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
        intent.putExtra("product_id", product.id)

        startActivity(intent)

    }

    companion object {
        private const val TAB_DATA = "tab_data"

        fun newInstance(category: String): MoreProductFragment {
            val fragment = MoreProductFragment()
            val args = Bundle().apply {
                putString(TAB_DATA, category) // Data class needs to implement Parcelable
                // OR use putSerializable if Data implements Serializable
                // putSerializable(ARG_FEED_DATA, data)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
package com.azad.productcatalogappairawatrf.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
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
import com.azad.productcatalogappairawatrf.ui.SharedViewModel
import com.azad.productcatalogappairawatrf.ui.SharedViewmodelFactory
import com.azad.productcatalogappairawatrf.ui.adapters.ProductsAdapter
import kotlin.getValue


class MoreProductFragment : Fragment(), ProductClickListener {
    lateinit var binding: FragmentMoreProductBinding
    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: ProductsAdapter
    private var category: String? = null
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
    private val viewModel: SharedViewModel by activityViewModels { viewModelFactory }

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
        viewModel.getProductsCategory(category)
        viewModel.specificProducts.observe(viewLifecycleOwner) { products ->
            handleCollectionTemplatesResource(products)
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
                resource.data.let { templates ->
                    if (templates!=null){
                        templates.products.let { adapter.addProducts(it) }
                    }
                }
            }

            is Resource.Error<*> -> {
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
        TODO("Not yet implemented")
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
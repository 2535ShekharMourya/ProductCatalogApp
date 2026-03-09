package com.azad.productcatalogappairawatrf.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.azad.productcatalogappairawatrf.R
import com.azad.productcatalogappairawatrf.core.Resource
import com.azad.productcatalogappairawatrf.data.Repository
import com.azad.productcatalogappairawatrf.data.RepositoryImp
import com.azad.productcatalogappairawatrf.data.remotedata.RetrofitAPIClient
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.AllProductsResponse
import com.azad.productcatalogappairawatrf.databinding.FragmentHomeScreenBinding
import com.azad.productcatalogappairawatrf.ui.SharedViewModel
import com.azad.productcatalogappairawatrf.ui.SharedViewmodelFactory
import com.azad.productcatalogappairawatrf.ui.adapters.TabViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.collections.get
import kotlin.getValue

class HomeScreenFragment : Fragment() {
    lateinit var binding: FragmentHomeScreenBinding

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    lateinit var adapter: TabViewPagerAdapter

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
       // return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HomeScreenFragment", "onViewCreated")
        if (viewModel.products.value == null) {
            Log.d("HomeScreenFragment", "getAllProducts()")
            viewModel.getAllProducts()
        }
        viewModel.products.observe(viewLifecycleOwner) { collections ->
            Log.d("azad", "products.observe, HomeScreenFragment")
            handleCollectionsResource(collections)
        }
        tabLayout = view.findViewById(R.id.fragment_quizzes_tabs)
        viewPager = view.findViewById(R.id.view_pager)
    }

    private fun handleCollectionsResource(resource: Resource<AllProductsResponse>) {
        when (resource) {
            is Resource.Loading -> {
//                binding.lottieAnimationView.visibility = View.VISIBLE
//                binding.lottieAnimationView.playAnimation()
            }
            is Resource.Success -> {
//                binding.lottieAnimationView.visibility = View.GONE
//                binding.lottieAnimationView.pauseAnimation()
                resource.data?.let { collections ->
                    val products = collections.products

                    // Extract unique categories
                    val categories = products.map { it.category }.distinct().toMutableList()
                    Log.d("azad", "handleCollectionsResource: HomeScreenFragment: $categories")

//                    // Add "All" tab at first position
//                    categories.add(0, "All")




                    adapter = TabViewPagerAdapter(this, categories)
                    viewPager.adapter = adapter
                    TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                        val customView = LayoutInflater.from(tabLayout.context)
                            .inflate(R.layout.custom_tab, null)
                        val textView = customView.findViewById<TextView>(R.id.tabText)
                        textView.text =categories[position]
                        tab.customView = customView
                    }.attach()

                }

//                tabLayout.post {
//                    adjustTabMargins(tabLayout)
//                }
            }
            is Resource.Error -> {
//                binding.lottieAnimationView.visibility = View.GONE
//                binding.lottieAnimationView.pauseAnimation()
            }
        }
    }

    private fun adjustTabMargins(tabLayout: TabLayout) {
        val tabStrip = tabLayout.getChildAt(0) as ViewGroup
        tabStrip.setPadding(0, 0, 0, 0) // remove default padding

        for (i in 0 until tabStrip.childCount) {
            val tabView = tabStrip.getChildAt(i)

            // remove default internal padding
            tabView.setPadding(0, tabView.paddingTop, 0, tabView.paddingBottom)
            tabView.minimumWidth = 0

            // apply even margins
            val lp = tabView.layoutParams as ViewGroup.MarginLayoutParams
            val margin = (6 * tabLayout.resources.displayMetrics.density).toInt()
            lp.marginStart = margin
            lp.marginEnd = margin
            tabView.layoutParams = lp
        }
    }


//    fun switchToTab(collectionId: String) {
//        val index = adapter.collections.indexOfFirst { it.id.toString() == collectionId }
//
//        if (index != -1) {
//            binding.viewPager.currentItem = index
//           // "Switched to tab at index: $index for collectionId: $collectionId".logMessage()
//        } else {
//           // "CollectionId: $collectionId not found in tab list".logMessage()
//        }
////        binding.viewPager.currentItem = position
//       // "collectionId: $collectionId".logMessage()
//    }
}
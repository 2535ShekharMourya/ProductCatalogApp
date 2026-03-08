package com.azad.productcatalogappairawatrf.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azad.productcatalogappairawatrf.R
import com.azad.productcatalogappairawatrf.data.Repository
import com.azad.productcatalogappairawatrf.data.RepositoryImp
import com.azad.productcatalogappairawatrf.data.remotedata.RetrofitAPIClient
import com.azad.productcatalogappairawatrf.databinding.FragmentSearchProductsScreenBinding
import com.azad.productcatalogappairawatrf.ui.SharedViewModel
import com.azad.productcatalogappairawatrf.ui.SharedViewmodelFactory
import com.azad.productcatalogappairawatrf.ui.adapters.SearchProductAdapter
import kotlin.getValue

class SearchProductsScreenFragment : Fragment() {
    lateinit var binding: FragmentSearchProductsScreenBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: SearchProductAdapter

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchProductsScreenBinding.inflate(inflater, container, false)
        return binding.root
       // return inflater.inflate(R.layout.fragment_search_products_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerProducts)
        searchView = view.findViewById(R.id.searchView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllProducts()
        viewModel.products.observe(viewLifecycleOwner) { products ->
            adapter = SearchProductAdapter(products.data?.products)
            recyclerView.adapter = adapter
        }
        setupSearch()


    }
    private fun setupSearch() {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return true
            }
        })
    }

}
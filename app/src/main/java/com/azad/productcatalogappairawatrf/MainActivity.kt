package com.azad.productcatalogappairawatrf

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.azad.productcatalogappairawatrf.databinding.ActivityMainBinding
import com.azad.productcatalogappairawatrf.ui.BottomTabs
import com.azad.productcatalogappairawatrf.ui.fragments.FavoriteProductsScreenFragment
import com.azad.productcatalogappairawatrf.ui.fragments.HomeScreenFragment
import com.azad.productcatalogappairawatrf.ui.fragments.SearchProductsScreenFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initiallyShowHome()
        onBackPressedDispatcher.addCallback(this) {
            handleBackPress()
        }
        binding.tabHome.setOnClickListener {
           // binding.tabHome.animateClick()
            navigateToHome()
        }
        binding.tabSearch.setOnClickListener {
            navigateToSearch()
        }

        binding.tabFavorite.setOnClickListener {
            navigateToFavorite()
        }


    }
    private fun initiallyShowHome(){
        // Initially show home fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_container, HomeScreenFragment())
            .commit()
        setSelectedTab(BottomTabs.HOME)
    }
    private fun navigateToHome(){
        // supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_container, HomeScreenFragment())
            .commit()
        setSelectedTab(BottomTabs.HOME)
    }
    private fun navigateToSearch(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_container, SearchProductsScreenFragment())
            .commit()
        setSelectedTab(BottomTabs.SEARCH)
    }
    private fun navigateToFavorite(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_container, FavoriteProductsScreenFragment())
            .addToBackStack(null)
            .commit()
        setSelectedTab(BottomTabs.FAVORITE)
    }

    private fun setSelectedTab(tab: BottomTabs) {
        when (tab) {
            BottomTabs.HOME -> {
                // Handle Home tab
                binding.iconHome.setImageResource(R.drawable.home_selected)
                binding.iconSearch.setImageResource(R.drawable.search_unselected)
                binding.iconFavorite.setImageResource(R.drawable.favorite_unselected)
                binding.labelHome.setTextColor(ContextCompat.getColor(this, R.color.bottom_tab_text_selected))
                binding.labelSearch.setTextColor(ContextCompat.getColor(this, R.color.bottom_tab_text_unselected))
                binding.labelFavorite.setTextColor(ContextCompat.getColor(this, R.color.bottom_tab_text_unselected))
            }

            BottomTabs.SEARCH -> {
                // Handle Search tab
                binding.iconHome.setImageResource(R.drawable.home_unselected)
                binding.iconSearch.setImageResource(R.drawable.search_selected)
                binding.iconFavorite.setImageResource(R.drawable.favorite_unselected)
                binding.labelHome.setTextColor(ContextCompat.getColor(this, R.color.bottom_tab_text_unselected))
                binding.labelSearch.setTextColor(ContextCompat.getColor(this, R.color.bottom_tab_text_selected))
                binding.labelFavorite.setTextColor(ContextCompat.getColor(this, R.color.bottom_tab_text_unselected))
            }

            BottomTabs.FAVORITE -> {
                // Handle Favorite tab
                binding.iconHome.setImageResource(R.drawable.home_unselected)
                binding.iconSearch.setImageResource(R.drawable.search_unselected)
                binding.iconFavorite.setImageResource(R.drawable.favorite_selected)
                binding.labelHome.setTextColor(ContextCompat.getColor(this, R.color.bottom_tab_text_unselected))
                binding.labelSearch.setTextColor(ContextCompat.getColor(this, R.color.bottom_tab_text_unselected))
                binding.labelFavorite.setTextColor(ContextCompat.getColor(this, R.color.bottom_tab_text_selected))
            }
        }
//        if (tab == BottomTabs.HOME) {
//
//
//        } else {
//
//        }
    }
    private fun handleBackPress() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
            setSelectedTab(BottomTabs.HOME)  // Make sure to highlight home tab
        } else {
            finish() // or super.onBackPressed() if using older Android
        }
    }
}
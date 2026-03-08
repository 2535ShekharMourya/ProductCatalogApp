package com.azad.productcatalogappairawatrf.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azad.productcatalogappairawatrf.data.Repository

class SharedViewmodelFactory(
    private val repo: Repository // Takes the dependency
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Checks if the requested class is MinisHomeViewModel
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            // Returns a new instance, injecting the dependency
            return SharedViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
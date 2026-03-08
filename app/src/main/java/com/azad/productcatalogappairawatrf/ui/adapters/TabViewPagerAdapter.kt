package com.azad.productcatalogappairawatrf.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azad.productcatalogappairawatrf.data.remotedata.remotedatamodel.Product
import com.azad.productcatalogappairawatrf.ui.fragments.HomeScreenFragment
import com.azad.productcatalogappairawatrf.ui.fragments.MoreProductFragment

class TabViewPagerAdapter(
    fragment: Fragment,
    private val categories: List<String>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = categories.size

    override fun createFragment(position: Int): Fragment {
            return MoreProductFragment.newInstance(categories[position])

    }
}
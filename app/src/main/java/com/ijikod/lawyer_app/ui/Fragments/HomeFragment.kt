package com.ijikod.lawyer_app.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.ijikod.lawyer_app.R
import com.ijikod.lawyer_app.Utilities.FRAGMENT_POSITION
import com.ijikod.lawyer_app.ui.LawyersListViewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    lateinit var pagerAdapter: PagerAdapter


    lateinit var viewModel : LawyersListViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LawyersListViewModel::class.java)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.pager)

        tabLayout.setupWithViewPager(viewPager)




        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var titles = HashMap<Int, String>()


        viewModel.numberOfFeaturedLawyers.observe(requireActivity(), Observer {
            val featureTitle = getString(R.string.featured_title, it)
            titles.put(0, featureTitle)
            pagerAdapter.notifyDataSetChanged()

        })


        viewModel.lawyerData.observe(requireActivity(), Observer {
            val allTitle = getString(R.string.all_title, it.size)
            titles.put(1, allTitle)
            pagerAdapter.notifyDataSetChanged()

        })

        viewModel.numberOfFavLawyers.observe(requireActivity(), Observer {
            val favTitle = getString(R.string.fav_title, it)
            titles.put(2, favTitle)
            pagerAdapter.notifyDataSetChanged()

        })


        pagerAdapter = PagerAdapter(childFragmentManager, titles = titles)


        viewPager.adapter = pagerAdapter
    }


    inner class PagerAdapter(fragmentManager: FragmentManager, val titles : HashMap<Int, String>) : FragmentStatePagerAdapter(fragmentManager){


        override fun getItem(position: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt(FRAGMENT_POSITION, position)
            val fragment  = FeaturedFragment();
            fragment.arguments = bundle
            return fragment
        }

        override fun getCount() = titles.size

        override fun getPageTitle(position: Int): CharSequence? {
            return "${titles[position]}"
        }



    }

}

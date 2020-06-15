package com.ijikod.lawyer_app.ui.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ijikod.lawyer_app.Data.Model.Lawyer
import com.ijikod.lawyer_app.DetailsActivity
import com.ijikod.lawyer_app.R
import com.ijikod.lawyer_app.Utilities.FRAGMENT_POSITION
import com.ijikod.lawyer_app.Utilities.HeaderDecoration
import com.ijikod.lawyer_app.Utilities.LAWYER
import com.ijikod.lawyer_app.ui.Adapter
import com.ijikod.lawyer_app.ui.LawyersListViewModel


/**
 * A simple [Fragment] subclass.
 */
class FeaturedFragment : Fragment() {

    lateinit var featuredRecycler: RecyclerView
    lateinit var viewModel: LawyersListViewModel
    lateinit var adapter : Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LawyersListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  =  inflater.inflate(R.layout.fragment_featured, container, false)

        featuredRecycler = view.findViewById(R.id.featured_list)
        val header = LayoutInflater.from(requireContext()).inflate(R.layout.list_header, null)
        val headerDecoration = HeaderDecoration(header, false
            , 0.2f, 0f, 1)

        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        featuredRecycler.addItemDecoration(headerDecoration)
        featuredRecycler.layoutManager = layoutManager
        val position = arguments?.getInt(FRAGMENT_POSITION)

        val listener = object : Adapter.LawyerClick{
            override fun onLawyerClick(lawyer: Lawyer) {
                val bundle = Bundle()
                bundle.putParcelable(LAWYER, lawyer)

                val detailsIntent = Intent(requireActivity(), DetailsActivity::class.java)
                detailsIntent.putExtra(LAWYER, lawyer)

                requireActivity().startActivity(detailsIntent)
            }

        }

        if (position == 0){
            viewModel.featuredLawyers.observe(requireActivity(), Observer {
                adapter = Adapter(it, listener)
                featuredRecycler.adapter = adapter

            })
        }else if(position == 1){
            viewModel.lawyerData.observe(requireActivity(), Observer {
                adapter = Adapter(it, listener)
                featuredRecycler.adapter = adapter
            })
        }else if (position == 2){
            viewModel.favLawyerData.observe(requireActivity(), Observer {
                adapter = Adapter(it, listener)
                featuredRecycler.adapter = adapter
            })
        }


        return view
    }

}

package com.example.officefinder.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.officefinder.adapter.OfficeListAdapter
import com.example.officefinder.databinding.FragmentExploreBinding
import kotlinx.android.synthetic.main.fragment_explore.*


class ExploreFragment : Fragment() {

    private lateinit var exploreViewModel: ExploreViewModel
    private var _binding: FragmentExploreBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var recyclerAdapter: OfficeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreViewModel =
            ViewModelProvider(this).get(ExploreViewModel::class.java)

        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textExplore
        exploreViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        officeListRecyclerView.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = OfficeListAdapter()
        officeListRecyclerView.adapter = recyclerAdapter
    }

    private fun initViewModel() {

        exploreViewModel.getLiveOfficeObserver().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                recyclerAdapter.setOfficeList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "Could not get office information", Toast.LENGTH_SHORT).show()
            }
        })

        exploreViewModel.makeOfficeApiCall()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
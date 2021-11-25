package com.router.uwsmap

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.router.uwsmap.model.Item
import kotlinx.android.synthetic.main.fragment_uws_list.*

class UwsListFragment : Fragment() {


    private val viewModel : MainViewmodel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutMangerWrapper = LinearLayoutManager(context,
            RecyclerView.VERTICAL,
            false
        )

        viewModel.itemListLiveData.observe(viewLifecycleOwner,{
            val adapter = UWSRecyclerViewAdapter(it)
            uws_rv.layoutManager = linearLayoutMangerWrapper
            uws_rv.adapter = adapter
            adapter.itemClick = object : UWSRecyclerViewAdapter.ItemClick{
                override fun onClick(view: View, position: Int, item: Item) {
                    findNavController().navigate(UwsListFragmentDirections.actionUwsListFragmentToKakaoMapFragment(item))
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_uws_list, container, false)
    }

}
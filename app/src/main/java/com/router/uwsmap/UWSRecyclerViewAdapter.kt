package com.router.uwsmap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.router.uwsmap.databinding.UwsItemBinding
import com.router.uwsmap.model.Item
import com.router.uwsmap.model.ItemList

class UWSRecyclerViewAdapter(private val dataSet: ItemList) :
    RecyclerView.Adapter<UWSRecyclerViewAdapter.ViewHolder>() {

    interface ItemClick{
        fun onClick(view : View, position: Int, item : Item)
    }
    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding = UwsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val holderModel = dataSet.data.get(position)
        holder.bind(holderModel)

        if(itemClick != null) {
            holder.viewDataBinding.uwsItemCl.setOnClickListener {
                itemClick?.onClick(it, position, dataSet.data.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.data.size
    }

    inner class ViewHolder(val viewDataBinding: UwsItemBinding): RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(holderModel: Item) {
            viewDataBinding.holderModel = holderModel
        }
    }

}
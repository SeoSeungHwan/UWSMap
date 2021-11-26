package com.router.uwsmap

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.router.uwsmap.model.Item
import com.router.uwsmap.model.ItemList

class UWSRecyclerViewAdapter(private val dataSet: ItemList) :
    RecyclerView.Adapter<UWSRecyclerViewAdapter.ViewHolder>() {

    lateinit var context : Context

    interface ItemClick{
        fun onClick(view : View, position: Int, item : Item)
    }
    var itemClick : ItemClick? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val uws_location_tv: TextView
        val uws_phone_tv: TextView
        val uws_direction_tv: TextView
        val uws_km_tv: TextView
        val uws_count_tv: TextView
        val uws_item_cl : ConstraintLayout

        init {
            uws_location_tv = view.findViewById(R.id.uws_location_tv)
            uws_phone_tv = view.findViewById(R.id.uws_phone_tv)
            uws_direction_tv = view.findViewById(R.id.uws_direction_tv)
            uws_km_tv = view.findViewById(R.id.uws_km_tv)
            uws_count_tv = view.findViewById(R.id.uws_count_tv)
            uws_item_cl = view.findViewById(R.id.uws_item_cl)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.uws_item, viewGroup, false)

        context = view.context
        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.uws_location_tv.text = dataSet.data.get(position).addr
        viewHolder.uws_phone_tv.text = dataSet.data.get(position).tel
        viewHolder.uws_direction_tv.text = dataSet.data.get(position).name
        viewHolder.uws_km_tv.text = String.format("%.2f",dataSet.data.get(position).distance) + "km"
        if(dataSet.data.get(position).inventory.equals("0")){
            viewHolder.uws_count_tv.text = "재고없음"
            viewHolder.uws_count_tv.setTextColor(Color.RED)
        }else{
            viewHolder.uws_count_tv.text = dataSet.data.get(position).inventory+"개"
            viewHolder.uws_count_tv.setTextColor(Color.BLACK)

        }

        if(itemClick != null){
            viewHolder.uws_item_cl.setOnClickListener {
                itemClick?.onClick(it,position,dataSet.data.get(position))
            }
        }


    }
    override fun getItemCount() = dataSet.data.size



}
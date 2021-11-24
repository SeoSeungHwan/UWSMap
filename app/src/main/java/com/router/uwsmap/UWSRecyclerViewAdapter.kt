package com.router.uwsmap

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.router.uwsmap.model.ItemList

class UWSRecyclerViewAdapter(private val dataSet: ItemList) :
    RecyclerView.Adapter<UWSRecyclerViewAdapter.ViewHolder>() {

    lateinit var context : Context

    interface ItemClick{
        fun onClick(view : View, position: Int, board_id : String)
    }
    var itemClick : ItemClick? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val uws_location_tv: TextView
        val uws_phone_tv: TextView
        val uws_time_tv: TextView
        val uws_km_tv: TextView
        val uws_count_tv: TextView

        init {
            uws_location_tv = view.findViewById(R.id.uws_location_tv)
            uws_phone_tv = view.findViewById(R.id.uws_phone_tv)
            uws_time_tv = view.findViewById(R.id.uws_time_tv)
            uws_km_tv = view.findViewById(R.id.uws_km_tv)
            uws_count_tv = view.findViewById(R.id.uws_count_tv)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.uws_item, viewGroup, false)

        context = view.context
        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.uws_location_tv.text = dataSet.data.get(position).주소
        viewHolder.uws_phone_tv.text = dataSet.data.get(position).전화번호
        viewHolder.uws_time_tv.text = dataSet.data.get(position).영업시간
        viewHolder.uws_km_tv.text = "1km"
        if(dataSet.data.get(position).재고량.equals("0")){
            viewHolder.uws_count_tv.text = "재고없음"
        }else{
            viewHolder.uws_count_tv.text = dataSet.data.get(position).재고량+"개"
        }


    }
    override fun getItemCount() = dataSet.data.size



}
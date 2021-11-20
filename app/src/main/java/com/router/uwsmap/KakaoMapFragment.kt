package com.router.uwsmap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.daum.mf.map.api.MapView


class KakaoMapFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_kakao_map, container, false)

        val mapView = MapView(activity)
        val mapViewContainer = view.findViewById(R.id.map_view) as ViewGroup
        mapViewContainer.addView(mapView)

        return view
    }

}
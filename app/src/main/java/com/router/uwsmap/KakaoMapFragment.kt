package com.router.uwsmap

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class KakaoMapFragment : Fragment() {

    private val viewModel : MainViewmodel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args : KakaoMapFragmentArgs by navArgs()

        //MapView 초기화
        val mapView = MapView(activity)
        val mapViewContainer = view.findViewById(R.id.map_view) as ViewGroup
        mapViewContainer.addView(mapView)

        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(args.itemId.위도.toDouble(),args.itemId.경도.toDouble()),5,true)
        viewModel.itemListLiveData.observe(viewLifecycleOwner, {
            for (item in it.data) {
                val mapPoint = MapPoint.mapPointWithGeoCoord(item.위도.toDouble(), item.경도.toDouble())

                val marker = MapPOIItem()
                marker.itemName = item.명칭 +" : "+ item.재고량+"개"
                marker.tag = 0
                marker.mapPoint = mapPoint

                if (item.재고량.equals("0")) {
                    marker.markerType = MapPOIItem.MarkerType.RedPin
                } else {
                    marker.markerType = MapPOIItem.MarkerType.BluePin
                }
                mapView.addPOIItem(marker)
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_kakao_map, container, false)
    }

}
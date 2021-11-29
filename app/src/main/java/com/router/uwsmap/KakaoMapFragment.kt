package com.router.uwsmap


import android.os.Bundle
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
        mapView.setCalloutBalloonAdapter(CustomBalloonAdapter(layoutInflater))
        mapView.setPOIItemEventListener(BalloonEventListener(context))
        mapViewContainer.addView(mapView)

        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(args.itemId.lat.toDouble(),args.itemId.lng.toDouble()),5,true)
        viewModel.itemListLiveData.observe(viewLifecycleOwner, {
            for (item in it.data) {
                val mapPoint = MapPoint.mapPointWithGeoCoord(item.lat.toDouble(), item.lng.toDouble())

                val marker = MapPOIItem()
                marker.itemName = item.name +" : "+item.inventory+"개"
                marker.tag = 0
                marker.mapPoint = mapPoint
                marker.markerType = MapPOIItem.MarkerType.CustomImage

                val inventory = item.inventory.toInt()
                if (inventory ==0) {
                    marker.customImageResourceId = R.drawable.zero
                } else if(inventory in 1..99){
                    marker.customImageResourceId = R.drawable.markerunder
                } else if(inventory in 100..499){
                    marker.customImageResourceId = R.drawable.marker500
                }else{
                    marker.customImageResourceId = R.drawable.marker1000
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
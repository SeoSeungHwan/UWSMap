package com.router.uwsmap



import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.util.jar.Manifest


class KakaoMapFragment : Fragment() {

    private val viewModel : MainViewmodel by activityViewModels()

    private var mLocationManager: LocationManager? = null
    private var mLocationListener: LocationListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //MapView 초기화
        val mapView = MapView(activity)
        val mapViewContainer = view.findViewById(R.id.map_view) as ViewGroup
        mapViewContainer.addView(mapView)

        //현재위치
        mLocationManager = context?.getSystemService(LOCATION_SERVICE) as LocationManager
        mLocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                var lat = 0.0
                var lng = 0.0
                if (location != null) {
                    lat = location.latitude
                    lng = location.longitude
                    Log.d("GmapViewFragment", "Lat: ${lat}, lon: ${lng}")
                }
            }
        }

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


                Log.d(TAG, "onViewCreated: ${item.명칭}")
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
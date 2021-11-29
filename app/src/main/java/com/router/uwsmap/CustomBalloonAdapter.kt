package com.router.uwsmap

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


// 커스텀 말풍선 클래스
class CustomBalloonAdapter(inflater: LayoutInflater) : CalloutBalloonAdapter {
    val mCalloutBalloon: View = inflater.inflate(R.layout.kakaomap_custom_layout, null)
    val balloon_location_tv: TextView = mCalloutBalloon.findViewById(R.id.balloon_addr_tv)
    override fun getCalloutBalloon(poiItem: MapPOIItem?): View {
        // 마커 클릭 시 나오는 말풍선
        balloon_location_tv.text = poiItem?.itemName
        return mCalloutBalloon
    }

    override fun getPressedCalloutBalloon(poiItem: MapPOIItem?): View {

        return mCalloutBalloon
    }
}

// 마커 클릭 이벤트 리스너
class BalloonEventListener(context: Context?) : MapView.POIItemEventListener {
    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
        Log.d(TAG, "onPOIItemSelected: 1")
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
        Log.d(TAG, "onPOIItemSelected: 2")
    }

    override fun onCalloutBalloonOfPOIItemTouched(
        mapView: MapView?,
        poiItem: MapPOIItem?,
        buttonType: MapPOIItem.CalloutBalloonButtonType?
    ) {
        Log.d(TAG, "onPOIItemSelected: 3")
        //todo 말풍선 클릭 시 길찾기
        /*
        Log.d(TAG, "onCalloutBalloonOfPOIItemTouched: ")
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                    "navermaps://?menu=location&pinType=place&" +
                            "lat=${poiItem?.mapPoint?.mapPointGeoCoord?.latitude}&" +
                            "lng=${poiItem?.mapPoint?.mapPointGeoCoord?.longitude}&" +
                            "title=${poiItem?.itemName}"
                )
            ).apply {
                `package` = "com.nhn.android.nmap"
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context?.startActivity(intent)
            */

    }
    override fun onDraggablePOIItemMoved(
        mapView: MapView?,
        poiItem: MapPOIItem?,
        mapPoint: MapPoint?
    ) {
    }
}
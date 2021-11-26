package com.router.uwsmap

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
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
    val balloon_location_tv: TextView = mCalloutBalloon.findViewById(R.id.balloon_location_tv)
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
class BalloonEventListener(val context: Context?) : MapView.POIItemEventListener {
    override fun onPOIItemSelected(mapView: MapView?, poiItem: MapPOIItem?) {
        // 마커 클릭 시
    }

    override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?) {
        // 말풍선 클릭 시 (Deprecated)
        // 이 함수도 작동하지만 그냥 아래 있는 함수에 작성하자
        Log.d(TAG, "onCalloutBalloonOfPOIItemTouched: ")
    }

    override fun onCalloutBalloonOfPOIItemTouched(
        mapView: MapView?,
        poiItem: MapPOIItem?,
        buttonType: MapPOIItem.CalloutBalloonButtonType?
    ) {
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
package com.router.uwsmap

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.tabs.TabLayout
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewmodel by viewModels()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //PermissionListener 구현
        var permissionlistener: PermissionListener = object : PermissionListener {

            //권한이 성공적으로 허용되었을 때
            @SuppressLint("MissingPermission")
            override fun onPermissionGranted() {

                //현재위치 가져오기
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@MainActivity)
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        if (location != null) {

                            Toast.makeText(this@MainActivity,"항목 클릭시 지도로 이동합니다.",Toast.LENGTH_SHORT).show()

                            //요소수 정보 가져오기
                            viewModel.fetchUWSList(
                                0,
                                1000,
                                "TPjqY3dBCSVQ6T0f%2BBo7WsczzD%2FAy7pmHDdcXDJwRpeE8P4LVp%2Bxq8g8IaQcOLYGSkMWPi4ofPfwEuctz4DRGA%3D%3D",
                                location
                            )
                            //최종 업데이트 시간 가져오기
                            viewModel.itemListLiveData.observe(this@MainActivity,{
                                update_time_tv.text = "최종 업데이트 : ${it.data.get(0).regDt}"
                            })
                        }
                        else{
                            Toast.makeText(this@MainActivity,"현재위치를 가져오지 못했습니다.",Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this@MainActivity,"현재위치를 가져오지 못했습니다.",Toast.LENGTH_SHORT).show()
                    }
            }

            //권한 부여가 허용되지 않았을 때
            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(this@MainActivity, "권한을 허용하지 않아 진행이 불가합니다.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        viewModel.loadingLivedata.observe(this@MainActivity,{
            if (it) {
                progressBar.bringToFront()
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        //TedPermission 객체 생성
        TedPermission.with(this)
            .setPermissionListener(permissionlistener)
            .setRationaleMessage("앱의 기능을 사용하기 위해서는 권한이 필요합니다.")
            .setDeniedMessage("[설정] > [권한] 에서 권한을 허용할 수 있습니다.")
            .setPermissions(
                android.Manifest.permission.INTERNET,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .check()
    }


}
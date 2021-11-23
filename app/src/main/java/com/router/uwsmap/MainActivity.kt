package com.router.uwsmap

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.router.uwsmap.KakaoMapFragment.onClickTabEvent
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    private val viewModel: MainViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //요소수 정보 가져오기
        viewModel.fetchUWSList(0,1000,"TPjqY3dBCSVQ6T0f%2BBo7WsczzD%2FAy7pmHDdcXDJwRpeE8P4LVp%2Bxq8g8IaQcOLYGSkMWPi4ofPfwEuctz4DRGA%3D%3D")
        viewModel.itemListLiveData.observe(this,{
            Log.d(TAG, "onCreate: ${it.currentCount}")

        })
        
        //todo 프레그먼트 전환 구현하기
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){

                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }
}
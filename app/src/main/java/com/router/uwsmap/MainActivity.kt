package com.router.uwsmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.router.MainViewmodel



class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //요소수 정보 가져오기
        viewModel.fetchUWSList(0,1000,"TPjqY3dBCSVQ6T0f%2BBo7WsczzD%2FAy7pmHDdcXDJwRpeE8P4LVp%2Bxq8g8IaQcOLYGSkMWPi4ofPfwEuctz4DRGA%3D%3D")
    }
}
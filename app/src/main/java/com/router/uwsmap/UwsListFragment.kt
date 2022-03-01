package com.router.uwsmap

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.router.uwsmap.model.Item
import kotlinx.android.synthetic.main.fragment_uws_list.*
import java.lang.Exception

class UwsListFragment : Fragment() {

    private val viewModel : MainViewmodel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutMangerWrapper = LinearLayoutManager(context,
            RecyclerView.VERTICAL,
            false
        )

        viewModel.itemListLiveData.observe(viewLifecycleOwner,{
            val adapter = UWSRecyclerViewAdapter(it)
            uws_rv.layoutManager = linearLayoutMangerWrapper
            uws_rv.adapter = adapter
            adapter.itemClick = object : UWSRecyclerViewAdapter.ItemClick{
                override fun onClick(view: View, position: Int, item: Item) {
                    val menu = arrayOf<String>("지도보기", "전화걸기","경로안내")
                    val builder = AlertDialog.Builder(activity)
                    builder.setTitle(item.name)
                    builder.setItems(menu,DialogInterface.OnClickListener { dialogInterface, i ->
                        Log.d(TAG, "onClick: ${i}")
                        when(i){
                            //카카오맵으로 위치 확인
                            0 -> findNavController().navigate(UwsListFragmentDirections.actionUwsListFragmentToKakaoMapFragment(item))
                            //전화 걸기
                            1 -> {
                                var intent = Intent(Intent.ACTION_DIAL)
                                intent.data = Uri.parse("tel:${item.tel}")
                                startActivity(intent)
                            }
                            //카카오지도앱을 이용하여 길찾기 실행
                            else ->{
                                try {
                                    val url = "kakaomap://route?" +
                                            "sp=${viewModel.location.latitude},${viewModel.location.longitude}&" +
                                            "ep=${item.lat},${item.lng}&by=CAR"
                                    var intent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
                                    startActivity(intent)
                                }
                                //카카오맵이 설치안되어있을 경우
                                catch (e : Exception){
                                    Toast.makeText(context,"카카오맵 앱을 설치해야합니다.", Toast.LENGTH_SHORT).show()
                                    val url = "https://play.google.com/store/apps/details?id=net.daum.android.map"
                                    var intent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
                                    startActivity(intent)
                                }
                            }
                        }
                    })
                    builder.show()
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_uws_list, container, false)
    }
}
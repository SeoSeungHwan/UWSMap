package com.router.uwsmap

import android.app.Application
import android.graphics.Color
import android.graphics.Typeface
import android.widget.TextView
import androidx.databinding.BindingAdapter

class GlobalApplication : Application() {

    companion object{
        @BindingAdapter("inventory_change_color")
        @JvmStatic
        fun setTextBold(textView: TextView, inventory: String) {
            if (inventory.equals("0")) {
                textView.setTextColor(Color.RED)
            } else {
                textView.setTextColor(Color.BLACK)
            }
        }
    }
}
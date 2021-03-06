package com.lcworld.module_exchange

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil{
    fun getMonthBegin(date: Date):String{
        val c = Calendar.getInstance()
        c.time = date
        c.set(Calendar.DAY_OF_MONTH, 1)
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND,0)
        c.set(Calendar.MILLISECOND, 0)
        return c.timeInMillis.div(1000).toString()
    }

    fun getMonthEnd(date: Date):String{
        val c = Calendar.getInstance()
        c.time = date
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH))
        c.set(Calendar.HOUR_OF_DAY, 23)
        c.set(Calendar.MINUTE, 59)
        c.set(Calendar.SECOND,59)
        c.set(Calendar.MILLISECOND, 999)
        return c.timeInMillis.div(1000).toString()
    }

     @SuppressLint("SimpleDateFormat")
     fun formatData(date: Date, pattern: String = "yyyy年MM月"): String {
         val format = SimpleDateFormat(pattern)
        return format.format(date)
    }

    fun formatAmount(d: Double, pattern: String = "#0.00"): String {
        val format = DecimalFormat(pattern)
        return format.format(d)
    }
}
package com.lcworld.module_backstage.activity

import android.databinding.Observable
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.library_base.router.RouterActivityPath
import com.lcworld.module_backstage.BR
import com.lcworld.module_backstage.R
import com.lcworld.module_backstage.databinding.BackAcitivityEntranceBinding
import com.lcworld.module_backstage.model.FansCharItem
import com.lcworld.module_backstage.viewmodel.BackEntranceViewModel

/**
 * 后台
 */
@Route(path = RouterActivityPath.Backstage.PAGER_ENTRANCE)
class BackstageEntranceActivity : BaseActivityEnhance<BackAcitivityEntranceBinding, BackEntranceViewModel>() {
    override fun initContentView(bundle: Bundle?): Int = R.layout.back_acitivity_entrance

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.back_my_backstage)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        binding.layoutTitle.tvRight.setOnClickListener { startActivity(FanSearchActivity::class.java) }
        initCharLine()
        viewModel.loadData()

    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.getfansCharData.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                setLineCharData(viewModel.fansCharList)
            }

        })
    }

    //初始化图表
    private fun initCharLine() {
        binding.lineChar.apply {
            setNoDataText("暂无数据")
            setDrawGridBackground(false)//是否展示网格线
            setScaleEnabled(false)
            setDrawBorders(false)//是否显示边界
            isDragEnabled = false//是否可以拖动
            setTouchEnabled(false) //是否有触摸事件
            animateX(1000)//设置XY轴动画效果
            legend.isEnabled = false //不显示底部legend
            description = Description().apply { text = "" }//不显示description
            xAxis.apply {
                axisMinimum = 0f
                position = XAxis.XAxisPosition.BOTTOM
                gridColor = ContextCompat.getColor(context, R.color.color_e6e6e6)
                textColor = ContextCompat.getColor(context, R.color.tx_colorc)
                axisLineColor = ContextCompat.getColor(context, R.color.color_fc501c)
                textSize = 12f
                typeface = Typeface.DEFAULT_BOLD
            }
            axisLeft.apply {
                gridColor = ContextCompat.getColor(context, R.color.color_e6e6e6)
                textColor = ContextCompat.getColor(context, R.color.tx_colorc)
                axisLineColor = ContextCompat.getColor(context, R.color.color_e6e6e6)
                axisMinimum = 0f
                textSize = 12f
            }
            axisRight.isEnabled = false
        }
    }

    private fun setLineCharData(fansCharList: List<FansCharItem>) {
        if (fansCharList.isEmpty()) return
        fansCharList.map { Entry(it.time_key.toFloat(), it.count.toFloat()) }
        val lineDataSet = LineDataSet(listOf(), "")
        lineDataSet.setCircleColor(ContextCompat.getColor(this, R.color.color_fc501c))
        lineDataSet.circleRadius = 4.5f
        lineDataSet.setDrawCircleHole(false)
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER//设置为曲线
        lineDataSet.color = ContextCompat.getColor(this, R.color.color_fc501c)//设置线条颜色
        lineDataSet.lineWidth = 2f//设置线条宽带
        lineDataSet.setDrawFilled(true)
        lineDataSet.setDrawValues(false)
        lineDataSet.fillDrawable = ContextCompat.getDrawable(this, R.drawable.back_shape1)
        val lineData = LineData(lineDataSet)
        binding.lineChar.xAxis.apply {
            setLabelCount(fansCharList.size, false)
        }
        binding.lineChar.axisLeft.apply {
            setLabelCount(fansCharList.size, false)
        }
        binding.lineChar.data = lineData
        binding.lineChar.invalidate()
    }
}

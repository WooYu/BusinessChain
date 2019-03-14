package com.lcworld.module_backstage.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * 后台首页
 */
data class BackIndexEntity(
    val day_list: List<FansCharItem> = listOf(), val direct_count: String = "",
    val in_direct_count: String = "", val total_count: String = "",
    val members: List<FansItem> = listOf()
)

data class FansCharItem(val time_key: String = "", val count: String = "")
data class FansListEntity(val list: List<FansItem> = listOf(), val page_no: Int = 1, val page_size: String = "")
@Parcelize
data class FansItem(
    val address: String = "",
    val birthday: String = "",
    val city: String = "",
    val city_id: String = "",
    val consum_point: String = "",
    val county: String = "",
    val county_id: String = "",
    val create_time: String = "",
    val disabled: String = "",
    val email: String = "",
    val face: String = "",
    val find_code: String = "",
    val grade_point: String = "",
    val have_shop: String = "",
    val indirect_recommend_id: String = "",
    val info_full: String = "",
    val invitation_code: String = "",
    val is_cheked: String = "",
    val last_login: String = "",
    val login_count: String = "",
    val midentity: String = "",
    val mobile: String = "",
    val msn: String = "",
    val nickname: String = "",
    val province: String = "",
    val province_id: String = "",
    val recommend_code: String = "",
    val recommend_id: String = "",
    val recommend_point_state: String = "",
    val remark: String = "",
    val sex: String = "",
    val shop_id: String = "",
    val tel: String = "",
    val town: String = "",
    val town_id: String = "",
    val type: String = "",
    val uname: String = ""
) : Parcelable


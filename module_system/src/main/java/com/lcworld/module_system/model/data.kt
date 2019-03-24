package com.lcworld.module_backstage.model


data class QuestionItem(val id: String = "", val title: String = "", val problems: List<Problem> = listOf())
data class Problem(val problem: String = "", val content: String = "")
data class VipMsgEntity(val point: String = "", val name: String = "",val level: String = "", val content1: String = "",
                        val content2: String = "", val introduce: String = "",val num: String = "", val invitation_code: String = "",
                        val vip_list: List<VipType> = listOf())
data class VipType(val id: String = "", val name: String = "",val point: String = "", val power: String = "",
                   val level: String = "",val create_time: String = "")

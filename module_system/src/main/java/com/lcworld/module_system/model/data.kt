package com.lcworld.module_backstage.model


data class QuestionItem(val id: String = "", val title: String = "", val problems: List<Problem> = listOf())
data class Problem(val problem: String = "", val content: String = "")

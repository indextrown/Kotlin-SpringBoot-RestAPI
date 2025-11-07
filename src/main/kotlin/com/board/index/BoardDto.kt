package com.board.index

// 객체복제, 주소값비교, 객체값 비교 편리
data class BoardDto(
    val id: Long? = null,
    val title: String = "",
    val content: String = "",
    val name: String = "",
)
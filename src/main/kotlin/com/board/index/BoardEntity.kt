package com.board.index

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalTime

// option + enter로 자동 import 가능
@Entity
class BoardEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 고유값 1씩 증가
    val id: Long? = null,
    var title: String,
    var content: String,
    val name: String,
    val createAt: LocalTime = LocalTime.now()
) {
    fun update(boardDto: BoardDto) {
        this.title = boardDto.title
        this.content = boardDto.content
    }
}

/*
id: 자동생성된다(고유값)
title: 제목
content: 내용
name: 작성자
생성일, 수정일
 */
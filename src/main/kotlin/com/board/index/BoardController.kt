package com.board.index

import com.board.util.Logger
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/board")
// localhost:8080/board
class BoardController(
    private val boardService: BoardService
) {
    @PostMapping
    fun create(@RequestBody boardDto: BoardDto) {
        boardService.create(boardDto)
        Logger.d("디버그 로그 테스트")
        Logger.w("경고 로그 테스트")
        Logger.e("에러 로그 테스트")
    }
}
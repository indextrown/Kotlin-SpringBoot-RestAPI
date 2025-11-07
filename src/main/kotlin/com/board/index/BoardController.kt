package com.board.index

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
    }
}
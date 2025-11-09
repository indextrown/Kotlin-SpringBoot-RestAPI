package com.board.index

import com.board.util.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

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

    @GetMapping
    fun getAll(): List<BoardDto> {
        return boardService.getAll()
    }

    // 요청 파라미터 방식(PathVariable 방식)
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): BoardDto {
        return boardService.getById(id)
    }

    @GetMapping("/{id}/nullable")
    fun getByIdOrNull(@PathVariable id: Long): BoardDto {
        return boardService.getByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.")
    }

    // body 방식
    @PatchMapping
    fun update(@RequestBody boardDto: BoardDto) {
        boardService.update(boardDto)
    }
}
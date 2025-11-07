package com.board.index

import org.springframework.stereotype.Service

@Service
class BoardService (
    // 의존성 주입을 통해 boardRepository를 BoardService에서 사용하겠다
    private val boardRepository: BoardRepository
) {
    fun create(boardDto: BoardDto) {
        val boardEntity = BoardEntity(
            title = boardDto.title,
            content = boardDto.content,
            name = boardDto.name
        )
        boardRepository.save(boardEntity)
    }
}

/*
에티티로 처음부터 받지 않고 Dto로 받는 이유
- 엔티티는 영속성 컨텍스트 개념 존재
- 엔티티 값을 변경하거나 수정시 데이터를 직접 건들 수 있어서 오류 유발 방지
 */
package com.board.index

import org.springframework.data.repository.findByIdOrNull
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

    // 전체 조회
    fun getAll(): List<BoardDto> {
        val boardEntities = boardRepository.findAll()
        val boardDtoList = boardEntities.map { board -> // 이부분 없애고 it으로 접근가능
            BoardDto(
                id = board.id,
                title = board.title,
                content = board.content,
                name = board.name
            )
        }
        return boardDtoList
    }

    // 단건 조회
    fun getById(id: Long): BoardDto {
        val boardEntity = boardRepository.findById(id).get()
        val boardDto = BoardDto(
            id = boardEntity.id,
            title = boardEntity.title,
            content = boardEntity.content,
            name = boardEntity.name
        )
        return boardDto
    }

    fun getByIdOrNull(id: Long): BoardDto? {
        val boardEntity = boardRepository.findByIdOrNull(id) ?: return null // 존재하지 않으면 null 반환
        val boardDto = BoardDto(
            id = boardEntity.id,
            title = boardEntity.title,
            content = boardEntity.content,
            name = boardEntity.name
        )
        return boardDto
    }

    fun update(boardDto: BoardDto) {
        val id = boardDto.id!! // 절대 null이 아니다
        val boardEntity = boardRepository.findById(id).get()
        boardEntity.update(boardDto)

        // save는 jpa에서 영속성 컨텍스트 상으로 관리를 하므로 update시점에 자동으로 save되지만 명시적으로 호출
        boardRepository.save(boardEntity)
    }
}

/*
엔티티로 처음부터 받지 않고 Dto로 받는 이유
- 엔티티는 영속성 컨텍스트 개념 존재
- 엔티티 값을 변경하거나 수정시 데이터를 직접 건들 수 있어서 오류 유발 방지
- 엔티티를 직접 컨트롤러에 넘겨서 프론트와 상호작용하면 안된다 -> 레포지토리에서 조회된 데이터는 엔티티로 반환되는데 이를 DTO로 바꾸어 줘야 한다
 */
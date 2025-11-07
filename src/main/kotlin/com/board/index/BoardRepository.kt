package com.board.index

import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<BoardEntity, Long> {

}
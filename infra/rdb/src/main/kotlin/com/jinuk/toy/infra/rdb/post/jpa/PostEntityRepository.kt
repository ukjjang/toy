package com.jinuk.toy.infra.rdb.post.jpa

import com.jinuk.toy.infra.rdb.post.entity.PostEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostEntityRepository : JpaRepository<PostEntity, Long> {
    fun existsByTitle(title: String): Boolean

    fun findByTitleStartsWithIgnoreCaseOrderByIdDesc(
        title: String,
        pageable: Pageable,
    ): Page<PostEntity>

    fun findByOrderByIdDesc(pageable: Pageable): Page<PostEntity>
}

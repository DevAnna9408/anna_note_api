package kr.co.anna.domain.repository.dream

import kr.co.anna.domain.model.dream.Dream
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface DreamRepositoryCustom {
    fun getPagedDream(userOid: Long, pageable: Pageable) : Page<Dream>
}

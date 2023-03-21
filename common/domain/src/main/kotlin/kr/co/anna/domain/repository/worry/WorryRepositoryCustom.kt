package kr.co.anna.domain.repository.worry

import kr.co.anna.domain.model.worry.Worry
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface WorryRepositoryCustom {
    fun getPagedWorry(userOid: Long, pageable: Pageable): Page<Worry>
}

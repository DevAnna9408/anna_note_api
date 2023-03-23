package kr.co.anna.api.service.query.dream

import kr.co.anna.api.dto.dream.DreamOut
import kr.co.anna.domain.repository.dream.DreamRepository
import kr.co.anna.lib.security.SecurityUtil
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DreamQueryService (

    private val dreamRepository: DreamRepository

        ) {

    fun getPagedDream(userOid: Long, pageable: Pageable): Page<DreamOut> {
        SecurityUtil.checkUserOid(userOid)
        return dreamRepository.getPagedDream(userOid, pageable).map { DreamOut.fromEntity(it) }
    }

}

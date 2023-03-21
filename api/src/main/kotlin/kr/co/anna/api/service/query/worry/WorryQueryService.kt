package kr.co.anna.api.service.query.worry

import kr.co.anna.api.dto.worry.WorryOut
import kr.co.anna.domain.repository.worry.WorryRepository
import kr.co.anna.lib.security.SecurityUtil
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true)
class WorryQueryService (

    private val worryRepository: WorryRepository

        ) {

    fun getPagedWorry(userOid: Long, pageable: Pageable): Page<WorryOut> {
        SecurityUtil.checkUserOid(userOid)
        return worryRepository.getPagedWorry(userOid, pageable).map { WorryOut.fromEntity(it) }
    }
}

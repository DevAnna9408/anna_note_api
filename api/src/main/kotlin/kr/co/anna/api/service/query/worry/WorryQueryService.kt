package kr.co.anna.api.service.query.worry

import kr.co.anna.api.dto.worry.WorryOut
import kr.co.anna.api.dto.worry.WorryTotalOut
import kr.co.anna.domain.model.worry.Worry
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

    fun getTotalWorries(userOid: Long): WorryTotalOut {
        SecurityUtil.checkUserOid(userOid)
        val totalWorries = worryRepository.getTotalWorries(userOid)
        return WorryTotalOut(
            totalWorry = totalWorries.size,
            noneCount = totalWorries.filter { it.worryTag() == Worry.WorryTag.NONE }.size,
            notOccurrenceCount = totalWorries.filter { it.worryTag() == Worry.WorryTag.NOT_OCCURRENCE }.size,
            occurrenceCount = totalWorries.filter { it.worryTag() == Worry.WorryTag.OCCURRENCE }.size,
            tinyCount = totalWorries.filter { it.worryTag() == Worry.WorryTag.TINY }.size,
            cannotResolveCount = totalWorries.filter { it.worryTag() == Worry.WorryTag.CANNOT_RESOLVE }.size,
            doneCount = totalWorries.filter { it.worryTag() == Worry.WorryTag.DONE }.size
        )
    }
}

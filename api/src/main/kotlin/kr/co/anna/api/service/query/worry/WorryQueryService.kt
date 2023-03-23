package kr.co.anna.api.service.query.worry

import kr.co.anna.api.dto.base.WorryTagOut
import kr.co.anna.api.dto.worry.WorryOut
import kr.co.anna.api.dto.worry.WorryTagAndTotalOut
import kr.co.anna.api.dto.worry.WorryTagEditIn
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

        val noneCount = totalWorries.filter { it.worryTag() == Worry.WorryTag.NONE }.size
        val notOccurrenceCount = totalWorries.filter { it.worryTag() == Worry.WorryTag.NOT_OCCURRENCE }.size
        val occurrenceCount = totalWorries.filter { it.worryTag() == Worry.WorryTag.OCCURRENCE }.size
        val tinyCount = totalWorries.filter { it.worryTag() == Worry.WorryTag.TINY }.size
        val cannotResolveCount = totalWorries.filter { it.worryTag() == Worry.WorryTag.CANNOT_RESOLVE }.size
        val doneCount = totalWorries.filter { it.worryTag() == Worry.WorryTag.DONE }.size

        val list = listOf(
            WorryTagAndTotalOut(
                worryTag = WorryTagOut(code = Worry.WorryTag.NONE.name, value = Worry.WorryTag.NONE.value),
                totalCount = noneCount
            ),
            WorryTagAndTotalOut(
                worryTag = WorryTagOut(code = Worry.WorryTag.NOT_OCCURRENCE.name, value = Worry.WorryTag.NOT_OCCURRENCE.value),
                totalCount = notOccurrenceCount
            ),
            WorryTagAndTotalOut(
                worryTag = WorryTagOut(code = Worry.WorryTag.OCCURRENCE.name, value = Worry.WorryTag.OCCURRENCE.value),
                totalCount = occurrenceCount
            ),
            WorryTagAndTotalOut(
                worryTag = WorryTagOut(code = Worry.WorryTag.TINY.name, value = Worry.WorryTag.TINY.value),
                totalCount = tinyCount
            ),
            WorryTagAndTotalOut(
                worryTag = WorryTagOut(code = Worry.WorryTag.CANNOT_RESOLVE.name, value = Worry.WorryTag.CANNOT_RESOLVE.value),
                totalCount = cannotResolveCount
            ),
            WorryTagAndTotalOut(
                worryTag = WorryTagOut(code = Worry.WorryTag.DONE.name, value = Worry.WorryTag.DONE.value),
                totalCount = doneCount
            )
        )

        return WorryTotalOut(
            totalWorry = totalWorries.size,
            noneCount = noneCount,
            notOccurrenceCount = notOccurrenceCount,
            occurrenceCount = occurrenceCount,
            tinyCount = tinyCount,
            cannotResolveCount = cannotResolveCount,
            doneCount = doneCount,
            maxWorry = list.maxByOrNull { it.totalCount }!!
        )
    }
}

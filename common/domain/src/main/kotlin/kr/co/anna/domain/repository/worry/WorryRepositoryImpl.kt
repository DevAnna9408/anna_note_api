package kr.co.anna.domain.repository.worry

import kr.co.anna.domain.model.worry.QWorry
import kr.co.anna.domain.model.worry.Worry
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class WorryRepositoryImpl: QuerydslRepositorySupport(Worry::class.java), WorryRepositoryCustom {

    private val qWorry = QWorry.worry
    override fun getPagedWorry(userOid: Long, pageable: Pageable): Page<Worry> {
        val results = from(qWorry)
            .where(
                qWorry.postUser.oid.eq(userOid)
            )
            .limit(pageable.pageSize.toLong()).offset(pageable.offset)
            .orderBy(qWorry.createdDate.asc())
            .fetchResults()

        return PageImpl(results.results, pageable, results.total)
    }

}

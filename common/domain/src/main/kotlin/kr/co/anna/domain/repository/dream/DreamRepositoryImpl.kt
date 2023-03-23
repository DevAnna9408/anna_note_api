package kr.co.anna.domain.repository.dream

import kr.co.anna.domain.model.dream.Dream
import kr.co.anna.domain.model.dream.QDream
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class DreamRepositoryImpl: QuerydslRepositorySupport(Dream::class.java), DreamRepositoryCustom {

    private val qDream = QDream.dream
    override fun getPagedDream(userOid: Long, pageable: Pageable): Page<Dream> {
        val results = from(qDream)
            .where(
                qDream.postUser.oid.eq(userOid)
            )
            .limit(pageable.pageSize.toLong()).offset(pageable.offset)
            .orderBy(qDream.createdDate.desc())
            .fetchResults()

        return PageImpl(results.results, pageable, results.total)
    }

}

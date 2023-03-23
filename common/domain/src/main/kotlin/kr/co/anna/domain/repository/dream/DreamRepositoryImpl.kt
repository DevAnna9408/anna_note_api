package kr.co.anna.domain.repository.dream

import kr.co.anna.domain.model.dream.Dream
import kr.co.anna.domain.model.dream.QDream
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class DreamRepositoryImpl: QuerydslRepositorySupport(Dream::class.java), DreamRepositoryCustom {

    private val qDream = QDream.dream

}

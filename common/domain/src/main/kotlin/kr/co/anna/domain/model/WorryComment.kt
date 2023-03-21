package kr.co.anna.domain.model

import kr.co.anna.domain._common.AbstractEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table(name = "A_WORRY_COMMENT")
class WorryComment (

    oid: Long? = null,

    @Column(name = "COMMENT")
    private val comment: String

        ) : AbstractEntity(oid) {

    fun comment() = comment
}

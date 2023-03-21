package kr.co.anna.domain.model.dream

import kr.co.anna.domain._common.AbstractEntity
import kr.co.anna.domain.model.user.User
import kr.co.anna.domain.model.worry.Worry
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "A_DREAM")

class Dream (

    oid: Long? = null,

    @Column(name = "CREATED_DATE")
    private val createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "CONTENT")
    private val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_USER")
    private val postUser: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORRY")
    private val worry: Worry

    ) : AbstractEntity(oid) {

    fun createdDate() = createdDate
    fun content() = content
    fun postUser() = postUser
    fun worry() = worry
}

package kr.co.anna.domain.model.worry

import kr.co.anna.domain._common.AbstractEntity
import kr.co.anna.domain.model.user.User
import java.security.PrivateKey
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "A_WORRY")
class Worry (

    oid: Long? = null,

    @Column(name = "CREATED_DATE")
    private val createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "CONTENT")
    private val content: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "WORRY_TAG")
    private val worryTag: WorryTag = WorryTag.NONE,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_USER")
    private val postUser: User

    ): AbstractEntity(oid) {

    enum class WorryTag(val value: String) {
        NONE("#해결되지 않은 걱정"),
        NOT_OCCURRENCE("#현실로 일어나지 않은 걱정"),
        OCCURRENCE("#이미 일어난 일에 대한 걱정"),
        TINY("#너무 사소한 걱정"),
        CANNOT_RESOLVE("#내 힘으로는 해결할 수 없는 걱정"),
        DONE("#해결된 걱정")
    }

    fun createdDate() = createdDate
    fun content() = content
    fun worryTag() = worryTag
    fun postUser() = postUser
}

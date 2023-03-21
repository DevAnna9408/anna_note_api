package kr.co.anna.domain.model.base

import javax.persistence.*

@Entity
@Table(
    name = "A_PUSH_ALARM"
)
class PushAlarm (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OID")
    private val oid: Long? = null,

    @Column(name = "TITLE")
    private val title: String,

    @Column(name = "MESSAGE")
    private val message: String

        ) {

        fun title() = title
        fun message() = message
}

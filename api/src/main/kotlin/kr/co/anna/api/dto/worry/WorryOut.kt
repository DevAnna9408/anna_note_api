package kr.co.anna.api.dto.worry

import com.google.common.math.LongMath
import kr.co.anna.api.dto.base.WorryTagOut
import kr.co.anna.domain.model.user.User
import kr.co.anna.domain.model.worry.Worry
import java.time.LocalDateTime

data class WorryOut(
    val worryOid: Long?,
    val content: String,
    val worryTag: WorryTagOut,
    val createdDate: LocalDateTime,
    val userOid: Long?

) {
    companion object {
        fun fromEntity(e: Worry): WorryOut {
            return WorryOut(
                worryOid = e.oid,
                content = e.content(),
                worryTag = WorryTagOut(
                    code = e.worryTag().name,
                    value = e.worryTag().value
                ),
                userOid = e.postUser().oid,
                createdDate = e.createdDate()
            )
        }
    }
}

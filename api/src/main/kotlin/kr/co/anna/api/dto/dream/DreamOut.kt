package kr.co.anna.api.dto.dream

import kr.co.anna.api.dto.base.WorryTagOut
import kr.co.anna.api.dto.worry.WorryOut
import kr.co.anna.domain.model.dream.Dream
import kr.co.anna.domain.model.user.User
import kr.co.anna.domain.model.worry.Worry
import java.time.LocalDateTime

data class DreamOut(

    val dreamOid: Long?,
    val createdDate: LocalDateTime,
    val content: String,
    val postUserOid: Long?,
    val worry: WorryOut

) {

   companion object {
       fun fromEntity(e: Dream): DreamOut {
           return DreamOut(
               dreamOid = e.oid,
               createdDate = e.createdDate(),
               content = e.content(),
               postUserOid = e.postUser().oid,
               worry = WorryOut(
                   worryOid = e.worry().oid,
                   content = e.worry().content(),
                   worryTag = WorryTagOut(
                       code = e.worry().worryTag().name,
                       value = e.worry().worryTag().value
                   ),
                   createdDate = e.worry().createdDate(),
                   userOid = e.worry().postUser().oid
               )
           )
       }

   }

}

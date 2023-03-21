package kr.co.anna.api.dto.worry

import kr.co.anna.domain.model.user.User
import kr.co.anna.domain.model.worry.Worry

data class WorryEditIn(

    val worryOid: Long,
    val content: String

)

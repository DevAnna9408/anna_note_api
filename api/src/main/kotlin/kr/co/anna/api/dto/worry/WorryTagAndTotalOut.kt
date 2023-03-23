package kr.co.anna.api.dto.worry

import com.google.common.math.LongMath
import kr.co.anna.api.dto.base.WorryTagOut
import kr.co.anna.domain.model.user.User
import kr.co.anna.domain.model.worry.Worry
import java.time.LocalDateTime

data class WorryTagAndTotalOut(
    val worryTag: WorryTagOut,
    val totalCount: Int
)

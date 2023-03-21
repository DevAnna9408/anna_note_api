package kr.co.anna.api.dto.worry

import kr.co.anna.domain.model.user.User
import kr.co.anna.domain.model.worry.Worry

data class WorryIn(

    val content: String

) {
    fun toEntity(user: User): Worry {
        return Worry(
            content = content,
            postUser = user
        )
    }
}

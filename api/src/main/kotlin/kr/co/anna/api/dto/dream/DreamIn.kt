package kr.co.anna.api.dto.dream

import kr.co.anna.domain.model.dream.Dream
import kr.co.anna.domain.model.user.User
import kr.co.anna.domain.model.worry.Worry

data class DreamIn(

    val content: String

) {

    fun toEntity(u: User, w: Worry): Dream {
        return Dream(
            content = content,
            postUser = u,
            worry = w
        )

    }

}

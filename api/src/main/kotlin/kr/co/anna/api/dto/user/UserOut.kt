package kr.co.anna.api.dto.user

import kr.co.anna.domain.model.user.User

data class UserOut(
    val oid: Long,
    val userId: String,
    val email: String,
) {
    companion object {
        fun fromEntity(e: User): UserOut {
            return UserOut(
                oid = e.oid!!,
                userId = e.userId,
                email = e.email(),
            )
        }
    }
}

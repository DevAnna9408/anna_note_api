package kr.co.anna.api.dto.user

import kr.co.anna.domain._common.EnumValue
import kr.co.anna.lib.security.SignInUser

data class SignInOut(
    val userId: String,
    val accessToken: String,
    val roles: List<EnumValue>,
    val customInfo: CustomInfo,
) {
    data class CustomInfo(
        val userOid: Long?,
        val userId: String
        )

    companion object {
        fun from(signInUser: SignInUser, accessToken: String): SignInOut {
            return SignInOut(
                userId = signInUser.username,
                accessToken = accessToken,
                roles = signInUser.roles().map { EnumValue(it) },
                customInfo = CustomInfo(
                    userOid = signInUser.userOid(),
                    userId = signInUser.userId()
                )
            )
        }
    }
}

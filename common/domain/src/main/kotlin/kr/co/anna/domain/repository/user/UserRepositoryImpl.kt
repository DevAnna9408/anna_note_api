package kr.co.anna.domain.repository.user

import com.querydsl.core.types.dsl.BooleanExpression
import kr.co.anna.domain._common.DomainMessageUtil
import kr.co.anna.domain._common.containsName
import kr.co.anna.domain.exception.DomainEntityNotFoundException
import kr.co.anna.domain.model.user.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.data.support.PageableExecutionUtils
import java.util.*

class UserRepositoryImpl : QuerydslRepositorySupport(User::class.java), UserRepositoryCustom {

    private val qUser = kr.co.anna.domain.model.user.QUser.user

    override fun getByOid(oid: Long): User {
        return from(qUser)
            .where(
                isActive(),
                qUser.oid.eq(oid)
            )
            .fetchOne() ?: throw DomainEntityNotFoundException(oid, User::class, DomainMessageUtil.getMessage("USER_NOT_FOUND"))
    }

    override fun getByUserId(userId: String): User {
        return from(qUser)
            .where(
                isActive(),
                qUser.userId.eq(userId)
            )
            .fetchOne() ?: throw DomainEntityNotFoundException(userId, User::class, DomainMessageUtil.getMessage("USER_NOT_FOUND"))
    }

    override fun getByEmail(email: String): User {
        return from(qUser)
            .where(
                isActive(),
                qUser.email.eq(email)
            )
            .fetchOne() ?: throw DomainEntityNotFoundException(email, User::class, DomainMessageUtil.getMessage("USER_NOT_FOUND"))
    }

    override fun getByUserIdAndEmail(userId: String, email: String): User {
        return from(qUser)
                .where(
                    isActive(),
                        qUser.userId.eq(userId),
                        qUser.email.eq(email)
                )
                .fetchOne() ?: throw RuntimeException(DomainMessageUtil.getMessage("USER_NOT_FOUND") +
                DomainMessageUtil.getMessage("CHECK_ID_EMAIL"))
    }

    private fun isActive(): BooleanExpression? {
        return  qUser.status.eq(User.Status.ACTIVE)
    }

}

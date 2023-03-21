package kr.co.anna.domain.model.user

import kr.co.anna.domain._common.AbstractEntity
import kr.co.anna.domain._common.EnumModel
import kr.co.anna.domain.converter.RoleEnumToListConvert
import kr.co.anna.domain.model.Role
import javax.persistence.*

/**
 * 회원
 */
@Entity
@Table(
    name = "A_USER",
    indexes = [
        Index(name = "IDX_USER__USER_ID", columnList = "USER_ID", unique = true),
        Index(name = "IDX_USER__EMAIL", columnList = "EMAIL", unique = true)
    ]
)
class User(
    oid: Long? = null, //pk

    @Column(name = "USER_ID")
    val userId: String, //수정불가

    @Column(name = "EMAIL")
    private var email: String,

    @Column(name = "PASSWORD")
    private var password: String,

    @Column(name = "ROLES")
    @Convert(converter = RoleEnumToListConvert::class)
    private var roles: List<Role>,

    @Column(name = "QUESTION")
    private val question: String,

    @Column(name = "ANSWER")
    private val answer: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private var status: Status = Status.ACTIVE, // 사용자 상태 코드

    @Column(name = "FAIL_CNT")
    var failCnt: Int = 0,// 로그인 실패횟수

    @Column(name = "LOCK_YN")
    private var locked: Boolean = false//계정잠김

) : AbstractEntity(oid) {

    enum class Status(private val korName: String, private val engName: String) : EnumModel {

        ACTIVE("활성", "ACTIVE"),
        WITHDRAW("탈퇴", "WITHDRAW"),
        INACTIVE("비활성", "INACTIVE");

        override fun getKorName(): String {
            return korName
        }

        override fun getEngName(): String {
            return engName
        }

        override fun getCode(): String {
            return name
        }
    }

    fun changePassword(password: String) {
        this.password = password
    }

    data class NewValue(
        val email: String? = null,
        val password: String? = null,
        val roles: MutableList<Role> = mutableListOf(Role.ROLE_USER),
        var status: Status = Status.ACTIVE,
        val failCnt: Int = 0,
        val locked: Boolean = false
    )

    fun email() = email
    fun password() = password
    fun status() = status
    fun failCnt() = failCnt
    fun locked() = locked
    fun role() = roles
    fun question() = question
    fun answer() = answer
    fun checkActiveUser(): Boolean {
        return status().equals(Status.ACTIVE)
    }

    fun checkLock (failMaxCnt: Int)  {
        this.failCnt += 1
        if (this.failCnt > failMaxCnt) {
            this.locked = true
        }
    }

    fun deleteUser() {
        this.status = Status.WITHDRAW
    }

    fun reset() {
        this.status = Status.ACTIVE
        this.failCnt = 0
        this.locked = false
    }
}

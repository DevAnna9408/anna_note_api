package kr.co.anna.api.service.command.worry

import kr.co.anna.api.dto.base.WorryTagOut
import kr.co.anna.api.dto.worry.WorryIn
import kr.co.anna.api.dto.worry.WorryOut
import kr.co.anna.api.dto.worry.WorryTagEditIn
import kr.co.anna.domain.repository.user.UserRepository
import kr.co.anna.domain.repository.worry.WorryRepository
import kr.co.anna.lib.security.SecurityUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class WorryCommandService (

    private val worryRepository: WorryRepository,
    private val userRepository: UserRepository

        ) {

    fun createWorry(userOid: Long, worryIn: WorryIn): WorryOut {
        SecurityUtil.checkUserOid(userOid)
        val user = getUser(userOid)
        val worry = worryRepository.save(worryIn.toEntity(user))
        return WorryOut.fromEntity(worry)
    }

    fun deleteWorry(userOid: Long, worryOid: Long) {
        SecurityUtil.checkUserOid(userOid)
        val worry = getWorry(worryOid)
        worryRepository.delete(worry)
    }

    fun changeWorryTag(userOid: Long, worryTagEditIn: WorryTagEditIn): WorryTagOut {
        SecurityUtil.checkUserOid(userOid)
        val worry = worryRepository.getById(worryTagEditIn.worryOid)
        worry.changeWorryTag(worryTagEditIn.worryTag)
        return WorryTagOut(
            code = worryTagEditIn.worryTag.name,
            value = worryTagEditIn.worryTag.value
        )
    }

    private fun getUser(userOid: Long) = userRepository.getByOid(userOid)
    private fun getWorry(worryOid: Long) = worryRepository.getById(worryOid)

}

package kr.co.anna.api.service.command.dream

import kr.co.anna.api.dto.dream.DreamIn
import kr.co.anna.domain.repository.dream.DreamRepository
import kr.co.anna.domain.repository.user.UserRepository
import kr.co.anna.domain.repository.worry.WorryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DreamCommandService (

    private val userRepository: UserRepository,
    private val worryRepository: WorryRepository,
    private val dreamRepository: DreamRepository

        ) {

    fun createDream(userOid: Long, worryOid: Long, dreamIn: DreamIn) {
        val user = userRepository.getByOid(userOid)
        val worry = worryRepository.getById(worryOid)
        dreamRepository.save(dreamIn.toEntity(user, worry))
    }

}

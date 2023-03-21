package kr.co.anna.api.service.query.base

import kr.co.anna.api.dto.base.PushAlarmOut
import kr.co.anna.domain.repository.base.PushAlarmRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PushAlarmQueryService (

    private val pushAlarmRepository: PushAlarmRepository

        ) {

    fun getPushAlarm(): PushAlarmOut {
        val alarm = pushAlarmRepository.findAll().random()
        return PushAlarmOut(
            title = alarm.title(),
            message = alarm.message()
        )

    }
}

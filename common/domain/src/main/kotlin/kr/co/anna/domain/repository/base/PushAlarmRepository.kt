package kr.co.anna.domain.repository.base

import kr.co.anna.domain.model.base.PushAlarm
import org.springframework.data.jpa.repository.JpaRepository

interface PushAlarmRepository: JpaRepository<PushAlarm, Long> {
}

package kr.co.anna.domain.repository.worry

import kr.co.anna.domain.model.worry.Worry
import org.springframework.data.jpa.repository.JpaRepository

interface WorryRepository: JpaRepository<Worry, Long>, WorryRepositoryCustom

package kr.co.anna.domain.repository.dream

import kr.co.anna.domain.model.dream.Dream
import org.springframework.data.jpa.repository.JpaRepository

interface DreamRepository: JpaRepository<Dream, Long>, DreamRepositoryCustom

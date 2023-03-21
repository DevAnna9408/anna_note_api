package kr.co.anna.api.controller.worry

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.anna.api.dto.worry.WorryEditIn
import kr.co.anna.api.dto.worry.WorryIn
import kr.co.anna.api.dto.worry.WorryOut
import kr.co.anna.api.service.command.worry.WorryCommandService
import kr.co.anna.api.service.query.worry.WorryQueryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "걱정 API")
@RestController
@RequestMapping("/api/worry")
class WorryController (

    private val worryQueryService: WorryQueryService,
    private val worryCommandService: WorryCommandService

        ) {

    @Operation(summary = "걱정 조회")
    @GetMapping("/paged")
    fun getPagedWorry(
        @RequestParam("userOid") userOid: Long,
        @PageableDefault pageable: Pageable
    ): ResponseEntity<Page<WorryOut>> {
       return ResponseEntity.ok(worryQueryService.getPagedWorry(userOid, pageable))
    }

    @Operation(summary = "걱정 생성")
    @PostMapping
    fun createWorry(
        @RequestParam("userOid") userOid: Long,
        @RequestBody worryIn: WorryIn
    ): ResponseEntity<WorryOut> {
        return ResponseEntity.ok(worryCommandService.createWorry(userOid, worryIn))
    }

    @Operation(summary = "걱정 수정")
    @PutMapping
    fun editWorry(
        @RequestParam("userOid") userOid: Long,
        @RequestBody worryEditIn: WorryEditIn
    ): ResponseEntity<Nothing> {
        worryCommandService.editWorry(userOid, worryEditIn)
        return ResponseEntity.noContent().build()
    }

    @Operation(summary = "걱정 삭제")
    @DeleteMapping
    fun deleteWorry(
        @RequestParam("userOid") userOid: Long,
        @RequestParam("worryOid") worryOid: Long
    ): ResponseEntity<Nothing> {
        worryCommandService.deleteWorry(userOid, worryOid)
     return ResponseEntity.noContent().build()
    }
}

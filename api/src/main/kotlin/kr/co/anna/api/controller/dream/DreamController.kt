package kr.co.anna.api.controller.dream

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.anna.api.dto.dream.DreamIn
import kr.co.anna.api.dto.dream.DreamOut
import kr.co.anna.api.service.command.dream.DreamCommandService
import kr.co.anna.api.service.query.dream.DreamQueryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "다짐 API")
@RestController
@RequestMapping("/api/dream")
class DreamController (

    private val dreamQueryService: DreamQueryService,
    private val dreamCommandService: DreamCommandService

        ) {

    @Operation(summary = "다짐 작성")
    @PostMapping
    fun createDream(
        @RequestParam("userOid") userOid: Long,
        @RequestParam("worryOid") worryOid: Long,
        @RequestBody dreamIn: DreamIn
    ): ResponseEntity<Nothing> {
        dreamCommandService.createDream(userOid, worryOid, dreamIn)
        return ResponseEntity.noContent().build()
    }

    @Operation(summary = "다짐 조회")
    @GetMapping("/paged")
    fun getPagedDream(
        @RequestParam("userOid") userOid: Long,
        @PageableDefault pageable: Pageable
    ): ResponseEntity<Page<DreamOut>> {
        return ResponseEntity.ok(dreamQueryService.getPagedDream(userOid, pageable))
    }

    @Operation(summary = "다짐 삭제")
    @DeleteMapping
    fun deleteDream(
        @RequestParam("userOid") userOid: Long,
        @RequestParam("dreamOid") dreamOid: Long
    ) : ResponseEntity<Nothing> {
        dreamCommandService.deleteDream(userOid, dreamOid)
        return ResponseEntity.noContent().build()
    }


}

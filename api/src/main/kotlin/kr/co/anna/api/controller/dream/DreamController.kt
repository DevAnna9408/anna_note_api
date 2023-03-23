package kr.co.anna.api.controller.dream

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.anna.api.dto.dream.DreamIn
import kr.co.anna.api.service.command.dream.DreamCommandService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "다짐 API")
@RestController
@RequestMapping("/api/dream")
class DreamController (

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

}

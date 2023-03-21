package kr.co.anna.api.controller.user

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.anna.api.service.command.user.UserCommandService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "회원 관리 API")
@RequestMapping("/api/users")
@RestController
class UserController(
    private val userCommandService: UserCommandService,
) {

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    fun deleteUser(
        @RequestParam("userOid") userOid: Long
    ): ResponseEntity<Nothing> {
        userCommandService.deleteUser(userOid)
        return ResponseEntity.noContent().build()
    }

}

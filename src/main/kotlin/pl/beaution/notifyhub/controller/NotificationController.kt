package pl.beaution.notifyhub.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.beaution.notifyhub.dto.NotificationRequestDto
import pl.beaution.notifyhub.dto.NotificationResponseDto
import pl.beaution.notifyhub.service.email.IEmailNotificationService

@RestController
@RequestMapping("/api/notifications")
class NotificationController(private val emailNotificationService: IEmailNotificationService) {
    @PostMapping("/email")
    fun sendEmailNotification(@RequestBody request: NotificationRequestDto): ResponseEntity<NotificationResponseDto> {
        val response = emailNotificationService.send(request)
        return ResponseEntity.ok(response)
    }
}
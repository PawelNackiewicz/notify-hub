package pl.beaution.notifyhub.service.email

import pl.beaution.notifyhub.dto.NotificationRequestDto
import pl.beaution.notifyhub.dto.NotificationResponseDto

interface IEmailNotificationService {
    fun send(request: NotificationRequestDto): NotificationResponseDto
}
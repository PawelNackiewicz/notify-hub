package pl.beaution.notifyhub.dto

import pl.beaution.notifyhub.domain.model.NotificationStatus
import pl.beaution.notifyhub.domain.model.NotificationType

data class NotificationResponseDto(
    val id: Long?,
    val recipient: String,
    val subject: String? = null,
    val message: String,
    val type: NotificationType,
    val status: NotificationStatus,
    val sentAt: String? = null
)
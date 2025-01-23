package pl.beaution.notifyhub.dto

import pl.beaution.notifyhub.domain.model.NotificationType

data class NotificationRequestDto(
    val recipient: String,
    val subject: String? = null,
    val message: String,
    val type: NotificationType,
)
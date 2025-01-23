package pl.beaution.notifyhub.domain.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "notifications")
data class Notification(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val recipient: String,
    val subject: String?,
    val message: String,
    val type: NotificationType,
    val status: NotificationStatus,
    val scheduledAt: LocalDateTime? = null,
    val sentAt: LocalDateTime? = null
)

enum class NotificationType {
    EMAIL, SMS
}

enum class NotificationStatus {
    PENDING, SENT, FAILED, RETRY
}
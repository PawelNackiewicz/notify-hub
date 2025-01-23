package pl.beaution.notifyhub.domain.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "notification_templates")
data class NotificationTemplate(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val type: NotificationType,
    val subjectTemplate: String?,
    val bodyTemplate: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    @PrePersist
    fun onCreate() {
        updatedAt = LocalDateTime.now()
    }

    @PreUpdate
    fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }
}
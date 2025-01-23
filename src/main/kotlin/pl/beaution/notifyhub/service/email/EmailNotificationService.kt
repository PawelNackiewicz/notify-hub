package pl.beaution.notifyhub.service.email

import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import pl.beaution.notifyhub.domain.model.Notification
import pl.beaution.notifyhub.domain.model.NotificationStatus
import pl.beaution.notifyhub.domain.model.NotificationType
import pl.beaution.notifyhub.domain.repository.NotificationRepository
import pl.beaution.notifyhub.dto.NotificationRequestDto
import pl.beaution.notifyhub.dto.NotificationResponseDto

@Service
class EmailNotificationService(private val notificationRepository: NotificationRepository, private val mailSender: JavaMailSender): IEmailNotificationService {
    override fun send(request: NotificationRequestDto): NotificationResponseDto {
        if (request.type != NotificationType.EMAIL) {
            throw IllegalArgumentException("Unsupported notification type: ${request.type}")
        }

        val notificationEntity = Notification(
            recipient = request.recipient,
            type = request.type,
            subject = request.subject,
            message = request.message,
            status = NotificationStatus.PENDING
        )
        val savedNotification = notificationRepository.save(notificationEntity)

        return try {
            val mimeMessage: MimeMessage = mailSender.createMimeMessage()
            val helper = MimeMessageHelper(mimeMessage, true)

            helper.setTo(request.recipient)
            helper.setSubject(request.subject ?: "Brak tematu")
            helper.setText(request.message, true)

            mailSender.send(mimeMessage)

            savedNotification.copy(status = NotificationStatus.SENT).let {
                notificationRepository.save(it)
            }


            NotificationResponseDto(
                id = savedNotification.id,
                recipient = request.recipient,
                type = request.type,
                status = NotificationStatus.SENT,
                subject = request.subject,
                message = request.message,
                sentAt = java.time.LocalDateTime.now().toString()
            )
        } catch (e: Exception) {
            savedNotification.copy(status = NotificationStatus.FAILED).let {
                notificationRepository.save(it)
            }

            NotificationResponseDto(
                id = savedNotification.id,
                recipient = request.recipient,
                type = request.type,
                status = NotificationStatus.FAILED,
                subject = request.subject,
                message = request.message
            )
        }
    }

}
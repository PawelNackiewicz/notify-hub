package pl.beaution.notifyhub.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.beaution.notifyhub.domain.model.Notification

@Repository
interface NotificationRepository: JpaRepository<Notification, Long>
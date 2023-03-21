package kr.co.anna.api.service.command.firbase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import kr.co.anna.api.dto.base.PushAlarmOut
import kr.co.anna.api.service.query.base.PushAlarmQueryService
import org.springframework.core.io.ClassPathResource
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.io.IOException
import javax.annotation.PostConstruct

@Service
class NotificationScheduler (

    private val pushAlarmQueryService: PushAlarmQueryService

        ){

    private var instance: FirebaseMessaging? = null

    @PostConstruct
    @Throws(IOException::class)
    fun firebaseSetting() {
        val googleCredentials =
            GoogleCredentials.fromStream(ClassPathResource("firebase/anna-note-firebase-adminsdk-h5xpd-43ac51255f.json").inputStream)
                .createScoped(listOf("https://www.googleapis.com/auth/firebase.messaging"))
        val secondaryAppConfig = FirebaseOptions.builder()
            .setCredentials(googleCredentials)
            .build()
        val app = FirebaseApp.initializeApp(secondaryAppConfig)
        instance = FirebaseMessaging.getInstance(app)
    }

    @Scheduled(cron = "0 00 18 * * ?")
    @Throws(FirebaseMessagingException::class)
    fun pushDinnerAlarm() {
        pushAlarm(pushAlarmQueryService.getPushAlarm())
    }

    @Throws(FirebaseMessagingException::class)
    fun pushAlarm(data: PushAlarmOut) {
        val message = getMessage(data)
        sendMessage(message)
    }

    fun getMessage(data: PushAlarmOut): Message {
        val notification = Notification.builder().setTitle(data.title).setBody(data.message).build()
        val builder = Message.builder()
        val topic = "anna_note"
        return builder.setTopic(topic).setNotification(notification).build()
    }

    @Throws(FirebaseMessagingException::class)
    fun sendMessage(message: Message?): String? {
        return instance!!.send(message)
    }

}

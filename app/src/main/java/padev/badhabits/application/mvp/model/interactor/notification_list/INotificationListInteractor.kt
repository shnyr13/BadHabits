package padev.badhabits.application.mvp.model.interactor.notification_list

import io.reactivex.Observable
import padev.badhabits.application.mvp.model.entity.NotificationEntity

interface INotificationListInteractor {

    fun getAllNotifications(): Observable<NotificationEntity>
}
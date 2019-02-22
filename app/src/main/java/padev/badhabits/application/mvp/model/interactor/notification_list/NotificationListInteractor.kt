package padev.badhabits.application.mvp.model.interactor.notification_list

import io.reactivex.Observable
import padev.badhabits.application.mvp.model.entity.NotificationEntity
import padev.badhabits.core.interactor.BaseInteractor

class NotificationListInteractor: BaseInteractor(), INotificationListInteractor {


    override fun getAllNotifications(): Observable<NotificationEntity> {

        return Observable.create {

            /*val notifications =  notificationCRUD.selectAllData()

            for (notification in notifications) {
                it.onNext(notification)
            }*/
        }
    }


}
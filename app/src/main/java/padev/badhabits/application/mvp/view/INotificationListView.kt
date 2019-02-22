package padev.badhabits.application.mvp.view

import com.arellomobile.mvp.MvpView
import padev.badhabits.application.mvp.model.entity.NotificationEntity

interface INotificationListView: MvpView {

    fun showNotification(notification: NotificationEntity)

    fun showLoading()

    fun hideLoading()
}
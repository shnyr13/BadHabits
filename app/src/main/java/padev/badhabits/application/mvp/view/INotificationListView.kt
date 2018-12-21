package padev.badhabits.application.mvp.view

import com.arellomobile.mvp.MvpView

interface INotificationListView: MvpView {

    fun showLoading()

    fun hideLoading()
}
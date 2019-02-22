package padev.badhabits.application.mvp.presenter.notification_list

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import padev.badhabits.application.mvp.model.interactor.notification_list.NotificationListInteractor
import padev.badhabits.application.mvp.view.INotificationListView

@InjectViewState
class NotificationListPresenter: MvpPresenter<INotificationListView>(), INotificationListPresenter {

    val interactor = NotificationListInteractor()

    override fun getAllNotifications() {

        viewState.showLoading()

        val dispose = interactor.getAllNotifications()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ( {
                    viewState.showNotification(it)
                    viewState.hideLoading()
                },
                        {

                        },
                        {

                        } )

        if (dispose.isDisposed) viewState.hideLoading()
    }

}
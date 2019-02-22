package padev.badhabits.application.ui.activity

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.widget.Toolbar
import com.arellomobile.mvp.presenter.InjectPresenter
import padev.badhabits.R
import padev.badhabits.application.mvp.model.entity.NotificationEntity
import padev.badhabits.application.mvp.presenter.notification_list.NotificationListPresenter
import padev.badhabits.application.mvp.view.INotificationListView
import padev.badhabits.core.view.BaseActivity


class NotificationListActivity: BaseActivity(), INotificationListView {

    @InjectPresenter
    lateinit var presenter: NotificationListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(padev.badhabits.R.layout.activity_notification_list)

        val toolbar = findViewById<Toolbar>(padev.badhabits.R.id.activity_notification_list_appbar_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = getString(R.string.notifications)

        /*val builder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Title")
                .setContentText("Notification text")

        val notification = builder.build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)*/
    }

    override fun showNotification(notification: NotificationEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | SettingsEntity | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | SettingsEntity | File Templates.
    }
}
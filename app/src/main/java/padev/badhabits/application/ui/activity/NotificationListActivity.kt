package padev.badhabits.application.ui.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.arellomobile.mvp.presenter.InjectPresenter
import padev.badhabits.R
import padev.badhabits.application.mvp.presenter.notification_list.NotificationListPresenter
import padev.badhabits.application.mvp.view.INotificationListView
import padev.badhabits.core.view.BaseActivity

class NotificationListActivity: BaseActivity(), INotificationListView {

    @InjectPresenter
    lateinit var presenter: NotificationListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_notification_list)

        val toolbar = findViewById<Toolbar>(R.id.activity_notification_list_appbar_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = getString(R.string.notifications)
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
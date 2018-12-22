package padev.badhabits.application.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import padev.badhabits.R
import padev.badhabits.application.mvp.model.entity.NotificationEntity
import padev.badhabits.core.view.BaseFragment

class NotificationFragment: BaseFragment()  {

    lateinit var mNotificationEntity: NotificationEntity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        super.onViewCreated(view, savedInstanceState)

        //TODO set notification text

        return view
    }
}
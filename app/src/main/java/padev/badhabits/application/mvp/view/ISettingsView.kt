package padev.badhabits.application.mvp.view

import com.arellomobile.mvp.MvpView

interface ISettingsView: MvpView {

    fun showLoading()

    fun hideLoading()
}
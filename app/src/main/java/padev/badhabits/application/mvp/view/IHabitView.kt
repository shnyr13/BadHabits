package padev.badhabits.application.mvp.view;

import com.arellomobile.mvp.MvpView;

interface IHabitView: MvpView {

    fun showLoading()

    fun hideLoading()
}

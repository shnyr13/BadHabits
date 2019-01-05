package padev.badhabits.application.mvp.view;

import android.content.Context
import com.arellomobile.mvp.MvpView
import padev.badhabits.application.mvp.model.entity.HabitEntity

interface IHomeView: MvpView {

    fun showHabit(habit: HabitEntity)

    fun showAddHabitDialog()

    fun showLoading()

    fun hideLoading()



    fun getContext(): Context
}

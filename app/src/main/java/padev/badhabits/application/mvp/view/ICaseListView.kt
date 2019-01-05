package padev.badhabits.application.mvp.view

import com.arellomobile.mvp.MvpView
import padev.badhabits.application.mvp.model.entity.HabitDetailsEntity

interface ICaseListView: MvpView {

    fun showCase(habitCase: HabitDetailsEntity)
}
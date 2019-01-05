package padev.badhabits.application.mvp.presenter.home;

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import padev.badhabits.application.mvp.model.entity.HabitEntity
import padev.badhabits.application.mvp.model.interactor.home.HomeInteractor
import padev.badhabits.application.mvp.view.IHomeView
import java.util.ArrayList

class HomePresenter(var viewState: IHomeView) : IHomePresenter {

    val interactor = HomeInteractor(this)

    val TAG = HomePresenter::class.java.simpleName

    override fun getAllHabits() {
        interactor.getAllHabits()
    }

    override fun showAllHabits(habits: ArrayList<HabitEntity>) {

        for (habit in habits) {

            viewState.showHabit(habit)
        }
    }

    override fun showHabit(habit: HabitEntity) {

        viewState.showHabit(habit)
    }

    override fun habitAddStart() {

        viewState.showAddHabitDialog()
    }

    override fun habitAddPositive(name: String, time: Boolean, money: Boolean, health: Boolean) {

        interactor.addHabit(name, time, money, health)
    }

    override fun habitAddNegative() {
        TODO("not implemented") //To change body of created functions use File | SettingsEntity | File Templates.
    }

    override fun getContext(): Context {
        return viewState.getContext()
    }

}

package padev.badhabits.application.mvp.presenter.habit;

import padev.badhabits.application.mvp.model.interactor.habit.HabitInteractor
import padev.badhabits.application.mvp.view.IHabitView

class HabitPresenter(val viewState: IHabitView): IHabitPresenter {

    val interactor = HabitInteractor(this)


    override fun getHabitDetails(id: Long) {


    }
}

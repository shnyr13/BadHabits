package padev.badhabits.application.mvp.model.interactor.habit

import padev.badhabits.application.mvp.presenter.habit.IHabitPresenter
import padev.badhabits.core.interactor.BaseInteractor

class HabitInteractor(val presenter: IHabitPresenter): BaseInteractor(), IHabitInteractor {



}
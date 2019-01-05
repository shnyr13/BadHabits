package padev.badhabits.application.mvp.model.interactor.home

import padev.badhabits.application.mvp.model.crud.HabitCRUD
import padev.badhabits.application.mvp.model.entity.HabitEntity
import padev.badhabits.application.mvp.presenter.home.IHomePresenter
import padev.badhabits.core.interactor.BaseInteractor

class HomeInteractor (val presenter: IHomePresenter): BaseInteractor(), IHomeInteractor {

    private val habitCRUD: HabitCRUD = HabitCRUD(presenter.getContext())

    override fun getAllHabits() {

        val habits =  habitCRUD.selectAllData()

        presenter.showAllHabits(habits)
    }

    override fun addHabit(name: String, time: Boolean, money: Boolean, health: Boolean) {

        val habit = HabitEntity((-1).toLong(), name, time, money, health)

        habitCRUD.insertData(habit)

        presenter.showHabit(habit)
    }
}
package padev.badhabits.application.mvp.model.interactor.home

import io.reactivex.Observable
import padev.badhabits.application.mvp.model.crud.HabitCRUD
import padev.badhabits.application.mvp.model.entity.HabitEntity
import padev.badhabits.application.mvp.presenter.home.IHomePresenter
import padev.badhabits.core.interactor.BaseInteractor

class HomeInteractor (val presenter: IHomePresenter): BaseInteractor(), IHomeInteractor {

    private val habitCRUD: HabitCRUD = HabitCRUD(presenter.getContext())

    override fun getAllHabits(): Observable<HabitEntity> {

        return Observable.create {

            val habits =  habitCRUD.selectAllData()

            for (habit in habits) {
                it.onNext(habit)
            }
        }
    }

    override fun addHabit(name: String, time: Boolean, money: Boolean, health: Boolean): Observable<HabitEntity> {

        return Observable.create {

            val habit = HabitEntity((-1).toLong(), name, time, money, health)

            habitCRUD.insertData(habit)

            it.onNext(habit)
        }


    }
}
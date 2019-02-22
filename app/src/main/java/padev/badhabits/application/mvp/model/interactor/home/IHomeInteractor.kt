package padev.badhabits.application.mvp.model.interactor.home

import io.reactivex.Observable
import padev.badhabits.application.mvp.model.entity.HabitEntity

interface IHomeInteractor {

    fun getAllHabits(): Observable<HabitEntity>

    fun addHabit(name: String, time: Boolean, money: Boolean, health: Boolean): Observable<HabitEntity>
}
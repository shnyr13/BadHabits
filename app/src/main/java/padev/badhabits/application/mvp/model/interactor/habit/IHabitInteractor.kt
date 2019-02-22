package padev.badhabits.application.mvp.model.interactor.habit

import io.reactivex.Observable
import padev.badhabits.application.mvp.model.entity.HabitDetailsEntity

interface IHabitInteractor {

    fun getHabitDetails(id: Long): Observable<HabitDetailsEntity>
}